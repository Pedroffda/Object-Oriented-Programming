package .estoqueComProdutosPereciveis;

public class ProdutoJaCadastrado extends Exception {
	
	public ProdutoJaCadastrado(Produto p) {
		super("Produto de codigo " + p + "ja foi cadastrado");
	}
}
