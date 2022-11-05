
public class Individuo {
    String[] tablero;
    int ataques;
    int compatibilidad;
    double peso;
    int prob;

    Individuo(String[] _tablero, int _ataques, int _compatibilidad) {
        this.tablero = _tablero;
        this.ataques = _ataques;
        this.compatibilidad = _compatibilidad;
    }

    Individuo() {
    }
}
