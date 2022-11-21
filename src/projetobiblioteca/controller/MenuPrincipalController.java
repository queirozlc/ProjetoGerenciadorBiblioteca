
package projetobiblioteca.controller;

import java.io.IOException;

import projetobiblioteca.DAO.AlunoDAO;
import projetobiblioteca.DAO.LivroDAO;
import projetobiblioteca.DAO.PeriodicoDAO;
import projetobiblioteca.DAO.ProfessorDAO;
import projetobiblioteca.controller.helper.MenuPrincipalHelper;
import projetobiblioteca.model.Aluno;
import projetobiblioteca.model.Livro;
import projetobiblioteca.model.Periodico;
import projetobiblioteca.model.Professor;

/**
 *
 * @author Lucas
 */
public class MenuPrincipalController {

	private final MenuPrincipalHelper helper;
	private final LivroDAO livroDAO;
	private final PeriodicoDAO periodicoDAO;
	private final ProfessorDAO professorDAO;
	private final AlunoDAO alunoDAO;

	public MenuPrincipalController() {
		this.helper = new MenuPrincipalHelper();
		this.livroDAO = new LivroDAO();
		this.periodicoDAO = new PeriodicoDAO();
		this.professorDAO = new ProfessorDAO();
		this.alunoDAO = new AlunoDAO();
	}

	public MenuPrincipalHelper getHelper() {
		return helper;
	}

	public boolean cadastraItem(Object item) throws IOException {

		if (item instanceof Livro) {
			livroDAO.criaDatabase();
			if (!this.helper.validaCampos((Livro) item) && ((Livro) item).getTipo() != 'P'
					&& ((Livro) item).getTipo() != 'L') {
				System.out.println("Valor inválido no campo 'tipo'");

			} else if (this.helper.validaCampos(item)) {
				return this.livroDAO.insert((Livro) item);
			}

		} else if (item instanceof Periodico) {
			periodicoDAO.criaDatabase();
			if (!this.helper.validaCampos((Periodico) item) && ((Periodico) item).getTipo() != 'P'
					&& ((Periodico) item).getTipo() != 'R') {
				System.out.println("Valor inválido no campo 'tipo'");

			} else if (this.helper.validaCampos(item)) {
				return this.periodicoDAO.insert((Periodico) item);
			}

		}

		return false;
	}

	public boolean cadastraUsuario(Object obj) throws IOException {

		if (obj instanceof Professor) {
			this.professorDAO.criaDatabase();
			if (this.helper.validaCampos((Professor) obj)) {
				return this.professorDAO.insert((Professor) obj);
			} else {
				System.out.println("Preencha todos os campos !");
			}

		} else if (obj instanceof Aluno) {
			this.alunoDAO.criaDatabase();
			if (this.helper.validaCampos((Aluno) obj)) {
				return this.alunoDAO.insert((Aluno) obj);
			}
		}

		return false;
	}
}
