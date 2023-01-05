package .estoqueComProdutosPereciveis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conexao {

	private static Connection[] conexoes = new Connection[10];
	private static boolean conectou = false;
	private static int pos = 0;
	
	private Conexao() {}
		
		public static Connection getConexao() {
			if(pos == 10) {
				pos = 0;
			}
			if(!conectou) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					for(int i = 0; i < 10; i++) {
						conexoes[i] = DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "123456789");
					}
				}
				catch(Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
				
				conectou = true;
			}
			
			return conexoes[pos++];
		}
		
	public static void main(String[] args) {
		Connection conexao;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "123456789");
			System.out.println("Conectado OK");
			Statement st = conexao.createStatement();
			
			String comando1 = "insert into fornecedor (idFornecedor, nome) values (1, 'COCA')";
			
			st.executeUpdate(comando1);
			System.out.println(comando1);
			
			String comando = "insert into produto (idProduto, descricao, quantidade, lucro, fornecedor)"
					+ " values (" + 1 + ", " + "\'produto" + 1 + "', 5"  +  ", 10" + ", '" + "1" + "\')";
			System.out.println(comando);
			st.executeUpdate(comando);
			
//			for (int i = 2; i < 10; i++) {
//			    String comando = "insert into produto (idProduto, descricao, quantidade, lucro, fornecedor)"
//						+ " values (" + i + ", " + "\'produto" + i + "', 5"  +  ", 10" + ", '" + "COCA" + "\')";
//				System.out.println(comando);
//				st.executeUpdate(comando);
//			}
		}
		catch(Exception e){
			System.out.println("Erro na conexao");
			e.printStackTrace();
		}
	}
	
}
