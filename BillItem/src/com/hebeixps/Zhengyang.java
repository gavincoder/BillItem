package com.hebeixps;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;


public class Zhengyang extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField clientField;
	public static JTextField telephoneField;
	public static JTextField drawerField;
	private JTextField salerField;
	public static JTextField productNameField;
	public static JTextField specificationField;
	public static JTextField sliceField;
	public static JTextField amountField;
	public static JTextField priceField;
	public static JTextField remarkField;
	public static JTextField driverField;
	public static JTextField unitField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zhengyang frame = new Zhengyang();
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
	public Zhengyang() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Zhengyang.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		setBackground(new Color(153, 255, 153));
		setResizable(false);
		setTitle("\u5E10\u52A1\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 495);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel company = new JLabel("\u6CB3\u5317\u77F3\u5BB6\u5E84\u6B63\u9633\u4FDD\u6E29\u6750\u6599\u6709\u9650\u516C\u53F8\u9500\u552E\u5355");
		company.setBackground(new Color(153, 255, 153));
		company.setVerticalAlignment(SwingConstants.TOP);
		company.setHorizontalAlignment(SwingConstants.CENTER);
		company.setFont(new Font("宋体", Font.BOLD, 20));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 255, 102));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 255, 255));
		
		JButton openFile = new JButton("\u5B58\u50A8\u5B8C\u6210");
		openFile.setFont(new Font("宋体", Font.BOLD, 12));
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				////////////////////加上表尾，并打开excel//////////////////////
		        SimpleDateFormat dateStyle = new SimpleDateFormat("yyyy-MM-dd"); 
		        String newdate = dateStyle.format(new Date()); 
				String filePath="d:/Table/"+clientField.getText()+"-"+newdate+".xls";
                File parentFile=new File(filePath);			
     
                    Tail tailAdd=new Tail();
					try {
						tailAdd.tailSheet(parentFile);
					} catch (BiffException | WriteException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
				//////////////////////////////////////////////////////////////////
			}
		});
		
		JButton storeRecord = new JButton("\u5B58\u50A8\u8BB0\u5F55");
		storeRecord.setFont(new Font("宋体", Font.BOLD, 12));
		/////////////////////////////////////////////////////////////////
		storeRecord.addActionListener(new ActionListener() {
			private Component jPanel;
			public void actionPerformed(ActionEvent e) {	
			ArrayList<String>  storeData=new ArrayList<String>();
			storeData.add(0,clientField.getText());
			storeData.add(1,telephoneField.getText());
			storeData.add(2,drawerField.getText());
			storeData.add(3,salerField.getText());
//			storeData.add(4,idField.getText());
			storeData.add(4,productNameField.getText());
			storeData.add(5,specificationField.getText());
			storeData.add(6,sliceField.getText());
			storeData.add(7,amountField.getText());
			storeData.add(8,priceField.getText());
			storeData.add(9,remarkField.getText());
			storeData.add(10,unitField.getText());
			storeData.add(11,driverField.getText());
//			for(int i=0;i<15;i++){
//				System.out.println(storeData.get(i));
//			}
			if(storeData.get(0).trim().equals("")||storeData.get(2).trim().equals("")||storeData.get(3).trim().equals("")||storeData.get(4).trim().equals("")||storeData.get(5).trim().equals("")||
					storeData.get(6).trim().equals("")||storeData.get(7).trim().equals("")||storeData.get(8).trim().equals("")||storeData.get(10).trim().equals("")||storeData.get(11).trim().equals("")){
			JOptionPane.showMessageDialog(jPanel,"请将记录填写完整！","标题",JOptionPane.WARNING_MESSAGE);
						}else if(!(Home.userName.getText().trim().equals("谢鸿芳"))&&drawerField.getText().trim().equals("谢鸿芳")){
							drawerField.setText("");
							JOptionPane.showMessageDialog(jPanel,"请开票人输入您本人姓名！","温馨提示",JOptionPane.WARNING_MESSAGE);
			}else {
		        SimpleDateFormat dateStyle = new SimpleDateFormat("yyyy-MM-dd"); 
		        String newdate = dateStyle.format(new Date()); 
				String path="d:/Table/"+storeData.get(0)+"-"+newdate+".xls";
				File file=new File(path);
	            Joinpoint  join =new Joinpoint();
	            join.createJoin(file,storeData);  		
				
			}
			

/////////////////////////////////////////////////
			}
		}
	);
		JButton print = new JButton("\u62A5\u8868\u6253\u5370");
		print.setFont(new Font("宋体", Font.BOLD, 12));
		print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
/////////////////////////打印表格//////////////////////////////////
//		        SimpleDateFormat dateStyle = new SimpleDateFormat("yyyy-MM-dd"); 
//		        String newdate = dateStyle.format(new Date()); 
//				String printFile="d:/files/"+clientField.getText()+"-"+newdate+".xls";
//				Print gridPrint=new Print();
//				gridPrint.printExcel(printFile);				
//				gridPrint.printExcel("D:\\files\\qq.xls");
							
////////////////////////////////////////////////////////////////////				
			}
		});
		
		JButton exit = new JButton("\u6253\u5F00\u6587\u4EF6");
		exit.setFont(new Font("宋体", Font.BOLD, 12));
		exit.addActionListener(new ActionListener() {
			private Component jPanel;

			public void actionPerformed(ActionEvent arg0) {
				///////////////////打开excel表/////////////////////
		        SimpleDateFormat dateStyle = new SimpleDateFormat("yyyy-MM-dd"); 
		        String newdate = dateStyle.format(new Date()); 
				String openFilePath="d:/Table/"+clientField.getText()+"-"+newdate+".xls";
				File openFile=new File(openFilePath);
			//	 Workbook parentWorkbook;
				try {
					Workbook parentWorkbook = Workbook.getWorkbook(openFile);
					 int parentRowNum= parentWorkbook.getSheet(0).getRows();
					Cell check = parentWorkbook.getSheet(0).getCell(0,parentRowNum-1);//获得单元数据//excel 列在前，行在后。
					 if(!check.getContents().equals("公司电话:")){//避免重复加表尾
							JOptionPane.showMessageDialog(jPanel,"请点击“存储完成”，确保记录全部存完，再查看文件！","警告！",JOptionPane.WARNING_MESSAGE);
						 }else{
//							   Runtime.getRuntime().exec("C:\\Program Files (x86)\\Microsoft Office\\Office12\\EXCEL.EXE"+" "+openFilePath);
							 Runtime.getRuntime().exec("C:\\Program Files\\Microsoft Office\\Office15\\EXCEL.EXE"+" "+openFilePath);
						 }
				} catch (BiffException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	 
							
				/////////////////////////////////////////
				
				
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(company, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(45)
					.addComponent(storeRecord, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(openFile, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
					.addComponent(exit, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addComponent(print, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(42))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 656, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(company)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(storeRecord)
						.addComponent(openFile)
						.addComponent(print)
						.addComponent(exit))
					.addGap(18))
		);
		
		JLabel client = new JLabel("\u5BA2\u6237\u4FE1\u606F\uFF1A");
		client.setBounds(10, 10, 75, 15);
		
		clientField = new JTextField();
		clientField.setBounds(95, 7, 187, 21);
		clientField.setColumns(10);
		
		JLabel telephone = new JLabel("\u516C\u53F8\u7535\u8BDD\uFF1A");
		telephone.setBounds(10, 53, 75, 15);
		
		telephoneField = new JTextField();
		telephoneField.setBounds(95, 50, 187, 21);
		telephoneField.setColumns(10);
		
		JLabel drawer = new JLabel("\u5F00 \u7968 \u4EBA\uFF1A");
		drawer.setBounds(349, 10, 60, 15);
		
		drawerField = new JTextField();
		drawerField.setBounds(429, 7, 195, 21);
		drawerField.setColumns(10);
		
		JLabel saler = new JLabel("\u9500 \u552E \u5458\uFF1A");
		saler.setBounds(349, 53, 60, 15);
		
		salerField = new JTextField();
		salerField.setBounds(429, 50, 195, 21);
		salerField.setColumns(10);
		panel_1.setLayout(null);
		panel_1.add(client);
		panel_1.add(clientField);
		panel_1.add(telephone);
		panel_1.add(telephoneField);
		panel_1.add(drawer);
		panel_1.add(saler);
		panel_1.add(salerField);
		panel_1.add(drawerField);
		
		JLabel productName = new JLabel("\u5546\u54C1\u540D\u79F0\uFF1A");
		productName.setBounds(10, 27, 71, 15);
		
		productNameField = new JTextField();
		productNameField.setBounds(91, 24, 188, 21);
		productNameField.setColumns(10);
		
		JLabel specification = new JLabel("\u89C4    \u683C\uFF1A");
		specification.setBounds(349, 27, 72, 15);
		
		specificationField = new JTextField();
		specificationField.setBounds(431, 24, 193, 21);
		specificationField.setColumns(10);
		
		JLabel slice = new JLabel("\u5305  /  \u7247 \uFF1A");
		slice.setBounds(10, 171, 77, 15);
		
		sliceField = new JTextField();
		sliceField.setBounds(91, 168, 188, 21);
		sliceField.setColumns(10);
		
		JLabel amount = new JLabel("\u6570\u91CF(m3)\uFF1A");
		amount.setBounds(10, 98, 71, 15);
		
		amountField = new JTextField();
		amountField.setBounds(91, 95, 188, 21);
		amountField.setColumns(10);
		
		JLabel price = new JLabel("\u5355\u4EF7(\u5143)\uFF1A");
		price.setBounds(349, 98, 72, 15);
		
		priceField = new JTextField();
		priceField.setBounds(431, 95, 193, 21);
		priceField.setColumns(10);
		
		JLabel remark = new JLabel("\u5907     \u6CE8 \uFF1A");
		remark.setBounds(10, 232, 77, 15);
		
		remarkField = new JTextField();
		remarkField.setBounds(91, 229, 188, 21);
		remarkField.setColumns(10);
		
		JLabel driver = new JLabel("\u53F8      \u673A\uFF1A");
		driver.setBounds(349, 232, 72, 15);
		
		driverField = new JTextField();
		driverField.setBounds(431, 229, 193, 21);
		driverField.setColumns(10);
		panel.setLayout(null);
		panel.add(productName);
		panel.add(productNameField);
		panel.add(amount);
		panel.add(slice);
		panel.add(remark);
		panel.add(remarkField);
		panel.add(sliceField);
		panel.add(amountField);
		panel.add(driver);
		panel.add(driverField);
		panel.add(specification);
		panel.add(price);
		panel.add(priceField);
		panel.add(specificationField);
		
		JLabel unit = new JLabel("\u5355     \u4F4D\uFF1A");
		unit.setBounds(349, 171, 72, 15);
		panel.add(unit);
		
		unitField = new JTextField();
		unitField.setColumns(10);
		unitField.setBounds(431, 168, 193, 21);
		panel.add(unitField);
		contentPane.setLayout(gl_contentPane);
	}
}
