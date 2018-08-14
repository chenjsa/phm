package com.pms.socket.client;
 

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Base64;

//程序清单：codes\17\17.4\UdpClient.java

public class UdpClient  
{  
  // 定义发送数据报的目的地  
  public static final int DEST_PORT = 5678;  
  public static final String DEST_IP = "127.0.0.1";  
  // 定义每个数据报的最大大小为4KB  
  private static final int DATA_LEN = 4096;     
  // 定义接收网络数据的字节数组  
  byte[] inBuff = new byte[DATA_LEN];  
  // 以指定的字节数组创建准备接收数据的DatagramPacket对象  
  private DatagramPacket inPacket =   
      new DatagramPacket(inBuff , inBuff.length);  
  // 定义一个用于发送的DatagramPacket对象  
  private DatagramPacket outPacket = null;   
  private byte[] buf = new byte[6]; 
  PacketMsg pkt = new PacketMsg(buf);
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
  
  public void init()throws IOException  
  {  
      try  
      {  
    	  pkt.seq = (short) (pkt.seq+1);
    	  pkt.cmd = (byte)(3);
    	  pkt.code= (byte)(4);    	      	
    	  pkt.data=ObjectToByte((byte)2);
    	
    	  pkt.len =1;///(short) (pkt.data.length);///1 ///长度都是1，
    		//according to the snPkt, create the sending byte stream
  		ByteArrayOutputStream baos = new ByteArrayOutputStream();
  		DataOutputStream dos = new DataOutputStream(baos);

  		try {  //write the base head
  			dos.writeByte(pkt.cmd);
  			dos.writeByte(pkt.code);
  			dos.writeShort(pkt.seq); 
  			dos.writeShort(pkt.len);  
  			dos.writeByte(2);///功能任务信息	02-》2	智能库的功能任务改变
  		}catch (IOException e){
  			e.printStackTrace();
  		}
  	/*	try {
			///dos.write(pkt.data, 0, pkt.len-6);  //write the data field.
		}catch (IOException e){
			e.printStackTrace();
		}
*/
  		pkt.message = baos.toByteArray();  
		//System.arraycopy(pkt.data, 0, pkt.message, 6, pkt.len-6);
    	  // 创建一个客户端DatagramSocket，使用随机端口  
          DatagramSocket socket = new DatagramSocket();
          // 初始化发送用的DatagramSocket，它包含一个长度为0的字节数组  
          outPacket = new DatagramPacket(pkt.message, pkt.len+6 
              , InetAddress.getByName(DEST_IP) , DEST_PORT);  
		 
              // 发送数据报  
              socket.send(outPacket);  
              // 读取Socket中的数据，读到的数据放在inPacket所封装的字节数组中  
              socket.receive(inPacket);  
              System.out.println(new String(inBuff , 0  
                  , inPacket.getLength()));  
      }catch(Exception e){
    	  e.printStackTrace();
      }
  }  
  public static void main(String[] args)   
      throws IOException  
  {  
      new UdpClient().init();  
  }  
}