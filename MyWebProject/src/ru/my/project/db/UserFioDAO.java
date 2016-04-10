package ru.my.project.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFioDAO {

    public List<UserFIO> list() throws SQLException {
        List<UserFIO> studentsFIOs = new ArrayList<UserFIO>();

        	try {
				Class.forName("org.h2.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
//            Connection connection = DriverManager.getConnection("jdbc:h2:file://d:/javaEEprojects2/GIS_site/gisPortal","sa","");
            Connection connection=DBconnect.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT id, name, lastName, loginId, groupId, email FROM userFIO");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	UserFIO userFIO = new UserFIO();
            	userFIO.setId(resultSet.getLong("id"));
            	userFIO.setName(resultSet.getString("name"));
            	userFIO.setLastName(resultSet.getString("lastName"));
            	userFIO.setLoginId(resultSet.getLong("loginId"));
            	userFIO.setGroupId(resultSet.getLong("groupId"));
            	userFIO.setEmail(resultSet.getString("email"));
            	studentsFIOs.add(userFIO);
            }
            connection.close();
        return studentsFIOs;
    }
	
	public static long addNewUserFio(String fio, long accountID, long groupID, String email) throws SQLException {
        Connection connection=DBconnect.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into userFIO "
        	+ "(name, lastname, loginid, groupid, email) values(?,?,?,?,?)");
        String lastName = fio.substring(0, fio.indexOf(" ")); 
        System.out.println("lastName="+lastName);
        String name= fio.substring(fio.indexOf(" "),fio.length()); 
        System.out.println("Name="+name);
        statement.setString(1, name);
        statement.setString(2, lastName);
        statement.setLong(3, accountID);
        statement.setLong(4, groupID);
        statement.setString(5, email);
        int result= statement.executeUpdate();
        System.out.println("DB insert userFIO result="+result);
        connection.close();
        connection=DBconnect.getConnection();
        statement = connection.prepareStatement("SELECT id FROM userFIO where loginid='"+accountID+"'");
//        statement.setString(1, account.getLogin());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        System.out.println("from DAO inserted userFIO id="+resultSet.getLong("id"));
        long id=resultSet.getLong("id");
        connection.close();
        return id;
	}

}
