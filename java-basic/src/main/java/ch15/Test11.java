// hashCode 응용 V - 사용자가 만든 클래스를 key로 사용하기 위해 hashcode()와 equals() 오버라이딩 하기.
package ch15;

import java.util.HashMap;

class Key2 {
  String contents;
  
  public Key2(String contents){
    this.contents = contents;
  }

  @Override
  public String toString() {
    return "key2 [contents=" + contents + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((contents == null) ? 0 : contents.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Key2 other = (Key2) obj;
    if (contents == null) {
      if (other.contents != null)
        return false;
    } else if (!contents.equals(other.contents))
      return false;
    return true;
  }
}

public class Test11 {

  public static void main(String[] args) {
    HashMap map = new HashMap();

    Key2 k1 = new Key2("ok");
    Key2 k2 = new Key2("no");
    Key2 k3 = new Key2("haha");
    Key2 k4 = new Key2("ohora");
    Key2 k5 = new Key2("hul");
    
    //String을 key로 사용해보자!
    map.put(Integer.valueOf(100), new Student("홍길동", 20 , false));
    map.put(k2, new Student("홍길동", 20 , false));
    map.put(k3, new Student("유관순", 30 , false));
    map.put(k4, new Student("윤봉길", 20 , true));
    map.put(k5, new Student("오승빈", 20 , false));
    
    System.out.println(map.get(k3));

    // key를 사용하여 값을 꺼내보자.
    Key2 k6 = new Key2("haha");
        
    System.out.println(k3 == k6); // 인스턴스는 다르다
    System.out.println(k3.hashCode()); // hash code는 같다.
    System.out.println(k3.hashCode());
    System.out.println(k3.equals(k6)); // equals()의 비교 결과도 다르다.
    
    // 비록 k3와 k6는
    // hashCode()의 리턴 값이 같다 
    // equals()의 리턴 값이 true이기 때문에
    // HashMap 클래스에서는 서로 같은 같은 key로 간주한다.
  
  }
}

