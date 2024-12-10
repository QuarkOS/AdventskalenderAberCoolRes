module at.htl.adventskalenderabercool {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens at.htl.adventskalenderabercool to javafx.fxml;
    exports at.htl.adventskalenderabercool;
}