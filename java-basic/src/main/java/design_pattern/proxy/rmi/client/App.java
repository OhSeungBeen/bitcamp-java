package design_pattern.proxy.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import design_pattern.proxy.rmi.server.Calculator;

public class App {

  public static void main(String[] args) throws Exception {
    Scanner keyboard = new Scanner(System.in);

    Registry registry = LocateRegistry.getRegistry("study.bitcamp.co.kr");
    
    
    Calculator calc = (Calculator) registry.lookup("calc");
    
    while (true) {
      System.out.print("계산식> ");
      String input = keyboard.nextLine();
      if(input.equals("quit")) {
        break;
      }
      String[] values = input.split(" ");
      
      try {
        int a = Integer.parseInt(values[0]);
        int b = Integer.parseInt(values[2]);
        String op = values[1];
        
        switch (op) {
          case "+": System.out.println(calc.plus(a,b)); break;
          case "-": System.out.println(calc.minus(a,b)); break;
          default:
            System.out.println("해당 연산자를 지원하지 않습니다.");
        }
      } catch (Exception e) {
        System.out.println("식 또는 계산 오류 : " +e.getMessage());
      }
    }
    keyboard.close();
  }
}