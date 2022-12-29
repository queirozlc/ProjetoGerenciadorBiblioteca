/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetobiblioteca.model;

/**
 *
 * @author Lucas
 */
public class Aluno extends Generic{
    
    private String curso;
    private Double multa;
    
    public Aluno() {}

    public Aluno(int matricula, String nome, String endereco, String dataIngresso, String curso, Double multa) {
        super(matricula, nome, endereco, dataIngresso);
        this.curso = curso;
        this.multa = multa;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }
}
