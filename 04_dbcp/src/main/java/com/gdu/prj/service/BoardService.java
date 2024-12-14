package com.gdu.prj.service;

import com.gdu.prj.common.ActionForward;

import jakarta.servlet.http.HttpServletRequest;

//MVC 패턴에서 Model 역할 
public interface BoardService {
  ActionForward addBoard(HttpServletRequest request);

  ActionForward getBoardList(HttpServletRequest request);

  ActionForward getBoardByNo(HttpServletRequest request);

  ActionForward editBoard(HttpServletRequest request);

  ActionForward modifyBoard(HttpServletRequest request);

  ActionForward removeBoard(HttpServletRequest request);

  ActionForward removeBoards(HttpServletRequest request);
}
