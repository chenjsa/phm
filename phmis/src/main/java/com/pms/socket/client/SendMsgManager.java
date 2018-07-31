package com.pms.socket.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("sendMsgManager")
public class SendMsgManager {
	@Value("${serverIp}")
	private String serverIp;
	@Value("${serverPort}")
	private int serverPort;
	
	public void sendMsg(String id){
		  Socket socket = null;  
          try {  
              //创建一个流套接字并将其连接到指定主机上的指定端口号  
              socket = new Socket(serverIp, serverPort);    
                  
              //读取服务器端数据    
              DataInputStream input = new DataInputStream(socket.getInputStream());    
              //向服务器端发送数据    
              DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
              System.out.print("请输入: \t");    
              String str = new BufferedReader(new InputStreamReader(System.in)).readLine();    
              out.writeUTF(id);    
                  
              String ret = input.readUTF();     
              System.out.println("服务器端返回过来的是: " + ret);     
              out.close();  
              input.close();  
          } catch (Exception e) {  
              System.out.println("客户端异常:" + e.getMessage());   
          } finally {  
              if (socket != null) {  
                  try {  
                      socket.close();  
                  } catch (IOException e) {  
                      socket = null;   
                      System.out.println("客户端 finally 异常:" + e.getMessage());   
                  }  
              }  
          }  
      }     

}
