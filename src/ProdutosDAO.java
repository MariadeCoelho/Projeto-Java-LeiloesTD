

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
        
        return listagem;
    }
    
    
    
        
}

