package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Date;

public class Response {
  BufferedReader in;
  PrintWriter out;
  
  public Response(BufferedReader in, PrintWriter out) {
    this.in = in;
    this.out = out;
  }
  
  public int requestIntValue(String title) throws Exception {
    return Integer.parseInt(this.requestString(title));
  }
  
  public Date requestDateValue(String title) throws Exception {
    return Date.valueOf(this.requestString(title));
  }
  
  
  public void println(String message) {
    out.println(message);
    out.flush();
  }
  
  public String requestString(String title) throws Exception {
    out.println(title);
    out.println("!{}!");
    out.flush();
    return in.readLine();
  }
  
}
