// java.sql.Statment - delete 실행하기
package ch25.a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test11 {
  public static void main(String[] args) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")){
      System.out.println("DBMS에 연결됨!");

        try (Statement stmt = con.createStatement()){
        int count = stmt.executeUpdate("delete from x_board where board_id > 5");
         // 리턴 값은 delete 한 레코드의 개수이다.
        System.out.println(count);
        }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
