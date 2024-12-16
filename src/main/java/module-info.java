module at.htl.adventskalenderabercool {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires javafx.media;


    opens at.htl.adventskalenderabercool to javafx.fxml;
    exports at.htl.adventskalenderabercool;
}