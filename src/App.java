import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        //String[] tabInicial = { "....R...", "..R.....", "R.......", ".....R..", ".......R", ".R......", "...R....","......R." };
        String[] tabInicial = { ".R......", "....R...", "..R.....", "...R....", "......R.", ".R......", "...R....","......R." };
        String[] semilla = Formato.crearNuevoTablero(tabInicial);
        Formato.imprimirTab(semilla);

        List<String[]> poblacionGeneral = new ArrayList<>();
        poblacionGeneral = Algoritmo.generarPoblacion(semilla,50);//genero la poblacion general

        List<String[]> poblacionInicial = new ArrayList<>();
        poblacionInicial = Algoritmo.seleccionarPoblacionInicial(poblacionGeneral);

        if (Formato.verificarLongTab(semilla)) {//verifico si esta bien formado
            if (Algoritmo.esSolucion(semilla)) {//ya esta resuelto?
                System.out.println("es solucion");
            } else {
                System.out.println("no es solucion, buscando una . . .");
                
            }

        } else {
            System.out.println("no se puede resolver, tablero incorrecto");
        }

    }
}
