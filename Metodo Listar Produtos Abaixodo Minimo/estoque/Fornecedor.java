package estoque;

public class Fornecedor {
    private int cnpj;
    private String nome;
    private Produto produto;

    public Fornecedor(int Ocnpj){
        cnpj = Ocnpj;
    }

    public void setNome(String nome){
        this.nome = nome;
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
