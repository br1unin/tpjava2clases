import java.util.ArrayList;
import java.util.Scanner;

class Nota {
    private String catedra;
    private double notaExamen;

    public Nota(String catedra, double notaExamen) {
        this.catedra = catedra;
        this.notaExamen = notaExamen;
    }

    public double getNotaExamen() {
        return notaExamen;
    }

    @Override
    public String toString() {
        return "Cátedra: " + catedra + ", Nota: " + notaExamen;
    }
}

class Alumno {
    private String nombreCompleto;
    private long legajo;
    private ArrayList<Nota> notas;

    public Alumno(String nombreCompleto, long legajo) {
        this.nombreCompleto = nombreCompleto;
        this.legajo = legajo;
        this.notas = new ArrayList<>();
    }

    public void agregarNota(Nota nota) {
        notas.add(nota);
    }

    public double calcularPromedio() {
        double suma = 0;
        for (Nota nota : notas) {
            suma += nota.getNotaExamen();
        }
        return suma / notas.size();
    }

    @Override
    public String toString() {
        return "Alumno: " + nombreCompleto + ", Legajo: " + legajo + ", Promedio: " + calcularPromedio();
    }
}

public class CargaNotas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Alumno> alumnos = new ArrayList<>();

        System.out.print("Ingrese la cantidad de alumnos: ");
        int cantidadAlumnos = scanner.nextInt();

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Ingrese el nombre completo del alumno: (en lugar de espacios coloque ´_´) ");
            String nombre = scanner.next();
            System.out.print("Ingrese el número de legajo del alumno: ");
            long legajo = scanner.nextLong();
            Alumno alumno = new Alumno(nombre, legajo);

            System.out.print("Ingrese la cantidad de notas: ");
            int cantidadNotas = scanner.nextInt();

            if (cantidadNotas < 1) {
                System.out.println("Debe ingresar al menos una nota.");
                i--;
                continue;
            }

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.print("Ingrese la cátedra: ");
                String catedra = scanner.next();
                System.out.print("Ingrese la nota del examen: ");
                double notaExamen = scanner.nextDouble();
                Nota nota = new Nota(catedra, notaExamen);
                alumno.agregarNota(nota);
            }

            alumnos.add(alumno);
        }

        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }

        scanner.close();
    }
}
