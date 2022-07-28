package id.sch.pkbm31.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import id.sch.pkbm31.Main;
import id.sch.pkbm31.application.CatatanWaliKelas;
import id.sch.pkbm31.application.CatatanWaliKelasDAO;
import id.sch.pkbm31.application.MataPelajaran;
import id.sch.pkbm31.application.MataPelajaranDAO;
import id.sch.pkbm31.presentation.MataPelajaranController.DialogMode;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class CatatanWaliKelasController {


    @FXML
    private Button btnHapus;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnSunting;

    @FXML
    private Button btnTambah;

    @FXML
    private TableColumn<CatatanWaliKelas, String> catatan;

    @FXML
    private TableColumn<CatatanWaliKelas, String> namaPesertaDidik;

    @FXML
    private TableColumn<CatatanWaliKelas, String> nisn;

    @FXML
    private TableView tblCatatanWaliKelas;

    enum DialogMode {
    	TAMBAH,
    	SUNTING,
    	HAPUS
    }
    
    @FXML
    private void populateCatatanWaliKelas (ObservableList<CatatanWaliKelas> catatanWaliKelasData) throws ClassNotFoundException {
        //Set items to the userTable
    	tblCatatanWaliKelas.setItems(catatanWaliKelasData);
    }
    
    @FXML
    private void initialize() throws ClassNotFoundException, SQLException {
    	nisn.setCellValueFactory(cellData -> cellData.getValue().nisnProperty());
    	namaPesertaDidik.setCellValueFactory(cellData -> cellData.getValue().namaPesertaDidikProperty());
    	catatan.setCellValueFactory(cellData -> cellData.getValue().catatanProperty());
    	
    	try {
            //Get all Users information
            ObservableList<CatatanWaliKelas> catatanWaliKelasData = CatatanWaliKelasDAO.searchCatatanWaliKelas();
            //Populate Employees on TableView
            populateCatatanWaliKelas(catatanWaliKelasData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    @FXML
    void gotoHome(ActionEvent event) throws IOException {
    	Main.setRoot("Home");
    }

    @FXML
    void hapus(ActionEvent event) throws ClassNotFoundException, SQLException {
    	CatatanWaliKelas catatanWaliKelas= (CatatanWaliKelas) tblCatatanWaliKelas.getSelectionModel().getSelectedItem();
    	
    	Optional<ButtonType> isConfirmed = showConfirmationDialog("Hapus Catatan", "Konfirmasi Hapus", "Apakah anda yakin ingin menghapus catatan ini?");
    	
    	if (isConfirmed.get() == ButtonType.OK) {
    		String nisn = catatanWaliKelas.getNisn();
    		CatatanWaliKelasDAO.deleteCatatanWaliKelas(nisn);
    	}
    	
    	try {
            //Get all Users information
            ObservableList<CatatanWaliKelas> catatanWaliKelasData = CatatanWaliKelasDAO.searchCatatanWaliKelas();
            //Populate Employees on TableView
            populateCatatanWaliKelas(catatanWaliKelasData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    private Optional<ButtonType> showConfirmationDialog(String title, String headerText, String contentText) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(headerText);
    	alert.setContentText(contentText);
    	
    	return alert.showAndWait();
    }

    @FXML
    void sunting(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
    	CatatanWaliKelas catatanWaliKelas = null;
    	String DialogTitle = "";
    	DialogMode mode;
    	
    	if (event.getSource().equals(btnSunting)) {
    		mode = DialogMode.SUNTING;
    		DialogTitle = "Sunting Catatan Wali Kelas";
    		catatanWaliKelas = (CatatanWaliKelas) tblCatatanWaliKelas.getSelectionModel().getSelectedItem();	
    		
    	} else if (event.getSource().equals(btnTambah)) {
    		mode = DialogMode.TAMBAH;
    		DialogTitle = "Tambah Catatan Wali Kelas";
    		catatanWaliKelas = new CatatanWaliKelas();		
    	} else {
    		return;
    	}
    	
    	FXMLLoader fxmlLoader = new FXMLLoader();
    	fxmlLoader.setLocation(getClass().getResource("FormCatatanWaliKelas.fxml"));
    	DialogPane catatanWaliKelasDialogPane = fxmlLoader.load();
    	
    	FormCatatanWaliKelasController formCatatanWaliKelasController = fxmlLoader.getController();
    	formCatatanWaliKelasController.setCatatanWaliKelas(catatanWaliKelas);
    	
    	if (event.getSource().equals(btnSunting)) {
    		formCatatanWaliKelasController.cboNamaPesertaDidik.setDisable(true);
    	} else {
    		formCatatanWaliKelasController.cboNamaPesertaDidik.setDisable(false);
    	}

    	Dialog<ButtonType> dialog = new Dialog<>();
    	dialog.setDialogPane(catatanWaliKelasDialogPane);
    	dialog.setTitle(DialogTitle);
    	
    	Optional<ButtonType> clickedButton = dialog.showAndWait();
    	
    	if (clickedButton.get() == ButtonType.OK) {
    		String pesertaDidikData = formCatatanWaliKelasController.cboNamaPesertaDidik.getSelectionModel().getSelectedItem().toString();
			String[] pesertaDidikSplit = pesertaDidikData.split(" - ");
			String nisnPesertaDidik = pesertaDidikSplit[0];
			String namaPesertaDidik = pesertaDidikSplit[1];
			
			String catatan = formCatatanWaliKelasController.txtCatatan.getText();
			
    		if (mode == DialogMode.TAMBAH) {
    			CatatanWaliKelasDAO.addCatatanWaliKelas(nisnPesertaDidik, catatan);

    		} else if (mode == DialogMode.SUNTING) {
    			CatatanWaliKelasDAO.editCatatanWaliKelas(nisnPesertaDidik, catatan);
    	    }
    		

    		try {
                //Get all Users information
                ObservableList<CatatanWaliKelas> catatanWaliKelasData = CatatanWaliKelasDAO.searchCatatanWaliKelas();
                //Populate Employees on TableView
                populateCatatanWaliKelas(catatanWaliKelasData);
            } catch (SQLException e){
                System.out.println("Error occurred while getting employees information from DB.\n" + e);
                throw e;
            }
    	}
    }

    @FXML
    void tambah(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
    	sunting(event);
    }

}
