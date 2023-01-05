# Trabalho atualizado!

* Crie um sistema simples para Controle de Estoque. 

* Esse sistema deve possuir uma classe Produto, representando itens que podem estar a venda, por exemplo, em supermercados. Produtos devem ter código, descrição, preço de compra, preço de venda, lucro, quantidade, estoque mínimo. Seu construtor deve ter a seguinte assinatura:

```
public Produto(int cod, String desc, int min, double lucro, Fornecedor forn);
```

* Produtos são fornecidos por um Fornecedor, que tem CNPJ e nome. Seu construtor deve ter a seguinte assinatura:
```
public Fornecedor(int cod, String nome);
```
* Produtos devem ter o método “void compra(int quant, double val)”, que incrementa a quantidade e atualiza o atributo preço de compra. A atualização do preço de compra deve ser uma ponderação entre a quantidade existente e o preço de compra atual e a quantidade comprada e o novo preço. Exemplo: se havia 10 produtos com preço de compra R$2,5 e compramos mais 10 unidades do produto a R$7,50, então o novo preço deve ser (10 * 2.5 + 10 *. 7.5) / 20 = R$5.0. 

* O preço de venda deve ser um percentual acima do preço de compra. Esse percentual será definido na criação do produto e será um atributo da classe, denominado lucro. Toda vez que o preço de compra atualizar, devemos atualizar também o preço de venda, com o lucro aplicado ao preço de compra. Exemplo: se o preço de compra de um produto é R$4 e o lucro é 50% (0.5), então o seu preço de venda deveria ser R$6.

* A operação de venda deve ser feita pelo preço de venda atual, retornando o valor (quantidade vendida x preço de venda) e decrementando a quantidade de itens do produto. A venda só pode ser realizada se houver itens no estoque. Se for tentando uma venda acima da quantidade, a venda não deve ser realizada. Como sinalização do problema, o método deve retornar o valor -1. 

* Devemos implementar a classe Estoque. Ela será responsável por conter uma estrutura de dados de Produtos (use a que vc quiser!). Uma descrição dos seus métodos é listada abaixo. 

Métodos Classe Estoque

```
public class Estoque {

   public void incluir(Produto p);

  public void comprar(int cod, int quant, double preco);

  public double vender(intcod, intquant);

  public ArrayList<Produto> estoqueAbaixoDoMinimo()

}
```

* O método listarProdutosAbaixoDoMinimo() deve devolver um ARRAYLIST de produtos cuja quantidade está abaixo do estoque mínimo para o produto.

Foi atualizado o teste a ser usado para esse trabalho. 
