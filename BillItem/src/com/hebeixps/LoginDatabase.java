package com.hebeixps;

import java.awt.Cursor;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteDataSource;

public class LoginDatabase {
    public void initDatabase(String databasepath){
    	Connection conn = null;
    	Statement stat =null;
    	PreparedStatement prep =null;
    		try {
    			Class.forName("org.sqlite.JDBC");	
    	        conn = DriverManager.getConnection(databasepath);//// 1 建立一个数据库名zieckey.db的连接，如果不存在就在当前目录下创建之
    	        stat = conn.createStatement();
    	       // stat.executeUpdate("drop table if exists user;");
    	        stat.executeUpdate("create table user (name, password);");////////
    	        prep = conn.prepareStatement("insert into user values (?, ?);");
    	        // 写入数据目录,堆栈式存储！
    	        prep.setString(1, "谢鸿芳");
    	        prep.setString(2, "123456");
    	        prep.addBatch();
    	        prep.setString(1, "其他人员");
    	        prep.setString(2, "123456");
    	        prep.addBatch();
    	        
    	        conn.setAutoCommit(false);
    	        prep.executeBatch();
    	        conn.setAutoCommit(true);
    	        prep.close();
    	        stat.close();
    	        conn.close();//关闭连接
    			}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("SQLState:"+e.getSQLState());						 
					System.out.println("Message:"+e.getMessage());						 
                    System.out.println("Vendor:"+e.getErrorCode());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
		 }         
   }
		
    	





