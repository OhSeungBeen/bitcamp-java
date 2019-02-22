package com.eomcs.lms.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import com.eomcs.lms.dao.LessonDao;

public class LessonDeleteCommand extends AbstractCommand {

  LessonDao lessonDao;
  
  public LessonDeleteCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  

  @Override
  public void execute(Response response) {
    try {
      int no = response.requestIntValue("번호? ");
      
      if (lessonDao.delete(no) == 0) {
        response.println("해당 번호의 수업이 없습니다.");
        return;
      }
      response.println("삭제했습니다.");
      
    } catch (Exception e) {
      out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
}
