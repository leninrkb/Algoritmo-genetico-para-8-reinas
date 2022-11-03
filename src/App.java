
public class App {
    public static void main(String[] args) throws Exception {
        String[] tabInicial = { "R.......", ".R......", "....R...", "...R....", ".....R..", ".R......", "......R.",
                ".......R" };

        if (Formato.verificarLongTab(tabInicial)) {
            Formato.imprimirTab(tabInicial);
            Algoritmo.esSolucion(tabInicial);

        } else {
            System.out.println("no se puede resolver, tablero incorrecto");
        }

    }
}
