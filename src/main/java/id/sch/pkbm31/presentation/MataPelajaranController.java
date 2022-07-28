package id.sch.pkbm31.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import id.sch.pkbm31.Main;
import id.sch.pkbm31.application.MataPelajaran;
import id.sch.pkbm31.application.MataPelajaranDAO;
import id.sch.pkbm31.data.DBUtil;
import id.sch.pkbm31.presentation.PesertaDidikController.DialogMode;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class MataPelajaranController {

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
    private TableColumn<MataPelajaran, String> namaMapelColumn;

    @FXML
    private TableColumn<MataPelajaran, String> pengetahuanKkmColumn;

    @FXML
    private TableColumn<MataPelajaran, String> pengetahuanTidakKkmColumn;

    @FXML
    private TableColumn<MataPelajaran, String> keterampilanKkmColumn;
    
    @FXML
    private TableColumn<MataPelajaran, String> keterampilanTidakKkmColumn;

    @FXML
    private TableColumn<MataPelajaran, String> kodeMapelColumn;

    @FXML
    private TableView tblMataPelajaran;
    
    enum DialogMode {
    	TAMBAH,
    	SUNTING,
    	HAPUS
    }
    
    @FXML
    private void populateMataPelajaran (ObservableList<MataPelajaran> mataPelajaranData) throws ClassNotFoundException {
        //Set items to the userTable
    	tblMataPelajaran.setItems(mataPelajaranData);
    }
    
    @FXML
    private void initialize() throws ClassNotFoundException, SQLException {
    	kodeMapelColumn.setCellValueFactory(cellData -> cellData.getValue().kodeMapelProperty());
    	namaMapelColumn.setCellValueFactory(cellData -> cellData.getValue().namaMapelProperty());
    	pengetahuanKkmColumn.setCellValueFactory(cellData -> cellData.getValue().pengetahuanKkmProperty());
    	pengetahuanTidakKkmColumn.setCellValueFactory(cellData -> cellData.getValue().pengetahuanTidakKkmProperty());
    	keterampilanKkmColumn.setCellValueFactory(cellData -> cellData.getValue().keterampilanKkmProperty());
    	keterampilanTidakKkmColumn.setCellValueFactory(cellData -> cellData.getValue().keterampilanTidakKkmProperty());
    	
    	try {
            //Get all Users information
            ObservableList<MataPelajaran> mataPelajaranData = MataPelajaranDAO.searchMataPelajaran();
            //Populate Employees on TableView
            populateMataPelajaran(mataPelajaranData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    void cetakMataPelajaran(ActionEvent event) throws ClassNotFoundException, JRException, SQLException {
    	JasperPrint jp;
    	Map param = new HashMap();
    	
    	String reportPath = "C:\\Users\\Development\\Documents\\EclipseProjects\\rapor\\src\\main\\resources\\id\\sch\\pkbm31\\report\\MataPelajaranReport.jasper";
    	
    	
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
    void hapusMataPelajaran(ActionEvent event) throws ClassNotFoundException, SQLException {
    	MataPelajaran mataPelajaran= (MataPelajaran) tblMataPelajaran.getSelectionModel().getSelectedItem();
    	
    	Optional<ButtonType> isConfirmed = showConfirmationDialog("Hapus Mata Pelajaran", "Konfirmasi Hapus", "Apakah anda yakin ingin menghapus mata pelajaran ini?");
    	
    	if (isConfirmed.get() == ButtonType.OK) {
    		String kode_mapel = mataPelajaran.getKodeMapel();
    		MataPelajaranDAO.deleteMataPelajaran(kode_mapel);
    		MataPelajaranDAO.resetTableId();
    	}
    	
    	try {
            //Get all Users information
            ObservableList<MataPelajaran> mataPelajaranData = MataPelajaranDAO.searchMataPelajaran();
            //Populate Employees on TableView
            populateMataPelajaran(mataPelajaranData);
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
    void suntingMataPelajaran(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	MataPelajaran mataPelajaran = null;
    	String DialogTitle = "";
    	DialogMode mode;
    	
    	if (event.getSource().equals(btnSunting)) {
    		mode = DialogMode.SUNTING;
    		DialogTitle = "Sunting Mata Pelajaran";
    		mataPelajaran = (MataPelajaran) tblMataPelajaran.getSelectionModel().getSelectedItem();	
    		
    	} else if (event.getSource().equals(btnTambah)) {
    		mode = DialogMode.TAMBAH;
    		DialogTitle = "Tambah Mata Pelajaran";
    		mataPelajaran = new MataPelajaran();		
    	} else {
    		return;
    	}
    	
    	FXMLLoader fxmlLoader = new FXMLLoader();
    	fxmlLoader.setLocation(getClass().getResource("FormMataPelajaran.fxml"));
    	DialogPane mataPelajaranDialogPane = fxmlLoader.load();
    	
    	FormMataPelajaranController formMataPelajaranController = fxmlLoader.getController();
    	formMataPelajaranController.setMataPelajaran(mataPelajaran);
    	
    	if (event.getSource().equals(btnSunting)) {
    		formMataPelajaranController.txtKodeMapel.setDisable(true);
    	} else {
    		formMataPelajaranController.txtKodeMapel.setDisable(false);
    	}

    	Dialog<ButtonType> dialog = new Dialog<>();
    	dialog.setDialogPane(mataPelajaranDialogPane);
    	dialog.setTitle(DialogTitle);
    	
    	Optional<ButtonType> clickedButton = dialog.showAndWait();
    	
    	if (clickedButton.get() == ButtonType.OK) {
    		if (mode == DialogMode.TAMBAH) {
    			MataPelajaranDAO.addMataPelajaran(formMataPelajaranController.txtKodeMapel.getText(), formMataPelajaranController.txtNamaMapel.getText(), formMataPelajaranController.txtPengetahuanKkm.getText(), formMataPelajaranController.txtPengetahuanTdkKkm.getText(), formMataPelajaranController.txtKeterampilanKkm.getText(), formMataPelajaranController.txtKeterampilanTdkKkm.getText());
    			//int tableId = MataPelajaranDAO.getTableId(formMataPelajaranController.txtNamaMapel.getText());
    			//MataPelajaranDAO.createSubMataPelajaran(tableId);

    		} else if (mode == DialogMode.SUNTING) {
    			MataPelajaranDAO.editMataPelajaran(formMataPelajaranController.txtKodeMapel.getText(), formMataPelajaranController.txtNamaMapel.getText(), formMataPelajaranController.txtPengetahuanKkm.getText(), formMataPelajaranController.txtPengetahuanTdkKkm.getText(), formMataPelajaranController.txtKeterampilanKkm.getText(), formMataPelajaranController.txtKeterampilanTdkKkm.getText());
    		}
    		

			try {
	            //Get all Users information
	            ObservableList<MataPelajaran> mataPelajaranData = MataPelajaranDAO.searchMataPelajaran();
	            //Populate Employees on TableView
	            populateMataPelajaran(mataPelajaranData);
	        } catch (SQLException e){
	            System.out.println("Error occurred while getting Mata Pelajaran information from DB.\n" + e);
	            throw e;
	        }
    	}
    }

    @FXML
    void tambahMataPelajaran(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	suntingMataPelajaran(event);
    }

}
