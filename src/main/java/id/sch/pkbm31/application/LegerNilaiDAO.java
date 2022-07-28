package id.sch.pkbm31.application;

import java.sql.ResultSet;
import java.sql.SQLException;

import id.sch.pkbm31.data.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LegerNilaiDAO {
	//Buat table jika belum ada
    public static void createLegerNilai() throws SQLException, ClassNotFoundException {
        //Declare a CREATE TABLE statement
        String updateStmt =
        					"CREATE TABLE IF NOT EXISTS LEGER_NILAI (\n" +
        					"	table_id            INTEGER      PRIMARY KEY ASC AUTOINCREMENT,\n" +
        					"	kode_mapel          CHAR (10),\n" +
        					"	nisn                CHAR (10),\n" +
        					"	ganjil              INTEGER,\n" +
        					"	genap               INTEGER\n" +
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
    public static ObservableList<LegerNilai> searchLegerNilai () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT \n" +
							"	A.table_id, C.nisn, C.nama_lengkap, B.kode_mapel, B.nama_mapel, A.ganjil, A.genap\n" +
							"FROM\n" +
							"	((LEGER_NILAI AS A\n" +
							"	INNER JOIN MATA_PELAJARAN AS B ON A.kode_mapel = B.kode_mapel)\n" +
							"	INNER JOIN PESERTA_DIDIK AS C ON A.nisn = C.nisn);";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<LegerNilai> legerNilaiList = getLegerNilaiList(rs);
            //Return employee object
            return legerNilaiList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    
    //Select * from Leger Nilai operation
    private static ObservableList<LegerNilai> getLegerNilaiList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of User objects
        ObservableList<LegerNilai> legerNilaiList = FXCollections.observableArrayList();
        while (rs.next()) {
        	LegerNilai legerNilai = new LegerNilai();
        	legerNilai.setTableId(rs.getInt("table_id"));
        	legerNilai.setKodeMapel(rs.getString("kode_mapel"));
        	legerNilai.setNisn(rs.getString("nisn"));
        	legerNilai.setGanjil(rs.getInt("ganjil"));
        	legerNilai.setGenap(rs.getInt("genap"));
        	legerNilai.setNamaLengkap(rs.getString("nama_lengkap"));
        	legerNilai.setNamaMapel(rs.getString("nama_mapel"));
            //Add user to the ObservableList
        	legerNilaiList.add(legerNilai);
        }
        //return userList (ObservableList of Users)
        return legerNilaiList;
    }
    
    //*************************************
    //INSERT a Leger Nilai
    //*************************************
    public static void addLegerNilai (String kode_mapel, String nisn, String ganjil, String genap) throws SQLException, ClassNotFoundException {
        //Declare a INSERT statement
        String updateStmt =
                        "INSERT INTO LEGER_NILAI\n" +
                        "(kode_mapel, nisn, ganjil, genap)\n" +
                        "VALUES\n" +
                        "('"+kode_mapel+"', '"+nisn+"', '"+ganjil+"', '"+genap+"');";
        
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
    public static void editLegerNilai (String kode_mapel, String nisn, String ganjil, String genap) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =
                        "   UPDATE LEGER_NILAI\n" +
                        "      SET ganjil = '" + ganjil + "',\n" +
                        "          genap = '" + genap + "'\n" +
                        "    WHERE kode_mapel = '" + kode_mapel + "' AND nisn = '"+ nisn +"';";
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
    public static void deleteLegerNilai (String kode_mapel, String nisn) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                        "   DELETE FROM LEGER_NILAI\n" +
                        "         WHERE kode_mapel = '"+ kode_mapel +"' AND nisn = '" +nisn+ "';";
        
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
