
package proyectofinal.Logica;

import proyectofinal.persistencia.Persistencia;

public class Controladora {
    Persistencia  persis =new Persistencia();

    public String recibirUsuario(String user, String pass)  {
        
         return persis.validarUsuario(user, pass);
    }
    public String recibirDia(){
    
    return persis.cerrarDia();
    }
}
