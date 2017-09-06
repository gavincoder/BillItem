package com.hebeixps;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jxl.Cell;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Joinpoint {

	public void  createJoin(File file,ArrayList<String> storeData){//文件是对象

		try{
			if(!file.exists()){//文件不存在
				 //创建文件
				file.createNewFile();
				ControlExcel storeExcel=new ControlExcel();
				storeExcel.createExcel(file, storeData);
				///清空表内数据
				if(ControlExcel.checkException==0){
					Zhengyang.productNameField.setText("");//通过公共静态的类变量来实现修改其他类中的变量值。
					Zhengyang.specificationField.setText("");
					Zhengyang.sliceField.setText("");
					Zhengyang.amountField.setText("");
					Zhengyang.priceField.setText("");
					Zhengyang.remarkField.setText("");
					Zhengyang.unitField.setText("");	
				}else{
					ControlExcel.checkException=0;
				}
			}	else {//文件已经存在，追加数据,通过复制文件进行追加。			
				Workbook parentWorkbook = Workbook.getWorkbook(file);
				 int parentRowNum= parentWorkbook.getSheet(0).getRows();
				Cell check = parentWorkbook.getSheet(0).getCell(0,parentRowNum-1);//获得单元数据//excel 列在前，行在后。
				 if(check.getContents().equals("公司电话:")){//追加记录//删除后两行，再添加数据
					 int n =JOptionPane.showConfirmDialog(null, "您确信还要在已经生成的销售单上继续插入当前的记录吗?","询问",JOptionPane.YES_NO_OPTION);//i=0/1
					if(n==0){
						WritableWorkbook newWorkbook = parentWorkbook.createWorkbook(file,parentWorkbook);
				        WritableSheet sheet=newWorkbook.getSheet(0);
				        sheet.removeRow(parentRowNum-1);
				        sheet.removeRow(parentRowNum-2);
				        newWorkbook.write();
				        newWorkbook.close();
						GridData  gridRecord=new GridData();
						gridRecord.gridData(file, storeData);	
						 if(GridData.checkExcep==0){
								Zhengyang.productNameField.setText("");//通过公共静态的类变量来实现修改其他类中的变量值。
								Zhengyang.specificationField.setText("");
								Zhengyang.sliceField.setText("");
								Zhengyang.amountField.setText("");
								Zhengyang.priceField.setText("");
								Zhengyang.remarkField.setText("");
								Zhengyang.unitField.setText("");
						 }else{
						GridData.checkExcep=0;
					}
					}else{
						Zhengyang.productNameField.setText("");//通过公共静态的类变量来实现修改其他类中的变量值。
						Zhengyang.specificationField.setText("");
						Zhengyang.sliceField.setText("");
						Zhengyang.amountField.setText("");
						Zhengyang.priceField.setText("");
						Zhengyang.remarkField.setText("");
						Zhengyang.unitField.setText("");	
					}				 
				 }else{
						GridData  gridRecord=new GridData();
						gridRecord.gridData(file, storeData);	
						 if(GridData.checkExcep==0){
								Zhengyang.productNameField.setText("");//通过公共静态的类变量来实现修改其他类中的变量值。
								Zhengyang.specificationField.setText("");
								Zhengyang.sliceField.setText("");
								Zhengyang.amountField.setText("");
								Zhengyang.priceField.setText("");
								Zhengyang.remarkField.setText("");
								Zhengyang.unitField.setText("");
						 }else{
						GridData.checkExcep=0;
					}
					}	
				 }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
}
