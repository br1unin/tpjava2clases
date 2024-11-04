
public class Persona {
    String paisnacimiento;
    String genero;

    Persona(String paisnacimiento, String genero) {
        this.paisnacimiento = paisnacimiento;
        this.genero = genero;
    }

    void imprimir() {
        System.out.println("Pais de nacimiento: " + paisnacimiento);
        System.out.println("Genero: " + genero);
    }
        public static void main (String args[]){
            Persona p1 = new Persona("Argelia", "H");
            Persona p2 = new Persona("Chile", "M");
            p1.imprimir();
            p2.imprimir();
        }
    }

