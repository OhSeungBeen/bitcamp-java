package ch22.a;

import java.io.File;
import java.io.IOException;

public class Test07 {

  public static void main(String[] args) throws IOException {
    
    // 폴더와 파일을 한 번에 생성하는 방법
    File file = new File("temp2/a/b/c/test.txt");
    
    // 파일의 디렉토리 경로를 가지고 File 객체 생성
    //File dir = new File(file.getParent());
    File dir = file.getParentFile();
    System.out.println(file.getParent());
    
    // 먼저 디렉토리를 생성한다.
    if(dir.mkdirs()) {
      System.out.println("파일을 생성함.");
    } else {
      System.out.println("파일을 생성하지 못함.");
    }
    
    if (file.createNewFile()) {
      System.out.println("파일이 생성됨.");
    }else {
      System.out.println("디렉토리 삭제 못함.");
    }
    
  }
}
