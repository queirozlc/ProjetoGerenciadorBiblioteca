package projetobiblioteca.controller.helper;

import java.io.IOException;

import projetobiblioteca.DAO.LivroDAO;
import projetobiblioteca.DAO.PeriodicoDAO;
import projetobiblioteca.model.Livro;
import projetobiblioteca.model.Periodico;
import projetobiblioteca.view.Main;

/**
 *
 * @author Lucas
 */
public class MenuPrincipalHelper implements IHelper {

	private final LivroDAO livroDAO;
	private final PeriodicoDAO periodicoDAO;
	
	public MenuPrincipalHelper() {
		this.livroDAO = new LivroDAO();
		this.periodicoDAO = new PeriodicoDAO();
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

		}else if (obj instanceof Periodico) {
			return ((Livro) obj).getId() != 0 && ((Livro) obj).getTitulo() != null
					&& !((Livro) obj).getTitulo().isEmpty() && ((Livro) obj).getAutores() != null
					&& !((Livro) obj).getAutores().isEmpty() && ((Livro) obj).getIssn() != null
					&& !((Livro) obj).getIssn().isEmpty() && ((Livro) obj).getEditora() != null
					&& !((Livro) obj).getEditora().isEmpty() && ((Livro) obj).getTipo() == 'L'
					|| ((Livro) obj).getTipo() == 'P';
		}
		return false;
	}

	@Override
	public Object buscarModelo() {
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
			System.out.print("Informe o(s) autor(es) do periodico (Se houver mais de um, separar por vírgula): ");
			String autor = Main.getScanner().nextLine();
			
			System.out.print("Informe o titulo do periodico: ");
			String titulo = Main.getScanner().nextLine();
			
			System.out.print("Informe o tipo (R para revistas ou P para periódicos): ");
			char tipo = Main.getScanner().next().toUpperCase().charAt(0);
			
			System.out.print("Informe o fator de impacto do periódico: ");
			Double fatorImpacto = Main.getScanner().nextDouble();
			
			System.out.print("Informe o ISSN do periodico: ");
			String issn = Main.getScanner().nextLine();
			
			periodico = new Periodico(periodicoDAO.atualizaId(), autor, titulo, tipo, issn, fatorImpacto);
			
		} catch (IOException e) {
			e.getMessage();
		}
		
		return periodico;
	}
	
}
