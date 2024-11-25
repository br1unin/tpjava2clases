import java.util.ArrayList;
import java.util.List;

class Ingrediente {
    private String nombre;
    private double cantidad;
    private String unidadDeMedida;

    public Ingrediente(String nombre, double cantidad, String unidadDeMedida) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadDeMedida = unidadDeMedida;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", unidadDeMedida='" + unidadDeMedida + '\'' +
                '}';
    }
}

class Plato {
    private String nombreCompleto;
    private double precio;
    private boolean esBebida;
    private List<Ingrediente> ingredientes;

    public Plato(String nombreCompleto, double precio, boolean esBebida) {
        this.nombreCompleto = nombreCompleto;
        this.precio = precio;
        this.esBebida = esBebida;
        this.ingredientes = new ArrayList<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        if (!esBebida) {
            ingredientes.add(ingrediente);
        }
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public double getPrecio() {
        return precio;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Plato: ").append(nombreCompleto).append("\n");
        sb.append("Precio: ").append(precio).append("\n");
        if (!esBebida) {
            sb.append("Ingredientes: \n");
            for (Ingrediente i : ingredientes) {
                sb.append(i.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}

class MenuRestaurant {
    private List<Plato> platosMenu;

    public MenuRestaurant() {
        platosMenu = new ArrayList<>();
    }

    public void agregarPlato(Plato plato) {
        platosMenu.add(plato);
    }

    public void mostrarMenu() {
        for (Plato p : platosMenu) {
            System.out.println(p.toString());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MenuRestaurant menu = new MenuRestaurant();

        Plato pizzaEspecial = new Plato("Pizza Especial", 450, false);
        pizzaEspecial.agregarIngrediente(new Ingrediente("Harina", 100, "gramos"));
        pizzaEspecial.agregarIngrediente(new Ingrediente("Queso", 300, "gramos"));
        pizzaEspecial.agregarIngrediente(new Ingrediente("Jamon Cocido", 100, "gramos"));

        Plato bebidaCola = new Plato("Bebida Cola", 50, true);

        menu.agregarPlato(pizzaEspecial);
        menu.agregarPlato(bebidaCola);

        menu.mostrarMenu();
    }
}