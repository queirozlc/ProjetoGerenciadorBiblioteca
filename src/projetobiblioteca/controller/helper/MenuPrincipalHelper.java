package projetobiblioteca.controller.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.print.attribute.PrintJobAttribute;

import projetobiblioteca.DAO.LivroDAO;
import projetobiblioteca.model.Livro;

/**
 *
 * @author Lucas
 */
public class MenuPrincipalHelper implements IHelper {

	private final LivroDAO livroDAO;

	public MenuPrincipalHelper() {
		this.livroDAO = new LivroDAO();
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

		}
		return false;
	}

	public int atualizaId() throws FileNotFoundException, IOException {
		int ultimoId = this.livroDAO.verificaUltimoId();
		ultimoId++;
		return ultimoId;
	}

	@Override
	public Object buscarModelo() {
		Livro livro = null;
		Scanner scanner = new Scanner(System.in);
		try {
			// Entrada de dados
			System.out.print("Informe o titulo do livro: ");
			String titulo = scanner.nextLine();

			System.out.print("Informe o(s) autor(es) do livro (Se houver mais de um, separar por vírgula): ");
			String autor = scanner.nextLine();

			System.out.print("Informe a editora: ");
			String editora = scanner.nextLine();

			System.out.print("Informe o tipo (L para livro ou P para periódico): ");
			char tipo = scanner.next().toUpperCase().charAt(0);

			System.out.print("Informe o ano de publicação (formato yyyy): ");
			scanner.nextLine();
			String anoPublicacao = scanner.nextLine();

			System.out.print("Informe o ISSN do livro: ");
			String issn = scanner.nextLine();

			
			// instancia classe modelo e seta propriedades
			livro = new Livro(this.atualizaId(), autor, titulo, tipo, issn, editora, anoPublicacao);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			scanner.close();
		}
		
		return livro;
	}

}
