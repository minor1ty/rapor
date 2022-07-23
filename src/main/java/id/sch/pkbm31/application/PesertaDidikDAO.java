package id.sch.pkbm31.application;

import java.sql.ResultSet;
import java.sql.SQLException;

import id.sch.pkbm31.data.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PesertaDidikDAO {
	
	//Buat table jika belum ada
    public static void createPesertaDidik() throws SQLException, ClassNotFoundException {
        //Declare a CREATE TABLE statement
        String updateStmt =
        					"CREATE TABLE IF NOT EXISTS PESERTA_DIDIK (\n" +
        					"	table_id            INTEGER      PRIMARY KEY ASC AUTOINCREMENT,\n" +
        					"	nisn                CHAR (10),\n" +
        					"	nama_lengkap        VARCHAR (60),\n" +
        					"	jenis_kelamin       CHAR (9),\n" +
        					"	agama               VARCHAR (30)\n" +
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
    //SELECT Peserta Didik
    //*******************************
    public static ObservableList<PesertaDidik> searchPesertaDidik () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM PESERTA_DIDIK";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<PesertaDidik> pesertaDidikList = getPesertaDidikList(rs);
            //Return employee object
            return pesertaDidikList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    
    //Select * from employees operation
    private static ObservableList<PesertaDidik> getPesertaDidikList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of User objects
        ObservableList<PesertaDidik> pesertaDidikList = FXCollections.observableArrayList();
        while (rs.next()) {
        	PesertaDidik pesertaDidik = new PesertaDidik();
        	pesertaDidik.setTableId(rs.getInt("table_id"));
        	pesertaDidik.setNisn(rs.getString("nisn"));
        	pesertaDidik.setNamaLengkap(rs.getString("nama_lengkap"));
        	pesertaDidik.setJenisKelamin(rs.getString("jenis_kelamin"));
        	pesertaDidik.setAgama(rs.getString("agama"));
            //Add user to the ObservableList
        	pesertaDidikList.add(pesertaDidik);
        }
        //return userList (ObservableList of Users)
        return pesertaDidikList;
    }
    
    //*************************************
    //INSERT a peserta didik
    //*************************************
    public static void addPesertaDidik (String nisn, String nama_lengkap, String jenis_kelamin, String agama) throws SQLException, ClassNotFoundException {
        //Declare a INSERT statement
        String updateStmt =
                        "INSERT INTO PESERTA_DIDIK\n" +
                        "(nisn, nama_lengkap, jenis_kelamin, agama)\n" +
                        "VALUES\n" +
                        "('"+nisn+"', '"+nama_lengkap+"', '"+jenis_kelamin+"', '"+agama+"');";
        
        
        //Execute INSERT operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    
    //*************************************
    //UPDATE a Peserta Didik
    //*************************************
    public static void editPesertaDidik (String table_id, String nisn, String nama_lengkap, String jenis_kelamin, String agama) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =
                        "   UPDATE PESERTA_DIDIK\n" +
                        "      SET nisn = '" + nisn + "',\n" +
                        "          nama_lengkap = '" + nama_lengkap + "',\n" +
                        "          jenis_kelamin = '" + jenis_kelamin + "',\n" +
                        "          agama = '" + agama + "'\n" +
                        "    WHERE table_id = " + table_id + ";";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
    
    //*************************************
    //DELETE a Peserta Didik
    //*************************************
    public static void deletePesertaDidik (int table_id) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                        "   DELETE FROM PESERTA_DIDIK\n" +
                        "         WHERE table_id ="+ table_id +";";
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
                        "         SET seq = (SELECT MAX(table_id) FROM PESERTA_DIDIK)\n" +
        				"         WHERE name = 'PESERTA_DIDIK';";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
