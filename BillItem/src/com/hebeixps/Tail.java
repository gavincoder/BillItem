package com.hebeixps;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Tail extends ControlExcel {
private Component jPanel;

public void tailSheet(File parentFile)throws BiffException, IOException, WriteException{
	//FileInputStream in = new FileInputStream(parentFile);
	 Workbook  parentWorkbook=Workbook.getWorkbook(parentFile);	 
	 File tempFile=new File("d:/Table/tempFile.xls");
	 tempFile.createNewFile();
	 WritableWorkbook childWorkbook = Workbook.createWorkbook(tempFile, parentWorkbook);
	 WritableSheet childSheet = childWorkbook.getSheet(0);
	 int parentRowNum= parentWorkbook.getSheet(0).getRows();
	 int rowNum = parentRowNum+1;
	 Cell check = childSheet.getCell(0,parentRowNum-1);//获得单元数据//excel 列在前，行在后。
	 if(check.getContents().equals("公司电话:")){//避免重复加表尾
		JOptionPane.showMessageDialog(jPanel,"记录已经全部存储，请不要重复操作！","警告！",JOptionPane.WARNING_MESSAGE);
	     childWorkbook.close();
	     parentWorkbook.close();	 	 
	     tempFile.delete();
	 }
	 else{ 
	 WritableFont subtitleFontStyle = new WritableFont(WritableFont.createFont("宋体"),12,WritableFont.BOLD);//子标题字体设置：宋体12号加粗加黑
     WritableCellFormat subtitleCellStyle= new WritableCellFormat(subtitleFontStyle);//子标题单元格格式设置
     subtitleCellStyle.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
     subtitleCellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
     WritableFont recordFontStyle = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD);//账单记录字体设置：宋体12号加粗加黑
     WritableCellFormat recordCellStyle= new WritableCellFormat(recordFontStyle);//账单记录单元格格式设置
     recordCellStyle.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
     recordCellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
	 
    //合计
     double sumNum=0.0;
     double sumMoney=0.0;
     Label total = new Label(0,parentRowNum,"  合计：",subtitleCellStyle);// 人为地加了2个空格,不设置边框显示
     childSheet.addCell(total);
     childSheet.mergeCells(5, parentRowNum, 6, parentRowNum);//不设置显示边框，就看不见。
     for(int i=3;i<parentRowNum;i++){
    	 Cell c = childSheet.getCell(5,i);//获得单元数据//excel 列在前，行在后。
    	 sumNum+=Double.parseDouble(c.getContents());
     }
     jxl.write.NumberFormat amountSty = new jxl.write.NumberFormat("######.####"); 
     jxl.write.WritableCellFormat amountStyle = new jxl.write.WritableCellFormat(amountSty);
     amountStyle.setAlignment(jxl.format.Alignment.LEFT);//单元格中的内容水平方向居中
     amountStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中 
     jxl.write.Number totalRecord = new jxl.write.Number(5,parentRowNum,sumNum ,amountStyle); 
     childSheet.addCell(totalRecord);//格式化的数字  
   
     childSheet.mergeCells(7, parentRowNum, 8, parentRowNum);
     for(int i=3;i<parentRowNum;i++){
    	 Cell b = childSheet.getCell(7,i);//获得单元数据//excel 列在前，行在后。
    	 sumMoney+=Double.parseDouble(b.getContents());
     }
     jxl.write.Number totalMoney = new jxl.write.Number(7,parentRowNum,sumMoney ,amountStyle); 
     childSheet.addCell(totalMoney);//格式化的数字 
     
     //公司电话
     Label telephone = new Label(0,rowNum,"公司电话:",subtitleCellStyle);// 人为地加了2个空格,不设置边框显示
     childSheet.addCell(telephone);
     childSheet.mergeCells(1, rowNum, 3, rowNum);
     Label telephoneRecord = new Label(1, rowNum, Zhengyang.telephoneField.getText(),recordCellStyle); 
     childSheet.addCell(telephoneRecord);//文本型，字符型  
     
     //司机
     Label driver = new Label(4,rowNum,"  司机：",subtitleCellStyle);// 人为地加了2个空格,不设置边框显示
     childSheet.addCell(driver);
     childSheet.mergeCells(5, rowNum, 6, rowNum);
     Label driverRecord = new Label(5, rowNum, Zhengyang.driverField.getText(),recordCellStyle); 
     childSheet.addCell(driverRecord);//文本型，字符型  
     
     //开票人
     Label drawer = new Label(7,rowNum,"开票人：",subtitleCellStyle);// 人为地加了2个空格,不设置边框显示
     childSheet.addCell(drawer);
     Label drawerRecord = new Label(8, rowNum, Zhengyang.drawerField.getText(),recordCellStyle); 
     childSheet.addCell(drawerRecord);//文本型，字符型  	 
     
     childWorkbook.write();
     childWorkbook.close();
     parentWorkbook.close();
 	 
     parentFile.delete();
     tempFile.renameTo(parentFile);
	 }
}
}
