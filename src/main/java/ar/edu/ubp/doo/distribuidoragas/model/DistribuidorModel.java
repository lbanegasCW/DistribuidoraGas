package ar.edu.ubp.doo.distribuidoragas.model;

import ar.edu.ubp.doo.distribuidoragas.bo.Distribuidor;
import ar.edu.ubp.doo.distribuidoragas.dao.DistribuidorDAO;

import java.util.List;

public class DistribuidorModel {
    DistribuidorDAO distribuidorDAO = new DistribuidorDAO();

    public boolean guardarDistribuidor(Distribuidor distribuidor) {
        return distribuidorDAO.guardarDistribuidor(distribuidor);
    }

    public List<Distribuidor> obtenerDistribuidores() {
        List<Distribuidor> distribuidores = distribuidorDAO.buscarDistribuidores("");
        for (Distribuidor distribuidor : distribuidores) {
            distribuidor.setZona(new ZonaModel().obtenerZonaPorDistribuidor(distribuidor.getIdDistribuidor()));
        }
        return distribuidores;
    }

    public List<Distribuidor> buscarDistribuidores(String criterio) {
        List<Distribuidor> distribuidores = distribuidorDAO.buscarDistribuidores(criterio);
        for (Distribuidor distribuidor : distribuidores) {
            distribuidor.setZona(new ZonaModel().obtenerZonaPorDistribuidor(distribuidor.getIdDistribuidor()));
        }
        return distribuidores;
    }

}
