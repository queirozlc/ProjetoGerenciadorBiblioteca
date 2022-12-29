package projetobiblioteca.view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

import projetobiblioteca.controller.CriarContaController;
import projetobiblioteca.controller.LoginController;
import projetobiblioteca.controller.MenuPrincipalController;
import projetobiblioteca.model.Aluno;
import projetobiblioteca.model.Devolucao;
import projetobiblioteca.model.Funcionario;
import projetobiblioteca.model.Livro;
import projetobiblioteca.model.Periodico;
import projetobiblioteca.model.Professor;

public class Main {

	private static final Locale localePtBr = new Locale("pt", "BR");
	private static Scanner scanner = new Scanner(System.in).useLocale(localePtBr);
	private static Funcionario funcionarioLogado;

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

					if (loginController.entrarNoSistema(funcionario)) {
						// muda opçao escolhida para sair do while
						opcaoEscolhida = '0';
						opcaoEscolhidaMenu = '1';

						// Seta atributo para guardar funcionario logado
						funcionarioLogado = loginController.getFuncionarioDAO()
								.buscaUsuarioPorLoginESenha(funcionario.getLogin(), funcionario.getSenha());
						System.out.println("-----------------------------------------------------");
						System.out.println("Login feito com sucesso !");

					}
					break;

				// Criar conta
				case '2':
					// Entrada de dados
					System.out.println("============ MENU DE CADASTRO ============");
					funcionario = (Funcionario) criarContaController.getHelper().buscarModelo();

					// Chamando metodo do controller
					if (criarContaController.criarConta(funcionario)) {
						// muda opçao escolhida para sair do while
						opcaoEscolhida = '0';
						// Seta atributo para guardar funcionario logado
						funcionarioLogado = funcionario;
						System.out.println("-----------------------------------------------------");
						System.out.println("Funcionário cadastrado com sucesso !");
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
				System.out.println("5 - Fazer devolução.");
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
							Livro livro = (Livro) menuPrincipalController.getHelper().buscarModeloLivro();

							// cadastra livro
							if (menuPrincipalController.cadastraItem(livro)) {
								System.out.println("-----------------------------------------------------");
								System.out.println("Livro Cadastrado com sucesso !");
							}

							// Escolha de periódico
						} else if (escolhaItem == '2') {

							// busca periodico
							Periodico periodico = menuPrincipalController.getHelper().buscarModeloPeriodico();

							if (menuPrincipalController.cadastraItem(periodico)) {
								System.out.println("-----------------------------------------------------");
								System.out.println("Periódico Cadastrado com sucesso !");
							}

						} else {
							System.out.println("Opção inválida, tente novamente");
							break;
						}
						break;

					// Cadastrando usuário
					case 2:
						// Valida o usuário a ser cadastrado
						int escolhaUsuario;

						System.out.print("Digite 1 se o usuário for professor ou 2 se o usuário for aluno: ");
						escolhaUsuario = scanner.nextInt();
						scanner.nextLine();

						// cadastro de professor
						if (escolhaUsuario == 1) {

							// busca modelo da tela
							Professor professor = (Professor) menuPrincipalController.getHelper()
									.buscarModeloUsuario(escolhaUsuario);

							if (menuPrincipalController.cadastraUsuario(professor)) {
								System.out.println("-----------------------------------------------------");
								System.out.println("Professor Cadastrado com sucesso !");
							}

						} else if (escolhaUsuario == 2) {

							Aluno aluno = (Aluno) menuPrincipalController.getHelper()
									.buscarModeloUsuario(escolhaUsuario);

							if (menuPrincipalController.cadastraUsuario(aluno)) {
								System.out.println("-----------------------------------------------------");
								System.out.println("Aluno Cadastrado com sucesso !");
							}
						}
						break;

					// Emprestimo
					case 3:
						System.out.print("Informe 1 se o empréstimo for para professor ou 2 se for para aluno: ");
						int escolhaUsuarioEmprestimo = scanner.nextInt(), idItemEmprestimo = 0;
						scanner.nextLine();

						// Professor
						if (escolhaUsuarioEmprestimo == 1) {
							// Lista todos os usuários professores para escolher a matrícula
							menuPrincipalController.getHelper().listarUsuariosCadastrados(escolhaUsuarioEmprestimo);
							System.out.print("Informe a matricula do professor: ");
							int matricula = scanner.nextInt();
							scanner.nextLine();

							// Lista os livros ou periódicos cadastrados
							System.out.print("Informe 1 se o item do empréstimo for um livro ou 2 se for periódico: ");
							int escolhaItemEmprestimo = scanner.nextInt();
							scanner.nextLine();

							if (escolhaItemEmprestimo == 1 || escolhaItemEmprestimo == 2) {
								idItemEmprestimo = menuPrincipalController.getHelper()
										.listaItensCadastrados(escolhaItemEmprestimo);
							} else {
								System.out.println("Opção inválida, tente novamente!");
							}

							if (menuPrincipalController.cadastraEmprestimo(funcionarioLogado.getMatricula(), matricula,
									escolhaUsuarioEmprestimo, idItemEmprestimo)) {
								System.out.println("-----------------------------------------------------");
								System.out.println("Emprestimo cadastrado Cadastrado com sucesso !");
							}

							// Aluno
						} else if (escolhaUsuarioEmprestimo == 2) {
							// Lista todos os usuários alunos para escolher a matrícula
							menuPrincipalController.getHelper().listarUsuariosCadastrados(escolhaUsuarioEmprestimo);
							System.out.print("Informe a matrícula do aluno: ");
							int matriculaAluno = scanner.nextInt();
							scanner.nextLine();

							// Lista os livros ou periódicos cadastrados
							System.out.print("Informe 1 se o item do empréstimo for um livro ou 2 se for periódico: ");
							int escolhaItemEmprestimo = scanner.nextInt();
							scanner.nextLine();

							if (escolhaItemEmprestimo == 1 || escolhaItemEmprestimo == 2) {
								idItemEmprestimo = menuPrincipalController.getHelper()
										.listaItensCadastrados(escolhaItemEmprestimo);
							} else {
								System.out.println("Opção inválida, tente novamente!");
							}

							// cadastra emprestimo
							if (menuPrincipalController.cadastraEmprestimo(funcionarioLogado.getMatricula(),
									matriculaAluno,
									escolhaUsuarioEmprestimo, idItemEmprestimo)) {
								System.out.println("-----------------------------------------------------");
								System.out.println("Emprestimo cadastrado Cadastrado com sucesso !");
							}
						} else {
							System.out.println("Essa não é uma opção válida, tente novamente!");
						}
						break;

					// Emissão de relatórios
					case 4:
						int escolhaRelatorio;

						System.out.println("################### EMISSÃO DE RELATÓRIOS ###################");
						System.out.println("1 - Relatório de Livros cadastrados.");
						System.out.println("2 - Relatório de Periódicos cadastrados.");
						System.out.println("3 - Relatório de empréstimos feitos.");
						System.out.println("4 - Relatório de Funcionários cadastrados.");
						System.out.println("5 - Relatório de Alunos cadastrados.");
						System.out.println("6 - Impressão de multa individual.");
						System.out.print("Informe a opção que você deseja: ");
						escolhaRelatorio = scanner.nextInt();
						scanner.nextLine();

						if (menuPrincipalController.gerarRelatorio(escolhaRelatorio)) {
							System.out.println("-------------------------------------------");
							System.out.println("Relatório gerado com sucesso na pasta 'Downloads'!");

						} else if (escolhaRelatorio < 1 || escolhaRelatorio > 6) {
							System.out.println("Opção inválida, tente novamente !");
						}

						break;

					case 5:
						System.out.println("################### Devolução de Itens ###################");

						Devolucao devolucao = menuPrincipalController.getHelper().buscarModeloDevolucao();

						if (menuPrincipalController.realizaDevolucao(devolucao)) {
							System.out.println(
									"------------------------------------------------------------------------");
							System.out.println("Devolução realizada com sucesso !");

						}
						break;

					case 0:
						System.out.print("Finalizando programa...");
						break;
					default:
						System.out.println("Essa opção é inválida, tente novamente.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Valor inválido para opção escolhida");
				scanner.nextLine();

			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
				scanner.nextLine();
			}

		} while (opcaoEscolhidaMenu != 0);

		scanner.close();
	}

	public static Scanner getScanner() {
		return scanner;
	}

	public static Funcionario getFuncionarioLogado() {
		return funcionarioLogado;
	}

}
