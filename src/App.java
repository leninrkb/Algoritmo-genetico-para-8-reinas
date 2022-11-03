
public class App {
    public static void main(String[] args) throws Exception {
        //String[] tabInicial = { "....R...", "..R.....", "R.......", ".....R..", ".......R", ".R......", "...R....","......R." };
        String[] tabInicial = { ".R......", "....R...", "..R.....", "...R....", "......R.", ".R......", "...R....","......R." };

        Formato.imprimirTab(tabInicial);

        if (Formato.verificarLongTab(tabInicial)) {
            if (Algoritmo.esSolucion(tabInicial)) {
                System.out.println("es solucion");
            } else {
                System.out.println("no es solucion, buscando una . . .");
                Algoritmo.generarPoblacion(tabInicial,100);
            }

        } else {
            System.out.println("no se puede resolver, tablero incorrecto");
        }

    }
}
