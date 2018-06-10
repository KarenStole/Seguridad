/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Karen
 */
public class DataBaseController {
    

    /**
     *
     */
    public static Connection conectarBD (){

      Connection connection = null;

      try{
         connection = DriverManager.getConnection( "jdbc:sqlite:C:\\Users\\Karen\\Documents\\NetBeansProjects\\Seguridad\\Usuarios.db" );
         if ( connection != null ){
            //System.out.println("Conexión exitosa!");
         }
         return connection;
      }
      catch ( Exception ex ) {
         System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
         System.out.println("Error en la conexión");
         return null;
      }
   }
   
    public static void resgistrarUsuario(String user, String passHash) throws SQLException{
            String sqlInsertUser = "INSERT INTO Users (users_name, password) values ('"+ user+ "','"+ passHash+"');";
            Connection con = DataBaseController.conectarBD();
            Statement st = con.createStatement();
            st.execute(sqlInsertUser);
            con.close();
    }
    
    public static boolean verificarUsuario(String user) throws SQLException{
        String sqlUser = "SELECT users_name from Users where users_name = '" + user + "';";
        Connection con = DataBaseController.conectarBD();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlUser);
        if(rs.next()){

            con.close();
            return false;
        }
        else{ 
            con.close();
            return true;}
        
    }
    public static boolean validarLogin(String user, String hashPass) throws SQLException{
        String sqlUser = "SELECT * from Users where users_name = '" + user + "' and password = '"
                + hashPass+"';";
        Connection con = DataBaseController.conectarBD();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlUser);
        if(rs.next()){
            con.close();
            return true;
        }
        else{ 
            con.close();
            return false;}
        
    }


}
