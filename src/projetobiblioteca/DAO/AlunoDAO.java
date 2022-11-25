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

import projetobiblioteca.model.Aluno;

public class AlunoDAO implements IDAO<Aluno> {

	@Override
	public List<Aluno> selectAll() throws FileNotFoundException, IOException {
		File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\aluno.csv");
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		Aluno aluno;
		if (file.exists()) {
			
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String linha = reader.readLine();
				linha = reader.readLine();

				while (linha != null) {
					String[] linhaSplit = linha.split(";");
					int matricula = Integer.parseInt(linhaSplit[0]);
					String nome = linhaSplit[1];
					String endereco = linhaSplit[2];
					String dataIngresso = linhaSplit[3];
					String curso = linhaSplit[4];
					double multa = Double.valueOf(linhaSplit[5]);
					
					aluno = new Aluno(matricula, nome, endereco, dataIngresso, curso, multa);
					listaAlunos.add(aluno);
					linha = reader.readLine();
				}
			}
		}

		return listaAlunos;
	}

	@Override
	public void criaDatabase() throws IOException {
		// busca diretorio onde está o projeto
		File arquivo = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\aluno.csv");
		PrintWriter writer = null;

		if (!arquivo.exists()) {
			arquivo.createNewFile();
			try {
				FileWriter out = new FileWriter(arquivo, true);
				writer = new PrintWriter(out);
				writer.println("Matricula;Nome;Endereco;Data de Ingresso;Curso;Multa");

			} catch (IOException e) {
				System.out.println("Erro: " + e.getMessage());

			} finally {
				writer.close();
			}
		}

	}

	@Override
	public int atualizaId() throws FileNotFoundException, IOException {
		File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\aluno.csv");
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

			}

		}

		return ++id;
	}

	@Override
	public boolean insert(Aluno aluno) {
		File arquivo = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\aluno.csv");
		PrintWriter writer = null;

		if (arquivo.exists()) {
			try {
				FileWriter out = new FileWriter(arquivo, true);
				writer = new PrintWriter(out);

				writer.write(aluno.getMatricula() + ";" + aluno.getNome() + ";" + aluno.getEndereco() + ";"
						+ aluno.getDataFormatada() + ";" + aluno.getCurso() + ";" + aluno.getMulta() + "\n");
				writer.flush();
				return true;
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());

			} finally {
				writer.close();
			}

		}
		return false;
	}

	public Aluno buscaPorId(int matriculaUsuario) throws FileNotFoundException, IOException {
		File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\aluno.csv");
		Aluno aluno = null;

		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String linha = reader.readLine();
				linha = reader.readLine();

				while (linha != null) {
					String[] linhaSplit = linha.split(";");
					int matricula = Integer.parseInt(linhaSplit[0]);
					String nome = linhaSplit[1];
					String endereco = linhaSplit[2];
					String dataIngresso = linhaSplit[3];
					String curso = linhaSplit[4];
					double multa = Double.valueOf(linhaSplit[5]);

					if (matriculaUsuario == matricula) {
						aluno = new Aluno(matricula, nome, endereco, dataIngresso, curso, multa);
					}
					linha = reader.readLine();
				}
			}
		}

		return aluno;
	}

}
