package com.gdu.prj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gdu.prj.dto.BoardDto;

/*
 * view - (filter) - controller - service - dao - db
 */

// 개발 순서상 dao 를 먼저 만드는 것이 좋다. 
public class BoardDaoImpl implements BoardDao {

  // dao 는 db 를 처리한다. Data Access Object
  private Connection con;
  private PreparedStatement ps;
  private ResultSet rs;
  // db 접속에 필요한 3종

  // Connection Pool 관리를 위한 DataSource 객체 선언
  private DataSource dataSource;

  // SingletonPattern
  private static BoardDao boardDao = new BoardDaoImpl();

  private BoardDaoImpl() {
    // META-INF\context.xml 파일에 명시된 Resource 를 이용해 DataSource 객체 생성하기
    try {
      Context context = new InitialContext();
      Context env = (Context) context.lookup("java:comp/env");
      dataSource = (DataSource) env.lookup("jdbc/myoracle");
    } catch (Exception e) {
      System.out.println("관련 자원을 찾을 수 없습니다.");
    }
  }

  public static BoardDao getInstance() {
    return boardDao;
  }

  @Override
  public int insertBoard(BoardDto board) {
    int insertCount = 0;
    try {
      con = dataSource.getConnection(); // Connection 얻기
      String sql = "INSERT INTO BOARD_T(BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, CURRENT_DATE, CURRENT_DATE)";
      // Query 작성
      ps = con.prepareStatement(sql);
      ps.setString(1, board.getTitle()); // ? 변수 처리
      ps.setString(2, board.getContents()); // ? 변수 처리
      insertCount = ps.executeUpdate(); // 실행 결과
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
      // this.close(); // 현재 객체 this
    }
    return insertCount;
  }

  @Override
  public int updateBoard(BoardDto board) {
    int updateCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "UPDATE BOARD_T SET TITLE = ?, CONTENTS = ?, MODIFIED_AT = CURRENT_DATE WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setString(1, board.getTitle());
      ps.setString(2, board.getContents());
      ps.setInt(3, board.getBoard_no());
      updateCount = ps.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }

    return updateCount;
  }

  @Override
  public int deleteBoard(int board_no) {
    int deleteCount = 0;
    try {
      con = dataSource.getConnection(); // 커넥션 관리하는 풀에서 커넥션 얻어내기
      String sql = "DELETE FROM BOARD_T WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, board_no);
      deleteCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return deleteCount;
  }
  
  
 @Override
public int deleteBoards(String param) {
   int deleteCount = 0;
   try {
    con = dataSource.getConnection();
    String sql = "DELETE FROM BOARD_T WHERE BOARD_NO IN(" + param + ")";
    ps = con.prepareStatement(sql);
    deleteCount = ps.executeUpdate();
  } catch (Exception e) {
  e.printStackTrace();
  } finally {
    close();
  }
   return deleteCount;
}

  @Override
  public List<BoardDto> selectBoardList(Map<String, Object> params) {
    List<BoardDto> boardList = new ArrayList<>();
    
    // 페이징 처리하여 전체목록 보기
    try {
      con = dataSource.getConnection(); 
      String sql = "SELECT BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT" 
                 + "  FROM (SELECT ROW_NUMBER() OVER (ORDER BY BOARD_NO " + params.get("sort") + ") AS RN, BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT" // 정렬 자리 
                 + "          FROM BOARD_T)" 
                 + " WHERE RN BETWEEN ? AND ?"; 
      
      ps = con.prepareStatement(sql);
      ps.setInt(1, (int)params.get("begin"));
      ps.setInt(2, (int)params.get("end"));
      
      rs = ps.executeQuery();
      while (rs.next()) { // 갯수만큼 호출
        BoardDto board = BoardDto.builder()
            .board_no(rs.getInt(1))
            .title(rs.getString(2))
            .contents(rs.getString(3))
            .modified_at(rs.getDate(4))
            .created_at(rs.getDate(5))
          .build();
        boardList.add(board);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return boardList;
  }

  @Override
  public int getBoardCount() {
    int boardCount = 0;
    try {
      con = dataSource.getConnection(); // 연결
      String sql = "SELECT COUNT(*) FROM BOARD_T";
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      if (rs.next()) {
        boardCount = rs.getInt(1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return boardCount;
  }

  @Override
  public BoardDto selectBoardByNo(int board_no) {
    BoardDto board = null;
    try {
      con = dataSource.getConnection();
      String sql = "SELECT BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT FROM BOARD_T WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, board_no);
      rs = ps.executeQuery();
      if (rs.next()) {
        board = BoardDto.builder()
            .board_no(rs.getInt(1))
            .title(rs.getString(2))
            .contents(rs.getString(3))
            .modified_at(rs.getDate(4))
            .created_at(rs.getDate(5))
            .build();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return board;
  }

  @Override
  public void close() {
    try {
      if (rs != null)
        rs.close();
      if (ps != null)
        rs.close();
      if (con != null)
        rs.close(); // Connection 반납으로 동작
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
