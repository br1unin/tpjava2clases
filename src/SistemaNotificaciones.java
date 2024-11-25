import java.util.ArrayList;

interface Personalizable {
    void personalizarMensaje();
}

abstract class CanalNotificacion {
    protected String usuario;
    protected String mensaje;

    public CanalNotificacion(String usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public abstract void enviarNotificacion();
}

class CorreoElectronico extends CanalNotificacion implements Personalizable {
    private String direccionCorreo;

    public CorreoElectronico(String usuario, String mensaje, String direccionCorreo) {
        super(usuario, mensaje);
        this.direccionCorreo = direccionCorreo;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Enviando correo a: " + direccionCorreo + " Mensaje: " + mensaje);
    }

    @Override
    public void personalizarMensaje() {
        mensaje = "[Correo Personalizado] " + mensaje;
    }
}

class MensajeTexto extends CanalNotificacion implements Personalizable {
    private String numeroTelefono;

    public MensajeTexto(String usuario, String mensaje, String numeroTelefono) {
        super(usuario, mensaje);
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Enviando SMS a: " + numeroTelefono + " Mensaje: " + mensaje);
    }

    @Override
    public void personalizarMensaje() {
        mensaje = "[SMS Personalizado] " + mensaje;
    }
}

class Notificaciones {
    private ArrayList<CanalNotificacion> canales;

    public Notificaciones() {
        canales = new ArrayList<>();
    }

    public void agregarCanal(CanalNotificacion canal) {
        canales.add(canal);
    }

    public void enviarNotificaciones() {
        for (CanalNotificacion canal : canales) {
            canal.enviarNotificacion();
        }
    }

    public void personalizarMensajes() {
        for (CanalNotificacion canal : canales) {
            if (canal instanceof Personalizable) {
                ((Personalizable) canal).personalizarMensaje();
            }
        }
    }

    public void mostrarCanales() {
        for (CanalNotificacion canal : canales) {
            System.out.println(canal.getClass().getSimpleName() + ": Usuario: " + canal.usuario);
        }
    }
}

public class SistemaNotificaciones {
    public static void main(String[] args) {
        Notificaciones sistema = new Notificaciones();

        CorreoElectronico correo = new CorreoElectronico("Usuario1", "Hola, este es un correo!", "correo@ejemplo.com");
        MensajeTexto sms = new MensajeTexto("Usuario2", "Este es un mensaje de texto!", "1234567890");

        sistema.agregarCanal(correo);
        sistema.agregarCanal(sms);

        sistema.mostrarCanales();
        sistema.personalizarMensajes();
        sistema.enviarNotificaciones();
    }
}
