package .estoque;

public class Fornecedor {
	private int cnpj;
	private String nome;
	
	public Fornecedor(int cod, String nome) {
		cnpj = cod;
		this.nome = nome;
	}
}
