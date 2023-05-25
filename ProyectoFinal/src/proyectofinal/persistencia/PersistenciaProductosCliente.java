
package proyectofinal.persistencia;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PersistenciaProductosCliente {
    public void VisualizarProductos(Component mensaje, DefaultTableModel modelo, JTable productos, String idCliente){
        productos.setModel(modelo);
        Conexion conexion = new Conexion();
        Statement sentencia;
        try{
            sentencia = conexion.getConexion().createStatement();
            ResultSet resultado;
            resultado = sentencia.executeQuery("Select bp.ID, bp.NombreCliente, p.IdProducto, p.NombreProducto, p.Descripcion, p.Precio, bp.Fecha, p.Estado "
                    + "From base_productos bp "
                    + "INNER JOIN productos p ON bp.ID = p.IdVenta "
                    + "WHERE p.Activo = 1 AND bp.IdCliente LIKE '%"+idCliente+"%'");
            ResultSetMetaData campos = resultado.getMetaData();
            int cantidadColumnas = campos.getColumnCount();
            for (int i= 1; i <= cantidadColumnas; i++)
            {
                modelo.addColumn(campos.getColumnLabel(i));
            }
            while(resultado.next())
            {
                Object[]fila = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = resultado.getObject(i+1);
                }
                modelo.addRow(fila);
            }
            resultado.close();
            conexion.getConexion().close();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(mensaje,"Error SQL:"+e,"Informacion",JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(mensaje,"Error :"+e,"Informacion",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
