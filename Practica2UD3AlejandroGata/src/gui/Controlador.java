package gui;

import com.alejandrogata.practica2ud3.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Controlador implements ActionListener, ListSelectionListener{
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

        if (!conectado && !comando.equalsIgnoreCase("Conectar")){
            JOptionPane.showMessageDialog(null, "No has conectado con la BBDD",
                    "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println("Comando vale " + comando);
        switch(comando){
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
                System.out.println("ENTRO EN NUEVO PUZZLE");
                Puzzle nuevoPuzzle = new Puzzle();
                nuevoPuzzle.setTitulo(vista.txtTitulo.getText());
                nuevoPuzzle.setIsbn(vista.txtIsbn.getText());
                nuevoPuzzle.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
                modelo.altaPuzzle(nuevoPuzzle);
                break;

            case "Listar":
                listarPuzzles(modelo.getPuzzles());
                break;

            case "Modificar":
                Puzzle puzzleSeleccion = (Puzzle)vista.listPuzzles.getSelectedValue();
                puzzleSeleccion.setIsbn(vista.txtIsbn.getText());
                puzzleSeleccion.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
                System.out.println((Comprador)vista.listCompradores.getSelectedValue());
                modelo.modificar(puzzleSeleccion);
                break;

            case "eliminarPuzzleBtn":
                Puzzle puzzleBorrado  = (Puzzle)vista.listPuzzles.getSelectedValue();
                modelo.borrar(puzzleBorrado);
                break;
            case "ListarCompradores":
                listarCompradores(modelo.getCompradores());
                break;
            case "altaCompradorBtn":
                System.out.println("entro en alta comprador");
                break;

        }

        listarPuzzles(modelo.getPuzzles());
    }

    private void listarCompradores(ArrayList<Comprador> compradores) {
        vista.dlmCompradores.clear();
        for(Comprador comprador : compradores){
            vista.dlmCompradores.addElement(comprador);
        }
    }

    public void listarPuzzles(ArrayList<Puzzle> lista){
        vista.dlmPuzzles.clear();
        for(Puzzle unPuzzle : lista){
            vista.dlmPuzzles.addElement(unPuzzle);
        }
    }

    public void listarPuzzlesCompradores(List<Puzzle> lista){
        vista.dlmCompradorPuzzle.clear();
        for(Puzzle unPuzzle : lista){
            vista.dlmCompradorPuzzle.addElement(unPuzzle);
        }
    }

    private void addActionListeners(ActionListener listener){
        vista.conexionItem.addActionListener(listener);
        vista.salirItem.addActionListener(listener);
        vista.nuevoPuzzleBtn.addActionListener(listener);
        vista.eliminarPuzzleBtn.addActionListener(listener);
        vista.modPuzzleBtn.addActionListener(listener);
        vista.altaCompradorBtn.addActionListener(listener);
        vista.modificarCompradorBtn.addActionListener(listener);
        vista.eliminarCompradorBtn.addActionListener(listener);
        vista.altaTiendaBtn.addActionListener(listener);
        vista.modificarTiendaBtn.addActionListener(listener);
        vista.eliminarTiendaBtn.addActionListener(listener);
        vista.altaEditorialBtn.addActionListener(listener);
        vista.modificarEditorialBtn.addActionListener(listener);
        vista.eliminarEditorialBtn.addActionListener(listener);
        vista.listarPuzzlesComprador.addActionListener(listener);
        vista.listarPuzzlesEditorial.addActionListener(listener);
        vista.listarPuzzlesTienda.addActionListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener){
        vista.listPuzzles.addListSelectionListener(listener);
        vista.listCompradores.addListSelectionListener(listener);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
            if(e.getSource() == vista.listPuzzles) {
                Puzzle puzzleSeleccion = (Puzzle) vista.listPuzzles.getSelectedValue();
                vista.txtTitulo.setText(String.valueOf(puzzleSeleccion.getTitulo()));
                vista.txtIsbn.setText(String.valueOf(puzzleSeleccion.getIsbn()));
                vista.txtPrecio.setText(String.valueOf(puzzleSeleccion.getPrecio()));
                /*if (puzzleSeleccion.getCompradores() != null) {
                    vista.txtComprador.setText(puzzleSeleccion.getCompradores().toString());
                } else {
                    vista.txtComprador.setText("");
                }*/
            }else{
                if(e.getSource() == vista.listCompradores){
                    Comprador compradorSeleccionado = (Comprador) vista.listCompradores.getSelectedValue();
                    listarPuzzlesCompradores(modelo.getPuzzlesComprador(compradorSeleccionado));
                }
            }
        }
    }
}

