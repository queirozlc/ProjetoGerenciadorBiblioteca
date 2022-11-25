package projetobiblioteca.controller.helper;

import java.io.IOException;
import java.util.List;

import projetobiblioteca.DAO.AlunoDAO;
import projetobiblioteca.DAO.LivroDAO;
import projetobiblioteca.DAO.PeriodicoDAO;
import projetobiblioteca.DAO.ProfessorDAO;
import projetobiblioteca.model.Aluno;
import projetobiblioteca.model.Livro;
import projetobiblioteca.model.Periodico;
import projetobiblioteca.model.Professor;
import projetobiblioteca.view.Main;

/**
 *
 * @author Lucas
 */
public class MenuPrincipalHelper {

	private final LivroDAO livroDAO;
	private final PeriodicoDAO periodicoDAO;
	private final ProfessorDAO professorDAO;
	private final AlunoDAO alunoDAO;

	public MenuPrincipalHelper() {
		this.livroDAO = new LivroDAO();
		this.periodicoDAO = new PeriodicoDAO();
		this.professorDAO = new ProfessorDAO();
		this.alunoDAO = new AlunoDAO();
	}
	
	
	public boolean validaCampos(Object obj) {

		// verifica tipo do objeto
		if (obj instanceof Livro) {

			// valida todos os campos
			return ((Livro) obj).getId() != 0 && ((Livro) obj).getTitulo() != null
					&& !((Livro) obj).getTitulo().isEmpty() && ((Livro) obj).getAutores() != null
					&& !((Livro) obj).getAutores().isEmpty() && ((Livro) obj).getIssn() != null
					&& !((Livro) obj).getIssn().isEmpty() && ((Livro) obj).getEditora() != null
					&& !((Livro) obj).getEditora().isEmpty() && ((Livro) obj).getTipo() == 'L'
					|| ((Livro) obj).getTipo() == 'P';

		} else if (obj instanceof Periodico) {
			return ((Periodico) obj).getId() != 0 && ((Periodico) obj).getTitulo() != null
					&& !((Periodico) obj).getTitulo().isEmpty() && ((Periodico) obj).getAutores() != null
					&& !((Periodico) obj).getAutores().isEmpty() && ((Periodico) obj).getIssn() != null
					&& !((Periodico) obj).getIssn().isEmpty() && ((Periodico) obj).getFatorImpaco() != null
					&& ((Periodico) obj).getFatorImpaco() != 0 && ((Periodico) obj).getTipo() == 'R'
					|| ((Periodico) obj).getTipo() == 'P';

		} else if (obj instanceof Professor) {

			return ((Professor) obj).getNome() != null && !((Professor) obj).getNome().isEmpty()
					&& ((Professor) obj).getMatricula() != 0 && ((Professor) obj).getEndereco() != null
					&& !((Professor) obj).getEndereco().isEmpty() && ((Professor) obj).getDataFormatada() != null
					&& !((Professor) obj).getDataFormatada().isEmpty() && ((Professor) obj).getSetor() != null
					&& !((Professor) obj).getSetor().isEmpty();

		} else if (obj instanceof Aluno) {
			return ((Aluno) obj).getNome() != null && !((Aluno) obj).getNome().isEmpty()
					&& ((Aluno) obj).getMatricula() != 0 && ((Aluno) obj).getEndereco() != null
					&& !((Aluno) obj).getEndereco().isEmpty() && ((Aluno) obj).getDataFormatada() != null
					&& !((Aluno) obj).getDataFormatada().isEmpty() && ((Aluno) obj).getCurso() != null
					&& !((Aluno) obj).getCurso().isEmpty();
		}
		return false;
	}

	public Livro buscarModeloLivro() {
		Livro livro = null;
		try {

			// Entrada de dados
			System.out.print("Informe o titulo do livro: ");
			String titulo = Main.getScanner().nextLine();

			System.out.print("Informe o(s) autor(es) do livro (Se houver mais de um, separar por vírgula): ");
			String autor = Main.getScanner().nextLine();

			System.out.print("Informe a editora: ");
			String editora = Main.getScanner().nextLine();

			System.out.print("Informe o tipo (L para livro ou P para periódico): ");
			char tipo = Main.getScanner().next().toUpperCase().charAt(0);

			System.out.print("Informe o ano de publicação (formato yyyy): ");
			Main.getScanner().nextLine();
			String anoPublicacao = Main.getScanner().nextLine();

			System.out.print("Informe o ISSN do livro: ");
			String issn = Main.getScanner().nextLine();

			// instancia classe modelo e seta propriedades
			livro = new Livro(livroDAO.atualizaId(), autor, titulo, tipo, issn, editora, anoPublicacao);

			return livro;

		} catch (IOException e) {
			e.printStackTrace();

		}

		return livro;
	}

	public Periodico buscarModeloPeriodico() {
		Periodico periodico = null;

		try {
			// Entrada de dados
			System.out.print("Informe o titulo do periodico: ");
			String titulo = Main.getScanner().nextLine();

			System.out.print("Informe o(s) autor(es) do periodico (Se houver mais de um, separar por vírgula): ");
			String autor = Main.getScanner().nextLine();

			System.out.print("Informe o tipo (R para revistas ou P para periódicos): ");
			char tipo = Main.getScanner().next().toUpperCase().charAt(0);

			System.out.print("Informe o fator de impacto do periódico: ");
			Double fatorImpacto = Main.getScanner().nextDouble();
			Main.getScanner().nextLine();

			System.out.print("Informe o ISSN do periodico: ");
			String issn = Main.getScanner().nextLine();

			periodico = new Periodico(periodicoDAO.atualizaId(), autor, titulo, tipo, issn, fatorImpacto);

		} catch (IOException e) {
			e.getMessage();
		}

		return periodico;
	}

	public Object buscarModeloUsuario(int opcao) {
		Professor professor;
		Aluno aluno;
		try {
			if (opcao == 1) {
				System.out.print("Informe o nome do professor: ");
				String nome = Main.getScanner().nextLine();

				System.out.print("Informe o endereço do professor: ");
				String endereco = Main.getScanner().nextLine();

				System.out.print("Informe a data de ingresso do professor: ");
				String dataIngresso = Main.getScanner().nextLine();

				System.out.print("Informe o setor do professor: ");
				String setor = Main.getScanner().nextLine();

				professor = new Professor(this.professorDAO.atualizaId(), nome, endereco, dataIngresso, setor);

				return professor;
			} else if (opcao == 2) {

				System.out.print("Informe o nome do aluno: ");
				String nome = Main.getScanner().nextLine();

				System.out.print("Informe o endereço do aluno: ");
				String endereco = Main.getScanner().nextLine();

				System.out.print("Informe a data de ingresso do aluno: ");
				String dataIngresso = Main.getScanner().nextLine();

				System.out.print("Informe o curso do aluno: ");
				String curso = Main.getScanner().nextLine();

				System.out.print("Informe a multa do aluno (separado por vírgula e informe 0 caso não tenha.): ");
				double multa = Main.getScanner().nextDouble();

				aluno = new Aluno(this.alunoDAO.atualizaId(), nome, endereco, dataIngresso, curso, multa);

				return aluno;
			} else {
				System.out.println("Opção inválida, tente novamente !");
			}

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

		return null;
	}

	public void listarUsuariosCadastrados(int opcao) {
		
		try {
			if (opcao == 1) {
				List<Professor> listaProfessores = professorDAO.selectAll();

				if (listaProfessores != null && !listaProfessores.isEmpty()) {
					System.out.println("\n############### LISTA DE PROFESSORES CADASTRADOS ###############");
					for (Professor professor : listaProfessores) {
						System.out.println("===================================================================");
						System.out.println("Matrícula: " + professor.getMatricula());
						System.out.println("Nome: " + professor.getNome());
					}
				}

			} else if (opcao == 2) {
				List<Aluno> listaAlunos = alunoDAO.selectAll();
				
				if (listaAlunos != null && !listaAlunos.isEmpty()) {
					System.out.println("\n############### LISTA DE ALUNOS CADASTRADOS ###############");
					
					for (Aluno aluno : listaAlunos) {
						System.out.println("===================================================================");
						System.out.println("Matrícula: " + aluno.getMatricula());
						System.out.println("Nome: " + aluno.getNome());
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}
	
	
}
