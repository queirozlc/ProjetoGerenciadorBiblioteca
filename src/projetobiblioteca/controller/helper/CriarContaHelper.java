package projetobiblioteca.controller.helper;

import java.util.Scanner;

import projetobiblioteca.model.Funcionario;

/**
 *
 * @author Lucas
 */
public class CriarContaHelper implements IHelper{

	public boolean validaCampos(Funcionario funcionario) {
		if (funcionario != null) {
			return funcionario.getMatricula() != 0 && funcionario.getNome() != null && !funcionario.getNome().isEmpty()
					&& funcionario.getEndereco() != null && !funcionario.getEndereco().isEmpty()
					&& funcionario.getDataFormatada() != null && funcionario.getLogin() != null
					&& !funcionario.getLogin().isEmpty() && funcionario.getSenha() != null
					&& !funcionario.getSenha().isEmpty() && funcionario.getSetor() != null
					&& !funcionario.getSetor().isEmpty();
		}

		return false;
	}
	
	@Override
	public Object buscarModelo() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Informe a matricula: ");
		int matricula = scanner.nextInt();
		
		System.out.print("Informe o nome: ");
		scanner.nextLine();
		String nome = scanner.nextLine();
		
		System.out.print("Informe o endereço: ");
		String endereco = scanner.nextLine();

		System.out.print("Informe a data de ingresso (formato dd/MM/yyyy): ");
		String dataIngresso = scanner.nextLine();

		System.out.print("Informe o login: ");
		String login = scanner.nextLine();

		System.out.print("Informe a senha: ");
		String senhaCadastro = scanner.nextLine();

		System.out.print("Informe o setor: ");
		String setor = scanner.nextLine();

		// Instanciando classe modelo
		Funcionario funcionario = new Funcionario(matricula, nome, endereco, dataIngresso, login, senhaCadastro, setor);
		
		return funcionario;
	}
}
