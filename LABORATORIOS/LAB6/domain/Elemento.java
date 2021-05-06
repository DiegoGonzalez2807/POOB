package domain;
import java.awt.Color;
import java.io.*;

/*No olviden adicionar la documentacion*/
public interface Elemento extends Serializable{
  int REDONDA = 1;
  int CUADRADA = 2;


  default void decida(){
  };
   
  default void cambie(){
  };
  
  default int forma(){
          return REDONDA;
      }
  
  default Color getColor(){
      return Color.black;
  };
  
  default boolean isVivo(){
      return false;
  }
  
}
