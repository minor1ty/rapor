package id.sch.pkbm31.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import id.sch.pkbm31.Main;
import id.sch.pkbm31.application.FormLegerNilai;
import id.sch.pkbm31.application.LegerNilai;
import id.sch.pkbm31.application.LegerNilaiDAO;
import id.sch.pkbm31.application.MataPelajaran;
import id.sch.pkbm31.application.MataPelajaranDAO;
import id.sch.pkbm31.data.DBUtil;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class LegerNilaiController {

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
    private TableColumn<LegerNilai, String> nisnColumn;

    @FXML
    private TableColumn<LegerNilai, String> namaPesertaDidikColumn;

    @FXML
    private TableColumn<LegerNilai, String> kodeMapelColumn;

    @FXML
    private TableColumn<LegerNilai, String> namaMapelColumn;

    @FXML
    private TableColumn<LegerNilai, Integer> nilaiGanjilColumn;

    @FXML
    private TableColumn<LegerNilai, Integer> nilaiGenapColumn;

    @FXML
    private TableView tblLegerNilai;
    
    enum DialogMode {
    	TAMBAH,
    	SUNTING,
    	HAPUS
    }

    @FXML
    private void populateLegerNilai (ObservableList<LegerNilai> legerNilaiData) throws ClassNotFoundException {
        //Set items to the userTable
    	tblLegerNilai.setItems(legerNilaiData);
    }
    
    @FXML
    private void initialize() throws ClassNotFoundException, SQLException {
    	nisnColumn.setCellValueFactory(cellData -> cellData.getValue().nisnProperty());
    	namaPesertaDidikColumn.setCellValueFactory(cellData -> cellData.getValue().namaLengkapProperty());
    	kodeMapelColumn.setCellValueFactory(cellData -> cellData.getValue().kodeMapelProperty());
    	namaMapelColumn.setCellValueFactory(cellData -> cellData.getValue().namaMapelProperty());
    	nilaiGanjilColumn.setCellValueFactory(cellData -> cellData.getValue().ganjilProperty().asObject());
    	nilaiGenapColumn.setCellValueFactory(cellData -> cellData.getValue().genapProperty().asObject());
    	
    	try {
            //Get all Users information
            ObservableList<LegerNilai> legerNilaiData = LegerNilaiDAO.searchLegerNilai();
            //Populate Employees on TableView
            populateLegerNilai(legerNilaiData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    @FXML
    void cetak(ActionEvent event) throws ClassNotFoundException, JRException, SQLException {
    	JasperPrint jp;
    	Map param = new HashMap();
    	
    	String reportPath = "C:\\Users\\Development\\Documents\\EclipseProjects\\rapor\\src\\main\\resources\\id\\sch\\pkbm31\\report\\PerformaPesertaDidikData.jasper";
    	
    	
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
    void hapus(ActionEvent event) throws ClassNotFoundException, SQLException {
    	LegerNilai legerNilai= (LegerNilai) tblLegerNilai.getSelectionModel().getSelectedItem();
    	
    	Optional<ButtonType> isConfirmed = showConfirmationDialog("Hapus Leger Nilai", "Konfirmasi Hapus", "Apakah anda yakin ingin menghapus leger nilai ini?");
    	
    	if (isConfirmed.get() == ButtonType.OK) {
    		String kode_mapel = legerNilai.getKodeMapel();
    		String nisn = legerNilai.getNisn();
    		LegerNilaiDAO.deleteLegerNilai(kode_mapel, nisn);
    	}
    	
    	try {
            //Get all Users information
            ObservableList<LegerNilai> legerNilaiData = LegerNilaiDAO.searchLegerNilai();
            //Populate Employees on TableView
            populateLegerNilai(legerNilaiData);
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
    void sunting(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	LegerNilai legerNilai = null;
    	String DialogTitle = "";
    	DialogMode mode;
    	
    	if (event.getSource().equals(btnSunting)) {
    		mode = DialogMode.SUNTING;
    		DialogTitle = "Sunting Leger Nilai";
    		legerNilai = (LegerNilai) tblLegerNilai.getSelectionModel().getSelectedItem();	
    		
    	} else if (event.getSource().equals(btnTambah)) {
    		mode = DialogMode.TAMBAH;
    		DialogTitle = "Tambah Leger Nilai";
    		legerNilai = new LegerNilai();		
    	} else {
    		return;
    	}
    	
    	FXMLLoader fxmlLoader = new FXMLLoader();
    	fxmlLoader.setLocation(getClass().getResource("FormLegerNilai.fxml"));
    	DialogPane formLegerNilaiDialogPane = fxmlLoader.load();
    	
    	FormLegerNilaiController formLegerNilaiController = fxmlLoader.getController();
    	formLegerNilaiController.setLegerNilai(legerNilai);
    	
    	if (event.getSource().equals(btnSunting)) {
    		formLegerNilaiController.cboNamaPesertaDidik.setDisable(true);
    		formLegerNilaiController.cboMataPelajaran.setDisable(true);
    	} else {
    		formLegerNilaiController.cboNamaPesertaDidik.setDisable(false);
    		formLegerNilaiController.cboMataPelajaran.setDisable(false);
    	}

    	Dialog<ButtonType> dialog = new Dialog<>();
    	dialog.setDialogPane(formLegerNilaiDialogPane);
    	dialog.setTitle(DialogTitle);
    	
    	Optional<ButtonType> clickedButton = dialog.showAndWait();
    	
    	if (clickedButton.get() == ButtonType.OK) {
    		String pesertaDidikData = formLegerNilaiController.cboNamaPesertaDidik.getSelectionModel().getSelectedItem().toString();
			String[] pesertaDidikSplit = pesertaDidikData.split(" - ");
			String nisnPesertaDidik = pesertaDidikSplit[0];
			String namaPesertaDidik = pesertaDidikSplit[1];
			
			String mataPelajaranData = formLegerNilaiController.cboMataPelajaran.getSelectionModel().getSelectedItem().toString();
			String[] mataPelajaranSplit = mataPelajaranData.split(" - ");
			String kodeMapel = mataPelajaranSplit[0];
			String namaMapel = mataPelajaranSplit[1];
			
			String ganjil = formLegerNilaiController.txtGanjil.getText();
			String genap = formLegerNilaiController.txtGenap.getText();
			
    		if (mode == DialogMode.TAMBAH) {
    			LegerNilaiDAO.addLegerNilai(kodeMapel, nisnPesertaDidik, ganjil, genap);

    		} else if (mode == DialogMode.SUNTING) {
    			LegerNilaiDAO.editLegerNilai(kodeMapel, nisnPesertaDidik, ganjil, genap);
    		}
    		

    		try {
                //Get all Users information
                ObservableList<LegerNilai> legerNilaiData = LegerNilaiDAO.searchLegerNilai();
                //Populate Employees on TableView
                populateLegerNilai(legerNilaiData);
            } catch (SQLException e){
                System.out.println("Error occurred while getting employees information from DB.\n" + e);
                throw e;
            }
    	}
    }

    @FXML
    void tambah(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	sunting(event);
    }

}
