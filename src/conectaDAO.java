
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class conectaDAO {
    public String url = "jdbc:mysql://localhost:3306/leiloestd?useSSL=false";
    public String user = "root";
    public String password = "9805";
    public Connection conn;
    
    public Connection connectDB(){
       
      
        try {
            
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url,user,password);
        System.out.println("Conexão realizada:");
     
        
        } catch (ClassNotFoundException|SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        
        }
         return conn;
    }
    
}
