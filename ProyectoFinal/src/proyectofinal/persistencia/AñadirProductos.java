
package proyectofinal.persistencia;

import java.awt.Component;
import static java.lang.Math.random;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AñadirProductos {
    
    
    public void Añadir(Component mensaje, DefaultTableModel modelo, JTable productos, 
        String nombreProducto, String precioProducto, String descripcion){
        
        
        Object[]fila = new Object[4];
        fila[0] = "";
        fila[1] = nombreProducto;
        fila[2] = precioProducto;
        fila[3] = descripcion;
        modelo.addRow(fila);
        
        modelo.addRow(fila);
        productos.setModel(modelo);
    }
    
    public String GenerarID(){
        Date fecha = new Date();
        SimpleDateFormat idFecha = new SimpleDateFormat("YYYYMMddhhmmss");
        int random = (int) Math.floor(Math.random() * 100) + (int) Math.floor(Math.random() * 10);
        String idProducto = String.valueOf(idFecha.format(fecha)) + random;
        return idProducto;
    }
    
}
