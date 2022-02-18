package gui;

import com.alejandrogata.practica2ud3.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Controlador implements ActionListener, ListSelectionListener {
    private Vista vista;
    private Modelo modelo;
    private boolean conectado;

    public Controlador(Modelo modelo, Vista vista) {
        this.vista = vista;
        this.modelo = modelo;
        this.conectado = false;

        addActionListeners(this);
        addListSelectionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        if (!conectado && !comando.equalsIgnoreCase("Conectar")) {
            JOptionPane.showMessageDialog(null, "No has conectado con la BBDD",
                    "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println("Comando vale " + comando);
        switch (comando) {
            case "Salir":
                modelo.desconectar();
                System.exit(0);
                break;
            case "Conectar":
                vista.conexionItem.setEnabled(false);
                modelo.conectar();
                conectado = true;
                break;

            case "nuevoPuzzleBtn":
                Puzzle nuevoPuzzle = new Puzzle();
                nuevoPuzzle.setTitulo(vista.txtTitulo.getText());
                nuevoPuzzle.setIsbn(vista.txtIsbn.getText());
                nuevoPuzzle.setEditorial((Editorial) vista.comboEditoriales.getSelectedItem());
                modelo.insertar(nuevoPuzzle);
                break;

            case "listarPuzzles":
                listarPuzzles(modelo.getPuzzles());
                break;

            case "modificarPuzzleBtn":
                Puzzle puzzleSeleccion = (Puzzle) vista.listPuzzles.getSelectedValue();
                puzzleSeleccion.setTitulo(vista.txtTitulo.getText());
                puzzleSeleccion.setIsbn(vista.txtIsbn.getText());
                modelo.modificar(puzzleSeleccion);
                break;

            case "eliminarPuzzleBtn":
                Puzzle puzzleBorrado = (Puzzle) vista.listPuzzles.getSelectedValue();
                modelo.eliminar(puzzleBorrado);
                break;
            case "altaCompradorBtn": {
                System.out.println("entro en alta comprador");
                Comprador c = new Comprador();
                c.setNombre(vista.txtNombreComprador.getText());
                c.setApellidos(vista.txtApellidos.getText());
                c.setDni(vista.txtDNI.getText());
                modelo.altaComprador(c);
                break;
            }
            case "modificarCompradorBtn": {
                Comprador c = (Comprador) vista.listCompradores.getSelectedValue();
                c.setNombre(vista.txtNombreComprador.getText());
                c.setApellidos(vista.txtApellidos.getText());
                c.setDni(vista.txtDNI.getText());
                modelo.modificarComprador(c);
            }
            break;
            case "eliminarCompradorBtn": {
                Comprador c = (Comprador) vista.listCompradores.getSelectedValue();
                modelo.eliminar(c);
                break;
            }
            case "listarCompradorPuzzles":
                break;
            case "altaTiendaBtn": {
                Tienda t = new Tienda();
                t.setNombre(vista.txtNombreTienda.getText());
                t.setTelefono(vista.txtTlfTienda.getText());
                modelo.insertar(t);
                break;
            }
            case "modificarTiendaBtn": {
                Tienda t = (Tienda) vista.listTiendas.getSelectedValue();
                t.setNombre(vista.txtNombreTienda.getText());
                t.setTelefono(vista.txtTlfTienda.getText());
                modelo.modificar(t);
            }
            break;
            case "eliminarTiendaBtn": {
                Tienda t = (Tienda) vista.listTiendas.getSelectedValue();
                modelo.eliminar(t);
                break;
            }

            case "altaEditorialBtn": {
                Editorial t = new Editorial();
                t.setNombre(vista.txtNombreEditorial.getText());
                t.setTelefono(vista.txtTlfEditorial.getText());
                modelo.insertar(t);
                break;
            }
            case "modificarEditorialBtn": {
                Editorial t = (Editorial) vista.listEditoriales.getSelectedValue();
                t.setNombre(vista.txtNombreEditorial.getText());
                t.setTelefono(vista.txtTlfEditorial.getText());
                modelo.modificar(t);
            }
            break;
            case "eliminarEditorialBtn": {
                Editorial t = (Editorial) vista.listEditoriales.getSelectedValue();
                modelo.eliminar(t);
                break;
            }
            case "altaVentaBtn": {
                VentaPuzzle v = new VentaPuzzle();
                v.setPrecio(Integer.valueOf(vista.txtPrecio.getText()));
                v.setPuzzle((Puzzle) vista.comboPuzzles.getSelectedItem());
                v.setTienda((Tienda) vista.comboTiendas.getSelectedItem());
                modelo.insertar(v);
                break;
            }
            case "modificarVentaBtn": {
                VentaPuzzle v = (VentaPuzzle) vista.listVentas.getSelectedValue();
                v.setPrecio(Integer.valueOf(vista.txtPrecio.getText()));
                v.setPuzzle((Puzzle) vista.comboPuzzles.getSelectedItem());
                v.setTienda((Tienda) vista.comboTiendas.getSelectedItem());
                modelo.modificar(v);
                break;
            }
            case "eliminarVentaBtn": {
                VentaPuzzle v = (VentaPuzzle) vista.listVentas.getSelectedValue();
                modelo.eliminar(v);
                break;
            }
        }
        actualizar();
    }

    private void actualizar() {
        listarPuzzles(modelo.getPuzzles());
        listarCompradores(modelo.getCompradores());
        listarTiendas(modelo.getTiendas());
        listarEditoriales(modelo.getEditoriales());
        listarVentas(modelo.getVentas());
        listarCompras(modelo.getCompras());
        listarEditorialPuzzles(modelo.getPuzzles());
    }

    private void listarCompras(ArrayList<CompradorPuzzle> compras) {

        vista.dlmCompras.clear();
        for (CompradorPuzzle cp : compras) {
            vista.dlmCompras.addElement(compras);
        }

        vista.comboPuzzles2.removeAllItems();
        ArrayList<Puzzle> p = modelo.getPuzzles();
        vista.comboComprador2.removeAllItems();
        ArrayList<Comprador> t = modelo.getCompradores();


        for (Puzzle puzzle : p) {
            vista.comboPuzzles2.addItem(puzzle);
        }
        for (Comprador comprador : t) {
            vista.comboComprador2.addItem(comprador);
        }
        vista.comboPuzzles2.setSelectedIndex(-1);
        vista.comboComprador2.setSelectedIndex(-1);
    }

    public void listarCompradores(ArrayList<Comprador> lista) {
        vista.dlmCompradores.clear();
        for (Comprador c : lista) {
            vista.dlmCompradores.addElement(c);
        }
    }

    private void listarTiendas(ArrayList<Tienda> lista) {
        vista.dlmTiendas.clear();
        for (Tienda t : lista) {
            vista.dlmTiendas.addElement(t);
        }
        vista.comboTiendas.removeAllItems();
        ArrayList<Tienda> tie = modelo.getTiendas();

        for (Tienda t : tie) {
            vista.comboTiendas.addItem(t);
        }
        vista.comboTiendas.setSelectedIndex(-1);
    }

    private void listarEditoriales(ArrayList<Editorial> lista) {
        vista.dlmEditoriales.clear();
        for (Editorial e : lista) {
            vista.dlmEditoriales.addElement(e);
        }
        vista.comboEditoriales.removeAllItems();
        ArrayList<Editorial> ed = modelo.getEditoriales();

        for (Editorial e : ed) {
            vista.comboEditoriales.addItem(e);
        }
        vista.comboEditoriales.setSelectedIndex(-1);
    }

    public void listarPuzzles(ArrayList<Puzzle> lista) {
        vista.dlmPuzzles.clear();
        for (Puzzle unPuzzle : lista) {
            vista.dlmPuzzles.addElement(unPuzzle);
        }
        vista.comboPuzzles.removeAllItems();
        ArrayList<Puzzle> puz = modelo.getPuzzles();

        for (Puzzle p : puz) {
            vista.comboPuzzles.addItem(p);
        }
        vista.comboPuzzles.setSelectedIndex(-1);
    }

    public void listarVentas(List<VentaPuzzle> ventas) {
        vista.dlmVentas.clear();
        for (VentaPuzzle venta : ventas) {
            vista.dlmVentas.addElement(venta);
        }

        vista.comboPuzzles.removeAllItems();
        ArrayList<Puzzle> p = modelo.getPuzzles();
        vista.comboTiendas.removeAllItems();
        ArrayList<Tienda> t = modelo.getTiendas();


        for (Puzzle puzzle : p) {
            vista.comboPuzzles.addItem(puzzle);
        }
        for (Tienda tienda : t) {
            vista.comboTiendas.addItem(tienda);
        }
        vista.comboPuzzles.setSelectedIndex(-1);
        vista.comboTiendas.setSelectedIndex(-1);
    }

    public void listarEditorialPuzzles(List<Puzzle> lista) {
        vista.dlmEditorialPuzzles.clear();
        for (Puzzle unPuzzle : lista) {
            vista.dlmEditorialPuzzles.addElement(unPuzzle);
        }
    }


    private void addActionListeners(ActionListener listener) {
        vista.conexionItem.addActionListener(listener);
        vista.salirItem.addActionListener(listener);
        vista.nuevoPuzzleBtn.addActionListener(listener);
        vista.eliminarPuzzleBtn.addActionListener(listener);
        vista.modificarPuzzleBtn.addActionListener(listener);
        vista.altaCompradorBtn.addActionListener(listener);
        vista.modificarCompradorBtn.addActionListener(listener);
        vista.eliminarCompradorBtn.addActionListener(listener);
        vista.altaTiendaBtn.addActionListener(listener);
        vista.modificarTiendaBtn.addActionListener(listener);
        vista.eliminarTiendaBtn.addActionListener(listener);
        vista.altaEditorialBtn.addActionListener(listener);
        vista.modificarEditorialBtn.addActionListener(listener);
        vista.eliminarEditorialBtn.addActionListener(listener);
        vista.altaVentaBtn.addActionListener(listener);
        vista.modificarVentaBtn.addActionListener(listener);
        vista.eliminarVentaBtn.addActionListener(listener);
        vista.altaCompraBtn.addActionListener(listener);
        vista.modificarCompraBtn.addActionListener(listener);
        vista.eliminarCompraBtn.addActionListener(listener);
        vista.listarEditorialPuzzles.addActionListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.listPuzzles.addListSelectionListener(listener);
        vista.listCompradores.addListSelectionListener(listener);
        vista.listTiendas.addListSelectionListener(listener);
        vista.listEditoriales.addListSelectionListener(listener);
        vista.listVentas.addListSelectionListener(listener);
        vista.listEditorialPuzzles.addListSelectionListener(listener);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            if (e.getSource() == vista.listPuzzles) {
                System.out.println("entro en listPuzzles");
                Puzzle puzzleSeleccion = (Puzzle) vista.listPuzzles.getSelectedValue();
                vista.txtTitulo.setText(String.valueOf(puzzleSeleccion.getTitulo()));
                vista.txtIsbn.setText(String.valueOf(puzzleSeleccion.getIsbn()));
            }
            if (e.getSource() == vista.listCompradores) {
                Comprador compradorSeleccionado = (Comprador) vista.listCompradores.getSelectedValue();
                vista.txtNombreComprador.setText(String.valueOf(compradorSeleccionado.getNombre()));
                vista.txtApellidos.setText(String.valueOf(compradorSeleccionado.getApellidos()));
                vista.txtDNI.setText(String.valueOf(compradorSeleccionado.getDni()));
                    /*if (puzzleSeleccion.getCompradores() != null) {
                        vista.txtNombreComprador.setText(puzzleSeleccion.getCompradores().toString());
                    } else {
                        vista.txtComprador.setText("");
                    }*/
                //listarPuzzlesCompradores(modelo.getPuzzlesComprador(compradorSeleccionado));
            }
            if (e.getSource() == vista.listTiendas) {
                Tienda t = (Tienda) vista.listTiendas.getSelectedValue();
                vista.txtNombreTienda.setText(String.valueOf(t.getNombre()));
                vista.txtTlfTienda.setText(String.valueOf(t.getTelefono()));
            }
            if (e.getSource() == vista.listEditoriales) {
                Editorial t = (Editorial) vista.listEditoriales.getSelectedValue();
                vista.txtNombreEditorial.setText(String.valueOf(t.getNombre()));
                vista.txtTlfEditorial.setText(String.valueOf(t.getTelefono()));
            }
        }

    }
}

