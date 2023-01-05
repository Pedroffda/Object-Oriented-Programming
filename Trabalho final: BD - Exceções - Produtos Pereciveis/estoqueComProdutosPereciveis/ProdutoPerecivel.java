package .estoqueComProdutosPereciveis;

import java.util.ArrayList;

import java.util.Date;


public class ProdutoPerecivel extends Produto {
	ArrayList<Lote> lotes = new ArrayList<Lote>();
	
	
	public ProdutoPerecivel(int cod, String desc, int min, double lucro, Fornecedor forn) {
		super(cod, desc, min, lucro, forn);
		
	}
	
   public ProdutoPerecivel(int cod, String descricao, int quantidade, double lucro, int estoqueMinimo,
			double precoDeCompra, double precoDeVenda, Fornecedor fornec, String descriminante) {
		// TODO Auto-generated constructor stub
		super(cod, descricao,quantidade, lucro, estoqueMinimo, precoDeCompra, precoDeVenda,
				fornec, descriminante);
	}
   public ProdutoPerecivel(int cod, String descricao, int quantidade, double lucro, int estoqueMinimo,
			double precoDeCompra, double precoDeVenda, Fornecedor fornec, ArrayList<Lote> l , String descriminante) {
		// TODO Auto-generated constructor stub
		super(cod, descricao,quantidade, lucro, estoqueMinimo, precoDeCompra, precoDeVenda,
				fornec, descriminante);
		this.lotes = l;
	}


public void compra(int quant, double val, Date validade) { //metodo de compra para um prod perecivel
	   	acrescentar(quant, validade);
	   	precoDeCompra= ((quantidade*precoDeCompra) + (quant*val))/ (quantidade+quant);
		quantidade+=quant;
		precoDeVenda = precoDeCompra * (1+lucro); // 2 * (1+0.5) = 3
   }
   
   @Override
   public double venda(int quant) { //metodo de venda para um produto perecivel
	   if(quant>(quantidade-quantVencidos())) { // nao permite a venda de uma quantidade acima do que se tem do produto
		   return -1;
	   }
	   
	   double venda=0;
	   int aux=quant, i=0;
	   Date atual = new Date();
	   
	   for (Lote lote : lotes) { //percorre o array de Lote
		if(lote.getValidade().after(atual)) { //verifica se o produto eh valido
			i++;
			if(lote.getQuant()>=quant) { //se o lote tem a quantidade suficiente da venda ele vende e encera o for
				lote.setQuant(lote.getQuant()-quant);
				quant = 0;
				break; 
			}else { 	//o lote nao tem a quantidade  suficiente entao eh 
						//vendido todos os produtos e volta no for para achar outro lote e completar
				quant-=lote.getQuant();		
				lote.setQuant(0);
			}
		 }
	   }
	   if(i>0) {
		   quantidade-=aux; //remove da quantidade total a quantidade vendida
		   venda= precoDeVenda * aux; 
		   return venda;
	   }
	   return -1;
   }
   
   public void acrescentar(int quant, Date validade) { //adicionar um novo lote ou quant a um lote existente
	 
	   int i=0;
	  // Lote loteNovo = new Lote(quant, validade);
	   for (Lote lote : lotes) {
		   if(validade.compareTo(lote.getValidade())==0) { //se ja houver um lote para a data ele acresecenta a quantidade
			   lote.setQuant(lote.getQuant()+quant); 
			   return ;
		   }
	   }  

	   for (Lote lote : lotes) { //cria um novo lote se nao exixtir um outro com aquela validade
		   //if(validade.compareTo(lote.getValidade())<0){
		   //lote.getValidade().after(atual)
		   if(validade.before(lote.getValidade())) { 
			   lotes.add(i, new Lote(quant, validade, getCodigo())); 
			   return;
		   }
		   i++;
	   }
	   
	   lotes.add(new Lote(quant, validade, getCodigo())); 
	    return ;
   }
  public int quantVencidos() { //contabiliza a quantidade de intens vencidos de um produtos 
	  int quantVencidos=0;
	  Date hoje = new Date();
	   for (Lote lote : lotes) {
			if(lote.getValidade().before(hoje)) {
				quantVencidos+=lote.getQuant();
			}
	   } 
	   return quantVencidos;
   }
   
  public ArrayList<Lote> getLote(){
	  return lotes;
  }
  
}
