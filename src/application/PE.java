package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Products;

public class PE {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Products> lista = new ArrayList<>();

		System.out.println("Insira com o caminho do arquivo: ");
		String arquivoStr = sc.nextLine();

		File arquivo = new File(arquivoStr);
		String arquivoPasta = arquivo.getParent();

		System.out.println(arquivoPasta);

		boolean sucesso = new File(arquivoPasta + "/arqv").mkdir();

		String arqvStr = arquivoPasta + "\\arqv\\summary.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(arquivoStr))) {
			String itemCsv = br.readLine();
			while (itemCsv != null) {
				String[] campos = itemCsv.split(",");
				String nome = campos[0];
				double preco = Double.parseDouble(campos[1]);
				int quantidade = Integer.parseInt(campos[2]);

				lista.add(new Products(nome, preco, quantidade));
				itemCsv = br.readLine();
			}
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(arqvStr))) {
				for (Products item : lista) {
					bw.write(item.getNome() + "," + String.format("%.2f", item.valorTotal()));
					bw.newLine();
				}
				System.out.println(arqvStr +" criado");

			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}

}
