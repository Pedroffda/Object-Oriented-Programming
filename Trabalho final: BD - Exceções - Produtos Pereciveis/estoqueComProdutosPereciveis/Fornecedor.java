package .estoqueComProdutosPereciveis;

import java.util.ArrayList;

public class Fornecedor {
	private int CNPJ;
	private String nome;
	private ArrayList<Produto> produtos = new ArrayList<>();


	public Fornecedor(int cod, String nome) { //cria um fornecedor para o produto
		CNPJ= cod;
		this.nome = nome;
	}
	
	public int getCNPJ() {
		return this.CNPJ;
	}
	
	public String getNome() {
		return this.nome;
	}

    public void setProdutos(ArrayList<Produto> p) {
        produtos = p;
    }

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	
}
