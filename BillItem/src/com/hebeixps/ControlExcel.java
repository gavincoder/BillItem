package com.hebeixps;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ControlExcel {
    public static int  checkException=0;
    private static int rowRecord=3;//只是记录值发生变化，标题不发生变化。

	 public void  createExcel(File xlsfile,ArrayList<String> dataList)throws WriteException,IOException {

        //创建工作薄（object）
        WritableWorkbook workbook = Workbook.createWorkbook(xlsfile);
        //创建表
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);      
        //创建表头//
        sheet.mergeCells(0, 0, 8, 0);//添加合并单元格，第一个参数是起始列，第二个参数是 起始行，第三个参数是终止列，第四个参数是终止行
        WritableFont headlineFontStyle = new WritableFont(WritableFont.createFont("宋体"),18,WritableFont.BOLD);//设置主标题字体：宋体，大小18，黑色加粗
        WritableCellFormat headlineCellStyle = new WritableCellFormat(headlineFontStyle);//生成一个单元格格式控制对象,对主标题单元格格式设定
        headlineCellStyle.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
        headlineCellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        //实际上标签这里的意思就是工作表的单元格,
        //Label label = new Label(col, row, title);三个参数分别表示col+1列，row+1行，标题内容是title。 
        Label headline = new Label(0,0,"河北石家庄正阳保温材料有限公司销售单",headlineCellStyle);
        sheet.setRowView(0, 500, false);//设置第一行的高度
        sheet.addCell(headline);//将标签加入到工作表中
        
        //创建要显示的具体内容//
        //表外子标题记录
        WritableFont subtitleFontStyle = new WritableFont(WritableFont.createFont("宋体"),12,WritableFont.BOLD);//子标题字体设置：宋体12号加粗加黑
        WritableCellFormat subtitleCellStyle= new WritableCellFormat(subtitleFontStyle);//子标题单元格格式设置
        subtitleCellStyle.setAlignment(jxl.format.Alignment.LEFT);//单元格中的内容水平方向居中
        subtitleCellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        WritableFont recordFontStyle = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD);//账单记录字体设置：宋体12号加粗加黑
        WritableCellFormat recordCellStyle= new WritableCellFormat(recordFontStyle);//账单记录单元格格式设置
        recordCellStyle.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
        recordCellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        //表内记录
        WritableFont subtitleFontStyle2 = new WritableFont(WritableFont.createFont("宋体"),12,WritableFont.BOLD);//子标题字体设置：宋体12号加粗加黑
        WritableCellFormat subtitleCellStyle2= new WritableCellFormat(subtitleFontStyle2);//子标题单元格格式设置
        subtitleCellStyle2.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
        subtitleCellStyle2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        subtitleCellStyle2.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
        WritableFont recordFontStyle2 = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD);//账单记录字体设置：宋体12号加粗加黑
        WritableCellFormat recordCellStyle2= new WritableCellFormat(recordFontStyle2);//账单记录单元格格式设置
        recordCellStyle2.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
        recordCellStyle2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        recordCellStyle2.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
        
        try{
        //设置-客户
        Label client = new Label(0,1,"  客户：",subtitleCellStyle);// 人为地加了2个空格,不设置边框显示
        sheet.addCell(client);
        sheet.mergeCells(1, 1, 3, 1);//单元格合并前后，单元格坐标不发生变化，合并的单元格坐标就是它的第一个单元格坐标
        Label clientRecord = new Label(1, 1, dataList.get(0),recordCellStyle); 
        sheet.addCell(clientRecord);//文本型，字符型
              
        //设置-日期
        Label date = new Label(4,1,"  日期：",subtitleCellStyle);
        sheet.addCell(date);
        sheet.mergeCells(5,1,6, 1);
        SimpleDateFormat dateStyle = new SimpleDateFormat("yyyy-MM-dd"); 
        String newdate = dateStyle.format(new Date()); 
        Label dateRecord = new Label(5, 1, newdate,recordCellStyle); 
        sheet.addCell(dateRecord);//日期型
        
        //设置-销售员
        Label saler = new Label(7,1,"销售员：",subtitleCellStyle);
        sheet.addCell(saler);
        Label salerRecord = new Label(8, 1, dataList.get(3),recordCellStyle); 
        sheet.addCell(salerRecord);//文本型，字符型
        
       
//       //表内数据// 
        //设置-商品名称
        Label productName = new Label(0,2,"商品名称",subtitleCellStyle2);
        sheet.addCell(productName);
        Label productNameRecord = new Label(0, rowRecord,  dataList.get(4),recordCellStyle2); 
        sheet.addCell(productNameRecord);//文本型，字符型
        
        //设置-规格
        sheet.mergeCells(1, 2, 2, 2);
        Label specification = new Label(1,2,"规格",subtitleCellStyle2);
        sheet.addCell(specification);
        sheet.mergeCells(1, rowRecord, 2, rowRecord);
        Label specificationRecord = new Label(1, rowRecord, dataList.get(5),recordCellStyle2); 
        sheet.addCell(specificationRecord);//文本型，字符型  
        
        //设置-单位
        Label unit = new Label(3,2,"单位",subtitleCellStyle2);
        sheet.addCell(unit);
        Label unitRecord = new Label(3,rowRecord, dataList.get(10),recordCellStyle2); 
        sheet.addCell(unitRecord);//文本型，字符型
        
        //设置-包/片
        Label slice = new Label(4,2,"包/片",subtitleCellStyle2);
        sheet.addCell(slice);
        jxl.write.Number sliceRecord = new jxl.write.Number(4,rowRecord, Integer.parseInt(dataList.get(6).trim()),recordCellStyle2); 
        sheet.addCell(sliceRecord);//整形
        
        //设置-数量
        Label amount = new Label(5,2,"数量(m3)",subtitleCellStyle2);
        sheet.addCell(amount);
        jxl.write.NumberFormat amountSty = new jxl.write.NumberFormat("######.####"); 
        jxl.write.WritableCellFormat amountStyle = new jxl.write.WritableCellFormat(amountSty);
        amountStyle.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
        amountStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        amountStyle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);   
        jxl.write.Number amountRecord = new jxl.write.Number(5,rowRecord,Double.parseDouble(dataList.get(7).trim()) ,amountStyle); 
        sheet.addCell(amountRecord);//格式化的数字  
       
        //设置-单价
        Label price = new Label(6,2,"单价(元)",subtitleCellStyle2);
        sheet.addCell(price);
        jxl.write.NumberFormat priceSty = new jxl.write.NumberFormat("#####.##"); 
        jxl.write.WritableCellFormat priceStyle = new jxl.write.WritableCellFormat(priceSty);
        priceStyle.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
        priceStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        priceStyle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);   
        jxl.write.Number priceRecord = new jxl.write.Number(6,rowRecord, Double.parseDouble(dataList.get(8).trim()),priceStyle); 
        sheet.addCell(priceRecord);//格式化的数字  
        
        //设置-总价
        Label money = new Label(7,2,"总价(元)",subtitleCellStyle2);
        sheet.addCell(money);
        jxl.write.NumberFormat moneySty = new jxl.write.NumberFormat("#######.##"); 
        jxl.write.WritableCellFormat moneyStyle = new jxl.write.WritableCellFormat(moneySty);
        moneyStyle.setAlignment(jxl.format.Alignment.CENTRE);//单元格中的内容水平方向居中
        moneyStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//单元格的内容垂直方向居中
        moneyStyle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);   
        double totalMoney=Double.parseDouble(dataList.get(7).trim())*Double.parseDouble( dataList.get(8).trim());
        jxl.write.Number moneyRecord = new jxl.write.Number(7,rowRecord, totalMoney,moneyStyle); 
        sheet.addCell(moneyRecord);//格式化的数字    
       
        //设置-备注
        Label remark = new Label(8,2,"备注",subtitleCellStyle2);
        sheet.addCell(remark);
        Label remarkRecord = new Label(8, rowRecord, dataList.get(9),recordCellStyle2); 
        sheet.addCell(remarkRecord);//文本型，字符型     
        
        workbook.write();
        workbook.close();
        }catch(Exception e){
        	checkException=1;
			e.printStackTrace();
			workbook.close();
			xlsfile.delete();//第一次就出错，直接删除这个无效生成文件就可！
			JOptionPane.showMessageDialog(null, "输入不合法，请重新输入！.", "警告",JOptionPane.ERROR_MESSAGE);  
		}

        
	 }
    }














