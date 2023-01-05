package .estoqueComProdutosPereciveis;

public class FornecedorInexistente extends Exception {
	public FornecedorInexistente(int cnpj) {
		super("Fornecedor Inexistente");
	}
}
