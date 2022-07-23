package id.sch.pkbm31.presentation;

import java.io.IOException;

import id.sch.pkbm31.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    private Button btnAbsensi;

    @FXML
    private Button btnCatatanWaliKelas;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnNilaiSikap;

    @FXML
    private Button btnLegerNilai;

    @FXML
    private Button btnMataPelajaran;

    @FXML
    private Button btnPesertaDidik;

    @FXML
    void gotoAbsensi(ActionEvent event) throws IOException {
    	Main.setRoot("Absensi");
    }

    @FXML
    void gotoCatatanWaliKelas(ActionEvent event) throws IOException {
    	Main.setRoot("CatatanWaliKelas");
    }

    @FXML
    void gotoDashboard(ActionEvent event) throws IOException {
    	
    }

    @FXML
    void gotoNilaiSikap(ActionEvent event) {

    }

    @FXML
    void gotoLegerNilai(ActionEvent event) throws IOException {
    	Main.setRoot("LegerNilai");
    }

    @FXML
    void gotoMataPelajaran(ActionEvent event) throws IOException {
    	Main.setRoot("MataPelajaran");
    }

    @FXML
    void gotoPesertaDidik(ActionEvent event) throws IOException {
    	Main.setRoot("PesertaDidik");
    }

}
