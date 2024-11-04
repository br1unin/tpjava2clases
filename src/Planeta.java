public class Planeta {
    double periodorbital;
    double periodorotacion;
    String nombre;
    Planeta(String nombre, double periodorbital, double periodorotacion) {
        this.nombre = nombre;
        this.periodorbital = periodorbital;
        this.periodorotacion = periodorotacion;
    }

    void imprimir() {
        System.out.println("Planeta: " +nombre);
        System.out.println("El periodo orbital es de: " + periodorbital +" año(s)");
        System.out.println("El periodo de rotacion es de: " + periodorotacion +" dia(s)");
    }
    public static void main (String args[]){
       Planeta p1 = new Planeta("Tierra", 1,1 );
        Planeta p2 = new Planeta("Jupiter", 11.86,0.41354 );

        p1.imprimir();
        p2.imprimir();
    }
}

