package id.sch.pkbm31.application;

import java.sql.ResultSet;
import java.sql.SQLException;

import id.sch.pkbm31.data.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CatatanWaliKelasDAO {
	//Buat table jika belum ada
    public static void createCatatanWaliKelas() throws SQLException, ClassNotFoundException {
        //Declare a CREATE TABLE statement
        String updateStmt =
        					"CREATE TABLE IF NOT EXISTS CATATAN_WALIKELAS (\n" +
        					"	table_id            INTEGER      PRIMARY KEY ASC AUTOINCREMENT,\n" +
        					"	nisn                CHAR (10),\n" +
        					"	catatan             TEXT\n" +
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
    public static ObservableList<CatatanWaliKelas> searchCatatanWaliKelas () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT \n" +
							"	A.nisn, B.nama_lengkap, A.catatan\n" +
							"FROM\n" +
							"	CATATAN_WALIKELAS AS A\n" +
							"	INNER JOIN PESERTA_DIDIK AS B ON A.nisn = B.nisn;";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<CatatanWaliKelas> catatanWaliKelasList = getCatatanWaliKelasList(rs);
            //Return employee object
            return catatanWaliKelasList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    
    //Select * from Leger Nilai operation
    private static ObservableList<CatatanWaliKelas> getCatatanWaliKelasList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of User objects
        ObservableList<CatatanWaliKelas> catatanWaliKelasList = FXCollections.observableArrayList();
        while (rs.next()) {
        	CatatanWaliKelas catatanWaliKelas = new CatatanWaliKelas();
        	catatanWaliKelas.setNisn(rs.getString("nisn"));
        	catatanWaliKelas.setNamaPesertaDidik(rs.getString("nama_lengkap"));
        	catatanWaliKelas.setCatatan(rs.getString("catatan"));
            //Add user to the ObservableList
        	catatanWaliKelasList.add(catatanWaliKelas);
        }
        //return userList (ObservableList of Users)
        return catatanWaliKelasList;
    }
    
    //*************************************
    //INSERT a Leger Nilai
    //*************************************
    public static void addCatatanWaliKelas (String nisn, String catatan) throws SQLException, ClassNotFoundException {
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
    public static void editCatatanWaliKelas (String nisn, String catatan) throws SQLException, ClassNotFoundException {
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
    public static void deleteCatatanWaliKelas (String nisn) throws SQLException, ClassNotFoundException {
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
    
    //*************************************
    //RESET table_id column so it continue the number
    //*************************************
    public static void resetTableId () throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                        "   UPDATE sqlite_sequence\n" +
                        "         SET seq = (SELECT MAX(table_id) FROM LEGER_NILAI)\n" +
        				"         WHERE name = 'LEGER_NILAI';";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
