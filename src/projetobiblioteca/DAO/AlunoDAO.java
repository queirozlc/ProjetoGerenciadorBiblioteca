package projetobiblioteca.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import projetobiblioteca.model.Aluno;

public class AlunoDAO implements IDAO {

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Object> selectAll() {
		// TODO Auto-generated method stub
		return null;
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

	public int atualizaMatricula() throws FileNotFoundException, IOException {
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

}
