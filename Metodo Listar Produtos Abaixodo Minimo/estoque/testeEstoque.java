package estoque;

import static org.junit.Assert.*;

import org.junit.Test;

public class testeEstoque {

    @Test 
    public void add_Produto(){
        Produto prod = new Produto(0, "teclado", 80, 100, 0);
        Estoque estoque = new Estoque(1);
        estoque.add_Produtos(prod);
        assertEquals(prod, estoque.getProduto(0));
    }

    @Test
    public void criar_Produto(){
        Estoque estoque = new Estoque(1);
        estoque.criar_Produto(0, "teclado", 80, 100, 0);
        assertEquals(estoque.getProduto(0).getCodigo(),0 );
        assertEquals(estoque.getProduto(0).getDescricao(),"teclado" );
        assertEquals(estoque.getProduto(0).getEstoque_Minimo(),0 );
        assertEquals(estoque.getProduto(0).getPreco_compra(),80, 0.001);
        assertEquals(estoque.getProduto(0).getPreco_venda(),100, 0.001 );
        assertEquals(estoque.getProduto(0).getQuantidade(),0 );
    }

    @Test
    public void testarCompra(){
        Produto prod = new Produto(0, "teclado", 80, 100, 0);
        prod.compra(10, 100);
        Estoque estoque = new Estoque(5);
        estoque.add_Produtos(prod);
        assertEquals(10, estoque.getProduto(0).getQuantidade());
    }

    @Test
    public void testarVenda(){
        Produto prod2 = new Produto(0, "teclado", 80, 100, 5);
        prod2.compra(10, 100);
        Estoque estoque2 = new Estoque(5);
        estoque2.add_Produtos(prod2);
        assertEquals(400 , estoque2.preco_venda(4, 0), 0.001 );
    }
    
    @Test
    public void testarQuantidade(){
        Produto prod3 = new Produto(0, "teclado", 80, 100, 5);
        prod3.compra(20, 100);
        Estoque estoque3 = new Estoque(5);
        estoque3.add_Produtos(prod3);
        estoque3.preco_venda(4,0);
        assertEquals(16, prod3.getQuantidade());
    }
}
