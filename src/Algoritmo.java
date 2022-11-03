
/*
 * clase encargada explicitamente de contener
 * los metodos correspodientes al algoritmo 
*/
public class Algoritmo {

    //verifico si el tablero es una solucion
    static Boolean esSolucion(String[] tab){
        for (int i = 0; i < tab.length; i++) {
            if (Formato.existeSoloUnaRcolumna(tab[i])) {
                if (Formato.existeSoloUnaRfila(tab,tab[i],i)) {
                    if(Formato.existeSoloUnaRdiagonalAbajoDerecha(tab, tab[i], i)){
                        if (Formato.existeSoloUnaRdiagonalArribaDerecha(tab, tab[i], i)) {
                            if (Formato.existeSoloUnaRdiagonalAbajoIzquierda(tab, tab[i], i)) {
                                if (Formato.existeSoloUnaRdiagonalArribaIzquierda(tab, tab[i], i)) {
                                }else{
                                    return false;
                                }
                            }else{
                                return false;
                            }
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }  
}
