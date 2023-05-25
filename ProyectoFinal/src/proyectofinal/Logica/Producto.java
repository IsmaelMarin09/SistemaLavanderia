
package proyectofinal.Logica;


public class Producto {
    private int id;
    private String producto ;
    private String precio;
    
    public Producto(String producto) {  
        this.producto = producto;
    }

    public Producto(int id, String producto, String precio) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
    }
   

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String toProducto() {
        return producto;
    }
    
    public String toPrecio(){
        return precio;
    }
}
