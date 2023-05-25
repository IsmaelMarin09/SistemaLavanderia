package proyectofinal.persistencia;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PersistenciaModificar {

    public void Buscar(String id, JTextField idProducto, JTextField nombreCliente,
            JTextField telefono, JTextField producto, JTextField descripcion, JTextField fecha, JTextField precio, JComboBox estado, Component Buscar) {
        try {
            Conexion conexion = new Conexion();
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery("Select * from base_productos bp "
                    + "INNER JOIN productos p ON bp.ID = p.IdVenta "
                    + "WHERE p.Activo = 1 AND IdProducto = '" + id + "'");
            idProducto.setEnabled(false);
            nombreCliente.setEnabled(false);
            telefono.setEnabled(false);
            producto.setEnabled(true);
            descripcion.setEnabled(true);
            fecha.setEnabled(false);
            precio.setEnabled(true);

            while (resultado.next()) {
                idProducto.setText("" + resultado.getString("IdProducto"));
                nombreCliente.setText("" + resultado.getString("NombreCliente"));
                telefono.setText("" + resultado.getString("Telefono"));
                producto.setText("" + resultado.getString("NombreProducto"));
                descripcion.setText("" + resultado.getString("Descripcion"));
                fecha.setText("" + resultado.getString("Fecha"));
                precio.setText("" + resultado.getString("Precio"));
                estado.setSelectedItem("" + resultado.getString("estado"));
            }
            resultado.close();
            conexion.getConexion().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(Buscar, "Error" + e, "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void Modificar(Component Modificar, String idProductoU,
            JTextField productoU, JTextField descripcionU, JTextField precioU, JComboBox estadoU) {
        int seleccion = JOptionPane.showOptionDialog(Modificar, "¿Desea modificar el registro? (Si/No)",
                "Seleccione una opción", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, new Object[]{"Si", "No"}, "Si");

        if (seleccion == JOptionPane.YES_OPTION) {
            try {
                String productoUpdate = productoU.getText();
                String descripcionUpdate = descripcionU.getText();
                String precioUpdate = precioU.getText();
                String estadoUpdate = estadoU.getSelectedItem().toString();
                
                Conexion conexion = new Conexion();
                String instruccion = "UPDATE productos p SET p.NombreProducto = '"+productoUpdate+"', "
                        + "p.Descripcion = '"+descripcionUpdate+"', p.Precio = '"+precioUpdate+"', "
                        + "p.estado = '"+estadoUpdate+"' "
                        + "WHERE p.IdProducto = "+idProductoU+";";
                conexion.sentencia = conexion.getConexion().prepareStatement(instruccion);
                conexion.sentencia.execute();
                System.out.println(instruccion); // Verificar la sentencia SQL
                int filas =  conexion.sentencia.executeUpdate();
                if (filas > 0) {
                    JOptionPane.showMessageDialog(Modificar, "Registro modificado", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Modificar, "No se encontró el registro o no se realizó ninguna modificación", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
                conexion.getConexion().close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(Modificar, "Error SQL: " + e.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(Modificar, "Registro NO MODIFICADO", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
