package bpl;

import java.sql.*;
import bpl.Registrasi;

public class koneksi 
{
	
	public static String url = "jdbc:mysql://localhost/tb_bpl?serverTimezone=Asia/Jakarta";
	public static String user = "root";
	public static String password = "";
	public static ResultSet rs;
	public static Connection conn;
	public static Statement stmt;
    
	public static void main(String[] args) 
	{
		try 
        	{
				conn = DriverManager.getConnection(url,user,password);
				if(conn != null){
					System.out.println("Berhasil koneksi ke MySql Server");
				}else{
					System.out.println("Gagal koneksi ke MySql Server");
				}
        	} 
        catch (SQLException e) 
        	{
            	e.printStackTrace();
        	}
    	} 
}
