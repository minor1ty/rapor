package id.sch.pkbm31.application;

import java.sql.SQLException;

import id.sch.pkbm31.data.DBUtil;

public class AbsensiDAO {
	//Buat table jika belum ada
    public static void createAbsensi() throws SQLException, ClassNotFoundException {
        //Declare a CREATE TABLE statement
        String updateStmt =
        					"CREATE TABLE IF NOT EXISTS ABSENSI (\n" +
        					"	table_id            INTEGER      PRIMARY KEY ASC AUTOINCREMENT,\n" +
        					"	nisn                CHAR (10),\n" +
        					"	sakit               INTEGER,\n" +
        					"	izin                INTEGER,\n" +
        					"	alpa                INTEGER\n" +
        					");";
        
        //Execute CREATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while CREATE Operation: " + e);
            throw e;
        }
    }
}
