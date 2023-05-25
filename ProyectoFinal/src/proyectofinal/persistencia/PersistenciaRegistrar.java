package proyectofinal.persistencia;

import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PersistenciaRegistrar {

    private String ID;
    private String IdVenta;
    private String Activo;
    private String IdCliente;
    private String NombreCliente;
    private String Telefono;
    private String Fecha;
    private String Abono;
    private String ValorTotal;
    ArrayList IdProducto;
    ArrayList NombreProducto;
    ArrayList Descripcion;
    ArrayList Precio;

    public PersistenciaRegistrar() {

    }

    public void Registrar(String IDr, String IdClienter, String Nombrer, String Telefonor,
            ArrayList IdProductor, ArrayList NombreProductor, ArrayList txtDescripcion, String Fechar,
            ArrayList Precior, String estado,String abono, String valorTotal, Component mensaje, String activo) throws SQLException {

        this.ID = IDr;
        this.IdVenta = IDr;
        this.Activo = activo;
        this.IdCliente = IdClienter;
        this.NombreCliente = Nombrer;
        this.Telefono = Telefonor;
        this.IdProducto = IdProductor;
        this.NombreProducto = NombreProductor;
        this.Precio = Precior;
        this.Descripcion = txtDescripcion;
        this.Fecha = Fechar;
        this.Abono = abono;
        this.ValorTotal = valorTotal;
        Conexion conexion = new Conexion();
        try {

            String Instruccion = "Insert into base_productos values(?,?,?,?,?)";
            conexion.sentencia = conexion.getConexion().prepareStatement(Instruccion);
            conexion.sentencia.setString(1, ID);
            conexion.sentencia.setString(2, IdCliente);
            conexion.sentencia.setString(3, NombreCliente);
            conexion.sentencia.setString(4, Telefono);
            conexion.sentencia.setString(5, Fecha);
            conexion.sentencia.execute();

            JOptionPane.showMessageDialog(mensaje, "Registro Agregado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println("Error ");

            JOptionPane.showMessageDialog(mensaje, "Error en el registro", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
        try {
            String query = "INSERT INTO productos VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
            conexion.sentencia = conexion.getConexion().prepareStatement(query);

            for (int i = 0; i < IdProducto.size(); i++) {
                conexion.sentencia.setString(1, IdProducto.get(i).toString());
                conexion.sentencia.setString(2, IdVenta);
                conexion.sentencia.setString(3, NombreProducto.get(i).toString());
                conexion.sentencia.setString(4, Descripcion.get(i).toString());
                conexion.sentencia.setString(5, Precio.get(i).toString());
                conexion.sentencia.setString(6, estado);
                conexion.sentencia.setString(7, Activo);
                 conexion.sentencia.setString(8, Abono);
                             conexion.sentencia.setString(9, ValorTotal);

                System.out.println(query);
                conexion.sentencia.execute();
               
            }

            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mensaje, e, "Informacion", JOptionPane.ERROR_MESSAGE);
        }

       
        conexion.getConexion().close();
    }

    public boolean ValidarCampos(String txtIdCliente, String txtNombre, String txtTelefono, String txtFecha,String abono) {
        if (txtIdCliente == "" || txtNombre == "" || txtTelefono == "" || txtFecha == "" || abono == "") {
            return false;
        } else {
            return true;
        }
    }
}
