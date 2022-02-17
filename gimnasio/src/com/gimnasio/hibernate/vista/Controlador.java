package com.gimnasio.hibernate.vista;

import com.gimnasio.hibernate.clases.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * CLASE CONTROLADOR donde interactuaremos con la interfaz del usuario, es decir, el nexo entre Vista y Modelo
 */
public class Controlador implements ActionListener, ListSelectionListener {
    private Vista vista;
    private Modelo modelo;

    /**
     * Constructor de clase con Vista y Modelo mencionados
     * @param vista nuestra vista principal
     * @param modelo nuesto modelo
     */
    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        addActionListeners(this);
        addListSelectionListener(this);
    }

    /**
     * Nos permitirá reaccionar a los distintos botones de Alta, Modificación, Eliminación, acceso a menús...
     * Y posteriormente conectará al modelo que a su vez conectará con la base de datos (Todo automatizado)
     * @param e el evento seleccionado (Un botón por ejemplo)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "Salir":
                modelo.desconectar();
                System.exit(0);
                break;
            case "Conectar":
                modelo.conectar();
                vista.conexionItem.setEnabled(false);
                listarTodo();
                break;
            case "Actualizar":
                if (!vista.conexionItem.isEnabled()){
                    listarTodo();
                }
                break;
            case "botonSocRegistrar":
                Socios nuevoSocio = new Socios();
                nuevoSocio.setNombre(vista.txtSociosNombre.getText());
                nuevoSocio.setApellidos(vista.txtSociosApellidos.getText());
                nuevoSocio.setDni(vista.txtSociosDni.getText());
                nuevoSocio.setFechanacimiento(Date.valueOf(vista.DatePickerSocios.getDateStringOrEmptyString()));
                nuevoSocio.setTarifa(String.valueOf(vista.comboSociosTarifa.getSelectedItem()));
                nuevoSocio.setInstructor((Instructores) vista.comboSociosInstructor.getItemAt(vista.comboSociosInstructor.getSelectedIndex()));

                modelo.altaSocio(nuevoSocio);
                limpiarCamposSocios();
                listarTodo();
                break;
            case "botonSocModificar":
                Socios socioModificado = (Socios) vista.ListSocio.getSelectedValue();
                socioModificado.setNombre(vista.txtSociosNombre.getText());
                socioModificado.setApellidos(vista.txtSociosApellidos.getText());
                socioModificado.setDni(vista.txtSociosDni.getText());
                socioModificado.setFechanacimiento(Date.valueOf(vista.DatePickerSocios.getDateStringOrEmptyString()));
                socioModificado.setTarifa(String.valueOf(vista.comboSociosTarifa.getSelectedItem()));
                socioModificado.setInstructor((Instructores) vista.comboSociosInstructor.getItemAt(vista.comboSociosInstructor.getSelectedIndex()));


                modelo.modificarSocio(socioModificado);
                listarTodo();
                break;
            case "botonSocEliminar":
                Socios socioEliminado = (Socios) vista.ListSocio.getSelectedValue();
                modelo.borrarSocio(socioEliminado);
                limpiarCamposSocios();
                listarTodo();
                break;
            case "botonInstRegistro":
                Instructores instructorInsertado = new Instructores();
                instructorInsertado.setNombre(vista.txtInsNombre.getText());
                instructorInsertado.setApellidos(vista.txtInsApellidos.getText());
                instructorInsertado.setFechanacimiento(Date.valueOf(vista.DatePickerInstruct.getDateStringOrEmptyString()));
                instructorInsertado.setCodigoInstructor(vista.txtInsCodigo.getText());
                modelo.altaInstructor(instructorInsertado);
                limpiarCamposInstructores();
                listarTodo();
                break;
            case "botonInstModificar":
                Instructores instructorModificado = (Instructores) vista.listaInstructor.getSelectedValue();
                instructorModificado.setNombre(vista.txtInsNombre.getText());
                instructorModificado.setApellidos(vista.txtInsApellidos.getText());
                instructorModificado.setFechanacimiento(Date.valueOf(vista.DatePickerInstruct.getDateStringOrEmptyString()));
                instructorModificado.setCodigoInstructor(vista.txtInsCodigo.getText());
                modelo.modificarInstructor(instructorModificado);
                listarTodo();
                break;
            case "botonInstEliminar":
                Instructores instructorEliminado = (Instructores) vista.listaInstructor.getSelectedValue();
                modelo.borrarInstructor(instructorEliminado);
                limpiarCamposInstructores();
                listarTodo();
                break;
            case "VerSociosInstructores":
                System.out.println("entro");
                Instructores instructorNuevo = (Instructores) vista.listaInstructor.getSelectedValue();
                instructorNuevo.setSocios((ArrayList) modelo.getSociosInstructor(instructorNuevo));
                listarSociosInstructores(modelo.getSociosInstructor(instructorNuevo));
                break;
            case "botonActRegistro":
                Actividades actividadNueva = new Actividades();
                actividadNueva.setTitulo(vista.txtActTitulo.getText());
                actividadNueva.setInstalacion(String.valueOf(vista.comboActividadesInstalacion.getItemAt(vista.comboActividadesInstalacion.getSelectedIndex())));
                actividadNueva.setHorasSemanales(Integer.parseInt(vista.txtActHSemanal.getText()));
                actividadNueva.setPrecio(Double.parseDouble(vista.txtActPrecio.getText()));

                modelo.altaActividad(actividadNueva);
                limpiarCamposActividades();
                listarTodo();
                break;
            case "botonActModificar":
                Actividades actividadModificada = (Actividades) vista.ListActividad.getSelectedValue();
                actividadModificada.setTitulo(vista.txtActTitulo.getText());
                actividadModificada.setInstalacion(String.valueOf(vista.comboActividadesInstalacion.getItemAt(vista.comboActividadesInstalacion.getSelectedIndex())));
                actividadModificada.setHorasSemanales(Integer.parseInt(vista.txtActHSemanal.getText()));
                actividadModificada.setPrecio(Double.parseDouble(vista.txtActPrecio.getText()));
                modelo.modificarActividad(actividadModificada);
                listarTodo();
                break;
            case "botonActEliminar":
                Actividades actividadBorrada = (Actividades) vista.ListActividad.getSelectedValue();
                modelo.borrarActividad(actividadBorrada);
                limpiarCamposActividades();
                listarTodo();
                break;
            case "botonMatRegistrar":
                byte activo = 0;
                Material materialNuevo = new Material();
                materialNuevo.setNombre(vista.txtMatNombre.getText());
                if (vista.comboActDispnible.getItemAt(vista.comboActDispnible.getSelectedIndex()).equals("SI")){
                    activo = 1;
                }else{
                    activo = 0;
                }
                materialNuevo.setDisponible(activo);
                modelo.altaMaterial(materialNuevo);
                limpiarCamposMateriales();
                listarTodo();
                break;
            case "botonMatModificar":
                Material materialModificado = (Material) vista.ListMaterial.getSelectedValue();
                materialModificado.setNombre(vista.txtMatNombre.getText());
                if (vista.comboActDispnible.getItemAt(vista.comboActDispnible.getSelectedIndex()).equals("SI")){
                    activo = 1;
                }else{
                    activo = 0;
                }
                materialModificado.setDisponible(activo);
                modelo.modificarMaterial(materialModificado);
                listarTodo();
                break;
            case "botonMatEliminar":
                Material materialEliminado = (Material) vista.ListMaterial.getSelectedValue();
                materialEliminado.setNombre(vista.txtMatNombre.getText());
                if (vista.comboActDispnible.getItemAt(vista.comboActDispnible.getSelectedIndex()).equals("SI")){
                    activo = 1;
                }else{
                    activo = 0;
                }
                materialEliminado.setDisponible(activo);
                modelo.borrarMaterial(materialEliminado);
                limpiarCamposMateriales();
                listarTodo();
                break;
            case "btnProvRegistrar":
                Proveedores proveedorNuevo = new Proveedores();
                proveedorNuevo.setTipo(vista.txtProvTipo.getText());
                proveedorNuevo.setDireccion(vista.txtProvDirecc.getText());
                proveedorNuevo.setTelefono(Integer.parseInt(vista.txtProvTelef.getText()));
                proveedorNuevo.setFax(vista.txtProvFax.getText());

                modelo.altaProveedor(proveedorNuevo);
                limpiarCamposProveedores();
                listarTodo();
                break;
            case "btnProvModificar":
                Proveedores proveedorModificado = (Proveedores) vista.ListProveedor.getSelectedValue();
                proveedorModificado.setTipo(vista.txtProvTipo.getText());
                proveedorModificado.setDireccion(vista.txtProvDirecc.getText());
                proveedorModificado.setTelefono(Integer.parseInt(vista.txtProvTelef.getText()));
                proveedorModificado.setFax(vista.txtProvFax.getText());

                modelo.modificarProveedor(proveedorModificado);
                listarTodo();
                break;
            case "btnProvEliminar":
                Proveedores proveedorEliminado = (Proveedores) vista.ListProveedor.getSelectedValue();

                modelo.borrarProveedor(proveedorEliminado);
                limpiarCamposProveedores();
                listarTodo();
                break;
            case "btnProvVerMat":
                Proveedores proveedorFiltro = (Proveedores) vista.ListProveedor.getSelectedValue();
                proveedorFiltro.setDetalles((ArrayList) modelo.getPedidosProveedores(proveedorFiltro));
                listarPedidoProveedor((ArrayList)proveedorFiltro.getDetalles());
                break;

            case "btnPedidoRegistrar":
                DetalleProveedor nuevoPedido = new DetalleProveedor();
                nuevoPedido.setMaterial((Material) vista.comboPedidoMaterial.getSelectedItem());
                nuevoPedido.setProveedor((Proveedores) vista.comboPedidoProveedor.getSelectedItem());
                nuevoPedido.setFechaPedido(Date.valueOf(vista.DatePickerPedidos.getDateStringOrEmptyString()));
                nuevoPedido.setCantidad(Integer.parseInt(vista.txtPedidoCantidad.getText()));

                modelo.altaPedido(nuevoPedido);
                limpiarCamposPedidos();
                listarTodo();
                break;
            case "btnPedidoModificar":
                DetalleProveedor editarPedido = (DetalleProveedor) vista.ListaPedido.getSelectedValue();
                editarPedido.setMaterial((Material) vista.comboPedidoMaterial.getSelectedItem());
                editarPedido.setProveedor((Proveedores) vista.comboPedidoProveedor.getSelectedItem());
                editarPedido.setFechaPedido(Date.valueOf(vista.DatePickerPedidos.getDate()));
                editarPedido.setCantidad(Integer.parseInt(vista.txtPedidoCantidad.getText()));

                modelo.modificarPedido(editarPedido);
                listarTodo();
                break;
            case "btnPedidoEliminar":
                DetalleProveedor eliminarPedido = (DetalleProveedor) vista.ListaPedido.getSelectedValue();

                modelo.borrarPedido(eliminarPedido);
                limpiarCamposPedidos();
                listarTodo();
                break;
        }
    }

    /**
     * Lista todas las tablas de la Vista sacando los datos del modelo
     */
    private void listarTodo() {
        listarSocios(modelo.getSocios());
        listarInstructores(modelo.getInstructor());
        listarActividades(modelo.getActividad());
        listarMateriales(modelo.getMaterial());
        listarProveedores(modelo.getProveedor());
        listarPedidos(modelo.getPedido());
    }

    /**
     * Método sobreescrito que nos permite detectar si hacemos click en una de las listas para auto-rellenar los campos
     * y así tenerlo en los TextFields para sobreescribirlos/modificarlos/borrarlos
     * @param e el evento de lista
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            if (e.getSource() == vista.ListSocio) {
                Socios seleccionSocios = (Socios) vista.ListSocio.getSelectedValue();
                vista.txtSociosNombre.setText(seleccionSocios.getNombre());
                vista.txtSociosApellidos.setText(seleccionSocios.getApellidos());
                vista.txtSociosDni.setText(seleccionSocios.getDni());
                vista.DatePickerSocios.setDate(seleccionSocios.getFechanacimiento().toLocalDate());
                vista.comboSociosTarifa.setSelectedItem(seleccionSocios.getTarifa());

                if (seleccionSocios.getInstructor() != null) {
                    vista.comboSociosInstructor.setSelectedItem(seleccionSocios.getInstructor());
                } else {
                    vista.txtSociosInstructor.setText("");
                }

            } else if (e.getSource() == vista.listaInstructor) {
                Instructores seleccionInstructor = (Instructores) vista.listaInstructor.getSelectedValue();
                vista.txtInsNombre.setText(seleccionInstructor.getNombre());
                vista.txtInsApellidos.setText(seleccionInstructor.getApellidos());
                vista.DatePickerInstruct.setDate(seleccionInstructor.getFechanacimiento().toLocalDate());
                vista.txtInsCodigo.setText(seleccionInstructor.getCodigoInstructor());
            } else if (e.getSource() == vista.ListActividad) {
                Actividades seleccionActividad = (Actividades) vista.ListActividad.getSelectedValue();
                vista.txtActTitulo.setText(seleccionActividad.getTitulo());
                vista.comboActividadesInstalacion.setSelectedItem(seleccionActividad.getInstalacion());
                vista.txtActHSemanal.setText(seleccionActividad.getHorasSemanales() + "");
                vista.txtActPrecio.setText(seleccionActividad.getPrecio() + "");
            } else if (e.getSource() == vista.ListMaterial){
                Material materialSeleccion = (Material) vista.ListMaterial.getSelectedValue();
                vista.txtMatNombre.setText(materialSeleccion.getNombre());
                if (materialSeleccion.getDisponible() == 1){
                    vista.comboActDispnible.setSelectedItem("SI");
                }else{
                    vista.comboActDispnible.setSelectedItem("NO");
                }
            } else if (e.getSource() == vista.ListProveedor){
                Proveedores proveedor = (Proveedores) vista.ListProveedor.getSelectedValue();
                vista.txtProvTipo.setText(proveedor.getTipo());
                vista.txtProvDirecc.setText(proveedor.getDireccion());
                vista.txtProvTelef.setText(String.valueOf(proveedor.getTelefono()));
                vista.txtProvFax.setText(proveedor.getFax());
            } else if (e.getSource() == vista.ListaPedido){
                DetalleProveedor pedidoAct = (DetalleProveedor) vista.ListaPedido.getSelectedValue();
                vista.txtPedidoCantidad.setText(pedidoAct.getCantidad()+"");
                vista.DatePickerPedidos.setDate(pedidoAct.getFechaPedido().toLocalDate());
                vista.comboPedidoProveedor.setSelectedItem(pedidoAct.getProveedor());
                vista.comboPedidoMaterial.setSelectedItem(pedidoAct.getMaterial());
            }
        }
    }


    /**
     * Método que añade todos los listeners de la Vista
     * @param listener el listener que añadir (En este caso la clase hereda de Listener así que será this)
     */
    private void addActionListeners(ActionListener listener) {
        vista.conexionItem.addActionListener(listener);
        vista.salirItem.addActionListener(listener);
        vista.actualizarItem.addActionListener(listener);

        vista.botonSocModificar.addActionListener(listener);
        vista.botonSocRegistrar.addActionListener(listener);
        vista.botonSocEliminar.addActionListener(listener);

        vista.botonInstEliminar.addActionListener(listener);
        vista.botonInstModificar.addActionListener(listener);
        vista.botonInstRegistro.addActionListener(listener);
        vista.btnInstVerSocios.addActionListener(listener);

        vista.botonActRegistro.addActionListener(listener);
        vista.botonActModificar.addActionListener(listener);
        vista.botonActRegistroEliminar.addActionListener(listener);

        vista.botonMatRegistrar.addActionListener(listener);
        vista.botonMatModificar.addActionListener(listener);
        vista.botonMatEliminar.addActionListener(listener);

        vista.btnProvRegistrar.addActionListener(listener);
        vista.btnProvModificar.addActionListener(listener);
        vista.btnProvEliminar.addActionListener(listener);
        vista.btnProvVerMat.addActionListener(listener);

        vista.btnPedidoRegistrar.addActionListener(listener);
        vista.btnPedidoModificar.addActionListener(listener);
        vista.btnPedidoEliminar.addActionListener(listener);
    }

    /**
     * Método que permite registrar los cambios en las tablas mediante el conocido ListSelectionListener
     * @param listener el listener de la tabla a mirar (En nuestro caso this)
     */
    private void addListSelectionListener(ListSelectionListener listener) {
        vista.ListSocio.addListSelectionListener(listener);
        vista.listaInstructor.addListSelectionListener(listener);
        vista.ListActividad.addListSelectionListener(listener);
        vista.ListMaterial.addListSelectionListener(listener);
        vista.ListProveedor.addListSelectionListener(listener);
        vista.ListaPedido.addListSelectionListener(listener);
    }

    /**
     * Método que limpia todos los campos por si en un futuro se implementase un control de usuarios por gerente
     */
    private void limpiarTodo() {
        limpiarCamposSocios();
        limpiarCamposInstructores();
        limpiarCamposActividades();
        limpiarCamposMateriales();
        limpiarCamposPedidos();
        limpiarCamposProveedores();
    }


    /**
     * Limpia todos los campos de SOCIOS
     */
    private void limpiarCamposSocios() {
        vista.txtSociosNombre.setText("");
        vista.txtSociosApellidos.setText("");
        vista.txtSociosDni.setText("");
        vista.DatePickerSocios.setText("");
        vista.comboSociosTarifa.setSelectedIndex(-1);
        vista.comboSociosInstructor.setSelectedIndex(-1);
        vista.comboSocActividad.setSelectedIndex(-1);
    }

    /**
     * Limpia todos los campos de INSTRUCTORES
     */
    private void limpiarCamposInstructores() {
        vista.txtInsNombre.setText("");
        vista.txtInsApellidos.setText("");
        vista.DatePickerInstruct.setText("");
        vista.txtInsCodigo.setText("");
        vista.comboInstAct.setSelectedIndex(-1);
    }

    /**
     * Limpia todos los campos de ACTIVIDADES
     */
    private void limpiarCamposActividades() {
        vista.txtActTitulo.setText("");
        vista.txtActPrecio.setText("");
        vista.txtActHSemanal.setText("");
        vista.comboActividadesInstalacion.setSelectedIndex(-1);
    }

    /**
     * Limpia todos los campos de MATERIALES
     */
    private void limpiarCamposMateriales() {
        vista.txtMatNombre.setText("");
        vista.comboActDispnible.setSelectedIndex(-1);
    }

    /**
     * Limpia todos los campos de PEDIDOS
     */
    private void limpiarCamposPedidos() {
        vista.txtPedidoCantidad.setText("");
        vista.comboPedidoMaterial.setSelectedIndex(-1);
        vista.comboPedidoProveedor.setSelectedIndex(-1);
        vista.DatePickerPedidos.setText("");
    }

    /**
     * Limpia todos los campos de PROVEEDORES
     */
    private void limpiarCamposProveedores() {
        vista.txtProvDirecc.setText("");
        vista.txtProvTipo.setText("");
        vista.txtProvTelef.setText("");
        vista.txtProvFax.setText("");
    }


    /**
     * Lista los socios de la base de datos en la pestaña de socios
     * @param socios lista de socios (sacada de modelo)
     */
    private void listarSocios(ArrayList<Socios> socios) {
        vista.dlmSocio.clear();
        for(Socios socio : socios){
            vista.dlmSocio.addElement(socio);
        }
    }

    /**
     * Lista los socios por instructor de la base de datos en la pestaña de instructor
     * @param sociosInstructor lista de socios (sacada de modelo)
     */
    private void listarSociosInstructores(ArrayList<Socios> sociosInstructor) {
        vista.dlmInstructorFiltroSocio.clear();
        for(Socios soc : sociosInstructor){
            vista.dlmInstructorFiltroSocio.addElement(soc);
        }

    }

    /**
     * Lista los Instructores de la base de datos en la pestaña de Instructores
     * @param lista lista de Instructores (sacada de modelo)
     */
    public void listarInstructores(ArrayList<Instructores> lista){
        vista.dlmInstructor.clear();
        for(Instructores instructor : lista){
            vista.dlmInstructor.addElement(instructor);
        }

        vista.comboSociosInstructor.removeAllItems();
        ArrayList<Instructores> ins = modelo.getInstructor();

        for (Instructores in : ins){
            vista.comboSociosInstructor.addItem(in);
        }
        vista.comboSociosInstructor.setSelectedIndex(-1);

    }

    /**
     * Lista los Actividades de la base de datos en la pestaña de Actividades
     * @param lista lista de Actividades (sacada de modelo)
     */
    public void listarActividades(ArrayList<Actividades> lista){
        vista.dlmActividad.clear();
        for(Actividades actividad : lista){
            vista.dlmActividad.addElement(actividad);
        }

    }


    /**
     * Lista los Pedidos de la base de datos en la pestaña de Pedidos
     * @param proveedoresLista lista de Pedidos (sacada de modelo)
     */
    private void listarPedidoProveedor(List<DetalleProveedor> proveedoresLista) {
        vista.dlmMaterialProveedor.clear();
        for(DetalleProveedor prov : proveedoresLista){
            vista.dlmMaterialProveedor.addElement(prov);
        }

    }

    /**
     * Lista los Materiales de la base de datos en la pestaña de Materiales
     * @param lista lista de Materiales (sacada de modelo)
     */
    public void listarMateriales(ArrayList<Material> lista){
        vista.dlmMaterial.clear();
        for(Material Material : lista){
            vista.dlmMaterial.addElement(Material);
        }
    }

    /**
     * Lista los Proveedores de la base de datos en la pestaña de Proveedores
     * @param lista lista de Proveedores (sacada de modelo)
     */
    public void listarProveedores(ArrayList<Proveedores> lista){
        vista.dlmProveedor.clear();
        for(Proveedores Proveedores : lista){
            vista.dlmProveedor.addElement(Proveedores);
        }
    }


    /**
     * Lista los Pedidos de la base de datos en la pestaña de Pedidos
     * @param lista lista de Pedidos (sacada de modelo)
     */
    public void listarPedidos(ArrayList<DetalleProveedor> lista){
        vista.dlmPedido.clear();
        for(DetalleProveedor pedido : lista){
            vista.dlmPedido.addElement(pedido);
        }

        vista.comboPedidoMaterial.removeAllItems();
        ArrayList<Material> mat = modelo.getMaterial();
        vista.comboPedidoProveedor.removeAllItems();
        ArrayList<Proveedores> prov = modelo.getProveedor();


        for(Material material : mat){
            vista.comboPedidoMaterial.addItem(material);
        }
        for(Proveedores proveedor : prov){
            vista.comboPedidoProveedor.addItem(proveedor);
        }
        vista.comboPedidoMaterial.setSelectedIndex(-1);
        vista.comboPedidoProveedor.setSelectedIndex(-1);

    }


}
