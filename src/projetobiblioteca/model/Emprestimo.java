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
public class Emprestimo {
    
    private int id;
    private int matriculaCliente;
    private int matriculaFuncionario;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Emprestimo(int id, int matriculaCliente, int matriculaFuncionario, String dataEmprestimo, String dataDevolucao) {
        this.id = id;
        this.matriculaCliente = matriculaCliente;
        this.matriculaFuncionario = matriculaFuncionario;
        
        try {
            this.dataEmprestimo = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmprestimo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
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

    public int getMatriculaCliente() {
        return matriculaCliente;
    }

    public void setMatriculaCliente(int matriculaCliente) {
        this.matriculaCliente = matriculaCliente;
    }

    public int getMatriculaFuncionario() {
        return matriculaFuncionario;
    }

    public void setMatriculaFuncionario(int matriculaFuncionario) {
        this.matriculaFuncionario = matriculaFuncionario;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    public String getDataEmprestimoFormatada() {
    	return new SimpleDateFormat("dd/MM/yyyy").format(this.dataEmprestimo);
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }
    
    public String getDataDevolucaoFormatada() {
    	return new SimpleDateFormat("dd/MM/yyyy").format(this.dataDevolucao);
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Emprestimo other = (Emprestimo) obj;
        return this.id == other.id;
    }
    
    
    
}
