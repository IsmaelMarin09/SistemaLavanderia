package proyectofinal.persistencia;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PersistenciaListadoDia {

    public void VisualizarFiltro(Component mensaje, DefaultTableModel modelo, JTable productos, String nombre, String id, String estado, String fecha) {
        productos.setModel(modelo);
        Conexion conexion = new Conexion();
        Statement sentencia;

        String consulta = "Select DISTINCT bp.ID, bp.IdCliente, bp.NombreCliente, bp.Telefono, bp.Fecha "
                + "FROM base_productos bp "
                + "INNER JOIN productos p ON bp.ID = p.IdVenta "
                + "WHERE p.Activo = 1";
        boolean whereAdded = false;

if (nombre != null && !nombre.isEmpty()) {
    consulta += " AND bp.NombreCliente LIKE '%" + nombre + "%'";
    whereAdded = true;
}

if (fecha != null && !fecha.isEmpty()) {
        consulta += " AND bp.Fecha LIKE '%" + fecha + "%'";
        whereAdded = true;
}

if (estado != null && !estado.isEmpty()) {
        consulta += " AND p.estado LIKE'%" + estado + "%' ";
        whereAdded = true;
}

if (id != null && !id.isEmpty()) {
    if (!whereAdded) {
        consulta += " WHERE p.Activo = 1 ";
    } else {
        consulta += " AND";
    }
    consulta += " IdCliente LIKE '%" + id + "%'";
}

consulta += " ORDER BY bp.ID DESC";
        System.out.println(consulta);
        try {
            sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);
            ResultSetMetaData campos = resultado.getMetaData();
            int cantidadColumnas = campos.getColumnCount();
            for (int i = 1; i <= cantidadColumnas; i++) {
                modelo.addColumn(campos.getColumnLabel(i));
            }
            while (resultado.next()) {
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = resultado.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            resultado.close();
            conexion.getConexion().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mensaje, "Error SQL:" + e, "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mensaje, "Error :" + e, "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
