package io.github.paulohdourado.cadastro.model;

public enum Sexo {
	MASCULINO(1, "Masculino"), 
	FEMININO(2, "Feminino");

	private int id;
	private String nomeFormatado;

	private Sexo(int id, String nomeFormatado) {
		this.id = id;
		this.nomeFormatado = nomeFormatado;
	}

	public int getId() {
		return id;
	}

	public String getNomeFormatado() {
		return nomeFormatado;
	}
	

	public static Sexo selecionarSexo(int opcao) {

		for (Sexo s : Sexo.values()) {
			if (s.getId() == opcao) {
				return s;
			}
		}
		return null;
	}

}
