import java.util.ArrayList;
import java.util.List;

/*
 * clase encargada explicitamente de contener
 * los metodos correspodientes al algoritmo 
*/
public class Algoritmo {

    //selecciono la poblacion inicial del general (4-8)
    static List<String[]> seleccionarPoblacionInicial(List<String[]> poblacionGeneral){
        List<String[]> nueva = new ArrayList<>();
        return nueva;
    }

    //genera una poblacion partiendo de una semilla
    static List<String[]> generarPoblacion(String[] semilla, int densidadPoblacion){
        List<String[]> poblacion = new ArrayList<>();

        for (int i = 0; i < densidadPoblacion; i++) {
            String[] nuevo = new String[8];
            nuevo = Formato.crearNuevoTablero(semilla);
            poblacion.add(nuevo);
        }

        return poblacion;
    }

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
