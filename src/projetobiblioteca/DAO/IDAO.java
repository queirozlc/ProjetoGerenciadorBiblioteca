package projetobiblioteca.DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface IDAO<T> {    
    abstract List<T> selectAll();
    
    abstract boolean insert(T t);
    
    abstract void criaDatabase() throws IOException;
    
    abstract int atualizaId() throws FileNotFoundException, IOException;
    
    abstract boolean existeRegistro();
    
    abstract void geraRelatorio();
}
