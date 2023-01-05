package .estoqueComProdutosPereciveis;
 
import java.util.Date;

public class Lote {
	private int quant=0;
	private Date validade;
	private int idproduto; 
	private Estoque estoque = new Estoque();

	
	public Lote(int quant, Date validade, int cod) {
		this.quant = quant;
		this.validade = validade;
		this.idproduto = cod;
	}

	public int getQuant() {
		
		return quant;
	}
	public void setQuant(int quant) {
		this.quant = quant;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	
	public int getIDProduto() {
		return idproduto;
	}
	
	
}
