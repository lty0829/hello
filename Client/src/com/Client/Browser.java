package com.Client;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

import javax.swing.*;
class Browser extends User{
	JComboBox jb1;
	public Browser(String name, String password, String role) {
		super(name, password, role);
	}
	void showMenu(){
		/*while(true){
			try{
				Scanner sc=new Scanner(System.in);
				System.out.println("1���أ�2��ʾ�ļ��б�3�˳��˺�");
				int i=sc.nextInt();
				if(i==1){
					downloadFile();
				}
				else if(i==2){
					showFileList();
				}
				else if(i==3){
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
	    jb1.addItem("2,��ʾ�����ļ���Ϣ������");
	    setJb(jb1);
	}
}
