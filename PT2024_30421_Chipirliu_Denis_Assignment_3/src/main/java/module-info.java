module org.application.pt2024_30421_chipirliu_denis_assignment_3 {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.logging;
    requires java.sql;
    requires java.desktop;


    opens org.application.pt2024_30421_chipirliu_denis_assignment_3.presentation to javafx.fxml;
    exports org.application.pt2024_30421_chipirliu_denis_assignment_3.presentation;

    opens org.application.pt2024_30421_chipirliu_denis_assignment_3.model to javafx.fxml;
    exports org.application.pt2024_30421_chipirliu_denis_assignment_3.model;

    opens org.application.pt2024_30421_chipirliu_denis_assignment_3.bll to javafx.fxml;
    exports org.application.pt2024_30421_chipirliu_denis_assignment_3.bll;

    opens org.application.pt2024_30421_chipirliu_denis_assignment_3.bll.validators to javafx.fxml;
    exports org.application.pt2024_30421_chipirliu_denis_assignment_3.bll.validators;

    opens org.application.pt2024_30421_chipirliu_denis_assignment_3.dao to javafx.fxml;
    exports org.application.pt2024_30421_chipirliu_denis_assignment_3.dao;

    opens org.application.pt2024_30421_chipirliu_denis_assignment_3.connection to javafx.fxml;
    exports org.application.pt2024_30421_chipirliu_denis_assignment_3.connection;

}