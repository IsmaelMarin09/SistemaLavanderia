
package proyectofinal.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
    public Connection conexion;
    public PreparedStatement sentencia;
    public Conexion(){
    String ruta= "jdbc:mysql://localhost:3306/productos";
    try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(ruta,"root","");
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Error:"+e);
        }
        catch (SQLException e){
            System.out.println("Error en la conexion:"+e);
        }
    }

    
    public Connection getConexion() {
        return conexion;
    }
    public void setConexion(Connection conexion){
        this.conexion = conexion;
    }
}