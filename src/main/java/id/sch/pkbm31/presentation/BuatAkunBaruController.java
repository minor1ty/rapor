package id.sch.pkbm31.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class BuatAkunBaruController {

    @FXML
    public ComboBox<String> cboJenisPengguna;

    @FXML
    public ComboBox<String> cboPertanyaanKeamanan;

    @FXML
    public DialogPane dialog;

    @FXML
    public TextField txtJawabanKeamanan;

    @FXML
    public PasswordField txtKataSandi;

    @FXML
    public PasswordField txtKonfirmasiKataSandi;

    @FXML
    public TextField txtNamaLengkap;

    @FXML
    public TextField txtNamaPengguna;
    
    @FXML
    private void initialize() {
    	cboJenisPengguna.getItems().addAll("Kepala Sekolah", "Wali Kelas");
    	
    	cboPertanyaanKeamanan.getItems().add("Apa nama hewan peliharaan pertama anda?");
    	cboPertanyaanKeamanan.getItems().add("Apa nama kota tempat anda lahir?");
    	cboPertanyaanKeamanan.getItems().add("Apa nama panggilan anda saat masih anak-anak?");
    	cboPertanyaanKeamanan.getItems().add("Apa nama kota tempat orang tua anda bertemu?");
    	cboPertanyaanKeamanan.getItems().add("Apa nama sekolah dasar anda?");
    }

}
