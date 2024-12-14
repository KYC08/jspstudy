package com.gdu.prj.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

// 페이징 처리 
@NoArgsConstructor
@Data
public class MyPageUtils {

  private int total; // 전체 게시글 갯수 (DB 에서 구한다.)
  private int display; // 한 페이지에 표시할 게시글 개수 (요청 파라미터로 받는다.)
  // (화면 하나(페이지)에 게시글을 몇개씩 보여줄것인지)
  private int page; // 현재 페이지 번호 (요청 파라미터로 받는다.)
  private int begin; // 한 페이지에 표시할 게시글의 시작 번호 (계산한다.)
  private int end; // 한 페이지에 표시할 게시글의 종료 번호 (계산한다.)

  private int totalPage; // 전체 페이지 개수
  private int pagePerBlock = 10; // 한 블록에 표시할 페이지 링크의 개수

  private int beginPage; // 한 블록에 표시할 페이지 링크의 시작 번호 (계산한다.)
  private int endPage; // 한 블록에 표시할 페이지 링크의 종료 번호 (계산한다.)

  public void setPaging(int total, int display, int page) {

    this.total = total;
    this.display = display;
    this.page = page;

    begin = (page - 1) * display + 1;
    end = begin + display - 1; // 보정 필요,,,? 한가?

    totalPage = (int) Math.ceil((double) total / display);
    beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
    endPage = Math.min(totalPage, beginPage + pagePerBlock - 1);

  }

  public String getPaging(String requestURI, String sort, int display) {

    StringBuilder builder = new StringBuilder();

    // builder.append("<div class=\"paging\">"); // 모듈화 때 사용한다.

    // <
    if (beginPage == 1) {
      builder.append("<div class=\"dont-click\">&lt;</div>");
    } else {
      builder.append("<div><a href=\"" + requestURI + "?page=" + (beginPage - 1) + "&sort=" + sort + "&display=" + display + "\">&lt;</a></div>");
    }

    // 1 2 3 4 5 6 7 8 9 10
    for (int p = beginPage; p <= endPage; p++) {
      if (p == page) {
        builder.append("<div><a class=\"current-page\" href=\"" + requestURI + "?page=" + p + "&sort=" + sort + "&display=" + display + "\">" + p + "</a></div>");
      } else {
        builder.append("<div><a href=\"" + requestURI + "?page=" + p + "&sort=" + sort + "&display=" + display + "\">" + p + "</a></div>");
      }
    }

    // >
    if (endPage == totalPage) {
      builder.append("<div class=\"dont-click\">&gt;</div>");
    } else {
      builder.append("<div><a href=\"" + requestURI + "?page=" + (endPage + 1) + "&sort=" + sort + "&display=" + display +  "\">&gt;</a></div>");
    }

    // builder.append("</div>"); // 모듈화 때 사용한다.

    return builder.toString();
  }
}
