import java.util.ArrayList;

abstract class Vuelo {
    protected int numeroVuelo;
    protected String origen;
    protected String destino;
    protected String fecha;

    public Vuelo(int numeroVuelo, String origen, String destino, String fecha) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
    }

    public abstract double calcularPrecio();
}

interface Promocionable {
    void aplicarPromocion();
}

class VueloRegular extends Vuelo implements Promocionable {
    private int numeroAsientos;
    private double precioPorAsiento;
    private double descuento = 0.1; // 10% de descuento

    public VueloRegular(int numeroVuelo, String origen, String destino, String fecha, int numeroAsientos, double precioPorAsiento) {
        super(numeroVuelo, origen, destino, fecha);
        this.numeroAsientos = numeroAsientos;
        this.precioPorAsiento = precioPorAsiento;
    }

    @Override
    public double calcularPrecio() {
        return numeroAsientos * precioPorAsiento;
    }

    @Override
    public void aplicarPromocion() {
        precioPorAsiento -= precioPorAsiento * descuento;
    }
}

class VueloCharter extends Vuelo implements Promocionable {
    private double precioTotal;
    private double descuento = 0.15; // 15% de descuento

    public VueloCharter(int numeroVuelo, String origen, String destino, String fecha, double precioTotal) {
        super(numeroVuelo, origen, destino, fecha);
        this.precioTotal = precioTotal;
    }

    @Override
    public double calcularPrecio() {
        return precioTotal;
    }

    @Override
    public void aplicarPromocion() {
        precioTotal -= precioTotal * descuento;
    }
}

class Reservas {
    private ArrayList<Vuelo> vuelos;

    public Reservas() {
        vuelos = new ArrayList<>();
    }

    public void agregarVuelos(Vuelo vuelo) {
        vuelos.add(vuelo);
    }

    public double calcularTotalReservas() {
        double total = 0;
        for (Vuelo vuelo : vuelos) {
            total += vuelo.calcularPrecio();
        }
        return total;
    }

    public void aplicarPromociones() {
        for (Vuelo vuelo : vuelos) {
            if (vuelo instanceof Promocionable) {
                ((Promocionable) vuelo).aplicarPromocion();
            }
        }
    }

    public void mostrarInfoVuelos() {
        for (Vuelo vuelo : vuelos) {
            System.out.println("Vuelo Número: " + vuelo.numeroVuelo);
            System.out.println("Origen: " + vuelo.origen);
            System.out.println("Destino: " + vuelo.destino);
            System.out.println("Fecha: " + vuelo.fecha);
            System.out.println("Precio: " + vuelo.calcularPrecio());
            System.out.println("-------------");
        }
    }
}

public class SistemaDeReservas {
    public static void main(String[] args) {
        Reservas reservas = new Reservas();

        VueloRegular vuelo1 = new VueloRegular(101, "New York", "Los Angeles", "2023-12-24", 100, 150.0);
        VueloCharter vuelo2 = new VueloCharter(102, "Miami", "Madrid", "2023-12-25", 8000.0);

        reservas.agregarVuelos(vuelo1);
        reservas.agregarVuelos(vuelo2);

        System.out.println("Antes de aplicar promociones:");
        reservas.mostrarInfoVuelos();

        reservas.aplicarPromociones();

        System.out.println("Después de aplicar promociones:");
        reservas.mostrarInfoVuelos();

        double total = reservas.calcularTotalReservas();
        System.out.println("Total de las reservas: " + total);
    }
}
