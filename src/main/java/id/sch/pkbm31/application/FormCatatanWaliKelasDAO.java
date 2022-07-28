package id.sch.pkbm31.application;

import java.sql.ResultSet;
import java.sql.SQLException;

import id.sch.pkbm31.data.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FormCatatanWaliKelasDAO {
	//*******************************
    //SELECT Leger Nilai
    //*******************************
    public static ObservableList<FormCatatanWaliKelas> searchPesertaDidik () throws SQLException, ClassNotFoundException {
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
            ObservableList<FormCatatanWaliKelas> pesertaDidikList = getPesertaDidikList(rs);
            //Return employee object
            return pesertaDidikList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    
    //Select * from Leger Nilai operation
    private static ObservableList<FormCatatanWaliKelas> getPesertaDidikList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of User objects
        ObservableList<FormCatatanWaliKelas> pesertaDidikList = FXCollections.observableArrayList();
        while (rs.next()) {
        	FormCatatanWaliKelas pesertaDidik = new FormCatatanWaliKelas();
        	pesertaDidik.setPesertaDidik(rs.getString("nisn") + " - " + rs.getString("nama_lengkap"));
        	pesertaDidikList.add(pesertaDidik);
        }
        //return userList (ObservableList of Users)
        return pesertaDidikList;
    }
}
