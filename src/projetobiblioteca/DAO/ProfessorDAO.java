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

import projetobiblioteca.model.Professor;

public class ProfessorDAO implements IDAO<Professor>{

	@Override
	public List<Professor> selectAll() throws FileNotFoundException, IOException {
		Professor professor;
		File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\professor.csv");
		List<Professor> listaProfessores = new ArrayList<Professor>();
		
		if (file.exists()) {
			
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String linha = reader.readLine();
				linha = reader.readLine();

				while (linha != null) {
					String[] linhaSplit = linha.split(";");
					
					// Busca atributos no banco e instanciando objeto
					int matricula = Integer.parseInt(linhaSplit[0]);
					String nome = linhaSplit[1];
					String endereco = linhaSplit[2];
					String dataIngresso = linhaSplit[3];
					String setor = linhaSplit[4];
					professor = new Professor(matricula, nome, endereco, dataIngresso, setor);
					
					// Adiciona objeto na lista e le a proxima linha.
					listaProfessores.add(professor);
					linha = reader.readLine();
				}
			}
			
		}
		
		return listaProfessores;
	}

	@Override
	public void criaDatabase() throws IOException {
		// busca diretorio onde está o projeto
		File arquivo = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\professor.csv");
		PrintWriter writer = null;

		if (!arquivo.exists()) {
			arquivo.createNewFile();
			try {
				FileWriter out = new FileWriter(arquivo, true);
				writer = new PrintWriter(out);
				writer.println("Matricula;Nome;Endereco;Data de Ingresso;Setor");

			} catch (IOException e) {
				System.out.println("Erro: " + e.getMessage());

			} finally {
				writer.close();
			}
		}

	}
	
	@Override
	public int atualizaId() throws FileNotFoundException, IOException {
		File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\professor.csv");
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
	public boolean insert(Professor professor) {
		File arquivo = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\professor.csv");
		PrintWriter writer = null;

		if (arquivo.exists()) {
			try {
				FileWriter out = new FileWriter(arquivo, true);
				writer = new PrintWriter(out);

				writer.write(professor.getMatricula() + ";" + professor.getNome() + ";" + professor.getEndereco() + ";"
						+ professor.getDataFormatada() + ";" + professor.getSetor() + "\n");
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

	public Professor buscaUsuarioPorMatricula(int matricula) throws FileNotFoundException, IOException {
		File file = new File(System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\professor.csv");
		Professor professor = null;
		int matriculaComparar;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String linha = reader.readLine();
			linha = reader.readLine();
			
			while(linha != null) {
				String[] linhaSplit = linha.split(";");
				matriculaComparar = Integer.parseInt(linhaSplit[0]);
				
				if (matricula == matriculaComparar) {
					return professor = new Professor(matriculaComparar, linhaSplit[1], linhaSplit[2], linhaSplit[3], linhaSplit[4]);
				}
				
				linha = reader.readLine();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return professor;
	}

	public Professor buscaPorId(int matriculaUsuario) {
		Professor professor = null;
		
		return professor;
	}

}
