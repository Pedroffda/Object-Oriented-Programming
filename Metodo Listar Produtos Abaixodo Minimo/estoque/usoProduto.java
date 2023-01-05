package estoque;

public class usoProduto {
    public static void main(String []args){

        Produto produto = new Produto(2022, "Notebook", 1000, 1500, 10, 2);
        Fornecedor fornecedor = new Fornecedor(7382, "Americanas");

        fornecedor.setProduto(produto);

        produto.compra(10, 1200);

        System.out.printf("Quantidade: %d\n", produto.getQuantidade());

        System.out.printf("Valor da venda: %.2f R$\n", produto.venda(18));

        System.out.printf("Quantidade: %d\n", produto.getQuantidade());

        System.out.printf("Codigo do Produto: %d\n", produto.getCodigo());

        System.out.printf("Descricao: %s\n", fornecedor.getProduto().getDescricao());

        System.out.printf("Nome da Fornecedora: %s\n", fornecedor.getNome());





    }
}
