package com.Client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;


public class OperatorUI {
	JTextField input;
	JTextField input1;
	JTextField input2;
	Operator user;
	JFrame f;
	GridBagLayout p1=new GridBagLayout();
	JButton cl1=new JButton("ȷ��"); 
	JButton cl2=new JButton("ȷ��");
	public OperatorUI(User user, JFrame f) {
		super();
		this.user = new Operator(user.getName(),user.getPassword(),user.getRole());
		this.f = f;
	}
	class disListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==cl1){
				putinpassword usex=new putinpassword();
				try {
					usex.blank(input.getText(),"wewwe");
					if(user.changeSelfInfo(input.getText())){
						JOptionPane.showMessageDialog(f,"�ɹ�","��ϲ",2);
						f.dispose();
						JCombo a=new JCombo(user);
					}
					else{
						JOptionPane.showMessageDialog(f,"����","����",2);
					}
				}catch (MyException e1) {
					JOptionPane.showMessageDialog(f,"����Ϊ��","����",2);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(f,"����","����",2);
				}
		}		
			if(e.getSource()==cl2){
				putinpassword usex=new putinpassword();
				try {
					usex.blank(input.getText(),input1.getText());
					usex.blank(input2.getText(),"222");
					if(user.uploadFile(input.getText(),input1.getText(),input2.getText())){
						JOptionPane.showMessageDialog(f,"�ɹ�","��ϲ",2);
						f.dispose();
						JCombo a=new JCombo(user);
					}
					else{
						JOptionPane.showMessageDialog(f,"ID����","����",2);
					}
				}catch (MyException e1) {
					JOptionPane.showMessageDialog(f,"�����п�","����",2);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(f,"����","����",2);
				}
			}
			else if(e.getActionCommand().equals("ѡ��·��")){
				try{
					String path = null;  
					String lookAndFeel=UIManager.getCrossPlatformLookAndFeelClassName();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
					JFileChooser jdir = new JFileChooser();  
					jdir.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 
					jdir.setDialogTitle("��ѡ��·��");  
					if (JFileChooser.APPROVE_OPTION == jdir.showDialog(new JLabel(), "ѡ��")) {//�û������ȷ��
						path = jdir.getSelectedFile().getAbsolutePath();//ȡ��·��ѡ��  
						UIManager.setLookAndFeel(lookAndFeel);
					}
					else{
						UIManager.setLookAndFeel(lookAndFeel);
					}
					input2.setText(path);
				}
				catch(Exception e1){
					
				}
			}
		}
	}
	public void er(JPanel t2,String t){
		if(t.substring(0, 1).equals("1")){
			t2.removeAll();
			input=new JTextField();
			JLabel a=new JLabel("��������");
			Font font=new Font("Serief",Font.BOLD,15);
			a.setFont(font);
	    	t2.setLayout(p1); 
	    	t2.add(a);
	    	t2.add(input);
	    	t2.add(cl1);
	    	cl1.addActionListener(new disListener());
	    	GridBagConstraints s2= new GridBagConstraints();
	    	s2.gridwidth=0;
			s2.weightx = 0;
			s2.weighty=1;
			p1.setConstraints(a, s2);
			s2.gridwidth=0;
			s2.weightx = 100;
			s2.weighty=1;
			s2.ipadx=200;
			p1.setConstraints(input, s2);
			s2.insets = new Insets(20, 0, 0, 0);
			s2.gridwidth=0;
			s2.weightx = 0;
			s2.weighty=20;
			s2.ipadx=0;
			p1.setConstraints(cl1, s2);
		}
		else if(t.substring(0, 1).equals("2")){
			t2.removeAll();
			input=new JTextField();
			input1=new JTextField();
			input2=new JTextField();
			//input.setSize(100,100);
			JLabel a=new JLabel("����ID");
			JLabel b=new JLabel("�����ļ�����");
			JLabel c=new JLabel("�����ϴ��ļ�·��");
			JButton dds=new JButton("ѡ��·��");
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
	    	t2.add(dds);
	    	t2.add(cl2);
	    	dds.addActionListener(new disListener());
	    	cl2.addActionListener(new disListener());
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
			s2.insets = new Insets(0, 85, 0,0);
			s2.gridwidth=1;
			s2.weightx = 1;
			s2.weighty=1;
			s2.ipadx=200;
			p1.setConstraints(input2, s2);
			s2.insets = new Insets(0, 0, 0,0);
			s2.gridwidth=0;
			s2.weightx = 0;
			s2.weighty=0;
			s2.ipadx=0;
			s2.ipady=0;
			p1.setConstraints(dds, s2);
			s2.insets = new Insets(20, 0, 0, 0);
			s2.gridwidth=0;
			s2.weightx = 1;
			s2.weighty=20;
			s2.ipadx=0;
			p1.setConstraints(cl2, s2);
		}
		else if(t.substring(0, 1).equals("3")){
			JPanel t4=new JPanel();
			FilelistUI q=new FilelistUI(t2, t4,f);
			q.er2();
		}
		else{
	        t2.removeAll();		                    	
	    }
	}
}
