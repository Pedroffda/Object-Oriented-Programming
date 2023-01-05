package estoque;

public class Produto {
    private int codigo;
    private String descricao;
    private double preco_compra;
    private double preco_venda;
    private int quantidade;
    private int estoque_minimo;

    public Produto(int  Ocodigo, String Adescricao, double Opreco_compra,
    double Opreco_venda, int Aquantidade, int Oestoque_minimo){
        codigo = Ocodigo;
        descricao = Adescricao;
        preco_compra = Opreco_compra;
        preco_venda = Opreco_venda;
        quantidade = Aquantidade;
        estoque_minimo = Oestoque_minimo;
    }
    
    public int getCodigo() {
        return codigo;
    }
    public String getDescricao(){
        return descricao;
    }
    public double getPreco_compra(){
        return preco_compra;
    }
    public double getPreco_venda(){
        return preco_venda;
    }
    public int getQuantidade(){
        return quantidade;
    } 
    public int getEstoque_minimo(){
        return estoque_minimo;
    }

    void compra(int quant, double val){
        quantidade += quant;
        preco_compra = val;
    }

    double venda(int quant){
        double valor_venda;
        if (quantidade - quant >= estoque_minimo){
            quantidade -= quant;
            valor_venda = quant * preco_venda;
            return valor_venda;
        }
        else{
            return 0;
        }
    }
}
