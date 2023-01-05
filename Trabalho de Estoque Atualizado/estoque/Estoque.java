package .estoque;

import java.util.ArrayList;

public class Estoque {
	
	private int cont=0;
	private Produto produtos[] = new Produto[10];
	
	public void incluir(Produto p) {
		Produto c = pesquisar(p.getCodigo());	
		if(c == null) {
			produtos[cont++] = p;
		}
	}
	
	public void comprar(int cod, int quant, double preco) {
        Produto p = pesquisar(cod);
        if(p != null){
            p.compra(quant, preco);
        }
	}
	
	public double vender(int cod, int quant) {
        double valor=0;
        Produto p = pesquisar(cod);
        if(p != null){
            valor = p.venda(quant);
        }
        return valor;
	}
	
	public ArrayList<Produto> estoqueAbaixoDoMinimo(){
		ArrayList<Produto> abaixoDoMinimo = new ArrayList<Produto>();
		int pos = 0;
		for(int i=0 ; i < cont; i++){
			if(produtos[i].getQuantidade() < produtos[i].getEstoqueMinimo()) {
				abaixoDoMinimo.add(produtos[i]);
				pos++;
			}
		}
		if (pos > 0) {
			return abaixoDoMinimo;
		}
		return null;
	}
	
	public Produto pesquisar(int cod) {
		for(int i=0; i < cont; i++){
			if(produtos[i].getCodigo() == cod) {
				return produtos[i];
			}	
		}
		return null;
	}
}
