package projetobiblioteca.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Devolucao {

    private Emprestimo emprestimo;
    private int matriculaFuncionario;
    private Date dataDevolucao;

    public Devolucao(Emprestimo emprestimo, int matriculaFuncionario, String dataDevolucao) {
        this.emprestimo = emprestimo;
        this.matriculaFuncionario = matriculaFuncionario;
        try {
            this.dataDevolucao = new SimpleDateFormat("dd/MM/yyyy").parse(dataDevolucao);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido: " + e.getMessage());
        }
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setIdEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public int getMatriculaFuncionario() {
        return matriculaFuncionario;
    }

    public void setMatriculaFuncionario(int matriculaFuncionario) {
        this.matriculaFuncionario = matriculaFuncionario;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

}
