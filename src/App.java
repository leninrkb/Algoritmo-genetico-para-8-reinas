import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        //String[] semilla = { "....R...", "..R.....", "R.......", ".....R..",".......R", ".R......", "...R....","......R." };
        String[] semilla = { ".R......", "....R...", "..R.....", "...R....", "......R.", ".R......", "...R....", "......R." };
        // String[] semilla = Formato.crearNuevoTablero(tabInicial);
        // Formato.imprimirTab(semilla);

        if (Formato.verificarLongTab(semilla)) {// verifico si esta bien formado
            if (Algoritmo.esSolucion(semilla)) {// ya esta resuelto?
                Formato.imprimirTab(semilla);
                System.out.println("es solucion");
            } else {
                System.out.println("no es solucion, buscando una . . .");
                //////////////////////////////////
                List<String[]> poblacionGeneral = new ArrayList<>();
                poblacionGeneral = Algoritmo.generarPoblacion(semilla, 50);// genero la poblacion general

                List<String[]> poblacionInicial = new ArrayList<>();
                poblacionInicial = Algoritmo.seleccionarPoblacionInicial(poblacionGeneral);

                List<String[]> padres = new ArrayList<>();
                List<String[]> hijos = new ArrayList<>();
                // comiezo iteraciones
                int i = 0;
                do {
                    hijos = null;
                    padres = Algoritmo.seleccionarPadres(poblacionInicial);
                    hijos = Algoritmo.cruze(padres);
                    poblacionInicial.remove(padres.get(0));
                    poblacionInicial.remove(padres.get(1));
                    padres = null;
                    hijos = Algoritmo.mutacion(hijos);
                    poblacionInicial.add(hijos.get(0));
                    poblacionInicial.add(hijos.get(1));
                    
                    i++;
                    System.out.println(i);
                } while (Algoritmo.esSolucion(hijos.get(0)) == false );
                Formato.imprimirTab(hijos.get(0));
                Formato.imprimirTab(hijos.get(1));
            }

        } else {
            System.out.println("no se puede resolver, tablero incorrecto");
        }

    }
}
