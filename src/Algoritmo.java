import java.util.ArrayList;
import java.util.List;

/*
 * clase encargada explicitamente de contener
 * los metodos correspodientes al algoritmo 
*/
public class Algoritmo {

    //realizo la mutacion
    static List<String[]> mutacion(List<String[]> hijos){
        int mutar = Formato.randomEntre(0, 7);
        return Formato.mutarHijos(mutar, hijos);
    }

    //realizo el crossover
    static List<String[]> cruze(List<String[]> padres){
        int corte = Formato.randomEntre(1, 8);
        return Formato.cruzar(corte, padres);
    }

    //selecciono los padres de forma random del vector de probabilidades
    static List<String[]> seleccionarPadres(List<String[]> poblacion){
        List<String[]> padres = new ArrayList<>();
        List<Individuo> indList = new ArrayList<>();
        for (int i = 0; i < poblacion.size(); i++) {
            Individuo ind = new Individuo(poblacion.get(i),Formato.contarAtaques(poblacion.get(i)),0);
            indList.add(ind);
        }

        Individuo femenino = new Individuo();
        femenino = indList.get(Formato.randomEntre(0, indList.size()-1));
        indList.remove(femenino);
        padres = Formato.seleccionarPadres(femenino,indList);

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
        //Formato.imprimirTab(tab);
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
