// URL 에서 값 추출하기 - @PathVariable
package bitcamp.app2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@RequestMapping("/c02_1")
public class Controller02_1 {

  // 테스트:
  // http://localhost:8080/java-spring-webmvc/app2/c02_1?name=kim&age=20
  @GetMapping
  @ResponseBody
  public String handler1(String name, int age) {

    // 클라이언트에서 값을 받는 일반적인 방법
    // => Query String으로 바든ㄴ다.
    // => 즉 URL 다음에 "변수=값&변수=값" 형태로 값을 받는다.
    // => Query String의 값을 requet handler에서 받ㄷ으려면
    //      아규먼트를 선언하면 된다.
    //      아규먼트 앞에 @RequestParam을 붙여도 되고
    //      아규먼트의 이름이 요청 파라미터의 이름과 같다면 @RequestParam을 생략해도 된다.
    return String.format("name=%s,age=%d", name, age);
  }
  
  
//테스트:
 // http://localhost:8080/java-spring-webmvc/app2/c02_1/kim/20
 @GetMapping("{name}/{age}")
 @ResponseBody
 public String handler2(
     /*
     @PathVariable("name") String name,
     @PathVariable("age") int age
     */
     // URL의 변수 이름을 생략하면 아규먼트 이름으로 꺼낸다.
     @PathVariable String name,
     @PathVariable int age
     ) {
   // URL에 값을 포함하여 전달할 수 있고, 그 값을 아규먼트로 받을 수 있따.
   // URL에 포함된 값을 받으려면 request handler의 URL을 설정할 때
   // 다음의 문법으로 선언해야 한다.
   // => .../{변수명}/{변수명{}
   // 이렇게 선언된 변수 값을 받으려면 다음과 같이 아규먼트를 선언해야 한다.
   // => @pathVariable(변수명) String 아규먼트
  
   return String.format("name=%s,age=%d", name, age);
   
 }
 
 
 
  
}
