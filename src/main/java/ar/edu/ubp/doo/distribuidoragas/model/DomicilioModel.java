package ar.edu.ubp.doo.distribuidoragas.model;

import ar.edu.ubp.doo.distribuidoragas.bo.Domicilio;
import ar.edu.ubp.doo.distribuidoragas.dao.DomicilioDAO;

public class DomicilioModel {
    DomicilioDAO domicilioDao = new DomicilioDAO();

    public Domicilio obtenerDomicilioPorCliente(int clienteId) {
        Domicilio domicilio = domicilioDao.obtenerDomicilioPorCliente(clienteId);
        if (domicilio != null) {
            domicilio.setBarrio(new BarrioModel().obtenerBarrioDomicilio(domicilio.getIdDomicilio()));
        }
        return domicilio;
    }
}
