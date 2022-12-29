
package projetobiblioteca.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import projetobiblioteca.DAO.AlunoDAO;
import projetobiblioteca.DAO.EmprestimoDAO;
import projetobiblioteca.DAO.FuncionarioDAO;
import projetobiblioteca.DAO.LivroDAO;
import projetobiblioteca.DAO.PeriodicoDAO;
import projetobiblioteca.DAO.ProfessorDAO;
import projetobiblioteca.controller.helper.MenuPrincipalHelper;
import projetobiblioteca.model.Aluno;
import projetobiblioteca.model.Devolucao;
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
	private final FuncionarioDAO funcionarioDAO;

	public MenuPrincipalController() {
		this.helper = new MenuPrincipalHelper();
		this.livroDAO = new LivroDAO();
		this.periodicoDAO = new PeriodicoDAO();
		this.professorDAO = new ProfessorDAO();
		this.alunoDAO = new AlunoDAO();
		this.emprestimoDAO = new EmprestimoDAO();
		this.funcionarioDAO = new FuncionarioDAO();
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
	public boolean cadastraEmprestimo(int matriculaFuncionario, int matriculaUsuario, int opcao, int idItem) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Aluno aluno;
		Professor professor;
		Livro livro = null;
		Periodico periodico = null;
		Emprestimo emprestimo;
		Date date = new Date();
		String dataEmprestimo, dataDevolucao;
		try {
			emprestimoDAO.criaDatabase();
			switch (opcao) {
				case 1:
					professor = professorDAO.buscaPorId(matriculaUsuario);
					livro = livroDAO.buscaPorId(idItem);
					periodico = periodicoDAO.buscaPorId(idItem);

					if (professor != null && livro != null || periodico != null) {
						if (matriculaFuncionario > 0 && matriculaUsuario > 0) {
							dataEmprestimo = dateFormat.format(date.getTime());

							// setando data para 7 dias depois da data de Emprestimo
							date.setDate(dateFormat.parse(dataEmprestimo).getDate() + 7);

							// converte data para String
							dataDevolucao = dateFormat.format(date);

							emprestimo = new Emprestimo(emprestimoDAO.atualizaId(), matriculaUsuario, null,
									matriculaFuncionario, livro != null ? livro.getId() : null,
									periodico != null ? periodico.getId() : null, dataEmprestimo, dataDevolucao);

							if (emprestimoDAO.insert(emprestimo)) {
								return true;
							}
						}
					} else {
						System.out.println("Não foi possível realizar esse empréstimo: entrada de valores inválida.");
					}

					break;

				// Empréstimo para aluno
				case 2:
					aluno = alunoDAO.buscaPorId(matriculaUsuario);
					livro = livroDAO.buscaPorId(idItem);
					periodico = periodicoDAO.buscaPorId(idItem);

					if (aluno != null && livro != null || periodico != null) {

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

							emprestimo = new Emprestimo(emprestimoDAO.atualizaId(), null, matriculaUsuario,
									matriculaFuncionario, livro != null ? livro.getId() : null,
									periodico != null ? periodico.getId() : null, dataEmprestimo, dataDevolucao);

							if (emprestimoDAO.insert(emprestimo)) {
								return true;
							}
						}

						break;

					} else {
						System.out.println("Não foi possível realizar esse empréstimo: entrada de valores inválida.");
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

	public boolean gerarRelatorio(int opcao) {

		switch (opcao) {
			case 1:
				if (livroDAO.existeRegistro()) {
					livroDAO.geraRelatorio();
					return true;
				} else {
					System.out.println("Não existe registro cadastrado!");
				}
				break;

			case 2:
				if (periodicoDAO.existeRegistro()) {
					periodicoDAO.geraRelatorio();
					return true;
				} else {
					System.out.println("Não existe registro cadastrado!");
				}
				break;

			case 3:

				if (emprestimoDAO.existeRegistro()) {
					emprestimoDAO.geraRelatorio();
					return true;
				} else {
					System.out.println("Não existe registro cadastrado!");
				}
				break;

			case 4:
				if (funcionarioDAO.existeRegistro()) {
					funcionarioDAO.geraRelatorio();
					return true;
				} else {
					System.out.println("Não existe registro cadastrado!");
				}
				break;

			case 5:

				if (alunoDAO.existeRegistro()) {
					alunoDAO.geraRelatorio();
					return true;
				} else {
					System.out.println("Não existe registro cadastrado!");
				}
				break;

			case 6:
				// Necessita outro método para gerar relatorio das multas individuais.
				if (alunoDAO.existeRegistro()) {
					int matricula = this.helper.listaAlunosCadastrados();
					alunoDAO.geraRelatorioMultas(matricula);
				} else {
					System.out.println("Não existe registro cadastrado!");
				}

				break;
			default:
				break;
		}

		return false;
	}

	public boolean realizaDevolucao(Devolucao devolucao) {
		Aluno aluno;
		if (devolucao != null) {

			/*
			 * Validar se o empréstimo foi feito por aluno, e se a data de devolução é a
			 * maior que a data de devolução do empréstimo.
			 */
			if (devolucao.getEmprestimo().getMatriculaAluno() != null
					&& devolucao.getEmprestimo().getMatriculaAluno() > 0) {

				aluno = alunoDAO.buscaPorId(devolucao.getEmprestimo().getMatriculaAluno());

				// valida se o aluno foi encontrado.
				if (aluno != null) {
					if (devolucao.getEmprestimo().getDataDevolucao().before(devolucao.getDataDevolucao())) {

						// Calcula a multa com base nos dias de atraso que o aluno tem.
						long diferencaDiasMs = Math.abs(devolucao.getDataDevolucao().getTime()
								- devolucao.getEmprestimo().getDataDevolucao().getTime());

						int diasDiferenca = (int) (diferencaDiasMs / (1000 * 60 * 60 * 24));

						// Insere a multa para o aluno que fez o empréstimo
						aluno.setMulta(aluno.getMulta() + (2.50 * diasDiferenca));

						// Atualiza no banco de dados.
						alunoDAO.updateMulta(aluno);

						System.out.println(
								"A devolução foi feita após o prazo correto, para verificar a multa do aluno faça a impressão do relatório de multas.");
						return true;
					}

					return true;
				}

			} else if (devolucao.getEmprestimo().getMatriculaAluno() == null
					|| devolucao.getEmprestimo().getMatriculaAluno() == 0
							&& devolucao.getEmprestimo().getMatriculaProfessor() != null
					|| devolucao.getEmprestimo().getMatriculaProfessor() > 0
							&& devolucao.getEmprestimo().getIdLivro() != null
					|| devolucao.getEmprestimo().getIdPeriodico() != null) {

				return true;
			}
		}

		return false;
	}

}
