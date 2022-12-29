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

import projetobiblioteca.model.Emprestimo;

public class EmprestimoDAO implements IDAO<Emprestimo> {

	@Override
	public List<Emprestimo> selectAll() {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\emprestimo.csv");
		List<Emprestimo> listaEmprestimos = new ArrayList<Emprestimo>();
		Emprestimo emprestimo;

		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String linha = reader.readLine();
				linha = reader.readLine();

				while (linha != null) {
					String[] linhaSplit = linha.split(";");
					int id = Integer.parseInt(linhaSplit[0]);
					String matriculaProfessor = linhaSplit[1];
					String matriculaAluno = linhaSplit[2];
					int matriculaFuncionario = Integer.parseInt(linhaSplit[3]);
					String idLivro = linhaSplit[4];
					String idPeriodico = linhaSplit[5];
					String dataEmprestimo = linhaSplit[6];
					String dataDevolucao = linhaSplit[7];

					emprestimo = new Emprestimo(id,
							!matriculaProfessor.equals("null") ? Integer.parseInt(matriculaProfessor) : null,
							!matriculaAluno.equals("null") ? Integer.parseInt(matriculaAluno) : null,
							matriculaFuncionario, !idLivro.equals("null") ? Integer.parseInt(idLivro) : null,
							!idPeriodico.equals("null") ? Integer.parseInt(idPeriodico) : null, dataEmprestimo,
							dataDevolucao);
					listaEmprestimos.add(emprestimo);
					linha = reader.readLine();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return listaEmprestimos;
	}

	@Override
	public void criaDatabase() throws IOException {
		// busca diretorio onde está o projeto
		File arquivo = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\emprestimo.csv");
		PrintWriter writer = null;

		if (!arquivo.exists()) {
			arquivo.createNewFile();
			try {
				FileWriter out = new FileWriter(arquivo, true);
				writer = new PrintWriter(out);
				writer.println(
						"Id;Matricula do Professor;Matricula do Aluno;Matricula do Funcionario;Id Livro;Id Periodico;Data de Emprestimo;Data de Devolucao;");
			} catch (IOException e) {
				System.out.println("Erro: " + e.getMessage());

			} finally {
				writer.close();
			}
		}
	}

	@Override
	public boolean insert(Emprestimo emprestimo) {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\emprestimo.csv");
		PrintWriter writer = null;

		if (file.exists()) {
			try {
				FileWriter out = new FileWriter(file, true);
				writer = new PrintWriter(out);

				writer.write(emprestimo.getId() + ";" + emprestimo.getMatriculaProfessor() + ";"
						+ emprestimo.getMatriculaAluno() + ";" + emprestimo.getMatriculaFuncionario() + ";"
						+ emprestimo.getIdLivro() + ";" + emprestimo.getIdPeriodico() + ";"
						+ emprestimo.getDataEmprestimoFormatada() + ";" + emprestimo.getDataDevolucaoFormatada()
						+ "\n");
				writer.flush();

				return true;
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				writer.close();
			}
		}
		return false;
	}

	@Override
	public int atualizaId() throws FileNotFoundException, IOException {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\emprestimo.csv");
		int id = 0;

		if (file.exists()) {

			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String linha = reader.readLine();
				linha = reader.readLine();

				while (linha != null) {
					String[] linhaSplit = linha.split(";");
					id = Integer.parseInt(linhaSplit[0]);
					linha = reader.readLine();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return ++id;
	}

	@Override
	public boolean existeRegistro() {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\emprestimo.csv");

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
		File file = new File(System.getProperty("user.home") + "\\Downloads\\RelatorioEmprestimo.csv");
		PrintWriter writer = null;
		List<Emprestimo> listaEmprestimos = this.selectAll();

		try {
			if (!file.exists()) {
				file.createNewFile();
				FileWriter out = new FileWriter(file, true);
				writer = new PrintWriter(out);
				writer.println(
						"Id;Matricula do Professor;Matricula do Aluno;Matricula do Funcionario;Id Livro;Id Periodico;Data de Emprestimo; Data de devolucao");

				for (Emprestimo emprestimo : listaEmprestimos) {

					writer.write(emprestimo.getId() + ";" + emprestimo.getMatriculaProfessor() + ";"
							+ emprestimo.getMatriculaAluno() + ";" + emprestimo.getMatriculaFuncionario() + ";"
							+ emprestimo.getIdLivro() + ";" + emprestimo.getIdPeriodico() + ";"
							+ emprestimo.getDataEmprestimoFormatada() + ";" + emprestimo.getDataDevolucaoFormatada()
							+ "\n");
				}
				writer.flush();

			} else {
				System.out.println("Este arquivo já existe na pasta 'Downloads'.");
			}

		} catch (IOException e) {
			System.out.println("Erro ao criar arquivo de relatorio: " + e.getMessage());

		} finally {
			writer.close();
		}
	}

	public Emprestimo buscaPorId(int id) {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\emprestimo.csv");
		Emprestimo emprestimo = null;

		if (file.exists()) {

			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String linha = reader.readLine();
				linha = reader.readLine();

				while (linha != null) {
					String[] linhaSplit = linha.split(";");
					int idBanco = Integer.parseInt(linhaSplit[0]);
					String matriculaProfessor = linhaSplit[1];
					String matriculaAluno = linhaSplit[2];
					int matriculaFuncionario = Integer.parseInt(linhaSplit[3]);
					String idLivro = linhaSplit[4];
					String idPeriodico = linhaSplit[5];
					String dataEmprestimo = linhaSplit[6];
					String dataDevolucao = linhaSplit[7];

					if (idBanco == id) {
						emprestimo = new Emprestimo(id,
								!matriculaProfessor.equals("null") ? Integer.parseInt(matriculaProfessor) : null,
								!matriculaAluno.equals("null") ? Integer.parseInt(matriculaAluno) : null,
								matriculaFuncionario, !idLivro.equals("null") ? Integer.parseInt(idLivro) : null,
								!idPeriodico.equals("null") ? Integer.parseInt(idPeriodico) : null, dataEmprestimo,
								dataDevolucao);
						return emprestimo;
					}

					linha = reader.readLine();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return emprestimo;
	}

}
