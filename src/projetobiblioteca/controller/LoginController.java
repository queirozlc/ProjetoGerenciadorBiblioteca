
package projetobiblioteca.controller;

import projetobiblioteca.DAO.FuncionarioDAO;

/**
 *
 * @author Lucas
 */
public class LoginController {
	
	private final FuncionarioDAO funcionarioDAO;	
	
	public LoginController() {
		this.funcionarioDAO = new FuncionarioDAO();
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

	

}
