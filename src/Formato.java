
/*
 * clase encargada de realizar las operaciones
 * que seran llamadas en el algoritmo
*/
public class Formato {

    //verifica que la longitud de cada cadena, debe ser 8
    static Boolean verificarLongTab(String[] tab){
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].length() != 8) {
                System.out.println("longitud incorrecta en la cadena N0. " + (i+1));
                return false;
            }
        }
        return true;
    }

    //verifica que exista solo una R por  columna
    static Boolean existeSoloUnaRcolumna(String c){
        int a = 0;
        for (int i = 0; i < c.length(); i++) {
            if (c.charAt(i) == 'R') {
                a++;
            }           
        }
        if (a == 1) {
            return true;
        }
        return false;
    }

    //verifica que exista solo una R en la fila correspondiente
    static Boolean existeSoloUnaRfila(String[] tab, String c, int pos){
        int a = 0;

        if ((pos+1) == 8) {
            return true;
        }

        for (int i = 0; i < c.length(); i++) {
            if (c.charAt(i) == 'R') {
                a++;
                for (int j = (pos+1); j < tab.length; j++) {
                    if (tab[j].charAt(i) == 'R') {
                        a++;
                    }
                }
            }           
        }
        if (a == 1) {
            return true;
        }
        return false;
    }

    //imprime el tablero
    static void imprimirTab(String[] tab){
        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[0].charAt(i)+" "+tab[1].charAt(i)+" "+tab[2].charAt(i)+" "+tab[3].charAt(i)+" "+tab[4].charAt(i)+" "+tab[5].charAt(i)+" "+tab[6].charAt(i)+" "+tab[7].charAt(i));
        }
    }
}
