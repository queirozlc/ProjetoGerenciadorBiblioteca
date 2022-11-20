
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
		
		if (funcionarioDAO.pesquisarPorLoginESenha(usuario, senha)) {
			return true;
		
		} else if (usuario != null && !usuario.isEmpty() && senha != null && !senha.isEmpty()
				&& !funcionarioDAO.pesquisarPorLoginESenha(usuario, senha)) {
			
			System.out.println("Login ou senha incorretos!");
		}
		
		return false;
	}

	public LoginHelper getHelper() {
		return helper;
	}


}
