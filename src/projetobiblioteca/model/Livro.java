
package projetobiblioteca.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lucas
 */
public class Livro extends GenericBiblioteca {

	private String editora;
	private Date anoPublicacao;

	public Livro(int id, String autores, String titulo, char tipo, String issn, String editora, String anoPublicacao) {
		super(id, autores, titulo, tipo, issn);
		this.editora = editora;

		try {
			this.anoPublicacao = new SimpleDateFormat("yyyy").parse(anoPublicacao);
		} catch (ParseException e) {
			System.out.println("Formato de data inválido, Erro: " + e.getMessage());
		}
	}

	public Livro() {
		super();
		this.id++;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Date getAnoPublicacao() {
		return anoPublicacao;
	}

	public String getAnoPublicacaoFormatado() {
		return new SimpleDateFormat("yyyy").format(this.anoPublicacao);
	}

	public void setAnoPublicacao(String anoPublicacao) throws ParseException {
		this.anoPublicacao = new SimpleDateFormat("yyy").parse(anoPublicacao);
	}

}
