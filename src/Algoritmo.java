import java.util.ArrayList;
import java.util.List;

/*
 * clase encargada explicitamente de contener
 * los metodos correspodientes al algoritmo 
*/
public class Algoritmo {

    static List<String[]> seleccionarPadres(List<String[]> poblacion){
        List<String[]> padres = new ArrayList<>();
        int rand = Formato.randomEntre(0, padres.size());
        int ataques = Formato.contarAtaques(padres.get(rand));
        int  compatibilidad = 0;
        Individuo femenino = new Individuo(padres.get(rand),ataques,compatibilidad);

        return padres;
    }

    //selecciono la poblacion inicial del general (4-8) - retorna nueva instancia
    static List<String[]> seleccionarPoblacionInicial(List<String[]> poblacionGeneral){
        List<String[]> nueva = new ArrayList<>();
        nueva = Formato.seleccionarRandom(poblacionGeneral);
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
