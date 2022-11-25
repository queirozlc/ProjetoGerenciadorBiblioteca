package projetobiblioteca.controller.helper;

import projetobiblioteca.DAO.FuncionarioDAO;
import projetobiblioteca.model.Funcionario;
import projetobiblioteca.view.Main;

public class LoginHelper implements IHelper {

	private final FuncionarioDAO funcionarioDAO;

	public LoginHelper() {
		this.funcionarioDAO = new FuncionarioDAO();
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
		/*
		if (funcionario == null || funcionario.getLogin() == null && funcionario.getSenha() == null) {
			System.out.println("Login ou senha não existem !");
			
		} else if (funcionario != null && funcionario.getLogin() != null && !funcionario.getLogin().isEmpty()
				&& funcionario.getSenha().isEmpty()) {
			System.out.println("Preencha o campo de senha !");

		} else if (funcionario != null && funcionario.getSenha() != null && !funcionario.getSenha().isEmpty()
				&& funcionario.getLogin().isEmpty()) {
			System.out.println("Preencha o campo login !");

		} else if (funcionario != null && funcionario.getSenha() == null
				|| funcionario.getSenha().isEmpty() && funcionario.getLogin() == null
				|| funcionario.getLogin().isEmpty()) {
			System.out.println("Preencha todos os campos !");
		}
*/
		return funcionario != null && funcionario.getLogin() != null && !funcionario.getLogin().isEmpty()
				&& funcionario.getSenha() != null && !funcionario.getSenha().isEmpty();
	}
}
