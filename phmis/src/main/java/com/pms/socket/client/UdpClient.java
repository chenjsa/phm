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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.pms.rcm.maintain.manager.SystemParametersInfoManager;
import com.pms.rcm.maintain.vo.SystemParametersInfo;

//程序清单：codes\17\17.4\UdpClient.java 
public class UdpClient  
{   
  // 定义发送数据报的目的地  
  @Value("${udp.port}")
  public  int DEST_PORT;
  @Value("${udp.ip}")
  public  String DEST_IP;
  // 定义每个数据报的最大大小为4KB  
  private static final int DATA_LEN = 4096;     
  // 定义接收网络数据的字节数组  
  byte[] inBuff = new byte[DATA_LEN];  
  // 以指定的字节数组创建准备接收数据的DatagramPacket对象  
  private DatagramPacket inPacket =   
      new DatagramPacket(inBuff , inBuff.length);  
  
  
  public DatagramPacket getInPacket() {
	return inPacket;
}

public void setInPacket(DatagramPacket inPacket) {
	this.inPacket = inPacket;
}
// 定义一个用于发送的DatagramPacket对象  
  private DatagramPacket outPacket = null;   
  ///private byte[] buf = new byte[6]; 
  private PacketMsg pkt;// = new PacketMsg(buf);
  
  public PacketMsg getPkt() {
	return pkt;
}

public void setPkt(PacketMsg pkt) {
	this.pkt = pkt;
}

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
  public UdpClient(PacketMsg pkt,String DEST_IP,int DEST_PORT){
	  this.pkt=pkt;
	  this.DEST_IP=DEST_IP;
	  this.DEST_PORT=DEST_PORT;
  }
  public void init()throws IOException  
  {  
      try  
      {  
    	 /* pkt.seq = (short) (pkt.seq+1);
    	  pkt.cmd = (byte)(3);
    	  pkt.code= (byte)(4);    	      	
    	  pkt.data=ObjectToByte((byte)2);
    	
    	  pkt.len =1;///(short) (pkt.data.length);///1 ///长度都是1，
*/    		//according to the snPkt, create the sending byte stream
  		ByteArrayOutputStream baos = new ByteArrayOutputStream();
  		DataOutputStream dos = new DataOutputStream(baos);

  		try {  //write the base head
  			dos.writeByte(pkt.cmd);
  			dos.writeByte(pkt.code);
  			dos.writeShort(pkt.seq); 
  			dos.writeShort(pkt.len);  
  			dos.writeByte(pkt.sdata);///功能任务信息	02-》2	智能库的功能任务改变
  		}catch (IOException e){
  			e.printStackTrace();
  		} 
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
          /*    System.out.println(new String(inBuff , 0  
                  , inPacket.getLength()));  */
      }catch(Exception e){
    	  e.printStackTrace();
      }
  }  
  public static void main(String[] args)   
      throws IOException  
  {  
     // new UdpClient(new PacketMsg(),DEST_IP,DEST_PORT).init();  
  }  
}