package id.sch.pkbm31.presentation;

import java.io.IOException;

import id.sch.pkbm31.Main;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
    	Main.setRoot("primary");
    }
}