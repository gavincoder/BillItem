package com.hebeixps;
//import org.sqlite.util.StringUtils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
public class Print { 
		public static void printExcel(String filePath) {
				//String PRINT_NAME="pdfFactory Pro";
			String PRINT_NAME="HP LaserJet 1020";
			ComThread.InitSTA();
			ActiveXComponent xl=new ActiveXComponent("Excel.Application");//使用Jacob创建ActiveX部件对象。
				try{
				Dispatch.put(xl, "visible", new Variant(true));//这里Visible是控制文档打开后是可见还是不可见，若是静默打印，那么第三个参数就设为false就好了
				Dispatch workbooks =xl.getProperty("Workbooks").toDispatch();
				Dispatch excel=Dispatch.call(workbooks,"Open",filePath).toDispatch();//打开文档

			    //xl.setProperty("ActivePrinter", new Variant("HP LaserJet 1020 local on LPT1:"));
				xl.setProperty("ActivePrinter", new Variant(PRINT_NAME));
				//设置竖向打印
//				Dispatch currentSheet = Dispatch.get(excel, "ActiveSheet").toDispatch();
//				Dispatch pageSetup = Dispatch.get(currentSheet, "PageSetup").toDispatch();
//				Dispatch.put(pageSetup, "Orientation", new Variant(1));
//				Dispatch.callN(excel,"PrintOut",new Object[]{Variant.DEFAULT,Variant.DEFAULT, new Integer(1),
//					   new Boolean(false),PRINT_NAME,new Boolean(true),Variant.DEFAULT, ""});
				Dispatch.call(excel,"Close",new Variant(false));
			   Dispatch.get(excel,"PrintOut");//开始打印
				
				}
				catch(Exception e){
					e.printStackTrace();
				//	ComThread.Release();//释放资源
				}
				 finally {
					ComThread.Release();//释放资源
					xl.invoke("Quit", new Variant[] {});//关闭excel文档
					xl=null;
				}     
			}
}
