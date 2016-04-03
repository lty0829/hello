package com.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import javax.swing.*;

public class JCombo extends JFrame /*implements ItemListener*/ {  
	  JFrame f;
	  //JComboBox jb;
	  Administrator play1;
	  User play=new User();
	  JPanel t1=new JPanel();
	  JPanel t4=new JPanel();
	  JPanel t3;
	  JPanel t2;
	  JButton ba1;
	  JButton ba2;
	  JButton ba3;
	  GridBagLayout p1=new GridBagLayout();
	  JButton cl1;
	  JButton cl3;
	  public JCombo(User user)
	  {
		  t2=new JPanel();
		  play=user;
		  f=new JFrame(user.getRole());
		  f.setSize(400, 390);
		  f.setLocationRelativeTo(null); 
	    JButton cl=new JButton("退出"); 
	    play.showMenu();
	    t1.setLayout(p1);
	    t1.add(play.getJb());
	    t1.add(t2);
	    t1.add(cl);
	    play.getJb().addItemListener(new itemListener());
	    GridBagConstraints s= new GridBagConstraints();
	    s.insets = new Insets(0, 0, 20, 0);
		s.gridwidth=0;
		s.weightx = 0;
		s.weighty=1;
		p1.setConstraints(play.getJb(), s);
		//s.fill = GridBagConstraints.BOTH;
		 s.insets = new Insets(0, 0, 0, 0);
		s.gridwidth=0;
		s.weightx = 4;
		s.weighty=50;
		p1.setConstraints(t2, s);
		// s.fill = GridBagConstraints.NONE;
		s.insets = new Insets(20, 0, 0, 0);
		s.gridwidth=0;
		s.weightx = 0;
		s.weighty=1;
		s.ipadx=100;
		p1.setConstraints(cl, s);
	    f.add(t1);  	    
	    cl.addActionListener(new ButtonListener());
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	    f.setVisible(true);  
	 }  
	 public class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			f.dispose();
			ClientUI new1=new ClientUI();
			new1.lay();
		}		 
	 }
		public class itemListener implements ItemListener{
					@Override
				public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange() == ItemEvent.SELECTED){	
							 String s=(String)play.getJb().getSelectedItem();
							 if(play.getRole().equals("administrator")){ 
								// play1= new Administrator(play.getName(),play.getPassword(),play.getRole());
								 play1=(Administrator) play;
								 if(s.substring(0, 1).equals("1")){
							        	t2.removeAll();
							        	t4.removeAll();
										ba1=new JButton("填加用户");
										ba2=new JButton("删除用户");
										ba3=new JButton("修改用户");
										JScrollPane jp=new JScrollPane();
							        	t3=new JPanel();
							            t3.setLayout(null);
							            jp.setViewportView(t3);
							            t2.setLayout(p1);
							            t4.setLayout(p1);
							            t4.add(jp);
							            t2.add(t4);
							            t2.add(ba1);
							            t2.add(ba2);
							            t2.add(ba3);
							            ba1.addActionListener(new buttonListener1());
							    		ba2.addActionListener(new buttonListener1());
							    		ba3.addActionListener(new buttonListener1());
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
										s2.insets = new Insets(10, 30, 0, 10);
										s2.gridwidth=1;
										s2.weightx = 0;
										s2.weighty=0;
										s2.ipadx=0;
										s2.ipady=0;
										p1.setConstraints(ba1, s2);
										s2.insets = new Insets(10, 20, 0, 20);
										s2.gridwidth=1;
										s2.weightx = 0;
										s2.weighty=0;
										s2.ipadx=0;
										s2.ipady=0;
										p1.setConstraints(ba2, s2);
										s2.insets = new Insets(10, 10, 0, 30);
										s2.gridwidth=0;
										s2.weightx = 0;
										s2.weighty=0;
										p1.setConstraints(ba3, s2);
							            JLabel b[]=new JLabel[3];
							            for(int i=0;i<3;i++){
							            	b[i]=new JLabel();
							            	t3.add(b[i]);
							            }
							            t3.add(b[0]);
							            b[0].setText("Name");
							            b[0].setBounds(0, 0, 120, 15);            
							            b[1].setText("Password");
							            b[1].setBounds(120, 0, 120, 15);
							            b[2].setText("Role");
							            b[2].setBounds(240, 0, 120, 15);
							            int num=play1.ListUser(t3);
							            t3.setPreferredSize(new Dimension(300,(num+1)*16));
			                    	f.setVisible(true); 
								 }
								 else if(s.substring(0, 1).equals("2")){
									 //AdministatorUI t=new AdministatorUI(play1,t2);
									 FilelistUI t=new FilelistUI(t2,t4,f);
									 t.er2();     
									 f.setVisible(true);
								 }
								 else {
									 t2.removeAll();
									 f.setVisible(true);
								 }
							 }
							 else if(play.getRole().equals("operator")){
								 OperatorUI t=new OperatorUI(play,f);
			                    	t.er(t2,s);         	
			                    	f.setVisible(true); 
							 }
							 else if(play.getRole().equals("browser")){
								 BrowerUI t=new BrowerUI(play,f);
			                    	t.er(t2,s);         	
			                    	f.setVisible(true); 
							 }       
					}
		} 
	}
		class buttonListener1 implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("填加用户")){
					AdministatorUI t=new AdministatorUI(play1,t4,f);
					t.er1("2","");
				}
				else if(play1.getIDs().equals("")){
					JOptionPane.showMessageDialog(null,"请选择用户","警告",2);
				}
				else if(e.getActionCommand().equals("删除用户")){
					t4.removeAll();
					play1.delUser(play1.getIDs());
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
					//s2.ipadx=150;
					s2.ipady=50;
		    		p1.setConstraints(jp, s2);
		    		JLabel b[]=new JLabel[3];
			        for(int i=0;i<3;i++){
			        	b[i]=new JLabel();
			            t3.add(b[i]);
			        }
			        t3.add(b[0]);
			        b[0].setText("Name");
			        b[0].setBounds(0, 0, 120, 15);            
			        b[1].setText("Password");
			        b[1].setBounds(120, 0, 120, 15);
			        b[2].setText("Role");
			        b[2].setBounds(240, 0, 120, 15);
			        int num=play1.ListUser(t3);
			        t3.setPreferredSize(new Dimension(300,(num+1)*16));
			        play1.setIDs("");
		            f.setVisible(true);
				}
				else if(e.getActionCommand().equals("修改用户")){
					AdministatorUI t=new AdministatorUI(play1,t4,f);
					t.er1("1",play1.getIDs());
				}
			}	
		}
}
	