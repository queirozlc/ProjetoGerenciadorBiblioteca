package projetobiblioteca.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import projetobiblioteca.model.Emprestimo;

public class EmprestimoDAO implements IDAO<Emprestimo> {

	@Override
	public List<Emprestimo> selectAll() {
		// TODO Auto-generated method stub
		return null;
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
						"Id;Matricula do Cliente;Matricula do Funcionário;Data de Empréstimo;Data de devolução;");

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

				writer.write(emprestimo.getId() + ";" + emprestimo.getMatriculaCliente() + ";"
						+ emprestimo.getMatriculaFuncionario() + ";" + emprestimo.getDataEmprestimoFormatada() + ";"
						+ emprestimo.getDataDevolucaoFormatada() + "\n");
				writer.flush();
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			
			}finally {
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

}
