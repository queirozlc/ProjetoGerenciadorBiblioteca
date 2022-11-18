/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetobiblioteca.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



/**
 *
 * @author Lucas
 */
public class Livro extends GenericBiblioteca{
    
    private String editora;
    private Date anoPublicacao;

    public Livro(int id, List<String> autores, String titulo, char tipo, String issn, String editora, String anoPublicacao) {
        super(id, autores, titulo, tipo, issn);
        this.editora = editora;
        
        try {
            this.anoPublicacao = new SimpleDateFormat("yyyy").parse(anoPublicacao);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public void setAnoPublicacao(Date anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    
    
}
