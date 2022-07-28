package id.sch.pkbm31.presentation;

import id.sch.pkbm31.application.PesertaDidik;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class FormPesertaDidikController {

    @FXML
    public ComboBox<String> cboAgama;

    @FXML
    public ComboBox<String> cboJenisKelamin;

    @FXML
    public DialogPane dialog;

    @FXML
    public TextField txtNamaLengkap;

    @FXML
    public TextField txtNisn;
    
    
    public PesertaDidik pesertaDidik;
    
    @FXML
    private void initialize() {
    	cboJenisKelamin.getItems().addAll("Laki-laki", "Perempuan");
    	
    	cboAgama.getItems().add("Islam");
    	cboAgama.getItems().add("Kristen Protestan");
    	cboAgama.getItems().add("Kristen Katolik");
    	cboAgama.getItems().add("Hindu");
    	cboAgama.getItems().add("Buddha");
    	cboAgama.getItems().add("Konghucu");
    	cboAgama.getItems().add("Agama Lainnya");
    }

    public void setPesertaDidik(PesertaDidik pesertaDidik) {
    	this.pesertaDidik = pesertaDidik;
    	
    	txtNisn.textProperty().bindBidirectional(pesertaDidik.nisnProperty());
    	txtNamaLengkap.textProperty().bindBidirectional(pesertaDidik.namaLengkapProperty());
    	cboJenisKelamin.valueProperty().bindBidirectional(pesertaDidik.jenisKelaminProperty());
    	cboAgama.valueProperty().bindBidirectional(pesertaDidik.agamaProperty());
    }
}
