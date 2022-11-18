package projetobiblioteca.controller.helper;

import projetobiblioteca.model.Funcionario;

/**
 *
 * @author Lucas
 */
public class CriarContaHelper {

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

}
