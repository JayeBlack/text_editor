module com.texteditor.text_editor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.texteditor.text_editor to javafx.fxml;
    exports com.texteditor.text_editor;
}