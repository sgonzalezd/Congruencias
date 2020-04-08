package logica;

public class Ecuacion {
    private int valor;
    private int modulo;
    public Ecuacion(int valor, int modulo){
        this.valor = valor;
        this.modulo = modulo;
    }
    /**
     * Metodo GET del modulo
     * @return modulo
     */
    public int getModulo() {
        return modulo;
    }
    /**
     * Metodo GET del valor
     * @return valor
     */
    public int getValor() {
        return valor;
    } 

    @Override
    public String toString(){
        return "x = "+ valor + "(mod" + modulo + ")";
    }
}