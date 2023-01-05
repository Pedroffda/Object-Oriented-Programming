package .estoqueComProdutosPereciveis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DAOLote {
	
	public void adicionar(Lote l) {
        try {
            Connection con = Conexao.getConexao();
            Statement stmt = con.createStatement();
            long t = l.getValidade().getTime();
            String cm = "insert into lote (quantidade,validade, produto) values (" + l.getQuant() + ", "
                    + t + ", " + l.getIDProduto()  + ");";
            System.out.println(cm);
            stmt.executeUpdate(cm);
            stmt.close();
        } catch (SQLException e2) {

        }
    }
	
	public ArrayList<Lote> pesquisar(int idProd){
		Connection con = Conexao.getConexao();
		Statement stmt;
		try {
			
			String comando = "select * from lote where produto = " + idProd + ";";
			stmt = con.createStatement();
			System.out.println(comando);
			ResultSet rs = stmt.executeQuery(comando);
			ArrayList<Lote> lotes = new ArrayList<Lote>();

			while(rs.next()) {
				int quantidade = rs.getInt("quantidade");
				long validade = rs.getLong("validade"); 
				Lote l = new Lote(quantidade, new Date(validade), idProd);
				lotes.add(l);
			}
			
			return lotes;

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Boolean verificar(Lote l){
		Connection con = Conexao.getConexao();
		Statement stmt;
		try {
			stmt = con.createStatement(); 
			String cm = "select * from lote where produto = " + l.getIDProduto() + " and validade = "
                    + l.getValidade().getTime() + ";";
			ResultSet rs = stmt.executeQuery(cm);
			if(rs.next()) {
				return true;
			}

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
		
	}
	
	public void atualizar(Lote l) {
        Connection con = Conexao.getConexao();
        Statement stmt;
        try {
            stmt = con.createStatement();
            String cm = "update lote set quantidade = " + l.getQuant() + " where produto = "
                    + l.getIDProduto() + " and validade = " + l.getValidade().getTime() + ";";
            stmt.executeUpdate(cm);
            stmt.close();
        } catch (SQLException e) {
        }
    }
	
	public void remover() { 
        Connection con = Conexao.getConexao();
        Statement stmt;
        try {
            stmt = con.createStatement();
            String cm = "delete from lote where quantidade = 0";
            stmt.executeUpdate(cm);
            stmt.close();
        } catch (SQLException e) {
        }
    }
		
}
