
package projetobiblioteca.controller;

import java.io.IOException;

import projetobiblioteca.DAO.FuncionarioDAO;
import projetobiblioteca.controller.helper.CriarContaHelper;
import projetobiblioteca.model.Funcionario;

/**
 *
 * @author Lucas
 */
public class CriarContaController {

	private final FuncionarioDAO funcionarioDAO;
	private final CriarContaHelper helper;

	public CriarContaController() {
		this.funcionarioDAO = new FuncionarioDAO();
		this.helper = new CriarContaHelper();
	}

	public boolean criarConta(Funcionario funcionario) throws IOException {
		funcionarioDAO.criaDatabase();

		// valida os campos e se matricula ou login cadastrados ja existem
		if (this.helper.validaCampos(funcionario)
				&& !funcionarioDAO.verificaLogin(funcionario.getLogin())) {
			this.funcionarioDAO.insert(funcionario);
			return true;

		} else if (!this.helper.validaCampos(funcionario)) {
			System.out.println("Preencha todos os campos !");

		} else if (this.helper.validaCampos(funcionario)
				&& funcionarioDAO.verificaLogin(funcionario.getLogin())) {
			System.out.println("Login ja existe !");
		}
			return false;
	}

	public CriarContaHelper getHelper() {
		return helper;
	}
}
