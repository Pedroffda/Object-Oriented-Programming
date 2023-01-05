package .estoqueComProdutosPereciveis;

public class ProdutoInexistente extends Exception{
	public ProdutoInexistente(int cod) {
		super("Produto Inexistente");
	}
}
