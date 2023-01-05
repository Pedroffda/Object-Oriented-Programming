package .estoqueComProdutoPerecivel;

import java.util.Date;
import java.util.ArrayList;

public class ProdutoPerecivel extends Produto{
	
	ArrayList<Lote> lote = new ArrayList<Lote>();
	public ProdutoPerecivel(int cod, String desc, int min, double l, Fornecedor forn){
		super(cod, desc, min, l, forn);
		lote = new ArrayList<Lote>();
	}
	
	// pode-se ser adicionado varios lotes do mesmo produto
	// dessa forma, quando os produtos tiverem a mesma data de validade,
	// esses produtos devem ser adicionados ao mesmo lote (adicionando a quantidade no lote pre-existente)
	// caso seja adicionado o mesmo produto com data de validade diferente, entao
	// um novo lote deve ser criado para armazenar essas novas caracteristicas 
	
	public void compra(int quant, double val, Date validade) {
		setPrecoDeCompra((((getQuantidade() * getPrecoDeCompra()) + (quant * val)) / (getQuantidade() + quant)));
		setPrecoDeVenda(getPrecoDeCompra() * (1 + getLucro()));
		// super.compra(quant, val);
		
		boolean mesmaData = false;
		
		for(Lote umLote : lote ) {
			if(umLote.getValidade().compareTo(validade)==0) { // caso a validade seja a mesma, adiciona-se no lote pre-existente
				mesmaData = true;
				setQuantidade(getQuantidade() + quant);
				break;
			}
		}
		if(!mesmaData) { // caso contrario, cria-se um novo lote
			lote.add(new Lote(quant, validade));
			setQuantidade(getQuantidade() + quant);
			ordenar();
		}
		
	}
		
	public void ordenar() { // organizar os lotes para que possam ser vendidos os produtos mais proximos da validade
		// organizar atraves da data, produtos com a validade mais recente devem estar no topo
		if (lote.size()>1) {
			for(int i =0; i < lote.size()-1; i++) {
				Lote temp;
				if(lote.get(i).getValidade().compareTo(lote.get(i+1).getValidade())>0) {
					temp = lote.get(i);
					lote.set(i, lote.get(i+1));
					lote.set(i+1, temp);
				}
			}	
		}
	}
	
	public int getVencidos() {
		Date hoje = new Date();
		int vencidos=0;
		for(Lote umLote: lote) {
			if(hoje.compareTo(umLote.getValidade())>0) {
				vencidos += umLote.getQuant();
			}
		}
		return vencidos;
	}
	
	public double venda(int quant) {
		double valor=0;
		Date hoje = new Date();
			if(super.getQuantidade() - getVencidos() >= quant) {
				for(int i=0; i < lote.size();i++) {
					if(hoje.compareTo(lote.get(i).getValidade())<=0) {
						if(lote.get(i).getQuant()>quant) {
							valor += super.venda(quant);
							lote.get(i).setQuantidade(lote.get(i).getQuant() - quant);
							break;
						}
						if(lote.get(i).getQuant()<quant) {
							valor += super.venda(lote.get(i).getQuant());
							quant -= lote.get(i).getQuant();
							lote.remove(i);
							i--;
							continue;
						}
						if(lote.get(i).getQuant() == quant) {
							valor += super.venda(quant);
							lote.remove(i);
							break;
						}
					}
				}
				return valor;
			}
		return -1;
	}

}
