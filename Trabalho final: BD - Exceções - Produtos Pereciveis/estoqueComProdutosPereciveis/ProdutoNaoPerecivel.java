package .estoqueComProdutosPereciveis;

public class ProdutoNaoPerecivel extends Exception{
	public ProdutoNaoPerecivel() {
		super("Produto não perecivel");
	}
}
