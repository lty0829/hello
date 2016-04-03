package com.Client;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;

import com.Client.ClientUI.WindowMonitor;
public class User {
	private JComboBox jb;
	private String name;
	private String password;
	private String role;
	Scanner sc=new Scanner(System.in);
	String IDs="";
	JFrame f;
	JTextField input=new JTextField();
	public User() {
		super();
	}
	public User(String name, String password, String role) {
		super();
		this.name = name;
		this.password = password;
		this.role = role;
	}
	void showMenu(){
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getRole() {
		return role;
	}
	public JComboBox getJb() {
		return jb;
	}
	public String getIDs() {
		return IDs;
	}
	public void setIDs(String iDs) {
		IDs = iDs;
	}
	public void setJb(JComboBox jb) {
		this.jb = jb;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) {
		this.role = role;
	}
	boolean changeSelfInfo(String userPassword){
		try{
			return DataProcessing.updateUser(getName(),userPassword,getRole());
		}
		catch(Exception e){
			return false;
		}
	}
	boolean downloadFile(String ID,String address){
			try{
				Doc ert=DataProcessing.searchDoc(ID);
				File srcFile = new File("D:\\eclipse\\workspace\\clientSocket\\"+ert.getID()+"\\"+ert.getFilename());
				File destFile= new File(address);
				if(destFile.isDirectory()){
					address=address+"\\"+ert.getFilename();
					destFile = new File(address);
				}
				if(!destFile.getParentFile().exists()) {
					if(!destFile.getParentFile().mkdirs()) {
						System.out.println("创建目标文件所在目录失败！");
					}
					destFile.createNewFile();
				}
				FileInputStream fis = new FileInputStream(srcFile);
				FileOutputStream fos = new FileOutputStream(destFile);
				byte[] buf = new byte[1024];  
				while(true){
					int temp=fis.read(buf,0,buf.length);
					if(temp==-1){
						break;
					}
					fos.write(buf,0,temp);
				}
				fis.close();    
				fos.close();	
				return true;
				//System.out.println(destFile.exists());
			}
			catch(Exception e){
				System.out.println("出错");
				return false;
			}
		}
		int showFileList(JPanel t1){
			Doc docs;
			int num=0;
			ButtonGroup group = new ButtonGroup();
			JLabel b[][]=new JLabel[100][4];
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			JRadioButton radioButton[] = new JRadioButton[100];
			//button.addActionListener(new buttonListener());
			try{
				for(Enumeration<Doc>e=DataProcessing.getAllDocs();e.hasMoreElements();num++){
	    			docs=e.nextElement();
					radioButton[num] = new JRadioButton(docs.getID());
					t1.add(radioButton[num]);
					radioButton[num].addActionListener(new radioListener());
					radioButton[num].setBounds(0, (num+1)*15, 100, 15);
					group.add(radioButton[num]);
					for(int i=0;i<4;i++){
						b[num][i]=new JLabel();
						t1.add(b[num][i]);
					}           
		            b[num][0].setText(docs.getCreator());
		            b[num][0].setBounds(100,(num+1)*15, 100, 15);
		            String a=df.format(docs.getTimestamp());
		            b[num][1].setText(a);
		            b[num][1].setBounds(200,(num+1)*15, 200, 15);
		            b[num][2].setText(docs.getDescription());
		            b[num][2].setBounds(400,(num+1)*15, 200, 15);
		            b[num][3].setText(docs.getFilename());
		            b[num][3].setBounds(600,(num+1)*15, 100, 15);
		            
				}
			}
			catch(Exception e){
				System.out.println("出错");
			}
			return num;
	}
		class radioListener implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setIDs(e.getActionCommand());
			}			
		}
		class WindowMonitor extends WindowAdapter {
			 public void windowClosing(WindowEvent e) {
			 f.dispose();
			 }
		}
}