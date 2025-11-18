package io.github.paulohdourado.cadastro.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import io.github.paulohdourado.cadastro.interfaces.Impressao;

public class Pessoa implements Impressao {

	private String nome;
	private int idade;
	private String cpf;
	private Sexo sexo;

	// Pessoa possui uma lista de telefones
	private List<Telefone> telefones;

	public Pessoa(String nome, int idade, String cpf, Sexo sexo) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.sexo = sexo;

		// Lista é criada dentro de pessoa, e é destruida se pessoa também for
		// (Composição).
		this.telefones = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void adicionarTelefone(String codigoArea, String numero) {

		// Cria o objeto Telefone dentro de Pessoa
		Telefone novoTelefone = new Telefone(codigoArea, numero);

		// Verificar se é null

		this.telefones.add(novoTelefone);
	}

	@Override
	public String toString() {
		return 	"\n-----------------------------" + 
				"\nCADASTRO" + 
				"\nNome: " + this.nome +
				"\nIdade: " + this.idade +
				"\nSexo: " + this.sexo.getNomeFormatado() + 
				"\nTelefone(s): " + this.telefones +
				"\n-----------------------------\n";
	}

	// Este método deve imprimir todas as informações (atributos) da classe
	@Override
	public void imprimir() {
		System.out.println(toString());
	}

	public static Pessoa lerDados(Scanner scan) {
		System.out.println("\n------ Ler dados de Pessoa ------\n");

		System.out.println("Digite o nome: ");
		String nomeScan = scan.nextLine();

		//---------------------------------------------------------------------------------
		
		boolean validadorIdade = false;
		int idadeScan = 0;
		
		while(validadorIdade != true) {
			
			try {
				
				System.out.println("Informe a idade: ");
				int idadeScanTry = scan.nextInt();
				scan.nextLine();
				
				if(idadeScanTry > 0) {
					idadeScan = idadeScanTry;
					validadorIdade = true;
				
				}else {
					System.out.println("Idade inválida, tente novamente: ");
					validadorIdade = false;
				}
				
				
			}catch(java.util.InputMismatchException e) {
				
	//			e.printStackTrace();
				System.out.println("A idade informada deve ser um número.");
				
				scan.nextLine();
				validadorIdade = false;
				
			}//Fim do catch

		}//Fim do while
		
		//---------------------------------------------------------------------------------
	
		System.out.println("Informe o cpf sem acentuação (ex.: 111 222 333 44): ");
		String cpfScan = scan.nextLine().trim();

		//---------------------------------------------------------------------------------
		
		boolean validadorSexo = false;
		Sexo sexoInformado = null;
		
		while(validadorSexo != true) {
			
			System.out.println("Informe o sexo: \n");
			System.out.println("[1] - Masculino");
			System.out.println("[2] - Feminino");

			try {
				int opcaoSexo = scan.nextInt();
				scan.nextLine();
						
				if(opcaoSexo == 1 || opcaoSexo == 2) {				
					Sexo sexoInformadoTry = Sexo.selecionarSexo(opcaoSexo);
					sexoInformado = sexoInformadoTry;
					validadorSexo = true;
					
				}else {
					System.out.println("Numero não corresponde, digite 1 ou 2.");
					validadorSexo = false;
					
				}
				
			}catch(java.util.InputMismatchException e) {
				System.out.println("Informe a opção correta [1] - Masculino ou [2] - Feminino.");
				
				scan.nextLine();
				validadorIdade = false;
				
			}//Fim do catch
	
		}//Fim do while
		
		//---------------------------------------------------------------------------------

		boolean validadorTelefone = false;
		int qddTelefones = 0;
		
		while(validadorTelefone != true) {
			
			try {
				
				System.out.println("Adicionar telefone(s): \n");
				

				System.out.println("Quantos telefones deseja adicionar? ");
				int qddTelefonesTry = scan.nextInt();
				scan.nextLine();
				
				if(qddTelefonesTry > 0) {
					qddTelefones = qddTelefonesTry;
					validadorTelefone = true;
				}
				
				else {
					System.out.println("Você deve informar ao menos um telefone para contato.");
					validadorTelefone = false;
					
				}
				
			}catch(java.util.InputMismatchException e) {
				System.out.println("Entrada inválida, apenas números são permitidos.");
				
				scan.nextLine();
				validadorTelefone = false;
			}//Fim do catch
		
		}//fim do while

		
		Pessoa novaPessoa = new Pessoa(nomeScan, idadeScan, cpfScan, sexoInformado);

		for (int i = 0; i < qddTelefones; i++) {
			System.out.println("Digite o " + (i + 1) + "° telefone. \n");
			System.out.println("Informe o DDD: ");

			String ddd = scan.nextLine();

			System.out.println("Informe o número: ");
			String numTelefone = scan.nextLine();

			novaPessoa.adicionarTelefone(ddd, numTelefone);
		}

		return novaPessoa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf);
	}

}
