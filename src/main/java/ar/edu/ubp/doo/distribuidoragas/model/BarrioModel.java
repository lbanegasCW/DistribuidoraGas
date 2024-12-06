package ar.edu.ubp.doo.distribuidoragas.model;

import ar.edu.ubp.doo.distribuidoragas.bo.Barrio;
import ar.edu.ubp.doo.distribuidoragas.dao.BarrioDAO;

import java.sql.SQLException;
import java.util.List;

public class BarrioModel {
    BarrioDAO barrioDAO = new BarrioDAO();

    public List<Barrio> obtenerBarrios() throws SQLException {
        return barrioDAO.obtenerBarrios();
    }

    public Barrio obtenerBarrioDomicilio(int domicilioId) {
        return barrioDAO.obtenerBarrioPorDomicilio(domicilioId);
    }

}
