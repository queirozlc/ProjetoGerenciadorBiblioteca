/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetobiblioteca.model;

/**
 *
 * @author Lucas
 */
public class Funcionario extends Generic {
    
    private String login;
    private String senha;
    private String setor;

    public Funcionario() {
    }
    

    public Funcionario(int matricula, String nome, String endereco, String dataIngresso, String login, String senha, String setor) {
        super(matricula, nome, endereco, dataIngresso);
        this.login = login;
        this.senha = senha;
        this.setor = setor;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
    
    
    
}
