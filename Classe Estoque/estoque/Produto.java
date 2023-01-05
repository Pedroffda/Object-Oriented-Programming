package estoque;

public class Produto {
    private int codigo;
    private String descricao;
    private double preco_compra;
    private double preco_venda;
    private int quantidade;
    private int estoque_minimo;

    public Produto(int codigo, String descricao, double preco_compra,
    double preco_venda, int estoque_minimo){
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco_compra = preco_compra;
        this.preco_venda = preco_venda;
        this.estoque_minimo = estoque_minimo;
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
    public int getEstoque_Minimo(){
        return estoque_minimo;
    }

    void compra(int quant, double val){
        quantidade += quant;
        preco_compra += val / quant ;
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

