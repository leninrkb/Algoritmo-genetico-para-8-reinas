
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

    //verifica que exista solo una R en la diagonal Abajo Derecha
    static Boolean existeSoloUnaRdiagonalAbajoDerecha(String[] tab, String c, int pos){
        int a = 0;

        if ((pos+1) == 8) {
            return true;
        }

        int posR = posicionRcadena(c);
        int vecesIterar = vecesIterarAbajo(posR);
        for (int i = pos; i < tab.length; i++) {
            if (vecesIterar < 0) {
                break;
            }
            if (tab[i].charAt(posR) == 'R') {
                a++;
            }
            posR++;
            vecesIterar--;
        }

        if (a == 1) {
            return true;
        }

        return false;
    }

    //verifica que exista solo una R en la diagonal Abajo Derecha
    static Boolean existeSoloUnaRdiagonalArribaDerecha(String[] tab, String c, int pos){
        int a = 0;

        if ((pos+1) == 8) {
            return true;
        }

        int posR = posicionRcadena(c);
        for (int i = pos; i < tab.length; i++) {
            if (posR < 0) {
                break;
            }
            if (tab[i].charAt(posR) == 'R') {
                a++;
            }
            posR--;
        }

        if (a == 1) {
            return true;
        }

        return false;
    }

    //verifica que exista solo una R en la diagonal Abajo izquierda
    static Boolean existeSoloUnaRdiagonalAbajoIzquierda(String[] tab, String c, int pos){
        int a = 0;

        if (pos == 0) {
            return true;
        }

        int posR = posicionRcadena(c);
        for (int i = pos; i >= 0; i--) {
            if (posR > 7) { 
                break;
            }
            if (tab[i].charAt(posR) == 'R') {
                a++;
            }
            posR++  ;
        }

        if (a == 1) {
            return true;
        }

        return false;
    }

    //verifica que exista solo una R en la diagonal Abajo izquierda
    static Boolean existeSoloUnaRdiagonalArribaIzquierda(String[] tab, String c, int pos){
        int a = 0;

        if (pos == 0) {
            return true;
        }

        int posR = posicionRcadena(c);
        for (int i = pos; i >= 0; i--) {
            if (posR < 0) { 
                break;
            }
            if (tab[i].charAt(posR) == 'R') {
                a++;
            }
            posR--;
        }

        if (a == 1) {
            return true;
        }

        return false;
    }


    //devuelve la posicion de la R en la cadena
    static int posicionRcadena(String c){
        for (int i = 0; i < c.length(); i++) {
            if (c.charAt(i) == 'R') {
                return i;
            }
        }
        return -1;
    }

    //devuelve el numero deveces que se debe iterar para comprobar la diagonal
    static int vecesIterarAbajo(int pos){
        return 7 - pos;
    }

    //devuelve el numero de veces que se debe iterar para comprobar la diagonal abajo izquierda
    static int vecesIterarAbajoIzquierda(int pos, int itera){
        int s = 7 - pos;
        int n = s - itera;
        int v = 7 - n;
        return v;
    }

    //imprime el tablero
    static void imprimirTab(String[] tab){
        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[0].charAt(i)+" "+tab[1].charAt(i)+" "+tab[2].charAt(i)+" "+tab[3].charAt(i)+" "+tab[4].charAt(i)+" "+tab[5].charAt(i)+" "+tab[6].charAt(i)+" "+tab[7].charAt(i));
        }
    }
}
