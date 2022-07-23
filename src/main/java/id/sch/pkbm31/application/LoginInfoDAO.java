package id.sch.pkbm31.application;

import java.sql.ResultSet;
import java.sql.SQLException;

import id.sch.pkbm31.data.DBUtil;

public class LoginInfoDAO {
    
    //Buat table jika belum ada
    public static void createLoginInfo() throws SQLException, ClassNotFoundException {
        //Declare a INSERT statement
        String updateStmt =
        					"CREATE TABLE IF NOT EXISTS LOGIN_INFO (\n" +
        					"	table_id            INTEGER      PRIMARY KEY ASC AUTOINCREMENT,\n" +
        					"	nama_pengguna       CHAR (30),\n" +
        					"	kata_sandi          CHAR (30)\n" +
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
    public static LoginInfo checkLoginInfo () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
    	LoginInfo loginInfo = null;
        String selectStmt = "SELECT * FROM LOGIN_INFO ORDER BY table_id DESC LIMIT 1;";
        
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            while (rs.next()) {
            	loginInfo = new LoginInfo(rs.getString("nama_pengguna"), rs.getString("kata_sandi"));
            }
            System.out.println(loginInfo);
            return loginInfo;
            
        } catch (SQLException e) {
            System.out.println("While searching a user, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
    
}
