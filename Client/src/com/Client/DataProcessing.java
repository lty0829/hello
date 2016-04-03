package com.Client;
import java.util.*;
import com.Client.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.sql.*;
import java.io.*;
import java.net.Socket;


public  class DataProcessing extends Socket{
	private static Connection con;
	private static Statement st;
	private static ResultSet rs;
//	private int numberofRows;
	private static boolean connectToDB=false;
	
	private static final String SERVER_IP ="127.0.0.1";
	private static final int SERVER_PORT =9100;

	private static Socket client;
	private static FileInputStream fis;
	private static DataOutputStream dos;
	private static DataInputStream dis;
	
	
	public static Doc searchDoc(String ID) throws SQLException,IllegalStateException {
		
			try {
			client =new Socket(SERVER_IP, SERVER_PORT);
			dos =new DataOutputStream(client.getOutputStream());
			dos.writeInt(2);
			dos.flush();
			dos.writeUTF(ID);
			dos.flush();
			dis =new DataInputStream(client.getInputStream());
			String uncreator= dis.readUTF();
			String time= dis.readUTF();
			String descriptions= dis.readUTF();
			String fileunname= dis.readUTF();
			Doc we=new Doc(ID,uncreator,Timestamp.valueOf(time),descriptions,fileunname);
			return we;
			}catch (Exception e) {
			e.printStackTrace();
			return null;
			}
	}
	
	public static Enumeration<Doc> getAllDocs() throws SQLException,IllegalStateException{		
		/*rs = st.executeQuery("SELECT * FROM docs");
		return rs;*/
		//Hashtable<String, Doc> docs = null;
		Hashtable<String, Doc> docs = null;
		docs = new Hashtable<String,Doc>();
		try {
			try {
			client =new Socket(SERVER_IP, SERVER_PORT);
			dos =new DataOutputStream(client.getOutputStream());
			dos.writeInt(3);
			dos.flush();
			String IDe="";
			dis =new DataInputStream(client.getInputStream());
			while(!(IDe=dis.readUTF()).equals("close")){
				Doc eex=new Doc(IDe,dis.readUTF(),Timestamp.valueOf(dis.readUTF()),dis.readUTF(),dis.readUTF());
				docs.put(IDe,eex);
			}
			}catch (Exception e) {
			e.printStackTrace();
			}finally{
			if(fis !=null)
			fis.close();
			if(dos !=null)
			dos.close();
			client.close();
			            }
			}catch (Exception e) {
			e.printStackTrace();
			        }
			Enumeration<Doc> e  = docs.elements();
			return e;
	} 
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException,IllegalStateException{
		try {
			try {
			client =new Socket(SERVER_IP, SERVER_PORT);
			                //向服务端传送文件
			File file =new File(filename);
			fis =new FileInputStream(file);
			dos =new DataOutputStream(client.getOutputStream());

			                //文件名和长度
			dos.writeInt(1);
			dos.flush();
			String unname=file.getName();
			byte[] ch=unname.getBytes();
			dos.writeInt(ch.length);
			dos.flush();
			for(int i=0;i<ch.length;i++){
				dos.writeByte(ch[i]);
				dos.flush();
			}	
			dos.writeUTF(ID);
			dos.flush();
			dos.writeUTF(creator);
			dos.flush();
			dos.writeUTF(description);
			dos.flush();
			dos.writeLong(file.length());
			dos.flush();
			dis =new DataInputStream(client.getInputStream());
			if(dis.readUTF().equals("exsit")){
				return false;
			}
			byte[] sendBytes =new byte[1024];
			int length =0;
			while((length = fis.read(sendBytes,0, sendBytes.length)) >0){
			dos.write(sendBytes,0, length);
			dos.flush();
			                }
			}catch (Exception e) {
			e.printStackTrace();
			}finally{
			if(fis !=null)
			fis.close();
			if(dos !=null)
			dos.close();
			client.close();
			            }
			}catch (Exception e) {
			e.printStackTrace();
			        }
			return true;
		
		
		/*rs = st.executeQuery("SELECT * FROM docs");
		while(rs.next()){
			if(rs.getString("IDs").equals(ID))
				return false;
		}
		int i = st.executeUpdate("insert into docs (IDs,uncreator,times,descriptions,fileunname) values ('"+ID+"','"+creator+"','"+timestamp+"','"+description+"','"+filename+"')");
		if(i==1)
			return true;
		else
			return false;*/
	} 
	public static boolean deleteDoc(String ID) throws SQLException,IllegalStateException{
		try {
			try {
			client =new Socket(SERVER_IP, SERVER_PORT);
			dos =new DataOutputStream(client.getOutputStream());
			dos.writeInt(4);
			dos.flush();
			byte[] ch = ID.getBytes();
			dos.writeUTF(ID);
			dos.flush();
			}catch (Exception e) {
			e.printStackTrace();
			}finally{
			if(fis !=null)
			fis.close();
			if(dos !=null)
			dos.close();
			client.close();
			            }
			}catch (Exception e) {
			e.printStackTrace();
			        }
			return true;
		
	}
	
	public static User searchUser(String name, String password) throws SQLException,IllegalStateException {
//		if ( !connectToDB ) 
//	        throw new IllegalStateException( "Not Connected to Database" );
//
//		
//		if (Math.random()>0.7)
//			throw new SQLException( "Error in excecuting Query" );	
	
		/*rs = st.executeQuery("SELECT * FROM users WHERE unname = '"+name+"' AND pds = '"+password+"'");
		if(rs.next()){
			User we=new User();
			if(rs.getInt("role")==1)
				we=new Administrator(rs.getString("unname"),rs.getString("pds"),"administrator");
			else if(rs.getInt("role")==2)
				we=new Operator(rs.getString("unname"),rs.getString("pds"),"operator");
			else if(rs.getInt("role")==3)
				we=new Browser(rs.getString("unname"),rs.getString("pds"),"browser");
			return we;  
		}
		else{
			disconnectFromDB();
			return null;
		}*/
		User we=new User();
		try {
			try {
			client =new Socket(SERVER_IP, SERVER_PORT);
			dos =new DataOutputStream(client.getOutputStream());

			dos.writeInt(9);
			dos.flush();
			dos.writeUTF(name);
			dos.flush();
			dos.writeUTF(password);
			dos.flush();
			dis =new DataInputStream(client.getInputStream());
			int i=dis.readInt();
			if(i==1)
				we=new Administrator(name,password,"administrator");
			else if(i==2)
				we=new Operator(name,password,"operator");
			else if(i==3)
				we=new Browser(name,password,"browser");
			else if(i==4)
				return null;
			}catch (Exception e) {
			     return null;
			}finally{
			if(fis !=null)
			fis.close();
			if(dos !=null)
			dos.close();
			client.close();
			            }
			}catch (Exception e) {
			e.printStackTrace();
			        }
		
			return we;
		
	}
	
	public static Enumeration<User> getAllUser() throws SQLException,IllegalStateException{
//		if ( !connectToDB ) 
//	        throw new IllegalStateException( "Not Connected to Database" );
//		
//		if (Math.random()>0.7)
//			throw new SQLException( "Error in excecuting Query" );
		Hashtable<String, User> users=null;
		users = new Hashtable<String, User>();
		try {
			try {
			client =new Socket(SERVER_IP, SERVER_PORT);
			dos =new DataOutputStream(client.getOutputStream());
			dos.writeInt(7);
			dos.flush();
			String namee="";
			dis =new DataInputStream(client.getInputStream());
			while(!(namee=dis.readUTF()).equals("close")){
				User eex=new User(namee,dis.readUTF(),dis.readUTF());
				users.put(namee,eex);
			}
			}catch (Exception e) {
			e.printStackTrace();
			}finally{
			if(fis !=null)
			fis.close();
			if(dos !=null)
			dos.close();
			client.close();
			            }
			}catch (Exception e) {
			e.printStackTrace();
			        }
		Enumeration<User> e  = users.elements();
		return e;
	}
	
	
	
	public static boolean updateUser(String name, String password, String role) throws SQLException,IllegalStateException{
		User user;
//		if ( !connectToDB ) 
//	        throw new IllegalStateException( "Not Connected to Database" );
//		
//		if (Math.random()>0.7)
//			throw new SQLException( "Error in excecuting Update" );
	/*	int t=0;
		if(role.equals("administrator"))
			t=1;
		else if(role.equals("operator"))
			t=2;
		else if(role.equals("browser"))
			t=3;		
		int i = st.executeUpdate("update users set pds = '"+password+"' WHERE unname ='"+name+"'"); 
		int k = st.executeUpdate("update users set role = "+t+" WHERE unname ='"+name+"'"); 
		if(i==1&&k==1){
			return true;
		}
		else {
			return false;
		}*/
		
		try {
			try {
			client =new Socket(SERVER_IP, SERVER_PORT);
			dos =new DataOutputStream(client.getOutputStream());
			dos.writeInt(6);
			dos.flush();
			dos.writeUTF(name);
			dos.flush();
			dos.writeUTF(password);
			dos.flush();
			dos.writeUTF(role);
			dos.flush();		
			dis =new DataInputStream(client.getInputStream());
			}catch (Exception e) {
			e.printStackTrace();
			}finally{
			if(fis !=null)
			fis.close();
			if(dos !=null)
			dos.close();
			client.close();
			            }
			}catch (Exception e) {
			e.printStackTrace();
			        }
		return true;
	}
	
	public static boolean insertUser(String name, String password, String role) throws SQLException,IllegalStateException{
//		if ( !connectToDB ) 
//	        throw new IllegalStateException( "Not Connected to Database" );
//		
//		if (Math.random()>0.7)
//			throw new SQLException( "Error in excecuting Insert" );
	/*	int t=0;
		rs = st.executeQuery("SELECT * FROM users");
		while(rs.next()){
			if(rs.getString("unname").equals(name))
				return false;
		}
		if(role.equals("administrator"))
			t=1;
		else if(role.equals("operator"))
			t=2;
		else if(role.equals("browser"))
			t=3;	
		int i = st.executeUpdate("insert into users (unname,pds,role) values ('"+name+"','"+password+"',"+t+")");
		if(i==1)
			return true;
		else
			return false;*/
		
		
		try {
			try {
			client =new Socket(SERVER_IP, SERVER_PORT);
			dos =new DataOutputStream(client.getOutputStream());
			dos.writeInt(5);
			dos.flush();
			dos.writeUTF(name);
			dos.flush();
			dos.writeUTF(password);
			dos.flush();
			dos.writeUTF(role);
			dos.flush();		
			dis =new DataInputStream(client.getInputStream());
			if(dis.readUTF().equals("exsit")){
				return false;
			}
			}catch (Exception e) {
			e.printStackTrace();
			}finally{
			if(fis !=null)
			fis.close();
			if(dos !=null)
			dos.close();
			client.close();
			            }
			}catch (Exception e) {
			e.printStackTrace();
			        }
		return true;
	}
	
	public static boolean deleteUser(String name) throws SQLException,IllegalStateException{
//		if ( !connectToDB ) 
//	        throw new IllegalStateException( "Not Connected to Database" );
//		
//		if (Math.random()>0.7)
//			throw new SQLException( "Error in excecuting Delete" );
	/*	int i = st.executeUpdate("delete from users where unname = '"+name+"'");
		if(i==1)
			return true;
		else
			return false;*/
		
		try {
			try {
			client =new Socket(SERVER_IP, SERVER_PORT);
			dos =new DataOutputStream(client.getOutputStream());
			dos.writeInt(8);
			dos.flush();
			dos.writeUTF(name);
			dos.flush();
			}catch (Exception e) {
			e.printStackTrace();
			}finally{
			if(fis !=null)
			fis.close();
			if(dos !=null)
			dos.close();
			client.close();
			            }
			}catch (Exception e) {
			e.printStackTrace();
			        }
		return true;
	}	
            
	public static void disconnectFromDB() {
		if ( connectToDB ){
			// close Statement and Connection            
			try{
				rs.close();                        
			    st.close();                        
			    con.close();                       
			}catch ( SQLException sqlException ){                                            
			    sqlException.printStackTrace();           
			}finally{                                            
				connectToDB = false;              
			}                             
		} 
   }           

	
	
}

