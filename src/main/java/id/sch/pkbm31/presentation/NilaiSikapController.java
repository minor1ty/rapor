package id.sch.pkbm31.presentation;

import java.io.IOException;

import id.sch.pkbm31.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class NilaiSikapController {

    @FXML
    private Button btnHapus;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnSunting;

    @FXML
    private Button btnTambah;

    @FXML
    private TableColumn<?, ?> deskripsiSosial;

    @FXML
    private TableColumn<?, ?> deskripsiSpiritual;

    @FXML
    private TableColumn<?, ?> namaPesertaDidik;

    @FXML
    private TableColumn<?, ?> nisn;

    @FXML
    private TableView tblNilaiSikap;

    @FXML
    void gotoHome(ActionEvent event) throws IOException {
    	Main.setRoot("Home");
    }

    @FXML
    void hapus(ActionEvent event) {

    }

    @FXML
    void sunting(ActionEvent event) {

    }

    @FXML
    void tambah(ActionEvent event) {

    }

}
