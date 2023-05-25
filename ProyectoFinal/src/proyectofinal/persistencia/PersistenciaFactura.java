package proyectofinal.persistencia;

import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PersistenciaFactura {

    public PersistenciaFactura() {
    }

    public void generarFact(Component mensaje, DefaultTableModel modelo, JTable productos, String id, JTextField num, JTextField name, JTextField abono, JTextField precio) {
        productos.setModel(modelo);
        Conexion conexion = new Conexion();
        Statement sentencia;

        String consulta = "Select  DISTINCT  bp.ID, bp.NombreCliente, p.NombreProducto, p.Precio, p.Abono, p.ValorTotal "
                + "FROM base_productos bp "
                + "INNER JOIN productos p ON bp.ID = p.IdVenta "
                + "WHERE p.Activo = 1 AND bp.IdCliente = '" + id + "'";

        System.out.println(consulta);
        try {
            sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);
            modelo.setColumnCount(0);
            modelo.addColumn("Producto");
            modelo.addColumn("Precio");
            while (resultado.next()) {
                Object[] fila = new Object[2];
                fila[0] = resultado.getObject("NombreProducto");
                fila[1] = resultado.getObject("Precio");
                modelo.addRow(fila);
                num.setText("" + resultado.getString("ID"));
                name.setText("" + resultado.getString("NombreCliente"));
                  abono.setText("" + resultado.getString("Abono"));
                  precio.setText("" + resultado.getString("ValorTotal"));
                
            }
            resultado.close();
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mensaje, "Error SQL:" + e, "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mensaje, "Error :" + e, "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
        
          }
    public void facturar(Component Modificar, String id){
        String Activo ="2";
        int seleccion = JOptionPane.showOptionDialog(Modificar, "¿Desea cerrar la venta? (Si/No)",
                "Seleccione una opción", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, new Object[]{"Si", "No"}, "Si");

        if (seleccion == JOptionPane.YES_OPTION) {
            try {
      
                
                Conexion conexion = new Conexion();
                String instruccion = "UPDATE productos p SET p.Activo = '"+Activo+"'"
                        + "WHERE p.IdVenta = "+id+";";
                conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
                conexion.sentencia.execute();
                System.out.println(instruccion); // Verificar la sentencia SQL
                int filas =  conexion.sentencia.executeUpdate();
                if (filas > 0) {
                    JOptionPane.showMessageDialog(Modificar, "Venta cerrada", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Modificar, "No se encontró el registro o no se realizó ninguna modificación", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
                conexion.getConexion().close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(Modificar, "Error SQL: " + e.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(Modificar, "venta NO cerrada", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    
    
    }
}
