//클래스를 정의할때 수퍼 클래스를 지정하지 않으면
//컴파일러는 자동으로 objcet를 상속 받는다.
package ch15;

import ch12.c.My;

class My5{
  String name;
  int age;
  
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    My5 other = (My5) obj;
    if (age != other.age)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }
  
}

public class Test05 {

  public static void main(String[] args) {
    My5 obj1 = new My5();
    obj1.name = "홍길동";
    obj1.age = 20;

    My5 obj2 = new My5();
    obj2.name = "홍길동";
    obj2.age = 20;

    System.out.println(obj1 == obj2);

    //Object에서 상속 받은 equals()는 인스턴스가 같은지 비교한다.
    // 만약 그 내용물이 같은지 비교하고 싶다면 equals()를 재정의 하라.
    System.out.println(obj1.equals(obj2));

    // 결론!
    // => Object에서 상속 받은 것을 그대로 사용하면 equals()는 인스턴스가 같은지 비교한다.
    // => 인스턴스의 내용물이 같은지 비교하도록 만들고 싶다면 equals()를 오버라이딩 하라!
    // => String와 wrapper 클래스는 equals() 오버라이딩 하였다.
    // => StringBuffer 클래스는 equals()를 오버라이딩 하지 않았다.
  }
}

