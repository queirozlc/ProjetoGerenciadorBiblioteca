
package projetobiblioteca.controller;

import java.io.IOException;

import projetobiblioteca.DAO.LivroDAO;
import projetobiblioteca.DAO.PeriodicoDAO;
import projetobiblioteca.controller.helper.MenuPrincipalHelper;
import projetobiblioteca.model.Livro;
import projetobiblioteca.model.Periodico;
/**
 *
 * @author Lucas
 */
public class MenuPrincipalController {
    
    private final MenuPrincipalHelper helper;
    private final LivroDAO livroDAO;
    private final PeriodicoDAO periodicoDAO;
    
    public MenuPrincipalController() {
        this.helper = new MenuPrincipalHelper();
		this.livroDAO = new LivroDAO();
		this.periodicoDAO = new PeriodicoDAO();
    }

    public MenuPrincipalHelper getHelper() {
        return helper;
    }


	public boolean cadastraItem(Object item) throws IOException {
		
		livroDAO.criaDatabase();
		periodicoDAO.criaDatabase();
		
		if (item instanceof Livro) {
			
			if (!this.helper.validaCampos((Livro) item) && ((Livro) item).getTipo() != 'P' && ((Livro) item).getTipo() != 'L') {
				System.out.println("Valor inválido no campo 'tipo'");
			
			} else if (this.helper.validaCampos(item)) {
				return this.livroDAO.insert((Livro) item);
			}
			
		} else if (item instanceof Periodico) {
			
			
			
		}
		
		return false;
	}
}
