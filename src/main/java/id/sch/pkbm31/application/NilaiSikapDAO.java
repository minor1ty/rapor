package id.sch.pkbm31.application;

import java.sql.SQLException;

import id.sch.pkbm31.data.DBUtil;

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
}
