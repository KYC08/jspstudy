package model;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
// 인터페이스 - 추상메소드만 
public interface MyInterface {
  ActionForward getDate(HttpServletRequest request); 
  ActionForward getTime(HttpServletRequest request); 
  ActionForward getDateTime(HttpServletRequest request); 
  
}
