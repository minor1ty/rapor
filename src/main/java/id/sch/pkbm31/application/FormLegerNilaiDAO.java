package id.sch.pkbm31.application;

import java.sql.ResultSet;
import java.sql.SQLException;

import id.sch.pkbm31.data.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FormLegerNilaiDAO {
	//*******************************
    //SELECT Leger Nilai
    //*******************************
    public static ObservableList<FormLegerNilai> searchPesertaDidik () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT \n" +
							"	nisn, nama_lengkap\n" +
							"FROM\n" +
							"	PESERTA_DIDIK;";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<FormLegerNilai> pesertaDidikList = getPesertaDidikList(rs);
            //Return employee object
            return pesertaDidikList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    
    //Select * from Leger Nilai operation
    private static ObservableList<FormLegerNilai> getPesertaDidikList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of User objects
        ObservableList<FormLegerNilai> pesertaDidikList = FXCollections.observableArrayList();
        while (rs.next()) {
        	FormLegerNilai pesertaDidik = new FormLegerNilai();
        	pesertaDidik.setPesertaDidik(rs.getString("nisn") + " - " + rs.getString("nama_lengkap"));
        	pesertaDidikList.add(pesertaDidik);
        }
        //return userList (ObservableList of Users)
        return pesertaDidikList;
    }
    
    //*******************************
    //SELECT Mata Pelajaran
    //*******************************
    public static ObservableList<FormLegerNilai> searchMataPelajaran () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT \n" +
							"	kode_mapel, nama_mapel\n" +
							"FROM\n" +
							"	MATA_PELAJARAN;";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<FormLegerNilai> mataPelajaranList = getMataPelajaranList(rs);
            //Return employee object
            return mataPelajaranList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    
    //Select * from Leger Nilai operation
    private static ObservableList<FormLegerNilai> getMataPelajaranList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of User objects
        ObservableList<FormLegerNilai> mataPelajaranList = FXCollections.observableArrayList();
        while (rs.next()) {
        	FormLegerNilai mataPelajaran = new FormLegerNilai();
        	System.out.println(rs.getString("kode_mapel") + " - " + rs.getString("nama_mapel"));
        	mataPelajaran.setPesertaDidik(rs.getString("kode_mapel") + " - " + rs.getString("nama_mapel"));
        	System.out.println(mataPelajaran);
        	mataPelajaranList.add(mataPelajaran);
        }
        //return userList (ObservableList of Users)
        return mataPelajaranList;
    }
}
