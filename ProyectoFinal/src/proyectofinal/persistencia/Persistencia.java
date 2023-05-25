/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Persistencia {

    public String validarUsuario(String usuario, String pass) {
        String mensaje = "";
        Conexion conexion = new Conexion();
        try {

            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT NombreUsuario,Contraseña FROM productos.usuarios WHERE NombreUsuario IN ('" + usuario + "') AND Contraseña IN ('" + pass + "') ");
            if (resultado.next()) {
                mensaje = "Usuario y contraseña válidos";
                System.out.println("Usuario y contraseña válidos");

            } else {
                mensaje = "Usuario y/o contraseña inválido";
                System.out.println("Usuario y/o contraseña inválidos");
            }

            resultado.close();
            sentencia.close();

        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "Error al validar Usuario";
        }
        return mensaje;
    }

    public String cerrarDia() {
       String  mensaje=" ";
       String estadoPost = "Lavando";
       String estadoNo = "En despacho"; 

        Conexion conexion = new Conexion();
        try {
               
                String instruccion = "Update productos p SET  p.Estado = '" +estadoPost+"'WHERE Estado ='"+estadoNo+"' ";
                conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
                conexion.sentencia.execute();
                mensaje="Se cerro el dia exitosamente";
                conexion.getConexion().close();
            } catch (SQLException e) {
                mensaje="Error SQL";
            }
        
        
        return mensaje;
        } 
}

    


