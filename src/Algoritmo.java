
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
                    System.out.println("funciona");
                }else{
                    System.out.println("algo paso");
                }
            }
        }
        return false;
    }  
}
