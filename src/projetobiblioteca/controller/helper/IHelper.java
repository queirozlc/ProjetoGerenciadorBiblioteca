/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projetobiblioteca.controller.helper;

/**
 *
 * @author Lucas
 */
public interface IHelper {
    
    // limpar campos de formulário
    abstract void limparTela();
    
    // buscar dados do formulário
    abstract Object buscarModelo();
}
