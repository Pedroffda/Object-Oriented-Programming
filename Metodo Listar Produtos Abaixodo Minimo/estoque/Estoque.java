package estoque;

public class Estoque {
    private int cont;
    private Produto[] produtos = new Produto[10];

    public void incluir(Produto prod){
            produtos[cont++] = prod;
    }
    
    public Produto getProduto(int c){
        if(c<cont){
            return produtos[c];
        }
        else return null;
        
    }
    
    public void comprar(int conta, int quantidade,double preco_compra ){
        Produto p = pesquisar(conta);
        if(p != null){
            p.compra(quantidade, preco_compra);
        }
    }
    
    public double vender(int conta, int quantidade){
        double valor;
        Produto p = pesquisar(conta);
        if(p != null){
            valor = p.venda(quantidade);
        }
        else valor = 0;
        return valor;
    }
    
    public Produto pesquisar(int c){
        for(int i=0; i < cont; i++){
            if(produtos[i].getCodigo() == c){
                return produtos[i];
            }
        }
        return null;
    }

    public Produto[] estoqueAbaixoDoMinimo(){
        Produto abaixoDoMinimo[] = new Produto[cont];
        int pos=0;
        for(int i=0; i < cont; i++){
            if(produtos[i].getQuantidade() < produtos[i].getEstoqueMinimo()){
                abaixoDoMinimo[pos++] = produtos[i];
            }
        }
        return abaixoDoMinimo;
    }
    
} 
