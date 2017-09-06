package com.hebeixps;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Home extends JFrame {
//
	   public static ArrayList nameData=new ArrayList();
	   public static ArrayList passwordData=new ArrayList();
	   public static  String newPassword;
	private JPanel contentPane;
	public static JTextField userName;
	private JTextField password;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	/**
	 * Create the frame.
	 */
	public Home() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/com/sun/java/swing/plaf/windows/icons/image-delayed.png")));
		setBackground(new Color(0, 153, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 373);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_3 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_3.setForeground(new Color(30, 144, 255));
		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 15));
		
		userName = new JTextField();
		userName.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel_4.setForeground(new Color(30, 144, 255));
		lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 15));
		
		password = new JTextField();
		password.setBackground(new Color(255, 250, 240));
		password.setColumns(10);
		
		JButton reset = new JButton("\u91CD \u7F6E");
		reset.setForeground(new Color(30, 144, 255));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/////////////////////////重置密码//////////////////////////
				 String databasepath="jdbc:sqlite:D:\\Table\\userdatabase.db";    
				 File userdataFile=new File(databasepath);
//				 if(!userdataFile.exists()){/////////////////////wen ti//////////////////
//					 System.out.println("dbfile exists!");
//					    LoginDatabase db2=new LoginDatabase();	
//					    db2.initDatabase(databasepath);		 
//				 }
				    try {
						Class.forName("org.sqlite.JDBC");
				        Connection conn = DriverManager.getConnection(databasepath);//连接数据库
				        Statement stat = conn.createStatement();		        
				        ResultSet rs = stat.executeQuery("select * from user;");				        
				    int i=0;				 
			        while (rs.next())//读完后结果集ResultSet rs自关闭了。
			        {
			        nameData.add(i,rs.getString("name"));//序号自增，只需定义个序号变量即可！
			        passwordData.add(i,rs.getString("password"));
	             System.out.println("Name = " + rs.getString("name"));
	             System.out.println("Password = " + rs.getString("password"));
			        }
			        int num=0;
			        int response=1;			        
			        for(int j=0;j<2;j++){			   
					    if(((userName.getText().trim()).equals(nameData.get(j))&&((password.getText().trim()).equals(passwordData.get(j))))){		
					    	num++;
					    	response=JOptionPane.showConfirmDialog(null,"您确定要修改密码吗？","提示",JOptionPane.YES_NO_OPTION);//i=0/1
					    	if(response==0){
					    		newPassword=JOptionPane.showInputDialog(null,"请您输入新密码：","设置新密码",JOptionPane.PLAIN_MESSAGE);
				    	   System.out.println(newPassword);
				    	   System.out.println(userName.getText());					    	
					       String sql="update  user set password = '"+newPassword+"' where name ='"+userName.getText()+"'"+";";
				    	   System.out.println(sql);
					       stat.executeUpdate(sql);
					       password.setText("");
					    	}
					    }else if(((userName.getText().trim()).equals(nameData.get(j))&&!((password.getText().trim()).equals(passwordData.get(j))))){
					    	num++;
					    	JOptionPane.showMessageDialog(null,"密码错误，请重新输入！","提示",JOptionPane.ERROR_MESSAGE);
					        password.setText("");
					    }
				        }
		       if(num==0){
					    	num=0;
					    	JOptionPane.showMessageDialog(null,"用户不存在 ! ","提示",JOptionPane.ERROR_MESSAGE);
					        password.setText("");
					        userName.setText("");
					    }
		        //rs.close();
		        conn.close();
		      //  stat.close();
					    } catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("SQLState:"+e.getSQLState());						 
							System.out.println("Message:"+e.getMessage());						 
	                        System.out.println("Vendor:"+e.getErrorCode());
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}					    
				////////////////////////////////////////////////////////////
			}
		});
		reset.setFont(new Font("宋体", Font.BOLD, 14));
		
		JButton login = new JButton("\u767B \u5F55");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//////////login///////
				   String databasepath="jdbc:sqlite:D:\\Table\\userdatabase.db";    
//					 File userdataFile1=new File(databasepath);
//					 if(!userdataFile1.exists()){
//					        System.out.println("dbfile exists!");
//						    LoginDatabase db=new LoginDatabase();	
//						    db.initDatabase(databasepath);		 
//					 }
				    try {
						Class.forName("org.sqlite.JDBC");
				        Connection conn = DriverManager.getConnection(databasepath);//连接数据库
				        Statement stat = conn.createStatement();			
				    ResultSet rs = stat.executeQuery("select * from user;");
				    int i=0;				 
			        while (rs.next())//读完后结果集ResultSet rs自关闭了。
			        {
			        nameData.add(i,rs.getString("name"));//序号自增，只需定义个序号变量即可！
			        passwordData.add(i,rs.getString("password"));
			        }
//			             System.out.println("Name = " + rs.getString("name"));
//			             System.out.println("Password = " + rs.getString("password"));
			        int num=0;
			        for(int j=0;j<2;j++){			   
				    if(((userName.getText().trim()).equals(nameData.get(j))&&((password.getText().trim()).equals(passwordData.get(j))))){		
				    	num++;
				        setVisible(false);//针对当前窗口，直接设置即可！
				    	EventQueue.invokeLater(new Runnable() {
				    		public void run() {
				    			try {
				    				Zhengyang frame = new Zhengyang();
				    				frame.setLocationRelativeTo(null);
				    				frame.setVisible(true);
				    			} catch (Exception e) {
				    				e.printStackTrace();
				    			}
				    		}
				    	});		
				    }else if(((userName.getText().trim()).equals(nameData.get(j))&&!((password.getText().trim()).equals(passwordData.get(j))))){
				    	num++;
				    	JOptionPane.showMessageDialog(null,"密码错误，请重新输入！","提示",JOptionPane.ERROR_MESSAGE);
				        password.setText("");
				    }
			        }
	       if(num==0){
				    	num=0;
				    	JOptionPane.showMessageDialog(null,"用户不存在 ! ","提示",JOptionPane.ERROR_MESSAGE);
				        password.setText("");
				        userName.setText("");
				    }
			        rs.close();
			        conn.close();
			   //     stat.close();
				    } catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("SQLState:"+e.getSQLState());					 
						System.out.println("Message:"+e.getMessage());					 
                        System.out.println("Vendor:"+e.getErrorCode());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}			
					
		//////////login///////
			}

		});
		login.setForeground(new Color(30, 144, 255));
		login.setFont(new Font("宋体", Font.BOLD, 14));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(81)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_4)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(login, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
							.addComponent(reset, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
						.addComponent(userName, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
						.addComponent(password, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
					.addGap(95))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(userName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(50)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(reset)
						.addComponent(login, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("W");
		lblNewLabel_1.setBackground(new Color(245, 255, 250));
		lblNewLabel_1.setForeground(new Color(148, 0, 211));
		lblNewLabel_1.setBounds(10, 12, 40, 85);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 70));
		
		JLabel lblNewLabel_2 = new JLabel("elcome to");
		lblNewLabel_2.setForeground(new Color(148, 0, 211));
		lblNewLabel_2.setBounds(43, 61, 99, 24);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 20));
		
		JLabel lblNewLabel = new JLabel("\u6CB3\u5317\u77F3\u5BB6\u5E84\u6B63\u9633\u4FDD\u6E29\u6750\u6599\u6709\u9650\u516C\u53F8\u5E10\u52A1\u7CFB\u7EDF");
		lblNewLabel.setBounds(55, 37, 399, 24);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		contentPane.setLayout(gl_contentPane);
	}
}
