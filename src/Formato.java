import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * clase encargada de realizar las operaciones
 * que seran llamadas en el algoritmo
*/
public class Formato {

    static List<String[]> mutarHijos(int mutar, List<String[]> hijos){
        int mutarHijo = Formato.randomEntre(0, 1);
        List<String[]> nuevos = new ArrayList<>();
        hijos.get(mutarHijo)[mutar] = crearNuevaCadena(hijos.get(mutarHijo)[mutar]);
        nuevos.add(hijos.get(mutarHijo));
        if (mutarHijo == 0) {
            nuevos.add(hijos.get(1));
        }else{
            nuevos.add(hijos.get(0));
        }
        return nuevos;
    }

    //realixo el cruze en base al criterio de corte
    static List<String[]> cruzar(int corte, List<String[]> padres){
        List<String[]> nuevos = new ArrayList<>();
        String[] corte1 = new String[8];
        String[] corte2 = new String[8];

        
        for (int j = 0; j < 8; j++) {
            if (j <= corte) {
                corte1[j] = padres.get(0)[j];
            }else{
                corte1[j] = padres.get(1)[j];
            }

            if (j <= corte) {
                corte2[j] = padres.get(1)[j];
            }else{
                corte2[j] = padres.get(0)[j];
            }
        }

        nuevos.add(corte1);
        nuevos.add(corte2);

        return nuevos;
        
    }

    //selecciono los padres conforme a un individuo femenino 
    static List<String[]> seleccionarPadres(Individuo femenino, List<Individuo> candidatos){
        List<String[]> padres = new ArrayList<>();
        padres.add(femenino.tablero);
        padres.add(generarRuleta(calcularProbCandidatos(femenino.ataques, candidatos)));
        return padres;
    }

    //forma la ruleta de probabilidades
    static String[] generarRuleta(List<Individuo> candidatos){
        Individuo[] vec = new Individuo[100];
        String[] seleccionado = new String[8];
        HashMap<Integer,String> nums = new HashMap<>();
        int rand = 0;
        for (int i = 0; i < candidatos.size()-1; i++) {
            for (int j = 0; j < candidatos.get(i).prob; j++) {
                do {
                    rand = randomEntre(0, 99);
                    if (!nums.containsKey(rand)) {
                        nums.put(rand, "");
                        break;
                    }
                } while (true);
                vec[rand] = candidatos.get(i);
            }
        }
        for (int i = 0; i < vec.length; i++) {
            if (vec[i]==null) {
                vec[i]=candidatos.get(candidatos.size()-1);
            }
        }
        rand = randomEntre(0, 99);
        seleccionado = vec[rand].tablero;
        return seleccionado;
    }

    //calculo prob de los candidatos 
    static List<Individuo> calcularProbCandidatos(int mod, List<Individuo> candidatos){        
        int  denominador = candidatos.size()+1;
        int interaciones = candidatos.size();
        List<Individuo> inds = new ArrayList<>();
        double suma = 0;
        int porc = 0;

        for (int i = 0; i < interaciones; i++) {
            inds.add(mayorAtaqueCandidato(candidatos));
        }
        for (int i = 0; i < inds.size(); i++) {
            inds.get(i).peso = ((i+1)*100)/denominador;
            suma += inds.get(i).peso;
        }
        for (int i = 0; i < inds.size(); i++) {
            inds.get(i).prob = (int) Math.floor((inds.get(i).peso*100)/suma);
            porc += inds.get(i).prob;
        }
        int dif = 100 - porc;
        inds.get(inds.size()-1).prob+=dif;

        return inds;
    }

    //retorna el mayor de la lista
    static Individuo mayorAtaqueCandidato(List<Individuo> candidatos){
        Individuo ind = new Individuo();
        ind = candidatos.get(0);
        for (int i = 0; i < candidatos.size(); i++) {
            if (ind.ataques < candidatos.get(i).ataques) {
                ind = candidatos.get(i);
            }
        }
        candidatos.remove(ind);
        return ind;
    }

    //cuanta los ataques de un tablero
    public static int contarAtaques(String[] strings) {
        int a = 0;
        for (int i = 0; i < strings.length; i++) {
            if(contarRcolumna(strings[i]) > 1){
                a++;
            }
            if(contarRfila(strings, strings[i], i) > 1){
                a++;
            }
            if(contarRdiagonalAbajoDerecha(strings, strings[i], i) > 1){
                a++;
            }
            if(contarRdiagonalArribaDerecha(strings, strings[i], i) > 1){
                a++;
            }
            if(contarRdiagonalAbajoIzquierda(strings, strings[i], i) > 1){
                a++;
            }
            if(contarRdiagonalArribaIzquierda(strings, strings[i], i) > 1){
                a++;
            }
        }
        return a;
    }

    //seleciona 4-6-8 padres de forma random - retorna nueva instancia
    static List<String[]> seleccionarRandom(List<String[]> general){
        List<String[]> nuevo = new ArrayList<>();
        int[] n = {4,6,8};
        int rand = randomEntre(0, 2);
        for (int i = 0; i < general.size(); i++) {
            if (i == n[rand]) {
                break;
            }
            nuevo.add(general.get(randomEntre(0, general.size()-1)));
        }
        return nuevo;
    }

    //crea un nuevo tablero - retorna nueva instancia
    static String[] crearNuevoTablero(){
        String[] nuevo = {"R.......","R.......","R.......","R.......","R.......","R.......","R.......","R......."};
        int rand=0;
        for (int i = 0; i < nuevo.length; i++) {
            rand = randomEntre(0, 7);
            nuevo[rand] = crearNuevaCadena(nuevo[rand]);
        }
        
        return nuevo;
    }

    //crea un nuevo tablero - retorna nueva instancia
    static String[] crearNuevoTablero(String[] semilla){
        String[] nuevo = new String[8];
        nuevo = copiarTablero(semilla);

        int rand = randomEntre(0, 7);
        nuevo[rand] = crearNuevaCadena(semilla[rand]);
        return nuevo;
    }

    //crea una nueva cadena
    static String crearNuevaCadena(String c){
        String[] aux = cadenaToArreglo(c); 
        String s = new String();
        s = arregloToCadena(aux);
        int posR = posicionRcadena(s);
        int rand = randomEntre(0, 7, posR);

        s = reemplazarEnCadena(s, ".", posR);
        s = reemplazarEnCadena(s, "R", rand);
        return s;
    }

    //copia una cadena a un arreglo - retorna nueva instancia
    static String[] cadenaToArreglo(String c){
        String[] aux = new String[8]; 
        for (int i = 0; i < c.length(); i++) {
            aux[i] = String.valueOf(c.charAt(i));
        }
        return aux;
    }

    //copia una cadena a un arreglo - retorna nueva instancia
    static String arregloToCadena(String[] c){
        String aux = new String(); 
        for (int i = 0; i < c.length; i++) {
            aux = aux.concat(c[i]);
        }
        return aux;
    }

    //copia un talero - retorna nueva instancia
    static String[] copiarTablero(String[] old){
        String[] nuevo = new String[old.length];
        for (int i = 0; i < old.length; i++) {
           nuevo[i] = old[i];
        }
        return nuevo;
    }

    //remmplaza un caracter en la posicion dada - retorna nueva instancia
    static String reemplazarEnCadena(String old, String nw, int pos){
        String[] aux = cadenaToArreglo(old); 
        aux[pos] = nw;
        String n = new String();
        n = arregloToCadena(aux);
        return n;
    }

    //genera un entero randon entre los valores dados
    static int randomEntre(int ini, int fin) {
        int valorEntero = (int) Math.floor(Math.random()*(fin-ini+1)+ini); 
        return valorEntero;
    }

    //genera un entero random  diferente del dado
    static int randomEntre(int ini, int fin, int dif) {
        int rand = 0;
        do {
            rand = randomEntre(ini, fin);
        } while (rand == dif);
        return rand;
    }

    //verifica la longitud de cada cadena, debe ser 8
    static Boolean verificarLongTab(String[] tab){
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].length() != 8) {
                System.out.println("longitud incorrecta en la cadena N0. " + (i+1));
                return false;
            }
        }
        return true;
    }

    ///////////////////////////////////////verificacion
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

    ////////////////////////////////para contar
    static int contarRcolumna(String c){
        int a = 0;
        for (int i = 0; i < c.length(); i++) {
            if (c.charAt(i) == 'R') {
                a++;
            }           
        }
        return a;
    }

    //verifica que exista solo una R en la fila correspondiente
    static int contarRfila(String[] tab, String c, int pos){
        int a = 0;
        
        if ((pos+1) == 8) {
            return a;
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
        return a;
    }


    static int contarRdiagonalAbajoDerecha(String[] tab, String c, int pos){
        int a = 0;

        if ((pos+1) == 8) {
            return a;
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
        return a;
    }

    static int contarRdiagonalArribaDerecha(String[] tab, String c, int pos){
        int a = 0;

        if ((pos+1) == 8) {
            return a;
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

        return a;
    }

    static int contarRdiagonalAbajoIzquierda(String[] tab, String c, int pos){
        int a = 0;

        if (pos == 0) {
            return a;
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

        return a;
    }

    static int contarRdiagonalArribaIzquierda(String[] tab, String c, int pos){
        int a = 0;

        if (pos == 0) {
            return a;
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

        return a;
    }

    ////////////////////////////////////////////////////////
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

    //imprime el tablero
    static void imprimirTab(String[] tab){
        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[0].charAt(i)+" "+tab[1].charAt(i)+" "+tab[2].charAt(i)+" "+tab[3].charAt(i)+" "+tab[4].charAt(i)+" "+tab[5].charAt(i)+" "+tab[6].charAt(i)+" "+tab[7].charAt(i));
        }
        System.out.println("");
    }

    
}
