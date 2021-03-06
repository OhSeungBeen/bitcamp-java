// 애플리케이션 예외의 종류: Excepton 계열의 예외 처리
package ch21.c;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Test02 {

  public static void main(String[] args) {

    Scanner keyboard = new Scanner(System.in);

    // Exception 계열의 예외 방법
    // 1) try ~ catch로 예외 받기
    //    try {
    //             예외발생코드
    //    }catch(예외 파리미터 {
    //      예외처리코드
    //      }
    //
    // 2) 호출자에게 예외 처리를 떠넘기기
    // void 메서드() throws 예외클래스명, 예외클래스명, ...{
    //    예외가 발생할 수 있는 코드
    //  }

    // '방법1' 적용
    // => try ~ catch 로 예외 처리하기
    try {
      Class<?> clazz  = Class.forName("ch21.c.PlusCommand");
      Constructor<?> constructor = clazz.getConstructor(Scanner.class);
      Command command = (Command) constructor.newInstance(keyboard);
      command.execute();

    } catch (ClassNotFoundException e) {
      System.out.println("해당 클래스를 찾지 못했습니다.");
    } catch (NoSuchMethodException e) {
      
    } catch (InstantiationException e) {
      
    } catch (IllegalAccessException e) {
      
    } catch (InvocationTargetException e) {
      
    }
    System.out.println("종료");

  }
}
