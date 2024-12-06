package ar.edu.ubp.doo.distribuidoragas.model;

import ar.edu.ubp.doo.distribuidoragas.bo.Zona;
import ar.edu.ubp.doo.distribuidoragas.dao.ZonaDAO;

import java.sql.SQLException;
import java.util.List;

public class ZonaModel {
    ZonaDAO zonaDAO = new ZonaDAO();

    public List<Zona> obtenerZonas() throws SQLException {
        return zonaDAO.obtenerZonas();
    }

    public Zona obtenerZonaPorDistribuidor(int distribuidorId) {
        return zonaDAO.obtenerZonaPorDistribuidor(distribuidorId);
    }

    public Zona obtenerZonaPorPedidoDetalle(int detallePedidoId) {
        return zonaDAO.obtenerZonaPorPedidoDetalle(detallePedidoId);
    }

}
