package com.hebeixps;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class GridData extends ControlExcel{
	public static int checkExcep=0;
 public void gridData(File file,ArrayList<String> dataList) throws BiffException, IOException, WriteException{
	 //FileInputStream in = new FileInputStream(file);
	 Workbook  parentWorkbook=Workbook.getWorkbook(file);	 
	 File tempFile=new File("d:/Table/tempFile.xls");
	 tempFile.createNewFile();
	 WritableWorkbook childWorkbook = Workbook.createWorkbook(tempFile, parentWorkbook);
	 WritableSheet childSheet = childWorkbook.getSheet(0);
	 int parentRowNum= parentWorkbook.getSheet(0).getRows();
	 int rowNum = parentRowNum;
	 try{
	 //表内记录
     WritableFont recordFontStyle2 = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD);//账单记录字体设置：宋体12号加粗加黑
     WritableCellFormat recordCellStyle2= new WritableCellFormat(recordFontStyle2);//账单记录单元格格式设置
     recordCellStyle2.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
     recordCellStyle2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
     recordCellStyle2.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
     
     //表内数据//   
    //设置-商品名称
    Label productNameRecord = new Label(0, rowNum, dataList.get(4),recordCellStyle2); 
    childSheet.addCell(productNameRecord);//文本型，字符型
    
    //设置-规格
    childSheet.mergeCells(1, rowNum, 2, rowNum);
    Label specificationRecord = new Label(1, rowNum, dataList.get(5),recordCellStyle2); 
    childSheet.addCell(specificationRecord);//文本型，字符型  
    
    //设置-单位
    Label unitRecord = new Label(3,rowNum, (String)dataList.get(10),recordCellStyle2);
    childSheet.addCell(unitRecord);//文本型，字符型
    
    //设置-包/片
    jxl.write.Number sliceRecord = new jxl.write.Number(4,rowNum, Integer.parseInt(dataList.get(6).trim()),recordCellStyle2); 
    childSheet.addCell(sliceRecord);//整形
    
    //设置-数量
    jxl.write.NumberFormat amountSty = new jxl.write.NumberFormat("######.####"); 
    jxl.write.WritableCellFormat amountStyle = new jxl.write.WritableCellFormat(amountSty);
    amountStyle.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
    amountStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
    amountStyle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);   
    jxl.write.Number amountRecord = new jxl.write.Number(5,rowNum,Double.parseDouble(dataList.get(7).trim()) ,amountStyle); 
    childSheet.addCell(amountRecord);//格式化的数字  
    //设置-单价
    jxl.write.NumberFormat priceSty = new jxl.write.NumberFormat("#####.##"); 
    jxl.write.WritableCellFormat priceStyle = new jxl.write.WritableCellFormat(priceSty);
    priceStyle.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
    priceStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
    priceStyle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);   
    jxl.write.Number priceRecord = new jxl.write.Number(6,rowNum, Double.parseDouble(dataList.get(8).trim()),priceStyle); 
    childSheet.addCell(priceRecord);//格式化的数字  
    
    //设置-总价
    jxl.write.NumberFormat moneySty = new jxl.write.NumberFormat("#######.##"); 
    jxl.write.WritableCellFormat moneyStyle = new jxl.write.WritableCellFormat(moneySty);
    moneyStyle.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
    moneyStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
    moneyStyle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);   
    double totalMoney=Double.parseDouble(dataList.get(7).trim())*Double.parseDouble( dataList.get(8).trim());
    jxl.write.Number moneyRecord = new jxl.write.Number(7,rowNum, totalMoney,moneyStyle); 
    childSheet.addCell(moneyRecord);//格式化的数字    
   
    //设置-备注
    childSheet.mergeCells(12, rowNum, 13, rowNum);
    Label remarkRecord = new Label(8, rowNum, dataList.get(9),recordCellStyle2);
    childSheet.addCell(remarkRecord);//文本型，字符型  
    
     }catch(Exception e){
    	     checkExcep=1;
			e.printStackTrace();
			tempFile.delete();//恢复原态！
			JOptionPane.showMessageDialog(null, "输入不合法，请重新输入！.", "警告",JOptionPane.ERROR_MESSAGE);  
		}
	    childWorkbook.write();
	    childWorkbook.close();
	    parentWorkbook.close();
		 

	    if(tempFile.exists()){
		file.delete();
	    tempFile.renameTo(file);
	    }
	 
	 
 }
	
}
