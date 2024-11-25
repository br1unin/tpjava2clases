import java.util.ArrayList;
import java.util.Scanner;

class DetalleFactura {
    private String codigoArticulo;
    private String nombreArticulo;
    private int cantidad;
    private double precioUnitario;
    private double descuentoItem;
    private double subtotal;

    public DetalleFactura(String codigoArticulo, String nombreArticulo, int cantidad, double precioUnitario) {
        this.codigoArticulo = codigoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        calcularDescuentoYSubtotal();
    }

    private void calcularDescuentoYSubtotal() {
        if (cantidad > 5) {
            descuentoItem = precioUnitario * 0.1 * cantidad;
        } else {
            descuentoItem = 0;
        }
        subtotal = precioUnitario * cantidad - descuentoItem;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return codigoArticulo + " " + nombreArticulo + " " + cantidad + " " + precioUnitario +
                " " + descuentoItem + " " + subtotal;
    }
}

class Factura {
    private String fechaFactura;
    private long numeroFactura;
    private double totalCalculadoFactura;
    private String cliente;
    private ArrayList<DetalleFactura> detallesFactura = new ArrayList<>();

    public Factura(String fechaFactura, long numeroFactura, String cliente) {
        this.fechaFactura = fechaFactura;
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
    }

    public void agregarDetalle(DetalleFactura detalle) {
        detallesFactura.add(detalle);
    }

    public void calcularMontoTotal() {
        totalCalculadoFactura = 0;
        for (DetalleFactura detalle : detallesFactura) {
            totalCalculadoFactura += detalle.getSubtotal();
        }
    }

    @Override
    public String toString() {
        calcularMontoTotal();
        StringBuilder facturaString = new StringBuilder("Fecha: " + fechaFactura + "\n" +
                "Numero: " + numeroFactura + "\n" + "Cliente: " + cliente + "\n");
        for(DetalleFactura detalle : detallesFactura) {
            facturaString.append(detalle.toString()).append("\n");
        }
        facturaString.append("Total: ").append(totalCalculadoFactura);
        return facturaString.toString();
    }
}

public class Facturacion {
    private static String[][] articulos = {
            {"101", "Leche", "25"},
            {"102", "Gaseosa", "30"},
            {"103", "Fideos", "15"},
            {"104", "Arroz", "28"},
            {"105", "Vino", "120"},
            {"106", "Manteca", "20"},
            {"107", "Lavandina", "18"},
            {"108", "Detergente", "46"},
            {"109", "Jabón en Polvo", "96"},
            {"110", "Galletas", "60"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la fecha de factura:");
        String fecha = scanner.nextLine();
        System.out.println("Ingrese el número de factura (mayor a cero):");
        long numero = scanner.nextLong();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println("Ingrese el nombre del cliente:");
        String cliente = scanner.nextLine();
        Factura factura = new Factura(fecha, numero, cliente);

        String codigo;
        do {
            System.out.println("Ingrese el código del artículo a facturar ('fin' para terminar):");
            codigo = scanner.nextLine();
            if (!codigo.equals("fin")) {
                int cantidad = 0;
                double precio = 0.0;
                String nombreArticulo = "";
                boolean articuloEncontrado = false;
                for (String[] articulo : articulos) {
                    if (articulo[0].equals(codigo)) {
                        nombreArticulo = articulo[1];
                        precio = Double.parseDouble(articulo[2]);
                        articuloEncontrado = true;
                        break;
                    }
                }

                if (!articuloEncontrado) {
                    System.out.println("El código ingresado no existe, intente nuevamente.");
                    continue;
                }

                System.out.println("Ingrese la cantidad a facturar:");
                cantidad = scanner.nextInt();
                scanner.nextLine();

                DetalleFactura nuevoDetalle = new DetalleFactura(codigo, nombreArticulo, cantidad, precio);
                factura.agregarDetalle(nuevoDetalle);
            }
        } while (!codigo.equals("fin"));

        System.out.println(factura.toString());
    }
}
