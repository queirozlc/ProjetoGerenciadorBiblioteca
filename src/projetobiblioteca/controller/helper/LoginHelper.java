package projetobiblioteca.controller.helper;

import projetobiblioteca.model.Funcionario;
import projetobiblioteca.view.Main;

public class LoginHelper implements IHelper {

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

	public void validaCampos(String usuario, String senha) {
		if (usuario.isEmpty()) {
			System.out.println("Preencha o campo usuário !");

		} else if (senha.isEmpty()) {
			System.out.println("Preencha o campo senha !");

		} else if (usuario.isEmpty() && senha.isEmpty()) {
			System.out.println("Preencha todos os campos!");
		}
	}

}
