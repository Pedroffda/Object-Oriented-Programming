package .estoqueComProdutosPereciveis;

import java.util.Date;
import java.util.ArrayList;

public class Produto {
	protected int codigo; 
	protected String descricao;
	protected double precoDeCompra=0;
	protected double precoDeVenda;
	protected int quantidade;
	protected int estoqueMinimo;
	protected Fornecedor forn;
	protected double lucro;	

	public Produto(int cod, String desc, int min, double l, Fornecedor forn) { // met. construtor
		this.codigo= cod;
		this.descricao = desc;
		estoqueMinimo = min;
		this.lucro = l;
		this.forn = forn;
	}
	  public Produto(int cod, String descricao, int quantidade, double lucro, int estoqueMinimo,
				double precoDeCompra, double precoDeVenda, Fornecedor fornec, String descriminante) {
			this.codigo= cod;
			this.descricao = descricao;
			this.quantidade = quantidade;
			this.estoqueMinimo = estoqueMinimo;
			this.lucro = lucro;
			this.forn = fornec;
			this.precoDeCompra = precoDeCompra;
			this.precoDeVenda = precoDeVenda;

		}
	
	//getters 
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public double getPrecoDeCompra() {
		return precoDeCompra;
	}
	
	public double getPrecoDeVenda() {
		return precoDeVenda;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	
	public int getEstoqueMinimo() {
		return estoqueMinimo;
	}
	
	public double getLucro() {
		return lucro;
	}
	
	public Fornecedor getFornecedor() {
		return forn;
	}

	//metodo compra e venda
	public void compra(int quant, double val) {
		precoDeCompra= ((quantidade*precoDeCompra) + (quant*val))/ (quantidade+quant);
		quantidade+=quant;
		precoDeVenda = precoDeCompra * (1+lucro); // 2 * (1+0.5) = 3
	}
	
	public double venda(int quant) {
		double venda;
		if(quantidade>quant) {
			venda= precoDeVenda* quant;
			quantidade= quantidade - quant;
			return venda;
		}else {
			return -1;
		}
	}
}
