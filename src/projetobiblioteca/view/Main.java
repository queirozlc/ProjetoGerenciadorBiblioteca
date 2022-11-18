package projetobiblioteca.view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import projetobiblioteca.controller.CriarContaController;
import projetobiblioteca.controller.LoginController;
import projetobiblioteca.controller.MenuPrincipalController;
import projetobiblioteca.model.Funcionario;
import projetobiblioteca.model.Livro;

public class Main {

	public static void main(String[] args) throws IOException {
		char opcaoEscolhida, opcaoEscolhidaMenu = '0';
		Scanner scanner = new Scanner(System.in);
		LoginController loginController = new LoginController();
		CriarContaController criarContaController = new CriarContaController();
		MenuPrincipalController menuPrincipalController = new MenuPrincipalController();
		
		// Menu Login e Criar conta
		do {
			System.out.println("############# BEM-VINDO AO GERENCIADOR DE BIBLIOTECA #############");
			System.out.println("0 - Sair do programa.");
			System.out.println("1 - Fazer login.");
			System.out.println("2 - Criar Conta.");
			System.out.print("Escolha uma opção: ");
			opcaoEscolhida = scanner.next().charAt(0);
			scanner.nextLine();
			
			switch (opcaoEscolhida) {
			// Login
			case '1':

				System.out.println("============ MENU DE LOGIN ============");
				System.out.print("Informe um Login: ");
				String usuario = scanner.nextLine();
				System.out.print("Informe uma senha: ");
				String senha = scanner.nextLine();
				
				if (loginController.entrarNoSistema(usuario, senha)) {
					System.out.println("-----------------------------------------------------");
					System.out.println("Login feito com sucesso !");
					// muda opçao escolhida para sair do while
					opcaoEscolhida = '0';
					opcaoEscolhidaMenu = '1';
				
				}else if (usuario.isEmpty()) {
					System.out.println("Preencha o campo usuário !");
				
				}else if (senha.isEmpty()) {
					System.out.println("Preencha o campo senha !");
					
				}else if (usuario.isEmpty() && senha.isEmpty()) {
					System.out.println("Preencha todos os campos!");
				}
				
				break;
				
				
			// Criar conta
			case '2':
				// Entrada de dados
				System.out.println("============ MENU DE CADASTRO ============");
				try {
					Funcionario funcionario = (Funcionario) criarContaController.getHelper().buscarModelo();
					// Chamando metodo do controller
					if (criarContaController.criarConta(funcionario)) {
						System.out.println("-----------------------------------------------------");
						System.out.println("Funcionário cadastrado com sucesso !");
						// muda opçao escolhida para sair do while
						opcaoEscolhida = '0';
						opcaoEscolhidaMenu = '1';
					}
				} catch (InputMismatchException e) {
					System.out.println("Algum campo está inválido, tente novamente");
					scanner.nextLine();
				
				}
				break;

			case '0':
				System.out.println("Finalizando programa...");
				break;

			default:
				System.out.println("\nOpção inválida, por favor tente novamente !");
			}
		} while (opcaoEscolhida != '0');
		
		// Menu Principal
		
		
		while (opcaoEscolhidaMenu != '0') {
			System.out.println("############# MENU PRINCIPAL #############");
			System.out.println("0 - Sair do programa.");
			System.out.println("1 - Cadastrar Item.");
			System.out.println("2 - Cadastrar Usuário.");
			System.out.println("3 - Realizar Empréstimo.");
			System.out.println("4 - Emitir Relatórios.");
			System.out.println("6 - Fazer devolução.");
			System.out.print("Informe opção escolhida: ");
			opcaoEscolhidaMenu = scanner.next().charAt(0);
			
			switch (opcaoEscolhidaMenu) {
			
			case '1':
				// Definindo se o item cadastrado será livro ou se será um periódico
				char escolhaItem = '0'; 
				System.out.print("Informe 1 se o item for Livro ou 2 se for periódico: ");
				escolhaItem = scanner.next().charAt(0);
				
				if (escolhaItem == '1') {
					// busca livro
					Livro livro = (Livro) menuPrincipalController.getHelper().buscarModelo();
					
					// cadastra livro
					if (menuPrincipalController.cadastraItem(livro)) {
						System.out.println("-----------------------------------------------------");
						System.out.println("Livro Cadastrado com sucesso !");
					}
				
				}else if (escolhaItem == '2') {
					
				
				}else {
					System.out.println("Opção inválida, tente novamente");
					break;
				}
				break;
				
			case '2':
				
				break;
				
			case '0':
				System.out.print("Finalizando programa...");
				break;
			default:
				System.out.println("Essa opção é inválida, tente novamente.");
			}
		}
		scanner.close();
	}

}
