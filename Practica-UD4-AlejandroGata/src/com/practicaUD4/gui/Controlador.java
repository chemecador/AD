package com.practicaUD4.gui;

import com.practicaUD4.base.Editorial;
import com.practicaUD4.base.Puzzle;
import com.practicaUD4.base.Sede;

import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Clase Controlador
 *
 * @author
 * @see java.awt.event.ActionListener
 * @see java.awt.event.KeyListener
 * @see javax.swing.event.ListSelectionListener
 */
public class Controlador implements ActionListener, KeyListener {
    //Campos
    Vista vista;
    Modelo modelo;

    /**
     * Constructor de Controlador
     *
     * @param vista  de tipo Vista
     * @param modelo de tipo Modelo
     */
    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        inicializar();
    }

    /**
     * Método inicializar listeners
     */
    private void inicializar() {
        addActionListeners(this);
        addKeyListeners(this);
        //addListSelectionListeners(this);
        modelo.conectar();
        listarPuzzles(modelo.getPuzzles());
        listarEditoriales(modelo.getEditoriales());
    }

    /**
     * Método addActionListeners(), añado los listeners a los botones
     *
     * @param listener de tipo ActionListener
     */
    private void addActionListeners(ActionListener listener) {
        vista.btnNuevoPuzzle.addActionListener(listener);
        vista.btnModificarPuzzle.addActionListener(listener);
        vista.btnEliminarPuzzle.addActionListener(listener);
        vista.btnEliminarEditorial.addActionListener(listener);
        vista.btnModificarEditorial.addActionListener(listener);
        vista.btnNuevaEditorial.addActionListener(listener);
        vista.nuevaSedeBtn.addActionListener(listener);
        vista.modificarSedeBtn.addActionListener(listener);
        vista.eliminarSedeBtn.addActionListener(listener);

    }

    /**
     * Método addListSelectionListeners(), añadir listeners a los JLists
     *
     * @param listener de ListSelectionListener
     */
    private void addListSelectionListeners(ListSelectionListener listener) {
        vista.listPuzzles.addListSelectionListener(listener);
        vista.listEditorial.addListSelectionListener(listener);
        vista.listSede.addListSelectionListener(listener);
    }

    /**
     * Método addKeyListeners(), añadir listeners a los campos de buscar
     *
     * @param listener de KeyListener
     */
    private void addKeyListeners(KeyListener listener) {
        vista.txtBuscar.addKeyListener(listener);
        vista.txtBuscarEditorial.addKeyListener(listener);
    }

    /**
     * Método actionPerformed(), implementado por ActionListener, donde asignas las acciones a los botones
     *
     * @param e de ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        Puzzle unPuzzle;
        Editorial unaEditorial;
        Sede unaSede;
        switch (comando) {
            case "Nuevo Puzzle":
                unPuzzle = new Puzzle();
                modificarPuzzleFromCampos(unPuzzle);
                modelo.guardarPuzzle(unPuzzle);
                listarPuzzles(modelo.getPuzzles());
                break;
            case "Modificar Puzzle":
                unPuzzle = (Puzzle) vista.listPuzzles.getSelectedValue();
                modificarPuzzleFromCampos(unPuzzle);
                modelo.modificarPuzzle(unPuzzle);
                listarPuzzles(modelo.getPuzzles());
                break;
            case "Eliminar Puzzle":
                unPuzzle = (Puzzle) vista.listPuzzles.getSelectedValue();
                modelo.borrarPuzzle(unPuzzle);
                listarPuzzles(modelo.getPuzzles());
                break;
            case "Nueva Editorial":
                unaEditorial = new Editorial();
                modificarEditorialFromCampos(unaEditorial);
                modelo.guardarEditorial(unaEditorial);
                listarEditoriales(modelo.getEditoriales());
                break;
            case "Modificar Editorial":
                unaEditorial = (Editorial) vista.listEditorial.getSelectedValue();
                modificarEditorialFromCampos(unaEditorial);
                modelo.modificarEditorial(unaEditorial);

                listarEditoriales(modelo.getEditoriales());
                break;
            case "Eliminar Editorial":
                unaEditorial = (Editorial) vista.listEditorial.getSelectedValue();
                modelo.borrarEditorial(unaEditorial);
                listarEditoriales(modelo.getEditoriales());
                break;
            case "Nueva Sede":
                unaSede = new Sede();
                modificarSedeFromCampos(unaSede);
                modelo.guardarSede(unaSede);
                listarSedes(modelo.getSedes());
                break;
            case "Modificar Sede":
                unaSede = (Sede) vista.listSede.getSelectedValue();
                modificarSedeFromCampos(unaSede);
                modelo.modificarSede(unaSede);
                listarSedes(modelo.getSedes());
                break;
            case "Eliminar Sede":
                unaSede = (Sede) vista.listSede.getSelectedValue();
                modelo.borrarSede(unaSede);
                listarSedes(modelo.getSedes());
                break;
        }
    }

    /**
     * Método listarPuzzles(), listo las Puzzles
     *
     * @param lista de List<Puzzle>
     */
    private void listarPuzzles(List<Puzzle> lista) {
        vista.dlmPuzzles.clear();
        for (Puzzle p : lista) {
            vista.dlmPuzzles.addElement(p);
        }
    }

    /**
     * Método listarEditoriales(), listo los editorial
     *
     * @param lista de List<Editorial>
     */
    private void listarEditoriales(List<Editorial> lista) {
        vista.dlmEditoriales.clear();
        for (Editorial e : lista) {
            vista.dlmEditoriales.addElement(e);
        }
    }

    /**
     * Método listarVendedores(), listo las Vendedores
     *
     * @param lista de List<Vendedor>
     */
    private void listarSedes(List<Sede> lista) {
        vista.dlmSedes.clear();
        for (Sede p : lista) {
            vista.dlmSedes.addElement(p);
        }
        for (Sede t : lista) {
            vista.comboSedes.addItem(t);
        }
        vista.comboSedes.setSelectedIndex(-1);
    }

    /**
     * Método modificarPuzzleFromCampos(), para modificar los datos
     *
     * @param unPuzzle de tipo Puzzle
     */
    private void modificarPuzzleFromCampos(Puzzle unPuzzle) {
        unPuzzle.setNombre(vista.txtNombre.getText());
        unPuzzle.setMarca(vista.txtMarca.getText());
        unPuzzle.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
        unPuzzle.setFechaFabricacion(vista.dateFabricacion.getDate());
    }

    /**
     * Método modificarEditorialFromCampos(), para modificar los datos
     *
     * @param unaEditorial de tipo Editorial
     */
    private void modificarEditorialFromCampos(Editorial unaEditorial) {
        unaEditorial.setNombre(vista.txtNombreEditorial.getText());
        unaEditorial.setMediaVentas(Double.parseDouble(vista.txtMediaVentas.getText()));
        unaEditorial.setFechaCreacion(vista.dateCreacion.getDate());
        unaEditorial.setSede((Sede) vista.listSede.getSelectedValue());
    }

    /**
     * Método modificarSedeFromCampos(), para modificar los datos
     *
     * @param unaSede de tipo Sede
     */
    private void modificarSedeFromCampos(Sede unaSede) {
        unaSede.setNombre(vista.txtNombreVendedor.getText());
    }

    /**
     * Método keyReleased(), para listar según lo que buscas
     *
     * @param e de KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.txtBuscar) {
            listarPuzzles(modelo.getPuzzles(vista.txtBuscar.getText()));
        }
        if (e.getSource() == vista.txtBuscarEditorial) {
            listarEditoriales(modelo.getEditorial(vista.txtBuscarEditorial.getText()));
        }
        if (e.getSource() == vista.txtBuscarVendedor) {
            listarSedes(modelo.getSede(vista.txtBuscarVendedor.getText()));
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }


}
