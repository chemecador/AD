package examenUD1.base;

import java.time.LocalDate;

public class Portatil extends Ordenador {
    private String touchpad;

    public Portatil(){super();}

    public Portatil(String nombre, String cantidad, String precio, LocalDate fechaOferta, String touchpad) {
        super(nombre, cantidad, precio, fechaOferta);
        this.touchpad = touchpad;
    }

    public String getTouchpad() {
        return touchpad;
    }

    public void setTouchpad(String touchpad) {
        this.touchpad = touchpad;
    }

    @Override
    public String toString() {
        return "Portatil : "+getNombre()+" "+getCantidad()+" "
                +getPrecio()+"€ "+getFechaOferta()+" TouchPad:"+ touchpad;
    }
}
