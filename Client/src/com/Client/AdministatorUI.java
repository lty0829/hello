package com.Client;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;

import com.Client.User.WindowMonitor;

public class AdministatorUI {
	JTextField input;
	JTextField input1;
	JComboBox input2;
	Administrator user;
	JFrame f;
	JFrame f1;
	String a1="";	
	GridBagLayout p1=new GridBagLayout();
	JButton cl1=new JButton("确定"); 
	JButton cl3=new JButton("确定"); 
	JPanel t2;
	JPanel t3;
	JPanel t4;
	public AdministatorUI(Administrator user, JPanel t2) {
		super();
		this.user = user;
		this.t3 = t2;
	}
	public AdministatorUI(Administrator user, JPanel t2,JFrame f1) {
		super();
		this.user = user;
		this.t3 = t2;
		this.f1=f1;
	}
	class WindowMonitor extends WindowAdapter {
		 public void windowClosing(WindowEvent e) {
		 f.dispose();
		 }
	}
	class disListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==cl1){
				putinpassword usex=new putinpassword();
				try {
					boolean yes;
					usex.blank(a1, input1.getText());
					String s=(String)input2.getSelectedItem();
					if(yes=!user.changeUserInfo(a1,input1.getText(),s)){
						JOptionPane.showMessageDialog(f,"出错","警告",2);
					}
					else if(!yes){
						JOptionPane.showMessageDialog(f,"成功","恭喜",2);
						t3.removeAll();
						JScrollPane jp=new JScrollPane();
			        	t4=new JPanel();
			            t4.setLayout(null);
			            jp.setViewportView(t4);
			            t3.setLayout(p1);
			            t3.add(jp);
			            GridBagConstraints s2= new GridBagConstraints();
			    		s2.fill=GridBagConstraints.BOTH;
			    		s2.gridwidth=0;
						s2.weightx = 10;
						s2.weighty=2;
						//s2.ipadx=150;
						s2.ipady=50;
			    		p1.setConstraints(jp, s2);
			    		JLabel b[]=new JLabel[3];
				        for(int i=0;i<3;i++){
				        	b[i]=new JLabel();
				            t4.add(b[i]);
				        }
				        t4.add(b[0]);
				        b[0].setText("Name");
				        b[0].setBounds(0, 0, 120, 15);            
				        b[1].setText("Password");
				        b[1].setBounds(120, 0, 120, 15);
				        b[2].setText("Role");
				        b[2].setBounds(240, 0, 120, 15);
				        int num=user.ListUser(t4);
				        t4.setPreferredSize(new Dimension(300,(num+1)*16));
				        user.setIDs("");
			            f1.setVisible(true);
						f.dispose();
					}
				} catch (MyException e1) {
					JOptionPane.showMessageDialog(f,e1.getMessage(),"警告",2);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(f,"出错","警告",2);
				}
			}

			else if(e.getSource()==cl3){
				putinpassword usex=new putinpassword();
				try {
					usex.blank(input.getText(), input1.getText());
					String s=(String)input2.getSelectedItem();
					if(user.addUser(input.getText(),input1.getText(),s)){
						JOptionPane.showMessageDialog(f,"成功","恭喜",2);
						t3.removeAll();
						JScrollPane jp=new JScrollPane();
			        	t4=new JPanel();
			            t4.setLayout(null);
			            jp.setViewportView(t4);
			            t3.setLayout(p1);
			            t3.add(jp);
			            GridBagConstraints s2= new GridBagConstraints();
			    		s2.fill=GridBagConstraints.BOTH;
			    		s2.gridwidth=0;
						s2.weightx = 10;
						s2.weighty=2;
						//s2.ipadx=150;
						s2.ipady=50;
			    		p1.setConstraints(jp, s2);
			    		JLabel b[]=new JLabel[3];
				        for(int i=0;i<3;i++){
				        	b[i]=new JLabel();
				            t4.add(b[i]);
				        }
				        t4.add(b[0]);
				        b[0].setText("Name");
				        b[0].setBounds(0, 0, 120, 15);            
				        b[1].setText("Password");
				        b[1].setBounds(120, 0, 120, 15);
				        b[2].setText("Role");
				        b[2].setBounds(240, 0, 120, 15);
				        int num=user.ListUser(t4);
				        t4.setPreferredSize(new Dimension(300,(num+1)*16));
				        user.setIDs("");
			            f1.setVisible(true);
						f.dispose();
					}
				} catch (MyException e1) {
					JOptionPane.showMessageDialog(f,e1.getMessage(),"警告",2);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(f,"出错","警告",2);
				}
			}		
		}	
	}
	public void er1(String t,String name) {
		if(t.equals("1")){
			String password = "";
			String role="";
			User uss;
			try{
				for(Enumeration<User>e=DataProcessing.getAllUser();e.hasMoreElements();){
	    			uss=e.nextElement();
					if(uss.getName().equals(name)){
						password=uss.getPassword();
						role=uss.getRole();
					}
				}
			}
			catch(Exception e){
			}
			a1=name;
			f=new JFrame("修改用户");
			f.setSize(400,300);
			f.setLocationRelativeTo(null); 
        	t2=new JPanel();
        	f.add(t2);
			input1=new JTextField();
			input2=new JComboBox();
			input2.addItem("browser");
			input2.addItem("operator");
			input2.addItem("administrator");
			int i=0;
			if(role.equals("browser")){
				i=0;
			}
			else if(role.equals("operator")){
				i=1;
			}
			else if(role.equals("administrator")){
				i=2;
			}
			input1.setText(password);
			input2.setSelectedIndex(i);
			if(user.getName().equals(name)){
				input2.setEnabled(false);
			}
			JLabel b=new JLabel("输入密码");
			JLabel c=new JLabel("选择权限");
			Font font=new Font("Serief",Font.BOLD,15);
			b.setFont(font);
			c.setFont(font);
	    	t2.setLayout(p1); 
	    	t2.add(b);
	    	t2.add(input1);
	    	t2.add(c);
	    	t2.add(input2);
	    	t2.add(cl1);
	    	cl1.addActionListener(new disListener());
	    	GridBagConstraints s2= new GridBagConstraints();
			s2.gridwidth=0;
			s2.weightx = 1;
			s2.weighty=1;
			s2.ipadx=0;
			p1.setConstraints(b, s2);
			s2.gridwidth=0;
			s2.weightx =5;
			s2.weighty=1;
			s2.ipadx=200;
			p1.setConstraints(input1, s2);
			s2.gridwidth=0;
			s2.weightx = 1;
			s2.weighty=1;
			s2.ipadx=0;
			p1.setConstraints(c, s2);
			s2.gridwidth=0;
			s2.weightx = 1;
			s2.weighty=1;
			p1.setConstraints(input2, s2);
			s2.insets = new Insets(20, 0, 0, 0);
			s2.gridwidth=0;
			s2.weightx = 1;
			s2.weighty=20;
			p1.setConstraints(cl1, s2);
			f.addWindowListener(new WindowMonitor());
			f.setVisible(true);
        }
        else if(t.equals("2")){
        	f=new JFrame("增加用户");
        	f.setSize(400,300);
        	f.setLocationRelativeTo(null); 
        	t2=new JPanel();
        	f.add(t2);
        	input=new JTextField();
        	input1=new JTextField();
        	input2=new JComboBox();
    		input2.addItem("browser");
			input2.addItem("operator");
			input2.addItem("administrator");
			//input.setSize(100,100);
			JLabel a=new JLabel("输入账号");
			JLabel b=new JLabel("输入密码");
			JLabel c=new JLabel("选择权限");
			Font font=new Font("Serief",Font.BOLD,15);
			a.setFont(font);
			b.setFont(font);
			c.setFont(font);
	    	t2.setLayout(p1); 
	    	t2.add(a);
	    	t2.add(input);
	    	t2.add(b);
	    	t2.add(input1);
	    	t2.add(c);
	    	t2.add(input2);
	    	t2.add(cl3);
	    	cl3.addActionListener(new disListener());
	    	GridBagConstraints s2= new GridBagConstraints();
	    	//s2.fill = GridBagConstraints.BOTH;
	    	s2.gridwidth=0;
			s2.weightx = 0;
			s2.weighty=1;
			p1.setConstraints(a, s2);
			s2.gridwidth=0;
			s2.weightx = 100;
			s2.weighty=1;
			s2.ipadx=200;
			p1.setConstraints(input, s2);
			s2.gridwidth=0;
			s2.weightx = 1;
			s2.weighty=1;
			s2.ipadx=0;
			p1.setConstraints(b, s2);
			s2.gridwidth=0;
			s2.weightx =5;
			s2.weighty=1;
			s2.ipadx=200;
			p1.setConstraints(input1, s2);
			s2.gridwidth=0;
			s2.weightx = 1;
			s2.weighty=1;
			s2.ipadx=0;
			p1.setConstraints(c, s2);
			s2.gridwidth=0;
			s2.weightx = 1;
			s2.weighty=1;
			p1.setConstraints(input2, s2);
			s2.insets = new Insets(20, 0, 0, 0);
			s2.gridwidth=0;
			s2.weightx = 1;
			s2.weighty=20;
			p1.setConstraints(cl3, s2);
			f.addWindowListener(new WindowMonitor());
			f.setVisible(true);
        }
      
	}
}
