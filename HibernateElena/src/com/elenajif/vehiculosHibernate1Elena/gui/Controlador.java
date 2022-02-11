package com.elenajif.vehiculosHibernate1Elena.gui;

import com.elenajif.vehiculosHibernate1Elena.Coche;
import com.elenajif.vehiculosHibernate1Elena.Propietario;

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

    public Controlador(Vista vista, Modelo modelo) {
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
                Coche nuevoCoche = new  Coche();
                nuevoCoche.setMatricula(vista.txtMatricula.getText());
                nuevoCoche.setMarca(vista.txtMarca.getText());
                nuevoCoche.setModelo(vista.txtModelo.getText());
                nuevoCoche.setFechaMatriculacion(Timestamp.valueOf(vista.dateTimePicker.getDateTimePermissive()));
                modelo.altaCoche(nuevoCoche);
                break;

            case "Listar":
                listarCoches(modelo.getCoches());
                break;

            case "Modificar":
                Coche cocheSeleccion = (Coche)vista.listCoches.getSelectedValue();
                cocheSeleccion.setModelo(vista.txtModelo.getText());
                cocheSeleccion.setMarca(vista.txtMarca.getText());
                cocheSeleccion.setMatricula(vista.txtMatricula.getText());
                cocheSeleccion.setFechaMatriculacion(Timestamp.valueOf(vista.dateTimePicker.getDateTimePermissive()));
                cocheSeleccion.setPropietario((Propietario)vista.listPropietarios.getSelectedValue());
                System.out.println((Propietario)vista.listPropietarios.getSelectedValue());
                modelo.modificar(cocheSeleccion);
                break;

            case "Borrar":
                Coche cocheBorrado  = (Coche)vista.listCoches.getSelectedValue();
                modelo.borrar(cocheBorrado);
                break;
            case "ListarPropietarios":
                listarPropietarios(modelo.getPropietarios());
                break;


        }

        listarCoches(modelo.getCoches());
    }

    private void listarPropietarios(ArrayList<Propietario> propietarios) {
        vista.dlmPropietarios.clear();
        for(Propietario propietario : propietarios){
            vista.dlmPropietarios.addElement(propietario);
        }
    }

    public void listarCoches(ArrayList<Coche> lista){
        vista.dlm.clear();
        for(Coche uncoche : lista){
            vista.dlm.addElement(uncoche);
        }
    }

    public void listarCochesPropietario(List<Coche> lista){
        vista.dlmCochesPropietario.clear();
        for(Coche uncoche : lista){
            vista.dlmCochesPropietario.addElement(uncoche);
        }
    }

    private void addActionListeners(ActionListener listener){
        vista.conexionItem.addActionListener(listener);
        vista.salirItem.addActionListener(listener);
        vista.altaButton.addActionListener(listener);
        vista.borrarButton.addActionListener(listener);
        vista.modificarButton.addActionListener(listener);
        vista.listarButton.addActionListener(listener);
        vista.listarPropietariosButton.addActionListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener){
        vista.listCoches.addListSelectionListener(listener);
        vista.listPropietarios.addListSelectionListener(listener);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
            if(e.getSource() == vista.listCoches) {
                Coche cocheSeleccion = (Coche) vista.listCoches.getSelectedValue();
                vista.txtId.setText(String.valueOf(cocheSeleccion.getId()));
                vista.txtMarca.setText(cocheSeleccion.getMarca());
                vista.txtMatricula.setText(cocheSeleccion.getMatricula());
                vista.txtModelo.setText(cocheSeleccion.getModelo());
                vista.dateTimePicker.setDateTimePermissive(cocheSeleccion.getFechaMatriculacion().toLocalDateTime());
                if (cocheSeleccion.getPropietario() != null) {
                    vista.txtPropietario.setText(cocheSeleccion.getPropietario().toString());
                } else {
                    vista.txtPropietario.setText("");
                }
            }else{
                if(e.getSource() == vista.listPropietarios){
                    Propietario propietarioSeleccionado = (Propietario)vista.listPropietarios.getSelectedValue();
                    listarCochesPropietario(modelo.getCochesPropietario(propietarioSeleccionado));
                }
            }
        }
    }
}
