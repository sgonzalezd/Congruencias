package logica;

import java.util.Stack;

public class Congruencia {
    private String cache;
    private Stack<int[]> pila;

    public Congruencia() {
        this.pila = new Stack<>();
        this.cache = "";
    }

    /**
     * @return the cache
     */
    public String getCache() {
        String cache2 = cache;
        cache = "";
        return cache2;
    }

    public void println(String cadena) {
        this.cache = this.cache + cadena + "\n";
    }

    public Ecuacion resolverSistema(Stack<Ecuacion> sistema) {
        Congruencia congruencia = new Congruencia();
        Stack<Ecuacion> sistemaC = (Stack<Ecuacion>) sistema.clone();
        while (sistemaC.size() > 1) {
            Ecuacion ecuacion1 = sistemaC.pop();
            Ecuacion ecuacion2 = sistemaC.pop();
            int c = (ecuacion2.getValor() - ecuacion1.getValor());
            int mcd = congruencia.mcd(ecuacion1.getModulo(), -1 * ecuacion2.getModulo());
            int y0 = 0; 
            if (c % ecuacion2.getModulo() == 0) {
            } else {
                congruencia.mcdP(ecuacion1.getModulo(), ecuacion2.getModulo());
                int[] combinacion = congruencia.combinacionLinealP();
                y0 = combinacion[1] * (c / mcd);
            }
            sistemaC.add(new Ecuacion((ecuacion1.getValor() + ecuacion1.getModulo() * y0),
                    Math.abs((ecuacion1.getModulo() * ecuacion2.getModulo()) / mcd)));
        }
        return sistemaC.pop();
    }

    public Ecuacion resolverSistemaP(Stack<Ecuacion> sistema) {
        Congruencia congruencia = new Congruencia();
        Stack<Ecuacion> sistemaC = (Stack<Ecuacion>) sistema.clone();
        println("\n\nNuestro sistema a resolver es: ");
        for (Ecuacion i : sistemaC) {
            println(i.toString());
        }
        while (sistemaC.size() > 1) {
            Ecuacion ecuacion1 = sistemaC.pop();
            Ecuacion ecuacion2 = sistemaC.pop();
            println("\n\nResolviendo: \n" + ecuacion1.toString() + "\n" + ecuacion2.toString());
            println("Llegamos a la ecuacion lineal: " + ecuacion1.getModulo() + "y=" + ecuacion2.getValor() + "-"
                    + ecuacion1.getValor() + "(mod " + ecuacion2.getModulo() + ")");
            println("Reduciendo: " + ecuacion1.getModulo() + "y=" + (ecuacion2.getValor() - ecuacion1.getValor())
                    + "(mod " + ecuacion2.getModulo() + ")");
            int c = (ecuacion2.getValor() - ecuacion1.getValor());
            int mcd = congruencia.mcd(ecuacion1.getModulo(), -1 * ecuacion2.getModulo());
            int y0 = 0; 
            if (c % ecuacion2.getModulo() == 0) {
                c = 0;
                println("Pasandolo a su forma diofantina: " + ecuacion1.getModulo() + "y+" + ecuacion2.getModulo()
                + "x=" + c);
            } else {
                println("Pasandolo a su forma diofantina: " + ecuacion1.getModulo() + "y+" + ecuacion2.getModulo()
                        + "x=" + c);
                congruencia.mcdP(ecuacion1.getModulo(), ecuacion2.getModulo());
                String cadena = congruencia.getCache();
                println("Por algoritmo de euclides: \n" + cadena.substring(0, cadena.length()-2));
                println("(" + ecuacion1.getModulo() + "," + ecuacion2.getModulo() + ")=" + mcd);
                int[] combinacion = congruencia.combinacionLinealP();
                cadena = congruencia.getCache();
                println("Expresandolo como una combinacion lineal: \n" + cadena.substring(0, cadena.length()-2));
                println("(y0', z0')=(" + combinacion[1] + "," + combinacion[3] + ")");
                y0 = combinacion[1] * (c / mcd);
                println("Y asi la solucion del sistema es (y0, z0)=(" + combinacion[1] * (c / mcd) + ","
                        + combinacion[3] * (c / mcd) + ")");
            }
            println("Por lo que y0= "+y0);
            println("De forma que y =" + y0 + "+ (" + ecuacion2.getModulo() + "/" + mcd + ")" + "z");
            println("y =" + y0 + "+" + Math.abs(ecuacion2.getModulo() / mcd) + "z");
            println("Entonces x =" + ecuacion1.getValor() + "+" + ecuacion1.getModulo() + "(" + y0 + "+"
                    + Math.abs(ecuacion2.getModulo() / mcd) + "z" + ")");
            println("Pasandolo a congruencias: x=" + (ecuacion1.getValor() + ecuacion1.getModulo() * y0) + "(mod"
                    + (Math.abs(ecuacion2.getModulo() / mcd)) + ")");
            println("Finalmente nuestro sistema de ecuaciones es:");
            sistemaC.add(new Ecuacion((ecuacion1.getValor() + ecuacion1.getModulo() * y0),
                    Math.abs((ecuacion1.getModulo() * ecuacion2.getModulo()) / mcd)));
            for (Ecuacion i : sistemaC) {
                println(i.toString());
            }
        }
        return sistemaC.pop();
    }

    public boolean tieneSolucion(Stack<Ecuacion> sistema) {
        Stack<Ecuacion> sistemaC = (Stack<Ecuacion>) sistema.clone();

        while (!sistemaC.isEmpty()) {
            Ecuacion ecuacion1 = sistemaC.pop();
            for (Ecuacion ecuacion2 : sistemaC) {
                int x = (ecuacion2.getValor() - ecuacion1.getValor());
                int y = this.mcd(ecuacion1.getModulo(), ecuacion2.getModulo());
                if ((ecuacion2.getValor() - ecuacion1.getValor())
                        % this.mcd(ecuacion1.getModulo(), ecuacion2.getModulo()) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean tieneSolucionP(Stack<Ecuacion> sistema) {
        println("Veamos si se puede resolver:");
        Stack<Ecuacion> sistemaC = (Stack<Ecuacion>) sistema.clone();

        while (!sistemaC.isEmpty()) {
            Ecuacion ecuacion1 = sistemaC.pop();
            for (Ecuacion ecuacion2 : sistemaC) {
                int x = (ecuacion2.getValor() - ecuacion1.getValor());
                int y = this.mcd(ecuacion1.getModulo(), ecuacion2.getModulo());
                if ((ecuacion2.getValor() - ecuacion1.getValor())
                        % this.mcd(ecuacion1.getModulo(), ecuacion2.getModulo()) != 0) {
                    println("(" + ecuacion1.getModulo() + ", " + ecuacion2.getModulo() + ")="
                            + this.mcd(ecuacion1.getModulo(), ecuacion2.getModulo()) + " no divide a "
                            + (ecuacion2.getValor() - ecuacion1.getValor()) + "=" + ecuacion2.getValor() + "-"
                            + ecuacion1.getValor());
                    println("Por lo que no se puede resolver");
                    return false;
                } else {
                    println("(" + ecuacion1.getModulo() + ", " + ecuacion2.getModulo() + ")="
                            + this.mcd(ecuacion1.getModulo(), ecuacion2.getModulo()) + "|"
                            + (ecuacion2.getValor() - ecuacion1.getValor()) + "=" + ecuacion2.getValor() + "-"
                            + ecuacion1.getValor());
                }
            }
        }
        println("Por lo que se puede resolver");
        return true;
    }

    public int mcdP(int a, int b) {
        this.pila.clear();
        return mcdPAux(a, b);
    }

    public int mcd(int a, int b) {
        this.pila.clear();
        return mcdAux(a, b);
    }

    public int mcdPAux(int a, int b) {
        int r = b % a;
        int q = b / a;
        println(b + "=" + a + "(" + q + ")  +" + r);
        pila.add(new int[] { b, a, q, r });
        if (r == 0) {
            return a;
        } else {
            return mcdPAux(r, a);
        }
    }

    public int mcdAux(int a, int b) {
        int r = b % a;
        int q = b / a;
        pila.add(new int[] { b, a, q, r });
        if (r == 0) {
            return a;
        } else {
            return mcdAux(r, a);
        }
    }

    public int[] combinacionLinealP() {
        int x = this.pila.pop()[1];
        int[] pe = this.pila.pop();
        int[] expresion = new int[] { pe[0], 1, pe[1], pe[2] * -1 }; // Coeficientes
        println(x + "=" + "(" + expresion[0] + ")" + "(" + expresion[1] + ")+" + "(" + expresion[2] + ")" + "("
                + expresion[3] + ")");
        while (!this.pila.isEmpty()) {
            var elem = this.pila.pop();
            if (elem[3] == expresion[0]) {
                expresion[0] = elem[0];
                expresion[3] = expresion[1] * elem[2] * -1 + expresion[3];
            } else {
                expresion[2] = elem[0];
                expresion[1] = expresion[3] * elem[2] * -1 + expresion[1];
            }
            println(x + "=" + "(" + expresion[0] + ")" + "(" + expresion[1] + ")+" + "(" + expresion[2] + ")" + "("
                    + expresion[3] + ")");
        }
        return expresion;
    }

    public int[] combinacionLineal() {
        int x = this.pila.pop()[1];
        int[] pe = this.pila.pop();
        int[] expresion = new int[] { pe[0], 1, pe[1], pe[2] * -1 }; // Coeficientes
        while (!this.pila.isEmpty()) {
            var elem = this.pila.pop();
            if (elem[3] == expresion[0]) {
                expresion[0] = elem[0];
                expresion[3] = expresion[1] * elem[2] * -1 + expresion[3];
            } else {
                expresion[2] = elem[0];
                expresion[1] = expresion[3] * elem[2] * -1 + expresion[1];
            }
        }
        return expresion;
    }

}