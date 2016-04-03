package com.Client;
import java.io.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
class Operator extends User{
	JComboBox jb1;
	public Operator(String name, String password, String role) {
		super(name, password, role);
	}
	boolean uploadFile(String ID,String desc,String address){
		try{
		/*	if(DataProcessing.searchDoc(ID)!=null){
				JOptionPane.showMessageDialog(f,"ID存在","警告",2);
				return false;
			}
			else{*/
				File srcFile = new File(address);
				if(srcFile.exists()){
					Timestamp time=new Timestamp(System.currentTimeMillis());
					return DataProcessing.insertDoc(ID,getName(),time,desc,address);				
				}
				else{
					JOptionPane.showMessageDialog(f,"地址错误","警告",2);
					return false;
				}
			//}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(f,"出错","警告",2);
			return false;
		}
	}
	void showMenu(){
		/*while(true){
			try{
				Scanner sc=new Scanner(System.in);
				System.out.println("1 上传 ， 2下载，3文件列表，4退出账号");
				int i=sc.nextInt();
				if(i==1){
					uploadFile();
				}
				else if(i==2){
					downloadFile();
				}
				else if(i==3){
					showFileList();
				}
				else if(i==4){
					break;
				}
				else{
					System.out.println("输入出错");
				}
			}
			catch(Exception e){
				System.out.println(e);
			}
		
		}*/
		jb1=new JComboBox();
		jb1.addItem("------");
	    jb1.addItem("1,修改密码");
	    jb1.addItem("2,上传文件");
	    jb1.addItem("3,显示所有文件信息并下载");
	    setJb(jb1);
	}
}
