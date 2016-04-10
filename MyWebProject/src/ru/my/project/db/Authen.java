package ru.my.project.db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Authen {

	Connection conn=null; // объект для связи с БД
	Statement stmt=null;

//	public Authen() {}
	
	private Statement getStmt() {
		Statement localStmt=null;
		try { //еще один блок try … catch
		// получаем доступ к БД jdbc:h2:file://d:\\test\\my_univer
			conn=DBconnect.getConnection();
			//получаем объект для выполнения команд SQL
			localStmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException ex) { //обрабатываем возможные ошибки
			Logger.getLogger(Authen.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("Trouble with query!!");
		}
		return localStmt;
	}

	public boolean isLogin(String login) {
		stmt=getStmt();
		ResultSet localRS=null; 
		try {
			localRS=stmt.executeQuery("select login from account");
			localRS.beforeFirst();
			while (localRS.next()) {
//				System.out.println(localRS.getString(1));
				if (login.equals(localRS.getString(1))) {
					System.out.println("Yes login!");
					conn.close();
					return true;
				}
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("No login");
		return false;
	}

	public boolean checkPassForLogin(String login, String pass) {
		stmt=getStmt();
		ResultSet localRS=null; 
		try {
			localRS=stmt.executeQuery("select login, pass from account");
			localRS.beforeFirst();
			while (localRS.next()) {
//				System.out.println(localRS.getString(1)+" "+localRS.getString(2));
//				if (login.equals(localRS.getString(1)) && getHash(pass).equals(localRS.getString(2))) {
				String passHash=getHash(pass);
				if (login.equals(localRS.getString(1)) && passHash.equals(localRS.getString(2))) {
					System.out.println("Yes login & pass");
					conn.close();
					return true;
				}
			}
			System.out.println("Pass isn't correct");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String getHash(String str) {
        
        MessageDigest md5 ;        
        StringBuffer  hexString = new StringBuffer();
        try {
            md5 = MessageDigest.getInstance("md5");
            
            md5.reset();
            md5.update(str.getBytes()); 
                        
            byte messageDigest[] = md5.digest();
                        
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
        } 
        catch (NoSuchAlgorithmException e) {                        
            return e.toString();
        }
        return hexString.toString();
    }
}
