package .estoqueComProdutoPerecivel;

public class Fornecedor {
	
	private int codigo;
	private String nome;
	
	public Fornecedor(int cod, String nome) {
		codigo = cod;
		this.nome = nome;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getNome() {
		return nome;
	}
}
