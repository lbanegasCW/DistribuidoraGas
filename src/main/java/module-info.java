module ar.edu.ubp.doo.distribuidoragas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    exports ar.edu.ubp.doo.distribuidoragas;
    exports ar.edu.ubp.doo.distribuidoragas.controller;
    opens ar.edu.ubp.doo.distribuidoragas.controller to javafx.fxml;
}