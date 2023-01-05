package estoque;

public class Fornecedor {
    private int cnpj;
    private String nome;
    private Produto produto;

    public Fornecedor(int Ocnpj, String Onome){
        cnpj = Ocnpj;
        nome = Onome;
    }
    public int getCnpj(){
        return cnpj;
    }
    public String getNome(){
        return nome;
    }
    public Produto getProduto(){
        return produto;
    }
    public void setProduto(Produto m){
        produto = m;
    }
}
