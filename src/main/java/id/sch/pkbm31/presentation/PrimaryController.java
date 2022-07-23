package id.sch.pkbm31.presentation;

import java.io.IOException;

import id.sch.pkbm31.Main;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
    	Main.setRoot("secondary");
    }
}
