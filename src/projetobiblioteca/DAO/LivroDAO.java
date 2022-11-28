package projetobiblioteca.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import projetobiblioteca.model.Livro;

public class LivroDAO implements IDAO<Livro> {

	@Override
	public List<Livro> selectAll() {
		File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\livro.csv");
		List<Livro> listaLivros = new ArrayList<Livro>();
		Livro livro;

		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String linha = reader.readLine();
				linha = reader.readLine();

				while (linha != null) {
					String[] linhaSplit = linha.split(";");
					int id = Integer.parseInt(linhaSplit[0]);
					String autores = linhaSplit[1];
					String titulo = linhaSplit[2];
					String editora = linhaSplit[3];
					char tipo = linhaSplit[4].charAt(0);
					String anoPublicacao = linhaSplit[5];
					String issn = linhaSplit[6];

					livro = new Livro(id, autores, titulo, tipo, issn, editora, anoPublicacao);
					listaLivros.add(livro);
					linha = reader.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return listaLivros;
	}

	@Override
	public void criaDatabase() throws IOException {
		// busca diretorio onde está o projeto
		File arquivo = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\livro.csv");
		PrintWriter writer = null;

		if (!arquivo.exists()) {
			arquivo.createNewFile();
			try {
				FileWriter out = new FileWriter(arquivo, true);
				writer = new PrintWriter(out);
				writer.println("Id;Autores;Titulo;Editora;Tipo;Ano de Publicacao;Issn");

			} catch (IOException e) {
				System.out.println("Erro: " + e.getMessage());

			} finally {
				writer.close();
			}
		}
	}

	@Override
	public boolean insert(Livro livro) {
		File arquivo = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\livro.csv");
		PrintWriter writer = null;

		if (arquivo.exists()) {

			try {
				FileWriter out = new FileWriter(arquivo, true);
				writer = new PrintWriter(out);

				writer.write(
						livro.getId() + ";" + livro.getAutores().toString().replace("[", "").replace("]", "").trim()
								+ ";" + livro.getTitulo() + ";" + livro.getEditora() + ";" + livro.getTipo() + ";"
								+ livro.getAnoPublicacaoFormatado() + ";" + livro.getIssn() + "\n");
				writer.flush();
				return true;
			} catch (IOException e) {
				System.out.println("Erro: " + e.getMessage());

			} finally {
				writer.close();
			}

		}

		return false;
	}

	@Override
	public int atualizaId() throws FileNotFoundException, IOException {
		File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\livro.csv");
		int id = 0;
		// valida se arquivo existe antes de tentar ler
		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

				String linha = reader.readLine();
				linha = reader.readLine();

				while (linha != null) {

					String[] linhaSplit = linha.split(";");
					id = Integer.parseInt(linhaSplit[0]);
					linha = reader.readLine();
				}
			}
		}

		return ++id;
	}

	@Override
	public boolean existeRegistro() {
		File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\livro.csv");

		if (file.exists()) {

			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

				String linha = reader.readLine();
				linha = reader.readLine();

				if (linha != null) {
					return true;
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return false;
	}

	@Override
	public void geraRelatorio() {
		File file = new File(System.getProperty("user.home") + "\\Downloads\\RelatorioLivros.csv");
		PrintWriter writer = null;
		List<Livro> listaLivros = this.selectAll();
		try {
			if (!file.exists()) {
				file.createNewFile();
				FileWriter out = new FileWriter(file, true);
				writer = new PrintWriter(out);
				writer.println("Autores;Titulo;Editora;Tipo;Ano de Publicacao;ISSN");

				for (Livro livro : listaLivros) {

					writer.write(livro.getAutores().toString().replace("[", "").replace("]", "").trim() + ";"
							+ livro.getTitulo() + ";" + livro.getEditora() + ";" + livro.getTipo() + ";"
							+ livro.getAnoPublicacaoFormatado() + ";" + livro.getIssn() + "\n");
				}
				writer.flush();
			
			}else {
				System.out.println("Este arquivo já existe na pasta 'Downloads'.");
			}
			
		} catch (IOException e) {
			System.out.println("Erro ao criar arquivo de relatorio: " + e.getMessage());

		} finally {
			writer.close();
		}
	}

	public Livro buscaPorId(int idItem) {
		File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\livro.csv");
		Livro livro = null;

		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String linha = reader.readLine();
				linha = reader.readLine();

				while (linha != null) {
					String[] linhaSplit = linha.split(";");
					int id = Integer.parseInt(linhaSplit[0]);
					String autores = linhaSplit[1];
					String titulo = linhaSplit[2];
					String editora = linhaSplit[3];
					char tipo = linhaSplit[4].charAt(0);
					String anoPublicacao = linhaSplit[5];
					String issn = linhaSplit[6];
					
					if (id == idItem) {
						livro = new Livro(id, autores, titulo, tipo, issn, editora, anoPublicacao);
						return livro;
					}
					linha = reader.readLine();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return livro;
	}

}
