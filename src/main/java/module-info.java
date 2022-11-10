module ca.bcit.comp2522.termproject.essence {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.bcit.comp2522.termproject.essence to javafx.fxml;
    exports ca.bcit.comp2522.termproject.essence;
}