// 정렬 객체를 일관성 있게 사용하려면 같은 타입으로 묶어야 한다.
package ch17.c;

public class Test01 {

  public static void main(String[] args) {
    int [] values = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    int [] values2 = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    int [] values3 = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    
    // 정렬 객체를 사용하는 방법을 통일하면 사용하기가 쉬워진다.
    // 어떻게?
    // 두 클래스를 같은 부모의 자식 클래스가 되게 하라.
    // => 같은 부모라는 뜻은 같은 메서드를 갖는다는 의미다.
    // => 즉 메서드가 같으니 사용법도 같다.
    Sorter s1;
    Sorter s2;
    
    // 두 개의 정렬 객체가 같은 타입이기 때문에 사용하기 편하다.
    // => 언제든 다른 객체로 교체할 수 있어 유연하다.
    display(new BubbleSort(), values);
    display(new QuickSort(), values2);
    
    // Sorter는 추상 클래스이기 때문에 인스턴스를 생성할 수 없다.
    // => 이렇게 서브 클래스에게 상속해주는 역할만 하는 클래스의 경우
    //    추상 클래스로 선언하여 인스턴스를 만들지 못하게 하라!
    // display(new Sorter(), values3); // 컴파일 오류!
    
  }
  static void display(Sorter sorter, int[] values) {
    // 정렬 객체의 클래스가 뭔지 상관없다.
    // 그 클래스를 사용할 때는 공통 분모가 되는 
    // 수퍼 클래스의 메서드를 호출한다.
    sorter.sort(values);
    
    for (int value : values) {
      System.out.print(value+",");
    }
    System.out.println();
    
  }
  
  static void display(QuickSort sorter, int[] values) {
    
    sorter.sort(values);
    
    for (int value : values) {
      System.out.print(value + ",");
    }
    System.out.println();
  }
  
}