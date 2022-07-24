package id.sch.pkbm31.presentation;

import java.sql.SQLException;

import id.sch.pkbm31.application.FormLegerNilai;
import id.sch.pkbm31.application.FormLegerNilaiDAO;
import id.sch.pkbm31.application.LegerNilai;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class FormLegerNilaiController {

    @FXML
    public ComboBox<FormLegerNilai> cboMataPelajaran;

    @FXML
    public ComboBox<FormLegerNilai> cboNamaPesertaDidik;

    @FXML
    public DialogPane dialog;

    @FXML
    public TextField txtGanjil;

    @FXML
    public TextField txtGenap;
    
    public LegerNilai legerNilai;
    
    @FXML
    private void populatePesertaDidik (ObservableList<FormLegerNilai> pesertaDidikData) throws ClassNotFoundException {
        //Set items to the userTable
    	cboNamaPesertaDidik.setItems(pesertaDidikData);
    }
    
    @FXML
    private void populateMataPelajaran (ObservableList<FormLegerNilai> mataPelajaranData) throws ClassNotFoundException {
        //Set items to the userTable
    	cboMataPelajaran.setItems(mataPelajaranData);
    }
    
    
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException {
    	
    	try {
            //Get all Users information
            ObservableList<FormLegerNilai> pesertaDidikData = FormLegerNilaiDAO.searchPesertaDidik();
            ObservableList<FormLegerNilai> mataPelajaranData = FormLegerNilaiDAO.searchMataPelajaran();
            //Populate Employees on TableView
            
            
            populatePesertaDidik(pesertaDidikData);
            populateMataPelajaran(mataPelajaranData);
            
            
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    public void setLegerNilai(LegerNilai legerNilai) {
    	this.legerNilai = legerNilai;
    	
    	//cboNamaPesertaDidik.valueProperty().bindBidirectional(legerNilai.namaLengkapProperty());
    	//cboMataPelajaran.valueProperty().bindBidirectional(legerNilai.namaMapelProperty());
    	txtGanjil.textProperty().bindBidirectional(legerNilai.ganjilProperty(), new NumberStringConverter());
    	txtGenap.textProperty().bindBidirectional(legerNilai.genapProperty(), new NumberStringConverter());
    }

}
