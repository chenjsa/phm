package com.pms.socket.client;
import java.net.InetAddress;

import org.springframework.stereotype.Service;
@Service("packetMsg")
public class PacketMsg {
    public byte cmd;
    public byte code;
    public short seq;
    public short len;
    public byte sdata;
    
    public byte[] message;
    public byte[] data;

    public String address;
    public int port;

    public PacketMsg(byte[] message) {
        this.message = message;
    }
    public PacketMsg() { 
    }
}

