/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetobiblioteca.model;

/**
 *
 * @author Lucas
 */
public class Periodico extends GenericBiblioteca{
    
    private Double fatorImpaco;

    public Periodico(int id, String autores, String titulo, char tipo, String issn, String fatorImpaco) {
        super(id, autores, titulo, tipo, issn);
        this.fatorImpaco = Double.valueOf(fatorImpaco);
    }

    public Double getFatorImpaco() {
        return fatorImpaco;
    }

    public void setFatorImpaco(Double fatorImpaco) {
        this.fatorImpaco = fatorImpaco;
    }
    
}
