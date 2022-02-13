package gui;

import com.alejandrogata.practica1ud3.Comprador;
import com.alejandrogata.practica1ud3.Puzzle;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Controlador implements ActionListener, ListSelectionListener{
    private Vista vista;
    private Modelo modelo;

    public Controlador(Modelo modelo, Vista vista) {
        this.vista = vista;
        this.modelo = modelo;

        addActionListeners(this);
        addListSelectionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch(comando){
            case "Salir":
                modelo.desconectar();
                System.exit(0);
                break;
            case "Conectar":
                vista.conexionItem.setEnabled(false);
                modelo.conectar();
                break;

            case "Alta":
                Puzzle nuevoPuzzle = new Puzzle();
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

            case "Borrar":
                Puzzle puzzleBorrado  = (Puzzle)vista.listPuzzles.getSelectedValue();
                modelo.borrar(puzzleBorrado);
                break;
            case "ListarPropietarios":
                listarCompradores(modelo.getPropietarios());
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
        vista.dlm.clear();
        for(Puzzle unPuzzle : lista){
            vista.dlm.addElement(unPuzzle);
        }
    }

    public void listarPuzzlesCompradores(List<Puzzle> lista){
        vista.dlmPuzzlesCompradores.clear();
        for(Puzzle unPuzzle : lista){
            vista.dlmPuzzlesCompradores.addElement(unPuzzle);
        }
    }

    private void addActionListeners(ActionListener listener){
        vista.conexionItem.addActionListener(listener);
        vista.salirItem.addActionListener(listener);
        vista.altaButton.addActionListener(listener);
        vista.borrarButton.addActionListener(listener);
        vista.modificarButton.addActionListener(listener);
        vista.listarButton.addActionListener(listener);
        vista.listarCompradoresButton.addActionListener(listener);
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
                vista.txtIsbn.setText(String.valueOf(puzzleSeleccion.getIsbn()));
                vista.txtPrecio.setText(String.valueOf(puzzleSeleccion.getPrecio()));
                if (puzzleSeleccion.getCompradores() != null) {
                    vista.txtComprador.setText(puzzleSeleccion.getCompradores().toString());
                } else {
                    vista.txtComprador.setText("");
                }
            }else{
                if(e.getSource() == vista.listCompradores){
                    Comprador compradorSeleccionado = (Comprador) vista.listCompradores.getSelectedValue();
                    listarPuzzlesCompradores(modelo.getPuzzlesComprador(compradorSeleccionado));
                }
            }
        }
    }
}

