package com.Client;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
 class Administrator extends User{
	 JPanel xy2;
	 JFrame a=new JFrame();
	 JComboBox jb1;
	 Scanner sc=new Scanner(System.in);
	 JFrame f1;
	 //DataProcessing data=new DataProcessing();
	public Administrator(String name, String password, String role) {
		super(name, password, role);
	}
	boolean changeUserInfo(String userName,String userPassword,String userRole){
		try{
			return DataProcessing.updateUser(userName,userPassword,userRole);
		}
		catch(Exception e){
			//System.out.println("出错");
			return false; 
		}
	}
	void delUser(String deleteName){
		try{
			if(getName().equals(deleteName)){
				JOptionPane.showMessageDialog(null,"不能删除自己","警告",2);
			}
			else{
				DataProcessing.deleteUser(deleteName);
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"出错","警告",2);
		}	
	}
	boolean addUser(String addName,String addPassword,String addrole){
		try{
			
			if(DataProcessing.insertUser(addName,addPassword,addrole)){
				return true;
			}
			else{
				JOptionPane.showMessageDialog(null,"账号存在","警告",2);
				return false;
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"出错","警告",2);
			return false;		
		}
	}
	public int ListUser(JPanel t1){
		User user;
		int num=0;
		xy2=t1;
		ButtonGroup group1 = new ButtonGroup();
		JLabel b[][]=new JLabel[100][4];
		JRadioButton radioButton1[] = new JRadioButton[100];
		try{
			for(Enumeration<User>e=DataProcessing.getAllUser();e.hasMoreElements();num++){
    			user=e.nextElement();
				radioButton1[num] = new JRadioButton(user.getName());
				t1.add(radioButton1[num]);
				radioButton1[num].addActionListener(new radioListener1());
				radioButton1[num].setBounds(0, (num+1)*15, 100, 15);
				group1.add(radioButton1[num]);
				for(int i=0;i<2;i++){
					b[num][i]=new JLabel();
					t1.add(b[num][i]);
				}           
	            b[num][0].setText(user.getPassword());
	            b[num][0].setBounds(120,(num+1)*15, 100, 15);
				b[num][1].setText(user.getRole());
	            b[num][1].setBounds(240,(num+1)*15, 200, 15);
			}
		}
		catch(Exception e){
			System.out.println("出错");
		}
		return num;
	}
	class radioListener1 implements ActionListener{		
		@Override
		public void actionPerformed(ActionEvent e) {
			setIDs(e.getActionCommand());
			//System.out.println(Names);
		}			
	}
	void showMenu(){
		jb1=new JComboBox();
		jb1.addItem("------");
	    jb1.addItem("1,显示所有账号信息");
	    jb1.addItem("2,显示所有文件信息并下载");
	    setJb(jb1);
	}
}

