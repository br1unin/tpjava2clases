abstract class MetodoPago {
    protected String titular;
    protected String numero;

    public MetodoPago(String titular, String numero) {
        this.titular = titular;
        this.numero = numero;
    }

    public abstract void realizarPago();
}

interface Cancelable {
    void cancelarPago();
}

class TarjetaCredito extends MetodoPago implements Cancelable {
    private String fechaExpiracion;
    private int codigoSeguridad;

    public TarjetaCredito(String titular, String numero, String fechaExpiracion, int codigoSeguridad) {
        super(titular, numero);
        this.fechaExpiracion = fechaExpiracion;
        this.codigoSeguridad = codigoSeguridad;
    }

    @Override
    public void realizarPago() {
        System.out.println("Realizando pago con Tarjeta de Crédito...");
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago con Tarjeta de Crédito cancelado.");
    }
}

class PayPal extends MetodoPago implements Cancelable {
    private String correoElectronico;

    public PayPal(String titular, String numero, String correoElectronico) {
        super(titular, numero);
        this.correoElectronico = correoElectronico;
    }

    @Override
    public void realizarPago() {
        System.out.println("Realizando pago con PayPal...");
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago con PayPal cancelado.");
    }
}

class Pagos {
    private MetodoPago[] metodosPago;
    private int index;

    public Pagos(int size) {
        metodosPago = new MetodoPago[size];
        index = 0;
    }

    public void agregarMetodo(MetodoPago metodo) {
        if (index < metodosPago.length) {
            metodosPago[index++] = metodo;
        }
    }

    public void realizarPagos() {
        for (MetodoPago metodo : metodosPago) {
            if (metodo != null) {
                metodo.realizarPago();
            }
        }
    }

    public void cancelarPagos() {
        for (MetodoPago metodo : metodosPago) {
            if (metodo instanceof Cancelable) {
                ((Cancelable) metodo).cancelarPago();
            }
        }
    }

    public void mostrarPagos() {
        for (MetodoPago metodo : metodosPago) {
            if (metodo instanceof TarjetaCredito) {
                System.out.println("Tarjeta de Crédito de: " + metodo.titular);
            } else if (metodo instanceof PayPal) {
                System.out.println("Cuenta PayPal de: " + metodo.titular);
            }
        }
    }
}
public class SistemaDePagos {
    public static void main(String[] args) {
        TarjetaCredito tarjeta = new TarjetaCredito("Juan Perez", "1234567890123456", "12/25", 123);
        PayPal paypal = new PayPal("Ana Gomez", "paypal123", "ana@example.com");

        Pagos sistemaPagos = new Pagos(10);
        sistemaPagos.agregarMetodo(tarjeta);
        sistemaPagos.agregarMetodo(paypal);

        sistemaPagos.realizarPagos();
        sistemaPagos.mostrarPagos();

        sistemaPagos.cancelarPagos();
    }
}
