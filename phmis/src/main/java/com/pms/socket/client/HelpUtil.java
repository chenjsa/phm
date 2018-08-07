package com.pms.socket.client;
 

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class HelpUtil {
	private byte[] buf = new byte[1500];

	private DatagramSocket socket;
	private DatagramPacket dp = new DatagramPacket(buf, buf.length);
	PacketMsg pkt = new PacketMsg(buf);
	PacketMsg snPkt = new PacketMsg(null);
	
	int CreateControlRet(){
		snPkt.code = pkt.code;
		return 0;
	}
	int CreateTransRet(){
		snPkt.code = 0;
		return 0;
	}
	
	int SendProc( ){  // according to the received pkt create the snPkt.
		int ret = 0;
		
		snPkt.seq = pkt.seq;
		snPkt.cmd = (byte)(1);
		snPkt.len = 6;

		System.out.println("send proc: cmd=" + snPkt.cmd);
		switch(snPkt.cmd){
			case 1: 
				ret = CreateControlRet();
				break;
			case 3:
				ret = CreateTransRet();
				break;
	
			default:
				System.out.println("NOT SUPPORT PACKET COMMAND!!!");
				break;
		}
		
		if(ret <0){
			System.out.println("No RETURN MESSAGE!!!");
			return ret;
		}

		//according to the snPkt, create the sending byte stream
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);

		try {  //write the base head
			dos.writeByte(snPkt.cmd);
			dos.writeByte(snPkt.code);
			dos.writeShort(snPkt.seq);
			dos.writeShort(snPkt.len-6);
		}catch (IOException e){
			e.printStackTrace();
		}
		
		try {
			dos.write(snPkt.data, 0, snPkt.len-6);  //write the data field.
		}catch (IOException e){
			e.printStackTrace();
		}

		snPkt.message = baos.toByteArray();
		System.arraycopy(snPkt.data, 0, snPkt.message, 6, snPkt.len-6);

		return ret;
	}
	
	public static void main(String[] args)
	{
		HelpUtil s=new HelpUtil();
		s.SendProc();
	}
}
