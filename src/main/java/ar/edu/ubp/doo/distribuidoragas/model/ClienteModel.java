package ar.edu.ubp.doo.distribuidoragas.model;

import ar.edu.ubp.doo.distribuidoragas.bo.Cliente;
import ar.edu.ubp.doo.distribuidoragas.dao.ClienteDAO;

import java.util.List;

public class ClienteModel {
    ClienteDAO clienteDAO = new ClienteDAO();

    public boolean guardarCliente(Cliente cliente) {
        return clienteDAO.guardarCliente(cliente);
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = clienteDAO.buscarClientes("");
        for (Cliente cliente : clientes) {
            cliente.setDomicilio(new DomicilioModel().obtenerDomicilioPorCliente(cliente.getIdCliente()));
        }
        return clientes;
    }

    public List<Cliente> buscarClientes(String criterio) {
        List<Cliente> clientes = clienteDAO.buscarClientes(criterio);
        for (Cliente cliente : clientes) {
            cliente.setDomicilio(new DomicilioModel().obtenerDomicilioPorCliente(cliente.getIdCliente()));
        }
        return clientes;
    }

    public Cliente obtenerClientePorPedido(int pedidoId) {
        Cliente cliente = clienteDAO.obtenerClientePorPedido(pedidoId);
        cliente.setDomicilio(new DomicilioModel().obtenerDomicilioPorCliente(cliente.getIdCliente()));

        return cliente;
    }

}
