/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetobiblioteca.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lucas
 */
public class ItemEmprestimo{
    
    private int id;
    private int idEmprestimo;
    private int idLivro;
    private int idPeriodico;
    private Date dataDevolucao;

    public ItemEmprestimo(int id, int idEmprestimo, int idLivro, int idPeriodico, Date dataDevolucao) {
        this.id = id;
        this.idEmprestimo = idEmprestimo;
        this.idLivro = idLivro;
        this.idPeriodico = idPeriodico;
        this.dataDevolucao = dataDevolucao;
    }
    
    public ItemEmprestimo(int id, int idEmprestimo, int idLivro, String dataDevolucao) {
        this.id = id;
        this.idEmprestimo = idEmprestimo;
        this.idLivro = idLivro;
        try {
            this.dataDevolucao = new SimpleDateFormat("dd/MM/yyyy").parse(dataDevolucao);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getIdPeriodico() {
        return idPeriodico;
    }

    public void setIdPeriodico(int idPeriodico) {
        this.idPeriodico = idPeriodico;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
    
}
