package .estoqueComProdutosPereciveis;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DAOProduto{

	public void incluir(Produto p) throws ProdutoJaCadastrado, DadosInvalidos {
		try {
			pesquisar(p.getCodigo());
			throw new ProdutoJaCadastrado(p);
		} catch (ProdutoInexistente e1) {
			Connection con = Conexao.getConexao();
			Statement stmt;
			DAOFornecedor daoF = new DAOFornecedor();
			try {
				Fornecedor f = daoF.pesquisar(p.getFornecedor().getCNPJ());
			} catch (Exception e2) {
				try {
					daoF.inserir(p.getFornecedor());
				} catch (Exception e) {
					System.exit(1);
				}
			}
			try {
				stmt = con.createStatement();
				String comando = "insert into produto (idProduto, descricao, estoqueMinimo ,  quantidade, lucro, fornecedor, descriminante) values (" +
				p.getCodigo() + ", \'" + p.getDescricao() + "\'," + p.getEstoqueMinimo() + "," + p.getQuantidade() + "," + p.getLucro() + "," + 
						p.getFornecedor().getCNPJ() + "";
				
				if(p instanceof ProdutoPerecivel) {
					DAOLote lote = new DAOLote();
					for(Lote l : ((ProdutoPerecivel)p).getLote()) {
						lote.adicionar(l);
					}
					comando += ",'p'" + ")";
				}
				else {
					 comando += ",'n'" + ")";
				}
						
				System.out.println(comando);
				stmt.executeUpdate(comando);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}		
	}

	public void removerTudo(){
		// TODO Auto-generated method stub
		Connection con = Conexao.getConexao();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String comando = "delete from fornecedor where idfornecedor >= 0;";
			String comando1 = "delete from lote where idLote >= 0;";
			String comando2 = "delete from produto where idProduto >= 0;";
			System.out.println(comando);
			stmt.executeUpdate(comando); // MUDAR ALGO NO BD
			stmt.executeUpdate(comando1); // MUDAR ALGO NO BD
			stmt.executeUpdate(comando2); // MUDAR ALGO NO BD			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}	}
	
	public void remover(int num) {
		Connection con = Conexao.getConexao();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String comando = "delete from produto where idProduto = " + num;
			System.out.println(comando);
			stmt.executeUpdate(comando);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}

	public Produto pesquisar(int cod) throws ProdutoInexistente {
		Connection con = Conexao.getConexao();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String comando = "select * from produto where idProduto = " + cod;
			System.out.println(comando);
			ResultSet rs = stmt.executeQuery(comando);
			Produto c = null;
			if (rs.next()) { 
				String descricao = rs.getString("descricao");
				int quantidade = rs.getInt("quantidade");
				double lucro = rs.getDouble("lucro");
				int estoqueMinimo = rs.getInt("estoqueMinimo");
				double precoDeCompra = rs.getDouble("precoDeCompra");
				double precoDeVenda = rs.getDouble("precoDeVenda");
				int fornecedor = rs.getInt("fornecedor");
				String descriminante = rs.getString("descriminante");
				DAOFornecedor forn = new DAOFornecedor(); 
				Fornecedor fornec = forn.pesquisar(fornecedor);
				System.out.println(descriminante);
				if(descriminante.equals("p")) {
					DAOLote l = new DAOLote();
					ArrayList<Lote> lote = l.pesquisar(cod);
					c = new ProdutoPerecivel(cod ,descricao,quantidade, lucro, estoqueMinimo, precoDeCompra,
							precoDeVenda,fornec, lote, descriminante);
				}
				else if (descriminante.equals("n")){
					c = new Produto(cod, descricao,quantidade, lucro, estoqueMinimo, precoDeCompra,
							precoDeVenda,fornec, descriminante);
				}

			}
			else {
				throw new ProdutoInexistente(cod);
			}
			stmt.close();
			return c;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Produto> returnALL() throws ProdutoInexistente {
		Connection con = Conexao.getConexao();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String comando = "select * from produto";
			System.out.println(comando);
			ResultSet rs = stmt.executeQuery(comando);
			Produto c = null;
			ArrayList<Produto> all = new ArrayList<>();
			while (rs.next()) { 
				int cod = rs.getInt("idProduto");
				String descricao = rs.getString("descricao");
				int quantidade = rs.getInt("quantidade");
				double lucro = rs.getDouble("lucro");
				int estoqueMinimo = rs.getInt("estoqueMinimo");
				double precoDeCompra = rs.getDouble("precoDeCompra");
				double precoDeVenda = rs.getDouble("precoDeVenda");
				int fornecedor = rs.getInt("fornecedor");
				String descriminante = rs.getString("descriminante");
				DAOFornecedor forn = new DAOFornecedor(); 
				Fornecedor fornec = forn.pesquisar(fornecedor);
				if(descriminante.equals("p")) {
					DAOLote l = new DAOLote();
					ArrayList<Lote> lote = l.pesquisar(cod);
					c = new ProdutoPerecivel(cod ,descricao,quantidade, lucro, estoqueMinimo, precoDeCompra,
							precoDeVenda,fornec, lote, descriminante);
				}
				else if (descriminante.equals("n")){
					c = new ProdutoPerecivel(cod, descricao,quantidade, lucro, estoqueMinimo, precoDeCompra,
							precoDeVenda,fornec, descriminante);				
					}
				all.add(c);
			} 
			stmt.close();
			return all;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void alterar(Produto p) throws ProdutoInexistente{
		  pesquisar(p.getCodigo());
		  Connection con = Conexao.getConexao();
		  Statement stmt;
		  try {
			stmt = con.createStatement();
			String comando = "update produto set precoDeCompra = " + p.getPrecoDeCompra() + 
			", precoDeVenda = " + p.getPrecoDeVenda() + ", quantidade =" + p.getQuantidade() +  
			" where idProduto = " + p.getCodigo()+";"; 
			
			if(p instanceof ProdutoPerecivel) {
				DAOLote lote = new DAOLote();
				lote.remover();
				for(Lote l : ((ProdutoPerecivel)p).getLote()) {
					if(lote.verificar(l)) {
						lote.atualizar(l);
					}
					else {
						lote.adicionar(l);
					}
				}
			}
			
			System.out.println(comando);
			stmt.executeUpdate(comando);
			stmt.close();
		  } catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		  }
		}
	

}
