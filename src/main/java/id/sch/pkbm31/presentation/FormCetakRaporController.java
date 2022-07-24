package id.sch.pkbm31.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FormCetakRaporController {

    @FXML
    private ComboBox<?> cboJenisRapor;

    @FXML
    private TableColumn<?, ?> colPesertaDidik;

    @FXML
    private TableColumn<?, ?> colPilih;

    @FXML
    private DialogPane dialog;

    @FXML
    private TableView<?> tblPesertaDidik;

}
