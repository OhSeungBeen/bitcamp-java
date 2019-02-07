// 인스턴스 출력하기 - ObjectOutputStream 데코레이터 사용하
package ch22.e;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Test04_1 {
  public static void main(String[] args) {

    // 다음 세 학생어 성적 정보를 Score.data 파일에 바이너리 형식으로 저장하라!
    // bufferedoutputstream 사용
    // dataoutputstream 사용
    ArrayList<Score> students = new ArrayList<>();
        students.add(new Score("홍길동", 100, 100, 100));
        students.add(new Score("임꺽정", 90, 90, 90));
        students.add(new Score("유관순", 80, 80, 80));

        // ObjectOutputStream
        // => DataOutputStream의 기능을 포함한다.
        // => 인스턴스를 바이트 배열로 만들어 출력하는 기능이 있다.
        // => 단 java.io.Serializable 인터페이스를 구현한 클래스에 대해서만 가능하다.
    try ( ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(
            new FileOutputStream("Score.data")))){
      
      out.writeInt(students.size());
      
      for(Score student : students) {
        out.writeObject(student);
      }

      System.out.println("학생 정보를 Score.data에 저장하였습니다.");

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

}
