package estoque;

public class Produto {
    private int codigo;
    private String descricao;
    private double preco_compra;
    private double preco_venda=0;
    private int quantidade;
    private int estoque_minimo;
    private Fornecedor fornecedor;

    public Produto(int codigo, String descricao, int estoque_minimo,
    double preco_venda, Fornecedor fornecedor){
        this.codigo = codigo;
        this.descricao = descricao;
        this.estoque_minimo = estoque_minimo;
        this.preco_venda = preco_venda;
        this.fornecedor = fornecedor;
    }
    
    public int getCodigo() {
        return codigo;
    }
    public String getDescricao(){
        return descricao;
    }
    public double getPrecoDeCompra(){
        return preco_compra;
    }
    public double getPrecoDeVenda(){
        return preco_venda;
    }
    public int getQuantidade(){
        return quantidade;
    } 
    public int getEstoqueMinimo(){
        return estoque_minimo;
    }
    public Fornecedor getFornecedor(){
        return fornecedor;
    }


    void compra(int quant, double val){
        quantidade += quant;
        preco_compra += val / quant ;
    }

    double venda(int quant){
        double valor_venda;
            quantidade -= quant;
            valor_venda = quant * preco_venda;
            return valor_venda;
    }

}

