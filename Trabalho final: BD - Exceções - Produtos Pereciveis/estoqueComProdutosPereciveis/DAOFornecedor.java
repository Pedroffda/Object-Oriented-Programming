package .estoqueComProdutosPereciveis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOFornecedor {
	
	public void inserir(Fornecedor f) throws FornecedorInexistente, ProdutoJaCadastrado, DadosInvalidos {
		//try {
			Fornecedor forn;
			forn = pesquisar(f.getCNPJ());
		//}catch(FornecedorInexistente x){
			if(forn==null){
			Connection con = Conexao.getConexao();
			Statement stmt;	
			try {	
				stmt = con.createStatement();
				String comando = "insert into fornecedor (idFornecedor, nome) values (" +
				f.getCNPJ() + ",\'" + f.getNome() + "\')";
				System.out.println(comando);
				stmt.executeUpdate(comando);
				DAOProduto daoP = new DAOProduto(); 
				ArrayList<Produto> produtos = f.getProdutos();
				for (Produto prod : produtos) {
					try {
					  daoP.pesquisar(prod.getCodigo());
					} catch (Exception e) {
						daoP.incluir(prod);
					}
				}
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	public void remover(int CNPJ) {
		Connection con = Conexao.getConexao();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String comando = "delete from fornecedor where idfornecedor = " + CNPJ;
			System.out.println(comando);
			stmt.executeUpdate(comando);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void removerTudo() {
		Connection con = Conexao.getConexao();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String comando = "delete from fornecedor where idfornecedor >= 0;";
			String comando1 = "delete from lote where idLote >= 0;";
			String comando2 = "delete from produto where idProduto >= 0;";

			System.out.println(comando);
			System.out.println(comando1);
			System.out.println(comando2);

			stmt.executeUpdate(comando); // MUDAR ALGO NO BD
			stmt.executeUpdate(comando1); // MUDAR ALGO NO BD
			stmt.executeUpdate(comando2); // MUDAR ALGO NO BD

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public Fornecedor pesquisar(int CNPJ) {
		Connection con = Conexao.getConexao();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String comando = "select * from fornecedor where idfornecedor = " + CNPJ;
			System.out.println(comando);
			ResultSet rs = stmt.executeQuery(comando); // RETORNAR ALGO
			Fornecedor f = null;
			if (rs.next()) { 
				String nome = rs.getString("nome");
				f = new Fornecedor(CNPJ, nome);
				return f;
			} 
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void alterar(Fornecedor f) throws FornecedorInexistente{
		//pesquisar(f.getCNPJ());
		  Connection con = Conexao.getConexao();
		  Statement stmt;
		  try {
			stmt = con.createStatement();
			String comando = "update fornecedor set nome =\'" +
			f.getNome() + "\' where idfornecedor = " + f.getCNPJ();
			System.out.println(comando);
			stmt.executeUpdate(comando);
			stmt.close();
		  } catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		  }
	}
	
}
