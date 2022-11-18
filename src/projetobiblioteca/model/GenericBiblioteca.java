/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetobiblioteca.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Lucas
 */
public abstract class GenericBiblioteca {
    
    protected int id;
    protected List<String> autores = new ArrayList<String>();
    protected String titulo;
    protected char tipo;
    protected String issn;
    
    public GenericBiblioteca() {
    	this.id = 0;
    }

    public GenericBiblioteca(int id, String autores, String titulo, char tipo, String issn) {
        this.id = id;
        try {
        	if (autores.contains(",")) {
        		String[] autoresSplit = autores.trim().split(",");
        		this.autores = Arrays.asList(autoresSplit);
        	}else {
        		this.autores.add(autores);
        	}
		} catch (Exception e) {
			System.out.println("Formato de entrada inválido no campo 'autor'");
		}
        this.titulo = titulo;
        this.tipo = tipo;
        this.issn = issn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
    	try {
        	if (autores.contains(",")) {
        		String[] autoresSplit = autores.trim().split(",");
        		this.autores = Arrays.asList(autoresSplit);
        	}else {
        		this.autores.add(autores);
        	}
		} catch (Exception e) {
			System.out.println("Formato de entrada inválido no campo 'autor'");
		}
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.id;
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
        final GenericBiblioteca other = (GenericBiblioteca) obj;
        return this.id == other.id;
    }
    
    
}
