package .estoqueComProdutoPerecivel;

import java.util.Date;

public class Lote {
	private int quantidade;
	private Date validade;
	
	public Lote(int quant, Date validade) {
		quantidade = quant;
		this.validade = validade;
	}
	
	public int getQuant() {
		return quantidade;
	}
	public void setQuantidade(int valor) {
		quantidade = valor;
	}
	public Date getValidade() {
		return validade;
	}
//	public void setValidade(Date data) {
//		validade = data;
//	}

}
