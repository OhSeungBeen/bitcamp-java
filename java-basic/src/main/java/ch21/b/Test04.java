// 예외 처리하기 - 상속 관계가 있을 때 예외 받는 순서 = catch 블록 순서
package ch21.b;

import java.util.HashMap;
import java.util.Scanner;

public class Test04 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    HashMap<String,Command> commandMap= new HashMap<>();
    commandMap.put("plus", new PlusCommand(keyboard));
    commandMap.put("divide", new DivideCommand(keyboard));

    while (true) {
      System.out.print("명령> ");
      String input = keyboard.nextLine();
      if(input.equals("quit"))
        break;
      // 예외 처리 문법을 적용하면, 예외가 발생하더라도 JVM을 멈추지 않는다.

      try {
        Command command = commandMap.get(input);
        command.execute();
        
        // catch 블록을 배치할 때 구멍 큰 그물 부터 배치하라.
        // 즉 예외 클래스들이 서로 상속 관계가 있을 때 서브 클래스의 예외부터 배치하라.
        // 만약 RuntimeException 예외를 받는 catch 블록을 먼저 두면
        // NumberFormatException 예외까지 받을 수 있기 때문에
        // 그 다음에 배치한 NumberFormatException catch 블록은 실행되지 않는다.
        //
      } catch (NumberFormatException e) {
        System.out.println("정수 값을 입력하세요!");
        
      } catch(RuntimeException e) { //ok! 공통 부모이기 때문에 가능!
        System.out.println("명령 처리 중 오류 발생!");
        System.out.println(e.toString());
      }

    }

    keyboard.close();
  }
}
