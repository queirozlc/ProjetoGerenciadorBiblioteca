package projetobiblioteca.view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import projetobiblioteca.controller.CriarContaController;
import projetobiblioteca.controller.LoginController;
import projetobiblioteca.model.Funcionario;

public class Main {

	public static void main(String[] args) throws IOException {
		char opcaoEscolhida;
		Scanner scanner = new Scanner(System.in);
		LoginController loginController = new LoginController();
		CriarContaController criarContaController = new CriarContaController();
		
		
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
				}
				
				break;

			case '2':
				// Entrada de dados
				System.out.println("============ MENU DE CADASTRO ============");
				try {
					System.out.print("Informe a matricula: ");
					int matricula = scanner.nextInt();
					
					System.out.print("Informe o nome: ");
					scanner.nextLine();
					String nome = scanner.nextLine();
					
					System.out.print("Informe o endereço: ");
					String endereco = scanner.nextLine();

					System.out.print("Informe a data de ingresso (formato dd/MM/yyyy): ");
					String dataIngresso = scanner.nextLine();

					System.out.print("Informe o login: ");
					String login = scanner.nextLine();

					System.out.print("Informe a senha: ");
					String senhaCadastro = scanner.nextLine();

					System.out.print("Informe o setor: ");
					String setor = scanner.nextLine();

					// Instanciando classe modelo
					Funcionario funcionario = new Funcionario(matricula, nome, endereco, dataIngresso, login,
							senhaCadastro, setor);

					// Chamando metodo do controller
					if (criarContaController.criarConta(funcionario)) {
						System.out.println("-----------------------------------------------------");
						System.out.println("Funcionário cadastrado com sucesso !");
						// muda opçao escolhida para sair do while
						opcaoEscolhida = '0';
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
		do {
			
			
			
		} while (opcaoEscolhida != '0');

		scanner.close();
	}

}
