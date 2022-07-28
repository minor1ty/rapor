package id.sch.pkbm31.presentation;

import java.sql.SQLException;

import id.sch.pkbm31.application.CatatanWaliKelas;
import id.sch.pkbm31.application.FormCatatanWaliKelas;
import id.sch.pkbm31.application.FormCatatanWaliKelasDAO;
import id.sch.pkbm31.application.FormLegerNilai;
import id.sch.pkbm31.application.FormLegerNilaiDAO;
import id.sch.pkbm31.application.LegerNilai;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.util.converter.NumberStringConverter;

public class FormCatatanWaliKelasController {

    @FXML
    public ComboBox<FormCatatanWaliKelas> cboNamaPesertaDidik;

    @FXML
    public DialogPane dialog;

    @FXML
    public TextArea txtCatatan;
    
    public CatatanWaliKelas catatanWaliKelas;
    
    @FXML
    private void populatePesertaDidik (ObservableList<FormCatatanWaliKelas> pesertaDidikData) throws ClassNotFoundException {
        //Set items to the userTable
    	cboNamaPesertaDidik.setItems(pesertaDidikData);
    }
  
   
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException {
    	
    	try {
            //Get all Users information
            ObservableList<FormCatatanWaliKelas> pesertaDidikData = FormCatatanWaliKelasDAO.searchPesertaDidik();

            populatePesertaDidik(pesertaDidikData);
            
            
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    public void setCatatanWaliKelas(CatatanWaliKelas catatanWaliKelas) {
    	this.catatanWaliKelas = catatanWaliKelas;
    	
    	txtCatatan.textProperty().bindBidirectional(catatanWaliKelas.catatanProperty());
    }
}
