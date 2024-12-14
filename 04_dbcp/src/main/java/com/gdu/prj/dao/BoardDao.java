package com.gdu.prj.dao;

import java.util.List;
import java.util.Map;

import com.gdu.prj.dto.BoardDto;

public interface BoardDao {
  int insertBoard(BoardDto board);

  int updateBoard(BoardDto board);

  int deleteBoard(int board_no);

  int deleteBoards(String param);

  List<BoardDto> selectBoardList(Map<String, Object> params); // List 로 반환 읽어오기

  int getBoardCount(); // 총 갯수 

  BoardDto selectBoardByNo(int board_no);// 상세보기 

  void close(); // 공동으로 사용할 자원 반납 메소드 
}
