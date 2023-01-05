# Trabalho final: BD, Exceções, Produtos Perecíveis	

* Chegamos no nosso ultimo trabalho. Agora o negócio ficou mais animado. Vamos salvar e recuperar tudo do banco de dados e ainda controlando as exceções que podem acontecer!

Nossa interface de estoque é definida abaixo.

```
public interface InterfaceEstoque {

 public void incluir(Produto p) throws ProdutoJaCadastrado, DadosInvalidos;

  public void comprar(int cod, int quant, double preco, Date val)  throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel;

public double vender(int cod, int quant) throws ProdutoInexistente, ProdutoVencido, DadosInvalidos;

public Produto pesquisar (int cod) throws ProdutoInexistente;

public ArrayList<Produto> estoqueAbaixoDoMinimo();

public ArrayList<Produto> estoqueVencido();

public int quantidadeVencidos(int cod) throws ProdutoInexistente;

}
```

* Algumas descrições de exceções para guiar o trabalho de todos:

+ Produto: Código, lucro, preço compra, preço venda, quantidade, estoque mínimo, não podem ser negativos.

  + Ainda Produto: Descrição não pode ser nula nem vazio (“”).

  + Em Compra e Venda a quantidade e preço não podem ser negativos.

  + Data não pode ser nula se envolver um Produto Perecível, assim como se não for perecível não pode ter validade enviada como parâmetro para a operação.

  + Venda de produtos com quantidade maior que o existente no estoque deve gerar dados inválidos.

Nosso teste de correção está aqui junto a tarefa. 
