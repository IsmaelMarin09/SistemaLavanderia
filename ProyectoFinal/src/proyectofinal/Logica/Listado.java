
package proyectofinal.Logica;

import proyectofinal.Logica.Producto;
import java.util.ArrayList;

public class Listado {
    private ArrayList<Producto> lista;
    
    public Listado() {
        lista =  new ArrayList<>();
        LlenarProducto();
    }

    public ArrayList<Producto> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Producto> lista) {
        this.lista = lista;
    }
    
    
     public void LlenarProducto(){
        Producto defualt = new Producto(1,"", "");
        Producto prenda1 = new Producto(2,"Buso", "6500");
        Producto prenda2 = new Producto(3,"Blazer", "6500");
        Producto prenda3 = new Producto(4,"Chaqueta", "8000");
        Producto prenda4 = new Producto(5,"Chaqueta de Moto", "15000");
        Producto prenda5 = new Producto(6,"Gaban", "10000");
        Producto prenda6 = new Producto(7,"Camisa", "5500");
        Producto prenda7 = new Producto(8,"Camiseta", "5500");
        Producto prenda8 = new Producto(9,"Corbata", "4000");
        Producto prenda9 = new Producto(10,"Pantalon", "5500");
        Producto prenda10 = new Producto(11,"Falda", "5500");
        Producto prenda11 = new Producto(12,"Bermuda", "5500");
        Producto prenda12 = new Producto(13,"Tenis", "14000");
        Producto prenda13 = new Producto(14,"Traje Señor", "9500");
        Producto prenda14 = new Producto(15,"Bufanda", "5500");
        Producto prenda15 = new Producto(16,"Ruana", "12000");
        
        lista.add(defualt);
        lista.add(prenda1);
        lista.add(prenda2);
        lista.add(prenda3);
        lista.add(prenda4);
        lista.add(prenda5);
        lista.add(prenda6);
        lista.add(prenda7);
        lista.add(prenda8);
        lista.add(prenda9);
        lista.add(prenda10);
        lista.add(prenda11);
        lista.add(prenda12);
        lista.add(prenda13);
        lista.add(prenda14);
        lista.add(prenda15);
        
    }
}
