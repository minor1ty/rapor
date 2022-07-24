package id.sch.pkbm31.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import id.sch.pkbm31.Main;
import id.sch.pkbm31.application.AbsensiDAO;
import id.sch.pkbm31.application.AkunPengguna;
import id.sch.pkbm31.application.AkunPenggunaDAO;
import id.sch.pkbm31.application.CatatanWaliKelasDAO;
import id.sch.pkbm31.application.LegerNilaiDAO;
import id.sch.pkbm31.application.LoginInfo;
import id.sch.pkbm31.application.LoginInfoDAO;
import id.sch.pkbm31.application.MataPelajaranDAO;
import id.sch.pkbm31.application.NilaiSikapDAO;
import id.sch.pkbm31.application.PesertaDidikDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class LoginController {

    @FXML
    private Button btnMasuk;

    @FXML
    private CheckBox chkSimpanAkun;

    @FXML
    private Hyperlink linkBuatAkunBaru;

    @FXML
    private Hyperlink linkLupaKataSandi;

    @FXML
    private BorderPane root;

    @FXML
    private PasswordField txtKataSandi;

    @FXML
    private TextField txtNamaPengguna;
    
    @FXML
    private void initialize() throws ClassNotFoundException, SQLException {
    	//Initialize DB Tables
    	AkunPenggunaDAO.createAkunPengguna();
    	LoginInfoDAO.createLoginInfo();
    	PesertaDidikDAO.createPesertaDidik();
    	MataPelajaranDAO.createMataPelajaran();
    	LegerNilaiDAO.createLegerNilai();
    	CatatanWaliKelasDAO.createCatatanWaliKelas();
    	NilaiSikapDAO.createNilaiSikap();
    	AbsensiDAO.createAbsensi();
    	/**
    	LoginInfoDAO.checkLoginInfo();
    	    	
		String namaPengguna = loginInfo.getNamaPengguna();
    	String kataSandi = loginInfo.getKataSandi();
    	
    	System.out.println(namaPengguna + ", " + kataSandi);
    	
    	if (namaPengguna != null) {
    		chkSimpanAkun.setSelected(true);
    		txtNamaPengguna.setText(namaPengguna);
    		txtKataSandi.setText(kataSandi);
    	} else {
    		chkSimpanAkun.setSelected(false);
    	}**/
    }
    
    @FXML
    void dialogBuatAkunBaru(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader();
    	fxmlLoader.setLocation(getClass().getResource("BuatAkunBaru.fxml"));
    	DialogPane buatAkunBaru = fxmlLoader.load();
    	
    	BuatAkunBaruController buatAkunBaruController = fxmlLoader.getController();
    	
    	Dialog<ButtonType> dialog = new Dialog<>();
    	dialog.setDialogPane(buatAkunBaru);
    	dialog.setTitle("Buat Akun Baru");
    	
    	Optional<ButtonType> clickedButton = dialog.showAndWait();
    	
    	if (clickedButton.get() == ButtonType.OK) {
    		AkunPenggunaDAO.addAkunPengguna(buatAkunBaruController.cboJenisPengguna.getSelectionModel().getSelectedItem(), buatAkunBaruController.txtNamaLengkap.getText(), buatAkunBaruController.txtNamaPengguna.getText(), buatAkunBaruController.txtKataSandi.getText(), buatAkunBaruController.cboPertanyaanKeamanan.getSelectionModel().getSelectedItem(), buatAkunBaruController.txtJawabanKeamanan.getText());
    	}
    }

    @FXML
    void dialogLupaKataSandi(ActionEvent event) throws IOException {
    	
    }

    @FXML
    void gotoHome(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
    	try {
            //Check username and password
            int checkResult = AkunPenggunaDAO.checkAkunPengguna(txtNamaPengguna.getText(), txtKataSandi.getText());
            if(checkResult == 1) {
            	
            	Main.setRoot("Home");
            	
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting employee information from DB.\n" + e);
            throw e;
        }
    }

}
