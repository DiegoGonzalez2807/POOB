package aplicacion.sorpresas;

import aplicacion.*;
import java.io.Serializable;

public abstract class FlechasVelocidad extends Sorpresa  implements Serializable {

    public FlechasVelocidad(Tablero tablero, Posicion posicion) {
        super(tablero,posicion);
	}

    @Override
    public void efecto(Usuario jugador){
        
    }
}
