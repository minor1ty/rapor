package id.sch.pkbm31.application;

import java.sql.ResultSet;
import java.sql.SQLException;

import id.sch.pkbm31.data.DBUtil;

public class AkunPenggunaDAO {

	//Buat table jika belum ada
    public static void createAkunPengguna() throws SQLException, ClassNotFoundException {
        //Declare a INSERT statement
        String updateStmt =
        					"CREATE TABLE IF NOT EXISTS AKUN_PENGGUNA (\n" +
        					"	table_id            INTEGER      PRIMARY KEY ASC AUTOINCREMENT,\n" +
        					"	jenis_pengguna      VARCHAR (30),\n" +
        					"	nama_lengkap        VARCHAR (60),\n" +
        					"	nama_pengguna       CHAR (30),\n" +
        					"	kata_sandi          CHAR (30),\n" +
        					"	pertanyaan_keamanan TEXT,\n" +
        					"	jawaban_keamanan    TEXT\n" +
        					");";
        
        //Execute CREATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while CREATE Operation: " + e);
            throw e;
        }
    }
    
    //Cek akun untuk login
    public static int checkAkunPengguna (String nama_pengguna, String kata_sandi) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
    	int checkResult = 0;
        String selectStmt = "SELECT * FROM AKUN_PENGGUNA WHERE nama_pengguna='"+nama_pengguna+"'AND kata_sandi='"+kata_sandi+"';";
        
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            if (rs.next()) {
            	//Return 1 if login is correct
            	checkResult = 1;
            }
            
            return checkResult;
            
        } catch (SQLException e) {
            System.out.println("While searching a user with " + nama_pengguna + ", an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
    
    //Tambah pengguna
    public static void addAkunPengguna (String jenis_pengguna, String nama_lengkap, String nama_pengguna, String kata_sandi, String pertanyaan_keamanan, String jawaban_keamanan) throws SQLException, ClassNotFoundException {
        //Declare a INSERT statement
        String updateStmt =
                        "INSERT INTO AKUN_PENGGUNA\n" +
                        "(jenis_pengguna, nama_lengkap, nama_pengguna, kata_sandi, pertanyaan_keamanan, jawaban_keamanan)\n" +
                        "VALUES\n" +
                        "('"+jenis_pengguna+"', '"+nama_lengkap+"', '"+nama_pengguna+"', '"+kata_sandi+"', '"+pertanyaan_keamanan+"', '"+jawaban_keamanan+"');";
        
        //Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
}
