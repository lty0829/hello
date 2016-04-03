package com.Client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.*;


public class FilelistUI {
	JFrame f;
	JFrame f1;
	JFrame f2;
	JPanel t2;
	JPanel t3;
	JPanel t4;
	User user= new User();
	JTextField input=new JTextField();
	GridBagLayout p1=new GridBagLayout();
	JButton ssd=new JButton("确定");
	JButton sxd=new JButton("取消");
	public FilelistUI(JPanel t2, JPanel t4,JFrame f1) {
		super();
		this.t2 = t2;
		this.t4 = t4;
		this.f1 = f1;
	}
	public void er2(){
    	t2.removeAll();
    	t4.removeAll();
    	JButton ba=new JButton("下载");
    	JButton ba1=new JButton("删除");
    	JScrollPane jp=new JScrollPane();
    	t3=new JPanel();
        t3.setLayout(null);
        jp.setViewportView(t3);
        t2.setLayout(p1);
        t4.setLayout(p1);
        t4.add(jp);
        t2.add(t4);
        t2.add(ba);
        t2.add(ba1);
        ba.addActionListener(new buttonListener());
        ba1.addActionListener(new buttonListener());
    	GridBagConstraints s2= new GridBagConstraints();
		s2.fill=GridBagConstraints.BOTH;
		s2.gridwidth=0;
		s2.weightx = 10;
		s2.weighty=2;
		s2.ipady=50;
		p1.setConstraints(jp, s2);
        s2.gridwidth=0;
		s2.weightx = 10;
		s2.weighty=2;
		s2.ipadx=350;
		s2.ipady=150;
		p1.setConstraints(t4, s2);
		s2.fill=GridBagConstraints.NONE;
		s2.insets = new Insets(10, 110, 0, 0);
		s2.gridwidth=1;
		s2.weightx = 0;
		s2.weighty=0;
		s2.ipadx=0;
		s2.ipady=0;
		p1.setConstraints(ba, s2);
		s2.insets = new Insets(10, 0, 0, 80);
		s2.gridwidth=0;
		s2.weightx = 0;
		s2.weighty=0;
		p1.setConstraints(ba1, s2);
        JLabel b[]=new JLabel[5];
        for(int i=0;i<5;i++){
        	b[i]=new JLabel();
        	t3.add(b[i]);
        }
        t3.add(b[0]);
        b[0].setText("ID");
        b[0].setBounds(0, 0, 100, 15);            
        b[1].setText("creator");
        b[1].setBounds(100, 0, 100, 15);
        b[2].setText("timestamp");
        b[2].setBounds(200, 0, 200, 15);
        b[3].setText("description");
        b[3].setBounds(400, 0, 200, 15);
        b[4].setText("filename");
        b[4].setBounds(600, 0, 100, 15);
        int num= user.showFileList(t3);
        t3.setPreferredSize(new Dimension(700,(num+1)*16));         
	}
	class buttonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("下载")&&!user.getIDs().equals("")){
				f=new JFrame("选择下载地址");
				f.setSize(400,200);
				JPanel address=new JPanel();
				f.add(address);
				JButton dis=new JButton("确定");
				JButton qus=new JButton("取消");
				JButton dds=new JButton("选择路径");
				address.setLayout(p1);
				address.add(input);
				address.add(dds);
				address.add(dis);
				address.add(qus);
				dis.addActionListener(new buttonListener());
				qus.addActionListener(new buttonListener());
				dds.addActionListener(new buttonListener());
				GridBagConstraints s= new GridBagConstraints();
				s.gridwidth=1;
				s.weightx = 0;
				s.weighty=0;
				s.ipadx=200;
				s.ipady=8;
				p1.setConstraints(input, s);
				s.insets = new Insets(0, 20, 0,0);
				s.gridwidth=0;
				s.weightx = 0;
				s.weighty=0;
				s.ipadx=0;
				s.ipady=0;
				p1.setConstraints(dds, s);
				s.insets = new Insets(20,50, 0,100);
				s.gridwidth=1;
				s.weightx = 0;
				s.weighty=0;
				s.ipady=0;
				p1.setConstraints(dis, s);
				s.insets = new Insets(20,0, 0, 50);
				s.gridwidth=0;
				s.weightx = 0;
				s.weighty=0;
				p1.setConstraints(qus, s);
				f.setLocationRelativeTo(null);
				f.addWindowListener(new WindowMonitor());    
			    f.setVisible(true);
			}
			else if(e.getActionCommand().equals("下载")&&user.getIDs().equals("")){
				JOptionPane.showMessageDialog(f1,"请选择文件","警告",2);
			}
			else if(e.getSource()==ssd){
				if(user.downloadFile(user.getIDs(),input.getText())){
					f2.dispose();
					f.dispose();
					input.setText("");
				}	
			}
			else if(e.getActionCommand().equals("确定")&&!input.getText().equals("")){
				File er=new File(input.getText());
				if(er.isDirectory()){
					try {
						Doc ert=DataProcessing.searchDoc(user.getIDs());
						File erto =new File(input.getText()+"\\"+ert.getFilename());
						if(erto.exists()){
							f2=new JFrame("文件存在");
							f2.setSize(300, 150);
							JPanel address=new JPanel();
							f2.add(address);
							JLabel wwr=new JLabel("是否覆盖");
							address.setLayout(p1);
							address.add(wwr);
							address.add(ssd);
							address.add(sxd);
							ssd.addActionListener(new buttonListener());
							sxd.addActionListener(new buttonListener());
							GridBagConstraints s= new GridBagConstraints();
							s.gridwidth=0;
							s.weightx = 0;
							s.weighty=0;
							p1.setConstraints(wwr, s);
							s.insets = new Insets(20,0, 0,10);
							s.gridwidth=1;
							s.weightx = 0;
							s.weighty=0;
							p1.setConstraints(ssd, s);
							s.insets = new Insets(20,10, 0,0);
							s.gridwidth=0;
							s.weightx = 0;
							s.weighty=0;
							p1.setConstraints(sxd, s);
							f2.setLocationRelativeTo(null);
							f2.addWindowListener(new WindowMonitor2());    
						    f2.setVisible(true);
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(f1,"出错","警告",2);
					} 
				}
				else if(er.isFile()){
					f2=new JFrame("文件存在");
					f2.setSize(200, 100);
					JPanel address=new JPanel();
					JLabel wwr=new JLabel("是否覆盖");
					address.setLayout(p1);
					address.add(wwr);
					address.add(ssd);
					address.add(sxd);
					ssd.addActionListener(new buttonListener());
					sxd.addActionListener(new buttonListener());
					GridBagConstraints s= new GridBagConstraints();
					s.gridwidth=0;
					s.weightx = 0;
					s.weighty=0;
					p1.setConstraints(wwr, s);
					s.insets = new Insets(0, 20, 0,0);
					s.gridwidth=1;
					s.weightx = 0;
					s.weighty=0;
					p1.setConstraints(ssd, s);
					s.insets = new Insets(20,50, 0,100);
					s.gridwidth=1;
					s.weightx = 0;
					p1.setConstraints(sxd, s);
					f2.setLocationRelativeTo(null);
					f2.addWindowListener(new WindowMonitor2());    
				    f2.setVisible(true);
				}
				else {
					if(user.downloadFile(user.getIDs(),input.getText())){
						f.dispose();
						input.setText("");
					}	
				}
			}
			else if(e.getActionCommand().equals("确定")&&input.getText().equals("")){
				JOptionPane.showMessageDialog(f1,"不能为空","警告",2);
			}
			else if(e.getSource()==sxd){
				f2.dispose();
			}
			else if(e.getActionCommand().equals("取消")){
				f.dispose();
				input.setText("");
			}
			else if(e.getActionCommand().equals("删除")){
				try{
					if(user.getIDs().equals("")){
						JOptionPane.showMessageDialog(f1,"请选择文件","警告",2);
					}
					else{
						t4.removeAll();
						if(DataProcessing.deleteDoc(user.getIDs())){
							JScrollPane jp=new JScrollPane();
					    	t3=new JPanel();
					        t3.setLayout(null);
					        jp.setViewportView(t3);
					        t4.setLayout(p1);
					        t4.add(jp);
					        GridBagConstraints s2= new GridBagConstraints();
							s2.fill=GridBagConstraints.BOTH;
							s2.gridwidth=0;
							s2.weightx = 10;
							s2.weighty=2;
							s2.ipady=50;
							p1.setConstraints(jp, s2);
					        s2.gridwidth=0;
							s2.weightx = 10;
							s2.weighty=2;
							s2.ipadx=350;
							s2.ipady=150;
							p1.setConstraints(t4, s2);
					        JLabel b[]=new JLabel[5];
					        for(int i=0;i<5;i++){
					        	b[i]=new JLabel();
					        	t3.add(b[i]);
					        }
					        t3.add(b[0]);
					        b[0].setText("ID");
					        b[0].setBounds(0, 0, 100, 15);            
					        b[1].setText("creator");
					        b[1].setBounds(100, 0, 100, 15);
					        b[2].setText("timestamp");
					        b[2].setBounds(200, 0, 200, 15);
					        b[3].setText("description");
					        b[3].setBounds(400, 0, 200, 15);
					        b[4].setText("filename");
					        b[4].setBounds(600, 0, 100, 15);
					        int num= user.showFileList(t3);
					        t3.setPreferredSize(new Dimension(700,(num+1)*16));
					        user.setIDs("");
					        f1.setVisible(true);
						}
					}
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null,"出错","警告",2);
				}	
			}
			else if(e.getActionCommand().equals("选择路径")){
				try{
					String path = null;  
					String lookAndFeel=UIManager.getCrossPlatformLookAndFeelClassName();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
					JFileChooser jdir = new JFileChooser();  
					jdir.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 
					jdir.setDialogTitle("请选择路径");  
					if (JFileChooser.APPROVE_OPTION == jdir.showDialog(new JLabel(), "选择")) {//用户点击了确定
						path = jdir.getSelectedFile().getAbsolutePath();//取得路径选择  
						UIManager.setLookAndFeel(lookAndFeel);
					}
					else{
						UIManager.setLookAndFeel(lookAndFeel);
					}
					File pps=new File(path);
					if(pps.isDirectory()){
						String file=""; 
						try{
							Doc uss;
							for(Enumeration<Doc>e1=DataProcessing.getAllDocs();e1.hasMoreElements();){
				    			uss=e1.nextElement();
								if(uss.getID().equals(user.getIDs())){
									file=uss.getFilename();
								}
							}
						}
						catch(Exception w){
							
						}
						input.setText(path+"\\"+file);
					}
					else
						input.setText(path);
				}
				catch(Exception e1){
					
				}
			}
		}				
	}
	class WindowMonitor extends WindowAdapter {
		 public void windowClosing(WindowEvent e) {
		 f.dispose();
		 input.setText("");
		 }
	}
	class WindowMonitor2 extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			f2.dispose();
		}
	}
}
