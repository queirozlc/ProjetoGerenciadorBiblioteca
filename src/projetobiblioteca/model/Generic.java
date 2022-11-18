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
public abstract class Generic {
    
    protected int matricula;
    protected String nome;
    protected String endereco;
    protected Date dataIngresso;

    public Generic() {
    }

    public Generic(int matricula, String nome, String endereco, String dataIngresso) {
        this.matricula = matricula;
        this.nome = nome;
        this.endereco = endereco;
        try {
            this.dataIngresso = new SimpleDateFormat("dd/MM/yyyy").parse(dataIngresso);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataIngresso() {
        return dataIngresso;
    }
    
    public String getDataFormatada() {
        return new SimpleDateFormat("dd/MM/yyyy").format(this.dataIngresso);
    }

    public void setDataIngresso(Date dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.matricula;
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
        final Generic other = (Generic) obj;
        return this.matricula == other.matricula;
    }
    
    
    
}
