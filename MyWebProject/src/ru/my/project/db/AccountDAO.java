package ru.my.project.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public List<Account> list() throws SQLException {
        List<Account> accounts = new ArrayList<Account>();

//        	try {
//				Class.forName("org.h2.Driver");
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
            Connection connection=DBconnect.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT id, login, pass FROM account");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	Account account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setLogin(resultSet.getString("login"));
                account.setPass(resultSet.getString("pass"));
                accounts.add(account);
            }
            connection.close();
            System.out.println("acounts="+accounts.size());
        return accounts;
    }
    public static Long addNewAccount(Account account) throws SQLException {
        Connection connection=DBconnect.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into account (login, pass) values(?,?)");
        statement.setString(1, account.getLogin());
        statement.setString(2, Authen.getHash(account.getPass()));
        int result= statement.executeUpdate();
        System.out.println("DB insert account result="+result);
        connection.close();
        connection=DBconnect.getConnection();
        statement = connection.prepareStatement("SELECT id FROM account where login='"+account.getLogin()+"'");
//        statement.setString(1, account.getLogin());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        account.setId(resultSet.getLong("id"));
        System.out.println("inserted account id="+account.getId());
        connection.close();
        return account.getId();
    }
}