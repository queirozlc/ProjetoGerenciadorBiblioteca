package projetobiblioteca.view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import projetobiblioteca.controller.CriarContaController;
import projetobiblioteca.controller.LoginController;
import projetobiblioteca.controller.MenuPrincipalController;
import projetobiblioteca.model.Funcionario;
import projetobiblioteca.model.Livro;
import projetobiblioteca.model.Periodico;

public class Main {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		Funcionario funcionario = null;
		char opcaoEscolhida;
		int opcaoEscolhidaMenu = -1;
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

				funcionario = (Funcionario) loginController.getHelper().buscarModelo();

				if (loginController.entrarNoSistema(funcionario.getLogin(), funcionario.getSenha())) {
					System.out.println("-----------------------------------------------------");
					System.out.println("Login feito com sucesso !");
					// muda opçao escolhida para sair do while
					opcaoEscolhida = '0';
					opcaoEscolhidaMenu = '1';
				} 
				
				loginController.getHelper().validaCampos(funcionario.getLogin(), funcionario.getSenha());
				break;

			// Criar conta
			case '2':
				// Entrada de dados
				System.out.println("============ MENU DE CADASTRO ============");
				funcionario = (Funcionario) criarContaController.getHelper().buscarModelo();

				// Chamando metodo do controller
				if (criarContaController.criarConta(funcionario)) {
					System.out.println("-----------------------------------------------------");
					System.out.println("Funcionário cadastrado com sucesso !");

					// muda opçao escolhida para sair do while
					opcaoEscolhida = '0';
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

		do {
			try {
				System.out.println("############# MENU PRINCIPAL #############");
				System.out.println("0 - Sair do programa.");
				System.out.println("1 - Cadastrar Item.");
				System.out.println("2 - Cadastrar Usuário.");
				System.out.println("3 - Realizar Empréstimo.");
				System.out.println("4 - Emitir Relatórios.");
				System.out.println("6 - Fazer devolução.");
				System.out.print("Informe opção escolhida: ");
				opcaoEscolhidaMenu = scanner.nextInt();
				scanner.nextLine();
				switch (opcaoEscolhidaMenu) {

				case 1:
					// Definindo se o item cadastrado será livro ou se será um periódico
					char escolhaItem = '0';
					System.out.print("Informe 1 se o item for Livro ou 2 se for periódico: ");
					escolhaItem = scanner.next().charAt(0);
					scanner.nextLine();
					
					// Escolha de livro
					if (escolhaItem == '1') {
						// busca livro
						Livro livro = (Livro) menuPrincipalController.getHelper().buscarModelo();

						// cadastra livro
						if (menuPrincipalController.cadastraItem(livro)) {
							System.out.println("-----------------------------------------------------");
							System.out.println("Livro Cadastrado com sucesso !");
						}

						// Escolha de periódico
					} else if (escolhaItem == '2') {
						
						// busca periodico
						Periodico periodico = menuPrincipalController.getHelper().buscarModeloPeriodico();
						
						if(menuPrincipalController.cadastraItem(periodico)) {
							System.out.println("-----------------------------------------------------");
							System.out.println("Periódico Cadastrado com sucesso !");
						}
						
					} else {
						System.out.println("Opção inválida, tente novamente");
						break;
					}
					break;

				case 2:

					break;

				case 0:
					System.out.print("Finalizando programa...");
					break;
				default:
					System.out.println("Essa opção é inválida, tente novamente.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Valor inválido para opção escolhida");

			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
			}

		} while (opcaoEscolhidaMenu != 0);
		
		scanner.close();
	}

	public static Scanner getScanner() {
		return scanner;
	}
	
}
