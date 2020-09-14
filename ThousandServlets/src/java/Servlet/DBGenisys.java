package Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author trez__000
 */
public class DBGenisys {
    
    // Withdraw balance from DB with username and balance
    public int withdraw(int withdraw, String username) throws Exception {
        int balance = getBalance(username);

        try {
            if(withdraw > balance) {
                System.out.println("Can't withdraw more than you have in your balance");
            } else {
                balance -= withdraw;
            }
            
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE test set balance = \""+balance+"\" where username = '"+username+"';");
            statement.execute();
        } catch (SQLException x) {
            x.printStackTrace();
        }
        return balance;
    }
    //Deposit balance to DB to this username with "deposit    
    
    public void deposit(int deposit, String username) throws Exception {
        
        try {
            int insert = getBalance(username) + deposit;
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE test set balance = \""+insert+"\" where username = '"+username+"';");
            statement.execute();
        } catch (SQLException x) {
            x.printStackTrace();
            System.out.println("Couln't deposit");            
        }
    }
    
    public int getBalance(String username) throws Exception {
        int balance = 0;
        String sql = "SELECT balance from test where username = '"+username+"';";
        try {
            Connection conn = getConnection();
            
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                balance = result.getInt("balance");
            }
        } catch (SQLException x) {
            System.out.println("Couldn't get balance: " + sql);
            x.printStackTrace();
        }
        return balance;
    }
    
    //Inserting player details in DB
    public void post(String username, String password) throws Exception {
        
        try {
            int balance = 0;
            Connection con = getConnection();
            createTable();
            //PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS TEST (id int NOT NULL AUTO_INCREMENT, first varchar(255), last varchar(255), PRIMARY KEY(id));");
            //create.executeUpdate();
            
            PreparedStatement posted = con.prepareStatement("INSERT INTO test (username, password, balance) VALUES ('"+username+"', '"+password+"', "+balance+");");
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println("Something wrong with post()");
        } finally {
            System.out.println("Insert Complete");
        }
    }
    
    
    
    
    public void createTable() throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS test (username varchar(255), password varchar(255), balance int(11), PRIMARY KEY(username));");
            create.executeUpdate();
            
        }catch (SQLException e) {
            System.out.println("Table not created!");
            e.printStackTrace();
            
        } finally {
            System.out.println("Function Complete");
        }
    }

    
    
    
    public Connection getConnection() throws Exception {
        try {
            
            String driver = "com.mysql.jdbc.Driver";
            /*
            String url = "jdbc:mysql://localhost:3306/blackjack";
            String username = "root";
            String password = "calarashi
*/
            Class.forName(driver);
            
            Connection con = DriverManager.getConnection("jdbc:mysql://139.59.139.10/?user=petro&password=petro");
            Statement s = con.createStatement();
            System.out.println("Checking for DB");
            s.executeUpdate("CREATE DATABASE IF NOT EXISTS blackjack");
            System.out.println("DB IS HERE!!!!");
            //Connection conn = DriverManager.getConnection(url, username, password);
            Connection conn = DriverManager.getConnection("jdbc:mysql://139.59.139.10/blackjack?user=petro&password=petro");
            System.out.println("Connected");
            return conn;
        } catch (Exception e) {
            System.out.println("No connection: " + e);
        } finally {
            
        }
        return null;
    }
    
    
    
     
    
    
    
    
}
