module algebra.hr.gamejava {
    requires javafx.controls;
    requires javafx.fxml;


    opens algebra.hr.gamejava to javafx.fxml;
    exports algebra.hr.gamejava;
}