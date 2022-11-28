
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

import projetobiblioteca.model.Funcionario;

/**
 *
 * @author Lucas
 */
public class FuncionarioDAO implements IDAO<Funcionario> {

	@Override
	public void criaDatabase() throws IOException {
		// busca diretorio onde está o projeto
		File arquivo = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\funcionarios.csv");
		PrintWriter writer = null;

		if (!arquivo.exists()) {
			arquivo.createNewFile();
			try {
				FileWriter out = new FileWriter(arquivo, true);
				writer = new PrintWriter(out);
				writer.println("Matricula;Nome;Endereco;Data Ingresso;Login;Senha;Setor");

			} catch (IOException e) {
				System.out.println("Erro: " + e.getMessage());

			} finally {
				writer.close();
			}
		}

	}

	@Override
	public boolean insert(Funcionario funcionario) {
		File arquivo = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\funcionarios.csv");
		PrintWriter writer = null;

		if (arquivo.exists()) {

			try {
				FileWriter out = new FileWriter(arquivo, true);
				writer = new PrintWriter(out);

				writer.write(funcionario.getMatricula() + ";" + funcionario.getNome() + ";" + funcionario.getEndereco()
						+ ";" + funcionario.getDataFormatada() + ";" + funcionario.getLogin() + ";"
						+ funcionario.getSenha() + ";" + funcionario.getSetor() + "\n");
				writer.flush();

				return true;
			} catch (IOException e) {
				System.out.println(e.getMessage());

			} finally {
				writer.close();
			}
		}

		return false;
	}

	@Override
	public List<Funcionario> selectAll() {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\funcionarios.csv");
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		Funcionario funcionario;

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
					String login = linhaSplit[4];
					String senha = linhaSplit[5];
					String setor = linhaSplit[6];

					funcionario = new Funcionario(matricula, nome, endereco, dataIngresso, login, senha, setor);
					listaFuncionario.add(funcionario);
					linha = reader.readLine();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return listaFuncionario;
	}

	public boolean verificaLogin(String loginAComparar) throws FileNotFoundException, IOException {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\funcionarios.csv");
		String login;

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			String linha = reader.readLine();
			linha = reader.readLine();

			while (linha != null) {
				String[] linhaSplit = linha.split(";");
				login = linhaSplit[4];
				if (login.equalsIgnoreCase(loginAComparar)) {
					return true;
				}
				linha = reader.readLine();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	public boolean pesquisarPorLoginESenha(String login, String senha) {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\funcionarios.csv");

		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String loginBanco, senhaBanco;
				String linha = reader.readLine();
				linha = reader.readLine();

				while (linha != null) {

					String[] linhaSplit = linha.split(";");
					loginBanco = linhaSplit[4];
					senhaBanco = linhaSplit[5];

					if (loginBanco.equalsIgnoreCase(login) && senhaBanco.equalsIgnoreCase(senha)) {
						return true;
					}

					linha = reader.readLine();
				}

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		return false;
	}

	public Funcionario buscaUsuarioPorLoginESenha(String login, String senha) {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\funcionarios.csv");
		Funcionario funcionario = new Funcionario();
		String loginBanco, senhaBanco;

		if (file.exists()) {

			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String linha = reader.readLine();
				linha = reader.readLine();

				while (linha != null) {
					String[] linhaSplit = linha.split(";");
					loginBanco = linhaSplit[4];
					senhaBanco = linhaSplit[5];

					if (login.equalsIgnoreCase(loginBanco) && senha.equals(senhaBanco)) {
						funcionario.setMatricula(Integer.parseInt(linhaSplit[0]));
						funcionario.setNome(linhaSplit[1]);
						funcionario.setEndereco(linhaSplit[2]);
						funcionario.setDataIngresso(linhaSplit[3]);
						funcionario.setLogin(loginBanco);
						funcionario.setSenha(senhaBanco);
						funcionario.setSetor(linhaSplit[6]);

						return funcionario;
					}

					linha = reader.readLine();
				}

			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}

		}

		return funcionario;
	}

	@Override
	public int atualizaId() throws FileNotFoundException, IOException {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\funcionarios.csv");
		int matricula = 0;

		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String linha = reader.readLine();
				linha = reader.readLine();

				while (linha != null) {
					String[] linhaSplit = linha.split(";");
					matricula = Integer.parseInt(linhaSplit[0]);
					linha = reader.readLine();
				}

			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}

		return ++matricula;
	}

	@Override
	public boolean existeRegistro() {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\projetobiblioteca\\DAO\\database\\funcionarios.csv");
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
		File file = new File(System.getProperty("user.home") + "\\Downloads\\RelatorioFuncionario.csv");
		PrintWriter writer = null;
		List<Funcionario> listaFuncionarios = this.selectAll();

		try {
			if (!file.exists()) {
				file.createNewFile();
				FileWriter out = new FileWriter(file, true);
				writer = new PrintWriter(out);
				writer.println("Matricula;Nome;Endereco;Data de Ingresso;Login;Setor");

				for (Funcionario funcionario : listaFuncionarios) {

					writer.write(funcionario.getMatricula() + ";" + funcionario.getNome() + ";"
							+ funcionario.getEndereco() + ";" + funcionario.getDataFormatada() + ";"
							+ funcionario.getLogin() + ";" + funcionario.getSetor() + "\n");
				}
				
				writer.flush();
			} else {
				System.out.println("Este arquivo já existe na pasta 'Downloads'.");
			}

		} catch (IOException e) {
			System.out.println("Erro ao criar arquivo de relatorio: " + e.getMessage());
		
		}finally {
			writer.close();
		}

	}
}
