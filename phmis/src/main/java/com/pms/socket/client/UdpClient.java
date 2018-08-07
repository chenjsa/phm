package com.pms.socket.client;
 

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java_cup.runtime.Scanner;

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
  public void init()throws IOException  
  {  
      try  
      {  
    	  pkt.seq = (short) (pkt.seq+1);
    	  pkt.cmd = (byte)(1);
    	  pkt.code= (byte)(3);    	  
    	  pkt.len =6;
    	  pkt.data="zhoubao".getBytes();
    		//according to the snPkt, create the sending byte stream
  		ByteArrayOutputStream baos = new ByteArrayOutputStream();
  		DataOutputStream dos = new DataOutputStream(baos);

  		try {  //write the base head
  			dos.writeByte(pkt.cmd);
  			dos.writeByte(pkt.code);
  			dos.writeShort(pkt.seq); 
  			dos.writeShort(pkt.len);  
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
  		System.out.println(pkt.message);
		//System.arraycopy(pkt.data, 0, pkt.message, 6, pkt.len-6);
    	  // 创建一个客户端DatagramSocket，使用随机端口  
          DatagramSocket socket = new DatagramSocket();
          // 初始化发送用的DatagramSocket，它包含一个长度为0的字节数组  
          outPacket = new DatagramPacket(pkt.message, pkt.len 
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