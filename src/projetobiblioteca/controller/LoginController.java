
package projetobiblioteca.controller;

import projetobiblioteca.DAO.FuncionarioDAO;
import projetobiblioteca.controller.helper.LoginHelper;
import projetobiblioteca.model.Funcionario;

/**
 *
 * @author Lucas
 */
public class LoginController {

	private final FuncionarioDAO funcionarioDAO;
	private final LoginHelper helper;

	public LoginController() {
		this.funcionarioDAO = new FuncionarioDAO();
		this.helper = new LoginHelper();
	}

	public boolean entrarNoSistema(Funcionario funcionario) {

		if (funcionario != null && this.helper.validaCampos(funcionario)
				&& funcionarioDAO.pesquisarPorLoginESenha(funcionario.getLogin(), funcionario.getSenha())) {
			return true;

		} else if (funcionario != null && this.helper.validaCampos(funcionario)
				&& !funcionarioDAO.pesquisarPorLoginESenha(funcionario.getLogin(), funcionario.getSenha())) {
			System.out.println("Login ou senha incorretos !");

		} else if (funcionario != null && !this.helper.validaCampos(funcionario) && !funcionario.getLogin().isEmpty()
				&& funcionario.getSenha().isEmpty()) {
			System.out.println("Preencha o campo de senha !");

		} else if (funcionario != null && !this.helper.validaCampos(funcionario) && !funcionario.getSenha().isEmpty()
				&& funcionario.getLogin().isEmpty()) {
			System.out.println("Preencha o campo de login !");

		} else if (funcionario != null && !this.helper.validaCampos(funcionario) && funcionario.getLogin().isEmpty()
				&& funcionario.getSenha().isEmpty()) {
			System.out.println("Preencha todos os campos !");
		}

		return false;
	}

	public LoginHelper getHelper() {
		return helper;
	}

	public FuncionarioDAO getFuncionarioDAO() {
		return funcionarioDAO;
	}

}
