package com.Client;
import java.util.*;
public class MyException extends Exception{
	public MyException(){
		super();
	}
	public MyException(String msg){
		super(msg);
	}
}

class putinpassword{
	public int putpassword(int num) throws MyException {
			num++;
			if(num>=10){
				num=0;
				throw new MyException("登入超过10次");
			}
			return num;
		//}		
	}
	public void blank(String a,String b) throws MyException{
		if(a.equals("")||b.equals("")){
			throw new MyException("账号或密码不能为空");
		}
	}
}

