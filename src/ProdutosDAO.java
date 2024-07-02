

import java.sql.Statement;
import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
public void cadastrarProduto (ProdutosDTO produto){
    conn = new conectaDAO().connectDB();
    try{
     st = conn.prepareStatement("INSERT INTO produtos (nome, valor,status) VALUES (?,?,?)");
     st.setString(1, produto.getNome());
     st.setDouble(2, produto.getValor());
     st.setString(3, produto.getStatus());
    
      int status = st.executeUpdate();
      if (status > 0){
       JOptionPane.showMessageDialog(null, "Cadastro feito");
      }
      else{
       JOptionPane.showMessageDialog(null, "Erro ao conectar");
      }
    } catch(SQLException ex){
     JOptionPane.showMessageDialog(null,"Erro a o cadastrar produto: " + ex.getMessage());
    } finally {
        try{
         if (conn != null) conn.close();
         if (st != null) st.close();
        } catch (SQLException e){
         JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
        }
    }
    }

public ArrayList<ProdutosDTO> listarProdutos(){
ArrayList<ProdutosDTO> produtos = new ArrayList<>();
conn = new conectaDAO().connectDB();
     try {
         st = conn.prepareStatement("SELECT id, nome , valor, status FROM produtos");
         rs = st.executeQuery();
        while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("Status"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + ex.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
                if (st != null) st.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return produtos;
}

public  void venderProduto(int produtoId){
String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

try (Connection conn = new conectaDAO().connectDB();
PreparedStatement pst = conn.prepareStatement (sql)){
pst.setInt(1,produtoId);
pst.executeUpdate();

} catch (SQLException e){
System.out.println(e.getMessage());

}
}
public ArrayList<ProdutosDTO> listarProdutosVendidos(){
String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
ArrayList<ProdutosDTO> produtosVendidos = new ArrayList<>();

try(Connection conn = new conectaDAO().connectDB();
Statement stm = conn.createStatement();
ResultSet rs = stm.executeQuery(sql)){
while (rs.next()){
ProdutosDTO produto = new ProdutosDTO();
produto.setId(rs.getInt("id"));
produto.setNome(rs.getString("nome"));
produto.setValor(rs.getInt("valor"));

produto.setStatus(rs.getString("Status"));
produtosVendidos.add(produto);
}

} catch(SQLException e){
System.out.println(e.getMessage());

}
return produtosVendidos;

}






}  
    