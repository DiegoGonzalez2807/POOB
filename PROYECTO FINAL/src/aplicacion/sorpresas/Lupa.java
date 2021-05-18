package aplicacion.sorpresas;

import aplicacion.*;
import java.io.Serializable;

public class Lupa extends Sorpresa implements Serializable  {

    public Lupa(Tablero tablero, Posicion posicion) {
        super(tablero,posicion);
    }
        
    @Override
    public void makeVisible() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeInvisible() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mover(String direccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPosicion(Posicion posicion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
