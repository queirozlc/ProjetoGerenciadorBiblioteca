package projetobiblioteca.controller.helper;

import projetobiblioteca.DAO.FuncionarioDAO;
import projetobiblioteca.model.Funcionario;
import projetobiblioteca.view.Main;

/**
 *
 * @author Lucas
 */
public class CriarContaHelper implements IHelper {

	private final FuncionarioDAO funcionarioDAO;

	public CriarContaHelper() {
		this.funcionarioDAO = new FuncionarioDAO();
	}

	
	public boolean validaCampos(Object obj) {
		if (((Funcionario) obj) != null) {
			return ((Funcionario) obj).getMatricula() != 0 && ((Funcionario) obj).getNome() != null
					&& !((Funcionario) obj).getNome().isEmpty() && ((Funcionario) obj).getEndereco() != null
					&& !((Funcionario) obj).getEndereco().isEmpty() && ((Funcionario) obj).getDataFormatada() != null
					&& ((Funcionario) obj).getLogin() != null && !((Funcionario) obj).getLogin().isEmpty()
					&& ((Funcionario) obj).getSenha() != null && !((Funcionario) obj).getSenha().isEmpty()
					&& ((Funcionario) obj).getSetor() != null && !((Funcionario) obj).getSetor().isEmpty();
		}

		return false;
	}

	@Override
	public Object buscarModelo() {
		Funcionario funcionario = null;
		try {
			System.out.print("Informe o nome do funcionário: ");
			String nome = Main.getScanner().nextLine();

			System.out.print("Informe o endereço do funcionário: ");
			String endereco = Main.getScanner().nextLine();

			System.out.print("Informe a data de ingresso do funcionário (formato dd/MM/yyyy): ");
			String dataIngresso = Main.getScanner().nextLine();

			System.out.print("Informe o login do funcionário: ");
			String login = Main.getScanner().nextLine();

			System.out.print("Informe a senha do funcionário: ");
			String senhaCadastro = Main.getScanner().nextLine();

			System.out.print("Informe o setor do funcionário: ");
			String setor = Main.getScanner().nextLine();

			// Instanciando classe modelo
			funcionario = new Funcionario(this.funcionarioDAO.atualizaId(), nome, endereco, dataIngresso, login,
					senhaCadastro, setor);

			return funcionario;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return funcionario;
	}
}
