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
				JOptionPane.showMessageDialog(f,"ID����","����",2);
				return false;
			}
			else{*/
				File srcFile = new File(address);
				if(srcFile.exists()){
					Timestamp time=new Timestamp(System.currentTimeMillis());
					return DataProcessing.insertDoc(ID,getName(),time,desc,address);				
				}
				else{
					JOptionPane.showMessageDialog(f,"��ַ����","����",2);
					return false;
				}
			//}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(f,"����","����",2);
			return false;
		}
	}
	void showMenu(){
		/*while(true){
			try{
				Scanner sc=new Scanner(System.in);
				System.out.println("1 �ϴ� �� 2���أ�3�ļ��б�4�˳��˺�");
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
					System.out.println("�������");
				}
			}
			catch(Exception e){
				System.out.println(e);
			}
		
		}*/
		jb1=new JComboBox();
		jb1.addItem("------");
	    jb1.addItem("1,�޸�����");
	    jb1.addItem("2,�ϴ��ļ�");
	    jb1.addItem("3,��ʾ�����ļ���Ϣ������");
	    setJb(jb1);
	}
}
