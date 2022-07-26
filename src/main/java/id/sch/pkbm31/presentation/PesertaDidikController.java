package id.sch.pkbm31.presentation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import id.sch.pkbm31.Main;
import id.sch.pkbm31.application.PesertaDidik;
import id.sch.pkbm31.application.PesertaDidikDAO;
import id.sch.pkbm31.data.DBUtil;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class PesertaDidikController {

    @FXML
    private Button btnCetak;

    @FXML
    private Button btnHapus;

    @FXML
    private Button btnHome;


    @FXML
    private Button btnSunting;

    @FXML
    private Button btnTambah;

    @FXML
    private TableView tblPesertaDidik;
     
    @FXML
    private TableColumn<PesertaDidik, String> nisnColumn;
    
    @FXML
    private TableColumn<PesertaDidik, String> namaLengkapColumn;
    
    @FXML
    private TableColumn<PesertaDidik, String> jenisKelaminColumn;
    
    @FXML
    private TableColumn<PesertaDidik, String> agamaColumn;
    
    enum DialogMode {
    	TAMBAH,
    	SUNTING,
    	HAPUS
    }
    
    private ObservableList<PesertaDidik> pesertaDidikOL = null;
    
    @FXML
    private void populatePesertaDidik (ObservableList<PesertaDidik> pesertaDidikData) throws ClassNotFoundException {
        //Set items to the userTable
    	tblPesertaDidik.setItems(pesertaDidikData);
    }
    
    @FXML
    private void initialize() throws ClassNotFoundException, SQLException {
    	nisnColumn.setCellValueFactory(cellData -> cellData.getValue().nisnProperty());
    	namaLengkapColumn.setCellValueFactory(cellData -> cellData.getValue().namaLengkapProperty());
    	jenisKelaminColumn.setCellValueFactory(cellData -> cellData.getValue().jenisKelaminProperty());
    	agamaColumn.setCellValueFactory(cellData -> cellData.getValue().agamaProperty());
    	
    	try {
            //Get all Users information
            ObservableList<PesertaDidik> pesertaDidikData = PesertaDidikDAO.searchPesertaDidik();
            //Populate Employees on TableView
            populatePesertaDidik(pesertaDidikData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    void cetakPesertaDidik(ActionEvent event) throws JRException, ClassNotFoundException, SQLException {
    	JasperPrint jp;
    	Map param = new HashMap();
    	
    	String reportPath = "C:\\Users\\Development\\Documents\\EclipseProjects\\rapor\\src\\main\\resources\\id\\sch\\pkbm31\\report\\PesertaDidikReport.jasper";
    	
    	
    	jp = JasperFillManager.fillReport(reportPath, param, DBUtil.dbConnect());
    	
    	JasperViewer viewer = new JasperViewer(jp, false);
    	viewer.setTitle("Cetak Data Peserta Didik");
    	viewer.setVisible(true);
    }

    @FXML
    void gotoHome(ActionEvent event) throws IOException {
    	Main.setRoot("Home");
    }

    @FXML
    void hapusPesertaDidik(ActionEvent event) throws ClassNotFoundException, SQLException {
    	PesertaDidik pesertaDidik = (PesertaDidik) tblPesertaDidik.getSelectionModel().getSelectedItem();
    	
    	Optional<ButtonType> isConfirmed = showConfirmationDialog("Hapus Peserta Didik", "Konfirmasi Hapus", "Apakah anda yakin ingin menghapus peserta didik ini?");
    	
    	if (isConfirmed.get() == ButtonType.OK) {
    		int table_id = pesertaDidik.getTableId();
    		System.out.println(table_id);
    		PesertaDidikDAO.deletePesertaDidik(table_id);
    		PesertaDidikDAO.resetTableId();
    	}
    	
    	try {
            //Get all Users information
            ObservableList<PesertaDidik> pesertaDidikData = PesertaDidikDAO.searchPesertaDidik();
            //Populate Employees on TableView
            populatePesertaDidik(pesertaDidikData);
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
    void suntingPesertaDidik(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	PesertaDidik pesertaDidik = null;
    	String DialogTitle = "";
    	DialogMode mode;
    	
    	if (event.getSource().equals(btnSunting)) {
    		mode = DialogMode.SUNTING;
    		DialogTitle = "Sunting Peserta Didik";
    		pesertaDidik = (PesertaDidik) tblPesertaDidik.getSelectionModel().getSelectedItem();		
    	} else if (event.getSource().equals(btnTambah)) {
    		mode = DialogMode.TAMBAH;
    		DialogTitle = "Tambah Peserta Didik";
    		pesertaDidik = new PesertaDidik();		
    	} else {
    		return;
    	}
    	
    	FXMLLoader fxmlLoader = new FXMLLoader();
    	fxmlLoader.setLocation(getClass().getResource("FormPesertaDidik.fxml"));
    	DialogPane pesertaDidikDialogPane = fxmlLoader.load();
    	
    	FormPesertaDidikController formPesertaDidikController = fxmlLoader.getController();
    	formPesertaDidikController.setPesertaDidik(pesertaDidik);
    	
    	if (event.getSource().equals(btnSunting)) {
    		formPesertaDidikController.txtNisn.setDisable(true);
    	}
    	
    	Dialog<ButtonType> dialog = new Dialog<>();
    	dialog.setDialogPane(pesertaDidikDialogPane);
    	dialog.setTitle(DialogTitle);
    	
    	Optional<ButtonType> clickedButton = dialog.showAndWait();
    	
    	if (clickedButton.get() == ButtonType.OK) {
    		if (mode == DialogMode.TAMBAH) {
    			PesertaDidikDAO.addPesertaDidik(formPesertaDidikController.txtNisn.getText(), formPesertaDidikController.txtNamaLengkap.getText(), formPesertaDidikController.cboJenisKelamin.getSelectionModel().getSelectedItem(), formPesertaDidikController.cboAgama.getSelectionModel().getSelectedItem());

    		} else if (mode == DialogMode.SUNTING) {
    			PesertaDidikDAO.editPesertaDidik(formPesertaDidikController.txtNisn.getText(), formPesertaDidikController.txtNamaLengkap.getText(), formPesertaDidikController.cboJenisKelamin.getSelectionModel().getSelectedItem(), formPesertaDidikController.cboAgama.getSelectionModel().getSelectedItem());
    		}
    		

			try {
	            //Get all Users information
	            ObservableList<PesertaDidik> pesertaDidikData = PesertaDidikDAO.searchPesertaDidik();
	            //Populate Employees on TableView
	            populatePesertaDidik(pesertaDidikData);
	        } catch (SQLException e){
	            System.out.println("Error occurred while getting employees information from DB.\n" + e);
	            throw e;
	        }
    	}
    }

    @FXML
    void tambahPesertaDidik(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	suntingPesertaDidik(event);
    }

}
