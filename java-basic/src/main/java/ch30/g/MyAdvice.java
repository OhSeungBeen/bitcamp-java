package ch30.g;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// 지정된 객체의 메서드를 호출할 때,
// => 메서드 호출 전이나 후에 어떤 작업을 수행한다.

//XML 설정을 사용할 때는 이 클래스의 객체 생성을 XML에서 했기 때문에
//@Component 애노테이션을 붙이지 않았다.
// 이제는 XML에서 이 클래스의 객체를 만들지 않기 때문에 이 애노테이션을 붙여야 한다.

// 이 클래스가 AOP 기능을 수행하는 클래스임을 표시해야 한다.
@Component
@Aspect
public class MyAdvice {
  
  @Pointcut("execution(* ch30.g.X.*(..))")
  public void calculatorOpertaion() {}
  
  @Before("calculatorOpertaion() and args(p2,p3,p1)")
  public void doBefore(String p1, int p2, int p3 ) {
    System.out.printf("%s.doBefore()\n",this.getClass().getName());
    System.out.printf(" => %s %d %d\n", p1, p2, p3);
  }
  
  @After("calculatorOpertaion()")
  public void doAfter() {
    System.out.printf("%s.doAfter()\n",this.getClass().getName());
  }
  @AfterReturning(pointcut="calculatorOpertaion()", returning="rv")
  public void doAfterReturning(Object rv) {
    System.out.printf("%s.doAfterReturning()\n",this.getClass().getName());
    System.out.printf("  => %d\n", rv);
  }
  
  @AfterThrowing(pointcut="calculatorOpertaion()", throwing="err")
  public void doAfterThrowing(Exception err) {
    System.out.printf("%s.doAfterThrowing()\n",this.getClass().getName());
    System.out.printf("  => %s\n",err.getMessage());
  }
  
}
