
package projetobiblioteca.model;

/**
 *
 * @author Lucas
 */
public class Professor extends Generic{
    
    private String setor;

    public Professor(int matricula, String nome, String endereco, String dataIngresso, String setor) {
        super(matricula, nome, endereco, dataIngresso);
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
    
}
