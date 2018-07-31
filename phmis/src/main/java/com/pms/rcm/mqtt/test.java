package com.pms.rcm.mqtt;

import javax.annotation.Resource;

import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

public class test {
	    @Resource  
	    private MqttPahoMessageHandler mqtt;  
	       
	    public void sendMessage(){  
	        Message<String> message = MessageBuilder.withPayload("==========1111111111111111111111111=========").setHeader(MqttHeaders.TOPIC, "robot_server").build();  
	        mqtt.handleMessage(message);  
	        System.out.println("成功");  
	    }  
	    
	    public static void main(String args[]){
	       test t =new test();
	       t.sendMessage();
	    }
}
