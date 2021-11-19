package examenUD1.base;

import java.time.LocalDate;

public class SobreMesa extends Ordenador {
    private String raton;

    public SobreMesa() {super();}

    public SobreMesa(String nombre, String cantidad, String precio, LocalDate fechaOferta, String raton) {
        super(nombre, cantidad, precio, fechaOferta);
        this.raton = raton;
    }

    public String getRaton() {
        return raton;
    }

    public void setRaton(String raton) {
        this.raton = raton;
    }

    @Override
    public String toString() {
        return "Sobremesa : "+getNombre()+" "+getCantidad()+" "
                +getPrecio()+"€ "+getFechaOferta()+" Raton:"+ raton;
    }
}
