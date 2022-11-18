package projetobiblioteca.DAO;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface IDAO {
    abstract void delete(int id);
    
    abstract List<Object> selectAll();
    
    abstract void criaDatabase() throws IOException;
}
