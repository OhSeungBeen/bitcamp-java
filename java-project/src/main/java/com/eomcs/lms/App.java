package com.eomcs.lms;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;

public class App {
  
  static Scanner keyboard = new Scanner(System.in);
  
  public static void main(String[] args) {
    
    LessonHandler l1 = new LessonHandler();
    LessonHandler.keyboard = keyboard;
    MemberHandler.keyboard = keyboard;
    BoardHandler.keyboard = keyboard;
    
    while (true) {
      
      String command = prompt();
      
      if (command.equals("/lesson/add")) {
        l1.addLesson();
      } else if (command.equals("/lesson/list")) {
        l1.listLesson();
      } else if (command.equals("/member/add")) {
        MemberHandler.addMember();
      } else if (command.equals("/member/list")) {
        MemberHandler.listMember();
      } else if (command.equals("/board/add")) {
        BoardHandler.addBoard();
      } else if (command.equals("/board/list")) {
        BoardHandler.listBoard();
      } else if (command.equals("quit")) {
        System.out.println("안녕!");
        break;

      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }

      System.out.println(); 
    }
    
  }

  static String prompt() {
    
    System.out.print("명령> ");
    String command = keyboard.nextLine();
    return command;
    
  }
}