package projetobiblioteca.controller.helper;

import projetobiblioteca.model.Funcionario;
import projetobiblioteca.view.Main;

/**
 *
 * @author Lucas
 */
public class CriarContaHelper implements IHelper {

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
		Funcionario funcionario = null;

		System.out.print("Informe a matricula: ");
		int matricula = Main.getScanner().nextInt();

		System.out.print("Informe o nome: ");
		Main.getScanner().nextLine();
		String nome = Main.getScanner().nextLine();

		System.out.print("Informe o endereço: ");
		String endereco = Main.getScanner().nextLine();

		System.out.print("Informe a data de ingresso (formato dd/MM/yyyy): ");
		String dataIngresso = Main.getScanner().nextLine();

		System.out.print("Informe o login: ");
		String login = Main.getScanner().nextLine();

		System.out.print("Informe a senha: ");
		String senhaCadastro = Main.getScanner().nextLine();

		System.out.print("Informe o setor: ");
		String setor = Main.getScanner().nextLine();

		// Instanciando classe modelo
		funcionario = new Funcionario(matricula, nome, endereco, dataIngresso, login, senhaCadastro, setor);

		return funcionario;
	}
}
