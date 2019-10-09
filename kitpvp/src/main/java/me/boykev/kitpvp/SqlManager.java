package me.boykev.kitpvp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.entity.Player;

public class SqlManager {
	private Main instance;
	public Connection con;
	private ConfigManager db;
	private String host, database, username, password;
	private int port;
	
    public SqlManager(Main main) {
		this.instance = main;
	}
    
    
    public void openConnection() throws SQLException, ClassNotFoundException {
    	db = new ConfigManager(instance);
    	host = db.getConfig().getString("sql.host");
    	database = db.getConfig().getString("sql.database");
    	port = db.getConfig().getInt("sql.port");
    	username = db.getConfig().getString("sql.user");
    	password = db.getConfig().getString("sql.password");
    if (con != null && !con.isClosed()) {
        return;
    }
 
    synchronized (this) {
        if (con != null && !con.isClosed()) {
            return;
        }
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?useSSL=false", this.username, this.password);
    }
}
    
    public boolean checkData() {
    	db = new ConfigManager(instance);
    	if(db.getConfig().getString("database.host").equals("-")) {
    		System.out.print("Host is niet insteld voor SQL, Database wordt niet gebruikt!");
    		return false;
    	}
    	if(db.getConfig().getString("database.database").equals("-")) {
    		System.out.print("Databasenaam is niet insteld voor SQL, Database wordt niet gebruikt!");
    		return false;
    	}
    	if(db.getConfig().getString("database.user").equals("-")) {
    		System.out.print("Database-gebruiker is niet insteld voor SQL, Database wordt niet gebruikt!");
    		return false;
    	}
    	if(db.getConfig().getString("database.password").equals("-")) {
    		System.out.print("Database-Wachtwoord is niet insteld voor SQL, Database wordt niet gebruikt!");
    		return false;
    	}
    	if(db.getConfig().getString("database.tabel").equals("-")) {
    		System.out.print("Database-Table is niet insteld voor SQL, Database wordt niet gebruikt!");
    		return false;
    	}
    	return true;
    }
    
	@SuppressWarnings("unused")
	public void updateData(Player player, String ex) {
		if(this.checkData() == false) {
			return;
		}
		String tabel = db.getConfig().getString("sql.tabel");
		db = new ConfigManager(instance);
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            openConnection();
            Statement statement = con.createStatement();   
            statement.executeUpdate("UPDATE " + tabel + " SET " + ex);
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
	}// Hier wordt een update uitgevoerd naar de SQL database, via de string kan de SET quary worden meegestuurd
	
    public ResultSet checkUpdate(UUID uuid) {
    	String tabel = db.getConfig().getString("sql.tabel");
        try {     
        	openConnection();
            Statement statement = con.createStatement();   
            ResultSet result = statement.executeQuery("SELECT * FROM " + tabel + " WHERE playeruuid = '" + uuid + "'");
            con.close();
            return result;
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
	}//hier wordt informatie opgehaald uit de database
    
	public void insertUser(Player p) {
		db = new ConfigManager(instance);
		String tabel = db.getConfig().getString("sql.tabel");
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            openConnection();
            Statement statement = con.createStatement();   
            statement.executeUpdate("INSERT INTO `" + tabel + "`(`createdate`, `playername`, `playeruuid`) VALUES ('" + format.format(now) + "', '" + p.getName().toString() + "', '" + p.getUniqueId().toString() + "');");
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
	}//hier wordt de speler aangemaakt als deze voor het eerst de server joint!

	
	
}