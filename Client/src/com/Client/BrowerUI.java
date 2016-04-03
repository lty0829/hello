package com.Client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;

import com.Client.OperatorUI.disListener;
import com.sun.org.apache.bcel.internal.generic.NEW;
public class BrowerUI {
	JTextField input;
	JTextField input1;
	JTextField input2;
	Operator user;
	JFrame f;
	GridBagLayout p1=new GridBagLayout();
	JButton cl1=new JButton("确定"); 
	public BrowerUI(User user, JFrame f) {
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
						JOptionPane.showMessageDialog(f,"成功","恭喜",2);
						f.dispose();
						JCombo a=new JCombo(user);
					}
					else{
						JOptionPane.showMessageDialog(f,"出错","警告",2);
					}
				}catch (MyException e1) {
					JOptionPane.showMessageDialog(f,"不能为空","警告",2);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(f,"出错","警告",2);
				}
			}		
		}
	}
	public void er(JPanel t2,String t){
		if(t.substring(0, 1).equals("1")){
			t2.removeAll();
			input=new JTextField();
			JLabel a=new JLabel("输入密码");
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
			JPanel t4=new JPanel();
			FilelistUI q=new FilelistUI(t2, t4,f);
			q.er2();
		}
		else{
	        t2.removeAll();		                    	
	    }
	}
}
