package com.pms.base.common;
 

import java.io.PrintWriter;
import java.io.StringWriter;

public class ResponseMessage
{
  private String success;
  private String message;
  private Object data;

  public ResponseMessage(String success, String message)
  {
    this.success = success;
    this.message = message;
 
  }

   

  public String getSuccess() {
	return success;
}



private ResponseMessage(String success) {
    this.success = success;
    this.message = "";
  }

 

  

  public static ResponseMessage success(String message) {
    return new ResponseMessage("success", message);
  }

  public static ResponseMessage success() {
    return new ResponseMessage("success");
  }

  public String isSuccess() {
    return this.success;
  }

  public void setSuccess(String success) {
    this.success = success;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

 

  public Object getData() {
    return this.data;
  }

  public ResponseMessage setData(Object data) {
    this.data = data;
    return this;
  }
}