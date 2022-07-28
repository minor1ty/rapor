package id.sch.pkbm31.application;

import java.sql.ResultSet;
import java.sql.SQLException;

import id.sch.pkbm31.data.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NilaiSikapDAO {
	//Buat table jika belum ada
    public static void createNilaiSikap() throws SQLException, ClassNotFoundException {
        //Declare a CREATE TABLE statement
        String updateStmt =
        					"CREATE TABLE IF NOT EXISTS NILAI_SIKAP (\n" +
        					"	table_id            INTEGER      PRIMARY KEY ASC AUTOINCREMENT,\n" +
        					"	nisn                CHAR (10),\n" +
        					"	predikat            CHAR (2),\n" +
        					"	nilai_spiritual     TEXT,\n" +
        					"	nilai_sosial        TEXT\n" +
        					");";
        
        //Execute CREATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while CREATE Operation: " + e);
            throw e;
        }
    }
    

    //*******************************
    //SELECT Leger Nilai
    //*******************************
    public static ObservableList<NilaiSikap> searchNilaiSikap() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT \n" +
							"	A.nisn, B.nama_lengkap, A.nilai_spiritual, A.nilai_sosial\n" +
							"FROM\n" +
							"	NILAI_SIKAP AS A\n" +
							"	INNER JOIN PESERTA_DIDIK AS B ON A.nisn = B.nisn;";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<NilaiSikap> nilaiSikapList = getNilaiSikapList(rs);
            //Return employee object
            return nilaiSikapList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    
    //Select * from Leger Nilai operation
    private static ObservableList<NilaiSikap> getNilaiSikapList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of User objects
        ObservableList<NilaiSikap> nilaiSikapList = FXCollections.observableArrayList();
        while (rs.next()) {
        	NilaiSikap nilaiSikap = new NilaiSikap();
        	nilaiSikap.setNisn(rs.getString("nisn"));
        	nilaiSikap.setNamaPesertaDidik(rs.getString("nama_lengkap"));
        	nilaiSikap.setNilaiSpiritual(rs.getString("nilai_spiritual"));
        	nilaiSikap.setNilaiSosial(rs.getString("nilai_spiritual"));
            //Add user to the ObservableList
        	nilaiSikapList.add(nilaiSikap);
        }
        //return userList (ObservableList of Users)
        return nilaiSikapList;
    }
    
    //*************************************
    //INSERT a Leger Nilai
    //*************************************
    public static void addNilaiSikap(String nisn, String catatan) throws SQLException, ClassNotFoundException {
        //Declare a INSERT statement
        String updateStmt =
                        "INSERT INTO CATATAN_WALIKELAS\n" +
                        "(nisn, catatan)\n" +
                        "VALUES\n" +
                        "('"+nisn+"', '"+catatan+"');";
        
        //Execute INSERT operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    
    //*************************************
    //UPDATE a Leger Nilai
    //*************************************
    public static void editNilaiSikap(String nisn, String catatan) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =
                        "   UPDATE CATATAN_WALIKELAS\n" +
                        "      SET catatan = '" + catatan + "'\n" +
                        "    WHERE nisn = '" + nisn + "';";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
    
    //*************************************
    //DELETE a Leger Nilai
    //*************************************
    public static void deleteNilaiSikap(String nisn) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                        "   DELETE FROM CATATAN_WALIKELAS\n" +
                        "         WHERE nisn ="+ nisn +";";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

}
