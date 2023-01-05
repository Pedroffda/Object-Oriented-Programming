package .estoqueComProdutosPereciveis;

import java.util.ArrayList;
import java.util.Date;

import org.hamcrest.core.IsInstanceOf;

public class Estoque {
	
	//private ArrayList<Produto> produtos = new ArrayList<Produto>();
	private DAOProduto produtos = new DAOProduto();

	public Estoque() {
		
	} 
	
	public void incluir(Produto p) throws ProdutoJaCadastrado, DadosInvalidos  { //cadastro produto
		try { 
			Produto p1 = pesquisar(p.getCodigo());
			throw new ProdutoJaCadastrado(p);
		}
		catch(ProdutoInexistente e) {
			// produto inexistente 
			if((p.codigo>=0) && (p.descricao != null) && (!p.descricao.isEmpty()) && (p.lucro>=0) && (p.precoDeCompra>=0) 
			&& (p.precoDeVenda>=0) && (p.quantidade>=0) && (p.estoqueMinimo>=0)	) {
				//produtos.add(p);
				produtos.incluir(p);
			}
			else {
				throw new DadosInvalidos();
			}
		}
	}
	
	public void comprar(int cod, int quant, double preco, Date validade) throws DadosInvalidos, ProdutoInexistente, ProdutoNaoPerecivel{ //comprar um produto
		
		Produto p = pesquisar(cod); 
		
		if((cod >=0) && (quant>=0) && (preco >=0) && (p.precoDeCompra>=0) && !(validade == null && p instanceof ProdutoPerecivel)) {
	    	if(validade==null && (p instanceof Produto)) {
	        	p.compra(quant, preco);
	        	produtos.alterar(p);
	    	}else if(validade != null && (p instanceof ProdutoPerecivel)) {
	    		((ProdutoPerecivel) p).compra(quant, preco, validade);
	    		produtos.alterar(p);
	    		
	    	}else if((validade != null && (p instanceof Produto))){
	    		throw new ProdutoNaoPerecivel();    	
	    	}
		}
		else {
			throw new DadosInvalidos();
		}
		
	}
	
	public double vender(int cod, int quant) throws ProdutoInexistente, DadosInvalidos, ProdutoVencido { //vender um produto
		Produto p = pesquisar(cod);
		double precoDeVenda=0;
		
		if((quant>=0) && (p.precoDeVenda>=0) && (p.quantidade>=quant)) {
			if(p instanceof ProdutoPerecivel) {
				if(quant>(p.quantidade- quantidadeVencidos(cod))) {
					throw new ProdutoVencido();
				}
				else {
					precoDeVenda = ((ProdutoPerecivel)p).venda(quant);
					produtos.alterar(p);
					return precoDeVenda;
				}
			}
			else if(!(p instanceof ProdutoPerecivel)) {
				precoDeVenda = p.venda(quant);
				produtos.alterar(p);
				return precoDeVenda;
			}
		}else {
			throw new DadosInvalidos();
		}
		return -1;
	}
		
	public Produto pesquisar(int cod) throws ProdutoInexistente { //pesquisa um produto pelo seu codigo
		return produtos.pesquisar(cod);
	}

	//public arrayList
	public ArrayList<Produto> estoqueAbaixoDoMinimo() throws ProdutoInexistente{
		ArrayList<Produto> abaixo = new ArrayList<Produto>();
		ArrayList<Produto> aux = new ArrayList<Produto>();
//		for(Produto prod: this.produtos) {
//			if(prod.getQuantidade() < prod.getEstoqueMinimo()) {
//				abaixo.add(prod);
//			}
//		}
		aux = produtos.returnALL();
		for(Produto prod: aux) {
			if(prod.getQuantidade() < prod.getEstoqueMinimo()){
				abaixo.add(prod);
			}
		}
		
		return abaixo;
		
	}
	
	public int quantidadeVencidos(int cod) throws ProdutoInexistente{
		int quantVencidos=0;
		Produto p = pesquisar(cod);
		if(p!=null) {
			quantVencidos= ((ProdutoPerecivel)p).quantVencidos(); 
			return quantVencidos;
		}else {
			return -1;
		}
	}

	public ArrayList<Produto> estoqueVencido() throws ProdutoInexistente {
		ArrayList<Produto> vencido = new ArrayList<Produto>();
		Date hoje = new Date();
//		for(Produto prod: this.produtos) {
//			if(prod != null && (prod instanceof ProdutoPerecivel)) {
//				for(Lote lote: ((ProdutoPerecivel)prod).lotes) {
//					if(lote.getValidade().before(hoje) && !vencido.contains(prod)) {
//						vencido.add(prod);
//					}
//				}
//			}
//		}
		ArrayList<Produto> aux = new ArrayList<Produto>();
		aux = produtos.returnALL();
//		for(Produto prod: aux) {
//			if(prod != null && (prod instanceof ProdutoPerecivel)) {
//				for(Lote lote: ((ProdutoPerecivel)prod).lotes) {
//					if(lote.getValidade().before(hoje) && !vencido.contains(prod)) {
//						vencido.add(prod);
//					}
//				}
//			}
//		}
		
		for(Produto prod: aux) {
			if(prod.getQuantidade() <= quantidadeVencidos(prod.getCodigo())) {
				vencido.add(prod);
			}
		}
		
		return vencido;
	}
	
	public void removerTudo(){
		//this.produtos.clear();
		produtos.removerTudo();
	}

	
}
