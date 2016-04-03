package com.Client;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.sql.SQLException;
public class ClientUI extends JFrame{
	JTextField input=new JTextField();
	JPasswordField input1=new JPasswordField();
	JFrame f=new JFrame("登入界面");
	int num=0;
	class WindowMonitor extends WindowAdapter {
		 public void windowClosing(WindowEvent e) {
		  System.exit(0);
		 }
		}
	class Textlistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			 //TODO Auto-generated method stub
			if(e.getActionCommand().equals("登陆")){
				putinpassword usex=new putinpassword();
				try {
						usex.blank(input.getText(), input1.getText());
						User user=DataProcessing.searchUser(input.getText(), input1.getText());
						if(user!=null){
							f.dispose();
							JCombo a=new JCombo(user);
							//user.showMenu();
						}
						else{
							JOptionPane.showMessageDialog(f,"账号或密码出错","警告",2);
							num=usex.putpassword(num);
						}
				} 
				catch (MyException e1) {
					num=0;
					JOptionPane.showMessageDialog(f,e1.getMessage(),"警告",2);
				} catch (IllegalStateException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(f,e1.getMessage(),"警告",2);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(f,e1.getMessage(),"警告",2);
				}
			}
		}
	}
	public void lay(){
		GridBagLayout p1=new GridBagLayout();
		f.setSize(400,390);
		f.setLocationRelativeTo(null);
		JLabel a=new JLabel("输入账号");
		JLabel b=new JLabel("输入密码");
		Font font=new Font("Serief",Font.BOLD,15);
		a.setFont(font);
		b.setFont(font);
		JPanel PNum=new JPanel();
		f.add(PNum,"Center");	
		JButton button1=new JButton("登陆");
		JButton button2=new JButton("取消");
		PNum.setLayout(p1);
		PNum.add(a);
		PNum.add(input);
		PNum.add(b);
		PNum.add(input1);
		PNum.add(button1);
		PNum.add(button2);
		input.addActionListener(new Textlistener());
		input1.addActionListener(new Textlistener());
		button1.addActionListener(new Textlistener());
		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
			}); 
		GridBagConstraints s= new GridBagConstraints();
		s.insets = new Insets(4, 8, 8, 8);
		s.gridwidth=0;
		s.weightx = 0;
		s.weighty=0;
		p1.setConstraints(a, s);
		s.insets = new Insets(8, 8, 8, 8);
		s.gridwidth=0;
		s.weightx = 0;
		s.weighty=0;
		s.ipadx=200;
		p1.setConstraints(input, s);
		s.insets = new Insets(0, 8, 8, 8);
		s.gridwidth=0;
		s.weightx = 0;
		s.weighty=0;
		s.ipadx=0;
		p1.setConstraints(b, s);
		s.insets = new Insets(8, 8, 8, 8);
		s.gridwidth=0;
		s.weightx = 0;
		s.weighty=0;
		s.ipadx=200;
		p1.setConstraints(input1, s);
		/*s.insets = new Insets(8, 8, 8, 8);
		s.gridwidth=3;
		s.weightx = 0;
		s.weighty=0;
		p1.setConstraints(bai, s);*/
		s.insets = new Insets(0, 35, 0, 0);
		s.gridwidth=1;
		s.weightx = 0;
		s.weighty=0;
		s.ipadx=0;
		p1.setConstraints(button1, s);
		s.insets = new Insets(0, 0, 0, 0);
		s.gridwidth=0;
		s.weightx = 0;
		s.weighty=0;
		p1.setConstraints(button2, s);
		f.addWindowListener(new WindowMonitor()); 
		f.setVisible(true);
	}
	public static void main(String args[]){
		ClientUI new1=new ClientUI();
		new1.lay();
	}
}

