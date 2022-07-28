package id.sch.pkbm31.presentation;

import id.sch.pkbm31.application.MataPelajaran;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class FormMataPelajaranController {

    @FXML
    public DialogPane dialog;

    @FXML
    public TextArea txtKeterampilanKkm;

    @FXML
    public TextArea txtKeterampilanTdkKkm;

    @FXML
    public TextField txtNamaMapel;

    @FXML
    public TextArea txtPengetahuanKkm;

    @FXML
    public TextArea txtPengetahuanTdkKkm;

    @FXML
    public TextField txtKodeMapel;
    
    
    public MataPelajaran mataPelajaran;
    
    public void setMataPelajaran(MataPelajaran mataPelajaran) {
    	this.mataPelajaran = mataPelajaran;
    	
    	txtKodeMapel.textProperty().bindBidirectional(mataPelajaran.kodeMapelProperty());
    	txtNamaMapel.textProperty().bindBidirectional(mataPelajaran.namaMapelProperty());
    	txtPengetahuanKkm.textProperty().bindBidirectional(mataPelajaran.pengetahuanKkmProperty());
    	txtPengetahuanTdkKkm.textProperty().bindBidirectional(mataPelajaran.pengetahuanTidakKkmProperty());
    	txtKeterampilanKkm.textProperty().bindBidirectional(mataPelajaran.keterampilanKkmProperty());
    	txtKeterampilanTdkKkm.textProperty().bindBidirectional(mataPelajaran.keterampilanTidakKkmProperty());
    }

}
