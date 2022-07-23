package id.sch.pkbm31.application;

import java.sql.ResultSet;
import java.sql.SQLException;

import id.sch.pkbm31.data.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MataPelajaranDAO {
	
	//Buat table jika belum ada
    public static void createMataPelajaran() throws SQLException, ClassNotFoundException {
        //Declare a CREATE TABLE statement
        String updateStmt =
        					"CREATE TABLE IF NOT EXISTS MATA_PELAJARAN (\n" +
        					"	table_id               INTEGER      PRIMARY KEY ASC AUTOINCREMENT,\n" +
        					"	kode_mapel             CHAR (10),\n" +
        					"	nama_mapel             VARCHAR (60),\n" +
        					"	pengetahuan_kkm        TEXT,\n" +
        					"	pengetahuan_tidak_kkm  TEXT,\n" +
        					"	keterampilan_kkm       TEXT,\n" +
        					"	keterampilan_tidak_kkm TEXT\n" +
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
    //SELECT Mata Pelajaran
    //*******************************
    public static ObservableList<MataPelajaran> searchMataPelajaran () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM MATA_PELAJARAN";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<MataPelajaran> mataPelajaranList = getMataPelajaranList(rs);
            //Return employee object
            return mataPelajaranList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    
    //Select * from employees operation
    private static ObservableList<MataPelajaran> getMataPelajaranList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of User objects
        ObservableList<MataPelajaran> mataPelajaranList = FXCollections.observableArrayList();
        while (rs.next()) {
        	MataPelajaran mataPelajaran = new MataPelajaran();
        	mataPelajaran.setTableId(rs.getInt("table_id"));
        	mataPelajaran.setKodeMapel(rs.getString("kode_mapel"));
        	mataPelajaran.setNamaMapel(rs.getString("nama_mapel"));
        	mataPelajaran.setPengetahuanKkm(rs.getString("pengetahuan_kkm"));
        	mataPelajaran.setPengetahuanTidakKkm(rs.getString("pengetahuan_tidak_kkm"));
        	mataPelajaran.setKeterampilanKkm(rs.getString("keterampilan_kkm"));
        	mataPelajaran.setKeterampilanTidakKkm(rs.getString("keterampilan_tidak_kkm"));
            //Add user to the ObservableList
        	mataPelajaranList.add(mataPelajaran);
        }
        //return userList (ObservableList of Users)
        return mataPelajaranList;
    }
    
    //*************************************
    //INSERT a peserta didik
    //*************************************
    public static void addMataPelajaran (String kode_mapel, String nama_mapel, String pengetahuan_kkm, String pengetahuan_tidak_kkm, String keterampilan_kkm, String keterampilan_tidak_kkm) throws SQLException, ClassNotFoundException {
        //Declare a INSERT statement
        String updateStmt =
                        "INSERT INTO MATA_PELAJARAN\n" +
                        "(kode_mapel, nama_mapel, pengetahuan_kkm, pengetahuan_tidak_kkm, keterampilan_kkm, keterampilan_tidak_kkm)\n" +
                        "VALUES\n" +
                        "('"+kode_mapel+"','"+nama_mapel+"', '"+pengetahuan_kkm+"', '"+pengetahuan_tidak_kkm+"', '"+keterampilan_kkm+"', '"+keterampilan_tidak_kkm+"');";
        
        
        //Execute INSERT operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    
    /**Buat table jika belum ada
    public static void createSubMataPelajaran(int table_id) throws SQLException, ClassNotFoundException {
        //Declare a CREATE TABLE statement
        String updateStmt =
        					"CREATE TABLE IF NOT EXISTS SUB_MATA_PELAJARAN_"+ table_id +" (\n" +
        					"	table_id   INTEGER      PRIMARY KEY ASC AUTOINCREMENT,\n" +
        					"	nisn       CHAR (10),\n" +
        					"	ganjil     INTEGER,\n" +
        					"	genap      INTEGER\n" +
        					");";
        System.out.println(updateStmt);
        
        //Execute CREATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while CREATE Operation: " + e);
            throw e;
        }
    }**/
    
    //*************************************
    //UPDATE a Mata Pelajaran
    //*************************************
    public static void editMataPelajaran (String kode_mapel, String nama_mapel, String pengetahuan_kkm, String pengetahuan_tidak_kkm, String keterampilan_kkm, String keterampilan_tidak_kkm) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =
                        "   UPDATE MATA_PELAJARAN\n" +
                        "      SET nama_mapel = '" + nama_mapel + "',\n" +
                        "          pengetahuan_kkm = '" + pengetahuan_kkm + "',\n" +
                        "          pengetahuan_tidak_kkm = '" + pengetahuan_tidak_kkm + "',\n" +
                        "          keterampilan_kkm = '" + keterampilan_kkm + "',\n" +
                        "          keterampilan_tidak_kkm = '" + keterampilan_tidak_kkm + "'\n" +
                        "    WHERE kode_mapel = " + kode_mapel + ";";
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
    public static void deleteMataPelajaran (String kode_mapel) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                        "   DELETE FROM MATA_PELAJARAN\n" +
                        "         WHERE kode_mapel ='"+ kode_mapel +"';";
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
                        "         SET seq = (SELECT MAX(table_id) FROM MATA_PELAJARAN)\n" +
        				"         WHERE name = 'MATA_PELAJARAN';";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
    
  //*******************************
    //SELECT Mata Pelajaran
    //*******************************
    public static int getTableId(String nama_mapel) throws SQLException, ClassNotFoundException {
        int tableId = 0;
    	//Declare a SELECT statement
        String selectStmt = "SELECT * FROM MATA_PELAJARAN WHERE nama_mapel = '" + nama_mapel + "';";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            
            if (rs.next()) {
            	tableId = rs.getInt("table_id");
            }
            
            //Return employee object
            return tableId;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
}
