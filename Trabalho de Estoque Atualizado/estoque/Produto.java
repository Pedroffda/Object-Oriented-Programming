package .estoque;

public class Produto {
	private int codigo;
	private String descricao;
	private int estoque_minimo;
	private double lucro;
	private Fornecedor fornecedor;
	private int quantidade;
	private String nome;
	private double preco_compra, preco_venda;
	
	public Produto(int cod, String desc, int min, double lucro, Fornecedor forn) {
		codigo = cod;
		descricao = desc;
		estoque_minimo = min;
		this.lucro = lucro;
		fornecedor = forn;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public int getEstoqueMinimo() {
		return estoque_minimo;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public double getLucro() {
		return lucro;
	}
	
	public double getPrecoDeCompra() {
		return preco_compra;
	}
	
	public double getPrecoDeVenda() {
		return preco_venda;
	}
	
	public void compra(int quant, double val) {
		preco_compra = ((quantidade * preco_compra) + (quant * val))/ (quantidade + quant);
		quantidade+=quant;
		preco_venda = preco_compra * (1 + lucro);
	}
	
	public double venda(int quant) {
		double valor;
		if(quant > 0) {
			if (quantidade >= quant){
				valor = quant * preco_venda;
				quantidade -= quant;
				return valor;
			}
		}
		return -1;
	}
	
}
