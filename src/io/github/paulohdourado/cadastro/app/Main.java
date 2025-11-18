package io.github.paulohdourado.cadastro.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import io.github.paulohdourado.cadastro.model.Pessoa;
import io.github.paulohdourado.cadastro.model.Sexo;

public class Main {

	// Scanner único compartilhado por todos os métodos
	public static Scanner scan = new Scanner(System.in);
	// Lista que armazena as pessoas
	public static List<Pessoa> pessoas = new ArrayList<>();

	public static void main(String[] args) {
		
			
			int opcao = -1;
			
			do{

				//Método que exibe o Menu
				exibirMenu();
				
				
				//Após exibir o menu, o programa tenta executar a lista de opções
				try {
					
				opcao = scan.nextInt();
				scan.nextLine();
				
				switch (opcao) {
				case 1:
					
					Pessoa pessoa = Pessoa.lerDados(scan);
					
					if (pessoas.contains(pessoa)) {
						System.out.println("Erro: Uma pessoa com esse CPF já foi cadastrada.");
					} else {
						
						pessoas.add(pessoa);
						System.out.println(pessoa.getNome() + " Adicionado(a) com sucesso!");
					}
					break;
					
				case 2:
				
					System.out.println("\n------ LISTA DE PESSOAS CADASTRADAS ------\n");
					imprimirLista(pessoas);
					break;
					
				case 3:
					
					boolean cont = false;
					
					while(cont != true) {
						
						// Imprimir por sexo
						System.out.println("Informe o sexo: \n");
						
						System.out.println("[1] - Masculino");
						System.out.println("[2] - Feminino");
						
						try {
							
							int opcaoSexo = scan.nextInt();
							scan.nextLine();
							
							if(opcaoSexo == 1 || opcaoSexo == 2) {
															
								Sexo sexoSelecionado = Sexo.selecionarSexo(opcaoSexo);					
								imprimirLista(pessoas, sexoSelecionado);
								cont = true;	
								
							}// Fim do if 
							
							else {		
								System.out.println("Digite um número válido: ");								
							}
							
						} catch(java.util.InputMismatchException e) {
							
							e.printStackTrace();						
							System.out.println("Digite apenas 1 ou 2: ");
							
							scan.nextLine();
							cont = false;
							
						}//Fim do catch
						
					}//Fim do while
					
					break;
					
				case 4:
					
					System.out.println("Informe o CPF da pessoa que deseja remover (apenas números): ");
					
					String cpfRemover = scan.nextLine().trim();
					Pessoa pessoaRemover = encontrarPessoa(cpfRemover);
					excluirPessoa(pessoaRemover);
					
					break;
					
				case 5:
					
					System.out.println("\n------ LISTA ORDENADA POR NOME ------\n");
					imprimirListaOrdenadaPorNome(pessoas);
					break;
					
				case 6:
					
					System.out.println("\n------ LISTA ORDENADA POR IDADE ------\n");
					imprimirListaOrdenadaPorIdade(pessoas);	
					break;
					
				case 7:
					
					System.out.println("\n------ LISTA ORDENADA POR SEXO ------\n");
					imprimirListaOrdenadaPorSexo(pessoas);	
					break;
					
				case 8:
					
					System.out.println("\n------ LISTA ORDENADA POR NOME (LAMBDAS) ------\n");	
					imprimirListaOrdenadaPorNomeLambda(pessoas);					
					break;
					
				case 9:
					
					System.out.println("\n------ LISTA ORDENADA POR IDADE (LAMBDAS) ------\n");					
					imprimirListaOrdenadaPorIdadeLambda(pessoas);				
					break;
					
				case 10:
					
					System.out.println("\n------ LISTA ORDENADA POR SEXO (LAMBDAS) ------\n");				
					imprimirListaOrdenadaPorSexoLambda(pessoas);					
					break;
					
				case 0 :
					System.out.println("Finalizando programa...");
					break;
					
				default:
					System.out.println("Selecione uma opção válida.");
					break;
				}
				
				
				//Caso o usuário digite um caractere que não seja um número, o bloco catch captura a exceção 
				}catch(java.util.InputMismatchException e) {
					e.printStackTrace();
					System.out.println("Você deve digitar um número. Tente novamente: ");
					
					scan.nextLine(); //Limpa o Buffer
					//Rezeta a variável de opcao
					opcao = -1;
				}
				
			} while (opcao != 0);
			
		

		System.out.println("Programa encerrado com sucesso.");

	}

	public static void exibirMenu() {

		System.out.println("\n------ SISTEMA DE CADASTRO DE PESSOAS ------\n");

		System.out.println("[1] - Cadastrar Pessoa");
		System.out.println("[2] - Imprimir Tudo");
		System.out.println("[3] - Imprimir por Sexo");
		System.out.println("[4] - Excluir Pessoa");
		System.out.println("------------------------------------------------");
		System.out.println("[5] - Ordenar por Nome");
		System.out.println("[6] - Ordenar por Idade");
		System.out.println("[7] - Ordenar por Sexo");
		System.out.println("------------------------------------------------");
		System.out.println("[8] - Ordenar por Nome - Lambdas");
		System.out.println("[9] - Ordenar por Idade - Lambdas");
		System.out.println("[10] - Ordenar por Sexo - Lambdas");
		System.out.println("------------------------------------------------");
		System.out.println("[0] - Sair");
		System.out.println("\nEscolha uma opção: ");
	}

	public static Pessoa encontrarPessoa(String cpf) {

		for (Pessoa p : pessoas) {
			if (p.getCpf().equalsIgnoreCase(cpf)) {
				return p;
			}
		}
		return null;
	}

	public static void excluirPessoa(Pessoa pessoa) {

		if (pessoa == null) {
			System.out.println("O cpf informado não foi encontrado no cadastro.");
		} else {
			pessoas.remove(pessoa);
			System.out.println(pessoa.getCpf() + " removido com sucesso!");
		}
	}

	// Métodos de impressão (Quest.03 - Quest.04)
	// -----------------------------------------------------------------------------------

	// Imprime todos as informações da lista recebida como parâmetro (Quest.03)
	public static void imprimirLista(List<Pessoa> lista) {

		for (Pessoa p : lista) {
			p.imprimir();
		}
	}

	// Imprime todos as informações da lista recebida como parâmetro, filtrando por
	// sexo (Quest.04)
	public static void imprimirLista(List<Pessoa> lista, Sexo sexo) {
		for (Pessoa p : lista) {
			if (p.getSexo() == sexo) {
				p.imprimir();
			}
		}
	}

	// Métodos de ordenação com classes anônimas (Quest.5)
	// -----------------------------------------------------------------------------------

	// Imprime uma cópia da lista pessoas ordenada por nome
	public static void imprimirListaOrdenadaPorNome(List<Pessoa> lista) {

		Comparator<Pessoa> sortByName = new Comparator<>() {
			@Override
			public int compare(Pessoa o1, Pessoa o2) {
				return o1.getNome().compareToIgnoreCase(o2.getNome());
			}
		};

		List<Pessoa> listaOrdenadaPorNome = new ArrayList<Pessoa>(lista);

		listaOrdenadaPorNome.sort(sortByName);

		imprimirLista(listaOrdenadaPorNome);
	}

	// Imprime uma cópia da lista pessoas ordenada por idade
	public static void imprimirListaOrdenadaPorIdade(List<Pessoa> lista) {

		Comparator<Pessoa> sortByAge = new Comparator<>() {

			@Override
			public int compare(Pessoa o1, Pessoa o2) {
				return Integer.compare(o1.getIdade(), o2.getIdade());
			}
		};

		// Cria cópia da lista pessoas para não modificar a original
		List<Pessoa> listaOredenadaPorIdade = new ArrayList<>(lista);

		listaOredenadaPorIdade.sort(sortByAge);

		imprimirLista(listaOredenadaPorIdade);
	}

	// Imprime uma cópia da lista pessoas ordenada por Sexo
	public static void imprimirListaOrdenadaPorSexo(List<Pessoa> lista) {

		Comparator<Pessoa> sortBySex = new Comparator<>() {

			@Override
			public int compare(Pessoa o1, Pessoa o2) {
				return o1.getSexo().compareTo(o2.getSexo());
			}
		};

		List<Pessoa> listaOrdenadaPorSexo = new ArrayList<>(lista);

		listaOrdenadaPorSexo.sort(sortBySex);

		imprimirLista(listaOrdenadaPorSexo);
	}

	// Implementando métodos de ordenação com lambdas (quest.06)
	// ----------------------------------------------------------------------------------------------------

	// Lista ordenada por nome usando Lambda
	public static void imprimirListaOrdenadaPorNomeLambda(List<Pessoa> lista) {

		Comparator<Pessoa> sortByName = (n1, n2) -> n1.getNome().compareToIgnoreCase(n2.getNome());

		// Passando o Comparator<Pessoa> como argumento
		lista.stream().sorted(sortByName).forEach(e -> e.imprimir());
	}

	// Lista ordenada por idade usando Lambda
	public static void imprimirListaOrdenadaPorIdadeLambda(List<Pessoa> lista) {

		// Criando o Comparator diretamente dentro de .sorted()
		lista.stream().sorted((i1, i2) -> Integer.compare(i1.getIdade(), i2.getIdade())).forEach(e -> e.imprimir());
	}

	// Lista ordenada por Sexo usando Lambda
	public static void imprimirListaOrdenadaPorSexoLambda(List<Pessoa> lista) {

		lista.stream().sorted((s1, s2) -> s1.getSexo().compareTo(s2.getSexo())).forEach(e -> e.imprimir());
	}
}
