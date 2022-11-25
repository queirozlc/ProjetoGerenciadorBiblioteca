
package projetobiblioteca.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import projetobiblioteca.DAO.AlunoDAO;
import projetobiblioteca.DAO.EmprestimoDAO;
import projetobiblioteca.DAO.LivroDAO;
import projetobiblioteca.DAO.PeriodicoDAO;
import projetobiblioteca.DAO.ProfessorDAO;
import projetobiblioteca.controller.helper.MenuPrincipalHelper;
import projetobiblioteca.model.Aluno;
import projetobiblioteca.model.Emprestimo;
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
	private final EmprestimoDAO emprestimoDAO;

	public MenuPrincipalController() {
		this.helper = new MenuPrincipalHelper();
		this.livroDAO = new LivroDAO();
		this.periodicoDAO = new PeriodicoDAO();
		this.professorDAO = new ProfessorDAO();
		this.alunoDAO = new AlunoDAO();
		this.emprestimoDAO = new EmprestimoDAO();
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

			} else {
				System.out.println("Preencha todos os campos !");
			}
		}

		return false;
	}

	@SuppressWarnings("deprecation")
	public boolean cadastraEmprestimo(int matriculaFuncionario, int matriculaUsuario, int opcao) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Aluno aluno;
		Professor professor;
		Emprestimo emprestimo;
		Date date = new Date();
		String dataEmprestimo, dataDevolucao;
		try {
			emprestimoDAO.criaDatabase();
			switch (opcao) {
			case 1:
				professor = professorDAO.buscaPorId(matriculaUsuario);
				
				if (professor != null) {
					if (matriculaFuncionario > 0 && matriculaUsuario > 0) {
						dataEmprestimo = dateFormat.format(date.getTime());

						// setando data para 7 dias depois da data de Emprestimo
						date.setDate(dateFormat.parse(dataEmprestimo).getDate() + 7);

						// converte data para String
						dataDevolucao = dateFormat.format(date);

						emprestimo = new Emprestimo(emprestimoDAO.atualizaId(), matriculaUsuario, matriculaFuncionario,
								dataEmprestimo, dataDevolucao);

						if (emprestimoDAO.insert(emprestimo)) {
							return true;
						}
					}
				} else {
					System.out.println("Não existe professor com a matrícula informada");
				}
				
				break;

			// Empréstimo para aluno
			case 2:
				aluno = alunoDAO.buscaPorId(matriculaUsuario);

				if (aluno != null) {

					if (aluno.getMulta() > 0) {
						System.out.printf(
								"Não foi possível cadastrar empréstimo para esse aluno, multa pendente no valor de R$ %.2f",
								aluno.getMulta());

					} else {
						dataEmprestimo = dateFormat.format(date.getTime());
						// setando data para 7 dias depois da data de Emprestimo
						date.setDate(dateFormat.parse(dataEmprestimo).getDate() + 7);
						// converte data para String
						dataDevolucao = dateFormat.format(date);

						emprestimo = new Emprestimo(emprestimoDAO.atualizaId(), matriculaUsuario, matriculaFuncionario,
								dataEmprestimo, dataDevolucao);

						if (emprestimoDAO.insert(emprestimo)) {
							return true;
						}
					}

				} else {
					System.out.println("Não existe aluno com a matrícula informada");
				}
				break;
			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}
