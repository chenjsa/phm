package com.pms.socket.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class test {

	private byte[] ObjectToByte(Object obj) {  
	      byte[] bytes = null;  
	      try {  
	          // object to bytearray  
	          ByteArrayOutputStream bo = new ByteArrayOutputStream();  
	          ObjectOutputStream oo = new ObjectOutputStream(bo);  
	          oo.writeObject(obj);  
	    
	          bytes = bo.toByteArray();  
	          System.out.println("bytes" + bytes);  
	          bo.close();  
	          oo.close();  
	      } catch (Exception e) {  
	          System.out.println("translation" + e.getMessage());  
	          e.printStackTrace();  
	      }  
	      return bytes;  
	  } 
	  
	  private Object ByteToObject(byte[] bytes) {
	      Object obj = null;
	      try {
	          // bytearray to object
	          ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
	          ObjectInputStream oi = new ObjectInputStream(bi);

	          obj = oi.readObject();
	          bi.close();
	          oi.close();
	      } catch (Exception e) {
	          System.out.println("translation" + e.getMessage());
	          e.printStackTrace();
	      }
	      return obj;
	  }
	  
	public static String strTo16(String s) {
	    String str = "";
	    for (int i = 0; i < s.length(); i++) {
	        int ch = (int) s.charAt(i);
	        String s4 = Integer.toHexString(ch);
	        str = str + s4;
	    }
	    return str;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test t=new test();
		System.out.print(t.strTo16("ABCDEF"));

	}

}
