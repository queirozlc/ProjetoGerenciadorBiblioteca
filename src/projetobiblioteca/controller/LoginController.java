
package projetobiblioteca.controller;

import projetobiblioteca.DAO.FuncionarioDAO;
import projetobiblioteca.controller.helper.LoginHelper;

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

	public boolean entrarNoSistema(String usuario, String senha) {
		return false;
	}

	

}
