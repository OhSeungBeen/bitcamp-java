// Java config - 자바 클래스로 스프링 IoC 컨테이너 설정하기
package ch29.j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test00 {
  public static void main(String[] args) {
    
    // java config를 다룰 때는 AnnotationConfigApplicationContext 클래스를 사용한다.
    ApplicationContext iocContainer = 
        new AnnotationConfigApplicationContext(AppConfig0.class);
    
    System.out.println("---------------------------------------");
    
    String[] names = iocContainer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", 
          name, iocContainer.getBean(name).getClass().getName());
    }
    System.out.println(iocContainer.getBean("car"));
    System.out.println(iocContainer.getBean("car2"));
  }
}





