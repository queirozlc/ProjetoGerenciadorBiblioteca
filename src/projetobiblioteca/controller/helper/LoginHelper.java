package projetobiblioteca.controller.helper;

import projetobiblioteca.model.Funcionario;
import projetobiblioteca.view.Main;

public class LoginHelper implements IHelper {


	public LoginHelper() {
	}

	@Override
	public Object buscarModelo() {
		Funcionario funcionario = new Funcionario();

		System.out.println("============ MENU DE LOGIN ============");
		System.out.print("Informe um Login: ");
		String login = Main.getScanner().nextLine();
		System.out.print("Informe uma senha: ");
		String senha = Main.getScanner().nextLine();

		funcionario.setLogin(login);
		funcionario.setSenha(senha);

		return funcionario;
	}

	public boolean validaCampos(Funcionario funcionario) {
		return funcionario != null && funcionario.getLogin() != null && !funcionario.getLogin().isEmpty()
				&& funcionario.getSenha() != null && !funcionario.getSenha().isEmpty();
	}
}
