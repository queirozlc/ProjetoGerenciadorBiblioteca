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

import projetobiblioteca.model.Periodico;

public class PeriodicoDAO implements IDAO<Periodico> {

	@Override
	public boolean insert(Periodico periodico) {
		File arquivo = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\periodico.csv");
		PrintWriter writer = null;

		if (arquivo.exists()) {

			try {
				FileWriter out = new FileWriter(arquivo, true);
				writer = new PrintWriter(out);

				writer.write(periodico.getId() + ";"
						+ periodico.getAutores().toString().replace("[", "").replace("]", "").trim() + ";"
						+ periodico.getTitulo() + ";" + periodico.getTipo() + ";" + periodico.getFatorImpaco() + ";"
						+ periodico.getIssn() + "\n");
				writer.flush();
				return true;
			} catch (Exception e) {
				e.getMessage();

			} finally {
				writer.close();
			}

		}

		return false;
	}

	@Override
	public List<Periodico> selectAll() {
		File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\periodico.csv");
		List<Periodico> listaPeriodicos = new ArrayList<Periodico>();
		Periodico periodico;

		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String linha = reader.readLine();
				linha = reader.readLine();

				while (linha != null) {
					String[] linhaSplit = linha.split(";");
					int id = Integer.parseInt(linhaSplit[0]);
					String autores = linhaSplit[1];
					String titulo = linhaSplit[2];
					char tipo = linhaSplit[3].charAt(0);
					double fatorImpacto = Double.parseDouble(linhaSplit[4]);
					String issn = linhaSplit[5];

					periodico = new Periodico(id, autores, titulo, tipo, issn, fatorImpacto);
					listaPeriodicos.add(periodico);
					linha = reader.readLine();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return listaPeriodicos;
	}

	@Override
	public void criaDatabase() throws IOException {
		// busca diretorio onde está o projeto
		File arquivo = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\periodico.csv");
		PrintWriter writer = null;

		if (!arquivo.exists()) {
			arquivo.createNewFile();
			try {
				FileWriter out = new FileWriter(arquivo, true);
				writer = new PrintWriter(out);
				writer.println("Id;Autores;Titulo;Tipo;Fator de Impacto;Issn");

			} catch (IOException e) {
				System.out.println("Erro: " + e.getMessage());

			} finally {
				writer.close();
			}
		}
	}

	public int atualizaId() throws FileNotFoundException, IOException {
		File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\periodico.csv");
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
		File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\periodico.csv");

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
		File file = new File(System.getProperty("user.home") + "\\Downloads\\RelatorioPeriodico.csv");
		PrintWriter writer = null;
		List<Periodico> listaPeriodicos = this.selectAll();

		try {
			if (!file.exists()) {
				file.createNewFile();
				FileWriter out = new FileWriter(file, true);
				writer = new PrintWriter(out);
				writer.println("Autores;Titulo;Tipo;Fator de Impacto;ISSN");

				for (Periodico periodico : listaPeriodicos) {

					writer.write(periodico.getAutores().toString().replace("[", "").replace("]", "").trim() + ";"
							+ periodico.getTitulo() + ";" + periodico.getTipo() + ";"
							+ periodico.getFatorImpaco().toString().replace(".", ",") + ";"
							+ periodico.getIssn() + "\n");
				}

				writer.flush();
			
			}else {
				System.out.println("Esse arquivo já existe !");
			}
			
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			writer.close();
		}
	}

	public Periodico buscaPorId(int idItem) {
		File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\periodico.csv");
		Periodico periodico = null;

		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String linha = reader.readLine();
				linha = reader.readLine();

				while (linha != null) {
					String[] linhaSplit = linha.split(";");
					int id = Integer.parseInt(linhaSplit[0]);
					String autores = linhaSplit[1];
					String titulo = linhaSplit[2];
					char tipo = linhaSplit[3].charAt(0);
					double fatorImpacto = Double.parseDouble(linhaSplit[4]);
					String issn = linhaSplit[5];

					if (id == idItem) {
						periodico = new Periodico(id, autores, titulo, tipo, issn, fatorImpacto);
						return periodico;
					}

					linha = reader.readLine();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return periodico;
	}
}
