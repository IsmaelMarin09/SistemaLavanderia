
package proyectofinal.persistencia;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import proyectofinal.IGU.Eliminar;

public class PersistenciaEliminar {
    
    public void BuscarRegistro(String id, JTextField idProducto, JTextField idClienteE, JTextField nombreClienteE,
            JTextField telefonoE, JTextField productoE, JTextField descripcionE, JTextField fechaE, JTextField precioE, Component Buscar){
        try {
            Conexion conexion=new Conexion();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("Select * from base_productos bp "
                    + "INNER JOIN productos p ON bp.ID = p.IdVenta "
                    + "WHERE p.Activo = 1 AND IdProducto = '"+id+"'");
            idProducto.setEnabled(false);
            idClienteE.setEnabled(false);
            nombreClienteE.setEnabled(false);
            telefonoE.setEnabled(false);
            productoE.setEnabled(false);
            descripcionE.setEnabled(false);
            fechaE.setEnabled(false);
            precioE.setEnabled(false);
            
            while (resultado.next()) {
                idProducto.setText(""+resultado.getString("ID"));
                idClienteE.setText(""+resultado.getString("IdCliente"));
                nombreClienteE.setText(""+resultado.getString("NombreCliente"));
                telefonoE.setText(""+resultado.getString("Telefono"));
                productoE.setText(""+resultado.getString("NombreProducto"));
                descripcionE.setText(""+resultado.getString("Descripcion"));
                fechaE.setText(""+resultado.getString("Fecha"));
                precioE.setText(""+resultado.getString("Precio"));
            }
            resultado.close();
            conexion.getConexion().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(Buscar,"Error"+e, "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void EliminarProducto(Component Eliminar, String idProducto, JTextField idProductoElimin){
        int seleccion = JOptionPane.showOptionDialog(Eliminar,"¿Desea eliminar el registro?(Si/No)", "Seleccione una opcion", 
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si","No"},"Si");
        if ((seleccion + 1) == 1) {
            try {
                Conexion conexion = new Conexion();
                String instruccion = "Update productos p SET p.Activo = 2 WHERE p.Activo = 1 AND IdProducto = '" +idProducto+"'";
                conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
                conexion.sentencia.execute();
                JOptionPane.showMessageDialog(Eliminar, "Registro Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                conexion.getConexion().close();
                Eliminar cerrarEliminar = new Eliminar();
                cerrarEliminar.setVisible(false);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(Eliminar,"Error SQL:"+e,"Informacion",JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(Eliminar,"Registro NO ELIMINADO", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
