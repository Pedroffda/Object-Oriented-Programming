package estoque;

public class Estoque {
    private Produto[] produtos;
    private int cont=0;
    private int tam;

    public Estoque(int t){
        tam = t;
        produtos = new Produto[tam];
    }
    
    public void add_Produtos(Produto prod){
        if(cont<tam){
            produtos[cont++] = prod;
        }
    }

    public void criar_Produto(int codigo, String descricao, double preco_compra,
    double preco_venda, int estoque_minimo){
        if(cont<tam){
            produtos[cont++] = new Produto(codigo, descricao, preco_compra,
             preco_venda, estoque_minimo);
        }
    }
    
    public Produto getProduto(int c){
        if(c<tam){
            return produtos[c];
        }
        else return null;
        
    }
    
    public void compra(double preco_compra, int quantidade, int conta){
        Produto p = mostrar_Codigo(conta);
        if(p != null){
            p.compra(quantidade, preco_compra);
        }
    }
    
    public double preco_venda(int quantidade, int conta){
        double valor;
        Produto p = mostrar_Codigo(conta);
        if(p != null){
            if(p.getQuantidade() - quantidade >= p.getEstoque_Minimo()){
                valor = p.venda(quantidade);
            }
            else valor = 0;
        }
        else valor = 0;
        return valor;
    }
    
    public Produto mostrar_Codigo(int c){
        for(int i=0; i < cont; i++){
            if(produtos[i].getCodigo() == c){
                return produtos[i];
            }
        }
        return null;
    }
    
} 
