// URL 에서 값 추출하기 - @PathVariable
package bitcamp.app2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@RequestMapping("/c02_2")
public class Controller02_2 {

  // 테스트:
  // http://localhost:8080/java-spring-webmvc/app2/c02_2?name=kim&age=20
  @GetMapping
  @ResponseBody
  public String handler1(String name, int age) {

    return String.format("name=%s,age=%d", name, age);
  }


  //테스트:
  // http://localhost:8080/java-spring-webmvc/app2/c02_2/name=kim;age=20
  @GetMapping("{value}")
  @ResponseBody
  public String handler2(
      @PathVariable("value") String value,
      // value 값 중에서 name 항목의 값을 받고 싶을 때 @MatrixVarialbe을 사용한다.
      // 단 value의 형식은 "이름=값;이름=값 .. " 형태이어야 한다.
      /*
     @MatrixVariable("name") String name,
     @MatrixVariable("age") int age
       */
      // 매트릭스 변수명을 생략하면 아규먼트의 이름을 사용한다.
      @MatrixVariable String name,
      @MatrixVariable int age
      ) {
    // @MatrixVariable 애노테이션을 사용하려면
    // IoC 컨테이너에서 활성화시키는 설정을 추가해야 한다.
    // @Autowired를 사용하려면 이 애노테이션 객체를 등록하는 것과 같다.
    // => <mvc:annotaion-driven enable-matrix-bariables="ture />

    http://localhost:8080/java-spring-webmvc/app2/c02_2/name=kim;age=20
      //테스트 1
      // => @PathVariable("value") : name=kim
      // => @MatrixVariable("name") : kim 
      // => @MatrixVariable("age") : 20

      //http://localhost:8080/java-spring-webmvc/app2/c02_2/user;name=kim;age=20
      //테스트 2
      // => @PathVariable("value") : user
      // => @MatrixVariable("name") : kim 
      // => @MatrixVariable("age") : 20

      return String.format("value:%s\n ==> name:%s age:%s", value, name, age);

  }

  //테스트:
  // http://localhost:8080/java-spring-webmvc/app2/c02_2/name=teamA;qty=5/title=work;state=1
  @GetMapping(value ="{team}/{task}", produces="text/plain;charsert=UTF-8")
  @ResponseBody
  public String handler3(
      // 여러 개의 패스 변수가 있을 때 값을 꺼내는 방법
      @MatrixVariable String name,
      @MatrixVariable int qty,
      @MatrixVariable String title,
      @MatrixVariable int state
      ) {

    return String.format("team:%s(%d)\n task: %s, %d", name, qty, title, state);
  }
  
  //테스트:
  // http://localhost:8080/java-spring-webmvc/app2/c02_2/h4/name=teamA;qty=5/name=work;qty=1
  @GetMapping(value ="h4/{team}/{task}", produces="text/plain;charsert=UTF-8")
  @ResponseBody
  public String handler4(
      // 여러 개의 패스 변수가 있을 때 값을 꺼내는 방법
      // => 만약 항목의 이름이 같다면?
      @MatrixVariable(name = "name", pathVar = "team") String name1,
      @MatrixVariable(name = "qty", pathVar = "team") int qty1,
      @MatrixVariable(name = "name", pathVar = "task") String name2,
      @MatrixVariable(name = "qty", pathVar = "task") int qty2
      ) {

    return String.format("team:%s(%d)\n task: %s, %d", name1, qty1, name2, qty2);
  }
}