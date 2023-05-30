package com.study.model;

import com.study.connection.ConnectionTest;
import com.study.util.JdbcUtil;
import com.study.common.PageVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO implements IBoardDAO {

//    private DataSource ds;
    private ConnectionTest ct;

    // 싱글톤 패턴
    private BoardDAO() {
        try {
//            InitialContext ct = new InitialContext();
//            ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");
            ct = new ConnectionTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static BoardDAO dao = new BoardDAO();

    public static BoardDAO getInstance() { return dao; }

    ////////////////////////////////////////////////////

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;


    /**
     * 게시글 작성
     * @param vo
     * @return
     */

    @Override
    public boolean insert(BoardVO vo) {
        boolean result = false;
        String sql = "INSERT INTO ebrainsoft_study.board(category, writer, pw, title, content, file1, file2, file3)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
//            conn = ds.getConnection();

            conn = ct.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getCategory());
            pstmt.setString(2, vo.getWriter());
            pstmt.setString(3, vo.getPw());
            pstmt.setString(4, vo.getTitle());
            pstmt.setString(5, vo.getContent());
            pstmt.setString(6, vo.getFile1());
            pstmt.setString(7, vo.getFile2());
            pstmt.setString(8, vo.getFile3());

            int rn = pstmt.executeUpdate();
            if (rn > 0) result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt);
        }

        return result;
    }


    /**
     * 글 목록 조회
     * @return
     */
    @Override
    public List<BoardVO> selectAll(PageVO paging) {
        String sql= "SELECT * FROM ("
                + "SELECT ROWNUM AS rn, tbl.* FROM ("
                + "SELECT * FROM ebrainsoft_study.board "
                + "ORDER BY board_id DESC"
                + ") tbl"
                + ") WHERE rn > ? AND rn <= ?";
        List<BoardVO> bList = new ArrayList<>();

        try {
//            conn = ds.getConnection();
            ConnectionTest ct = new ConnectionTest();
            conn = ct.getConnection();
            pstmt = conn.prepareStatement(sql);
            int start = (paging.getPage() - 1) * paging.getCountPerPage();
            pstmt.setInt(1, start);
            pstmt.setInt(2, start + 10);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                BoardVO vo = new BoardVO(  //빌더패턴 binUtils populate
                        rs.getInt("board_id"),
                        rs.getInt("view_cnt"),
                        rs.getInt("del_yn"),
                        rs.getString("category"),
                        rs.getString("writer"),
                        rs.getString("pw"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("file1"),
                        rs.getString("file2"),
                        rs.getString("file3"),
                        rs.getDate("del_dt"),
                        rs.getDate("mod_dt"),
                        rs.getDate("reg_dt")
                        );
                bList.add(vo);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
        return bList;
    }


    /**
     * 글 상세조회
     * @param bId
     * @return
     */
    @Override
    public BoardVO selectOne(int bId) {
        String sql = "SELECT * FROM ebrainsoft_study.board WHERE board_id=?";
        BoardVO vo = null;
        try {
//            conn = ds.getConnection();
            ConnectionTest ct = new ConnectionTest();
            conn = ct.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                vo = new BoardVO(
                    rs.getInt("board_id"),
                    rs.getInt("view_cnt"),
                    rs.getInt("del_yn"),
                    rs.getString("category"),
                    rs.getString("writer"),
                    rs.getString("pw"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("file1"),
                    rs.getString("file2"),
                    rs.getString("file3"),
                    rs.getDate("del_dt"),
                    rs.getDate("mod_dt"),
                    rs.getDate("reg_dt")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }

        return vo;
    }


    /**
     * 글 수정
     * @param vo
     * @return
     */
    @Override
    public boolean update(BoardVO vo) {
        String sql = "UPDATE ebrainsoft.board SET writer=?, title=?, content=?, file1=?, file2=?, file3=?" +
                "WHERE board_id=?";
        try {
            //비밀번호 검증
            BoardVO oldVo = selectOne(vo.getbId());
            if (!oldVo.getPw().equals(vo.getPw())) { return false; }

//            conn = ds.getConnection();
            ConnectionTest ct = new ConnectionTest();
            conn = ct.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getWriter());
            pstmt.setString(2, vo.getTitle());
            pstmt.setString(3, vo.getContent());
            pstmt.setString(4, vo.getFile1());
            pstmt.setString(5, vo.getFile2());
            pstmt.setString(6, vo.getFile3());
            pstmt.setInt(7, vo.getbId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt);
        }
        return true;
    }


    /**
     * 글 삭제
     * @param bId
     * @param pw
     * @return
     */
    @Override
    public boolean delete(int bId, String pw) {
        String sql = "UPDATE ebrainsoft_study.board SET del_yn=1, del_dt=now() WHERE board_id=?";
        try {
            //비밀번호 검증
            BoardVO oldVo = selectOne(bId);
            if (!oldVo.getPw().equals(pw)) { return false; }

//            conn = ds.getConnection();
            ConnectionTest ct = new ConnectionTest();
            conn = ct.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt);
        }
        return true;
    }


    /**
     * 카테고리 조회
     * @return
     */
    @Override
    public List<String> selectCategory() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT category FROM ebrainsoft_study.index ORDER BY category ASC";
        try {
//            conn = ds.getConnection();
            ConnectionTest ct = new ConnectionTest();
            conn = ct.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                categories.add(rs.getString("category"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
        return categories;
    }

    /**
     * 글 조회
     * @param keyword: 검색어
     * @param category: 카테고리
     * @return
     */
    @Override
    public List<BoardVO> searchBoard(String keyword, String category) {
        String sql="SELECT * FROM my_board WHERE " + category + " LIKE ? ORDER BY board_id DESC";
        List<BoardVO> list = new ArrayList<>();
        String newTitle = "%" + keyword + "%";

        try {
            ConnectionTest ct = new ConnectionTest();
            conn = ct.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newTitle);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                list.add(new BoardVO(
                        rs.getInt("board_id"),
                        rs.getInt("view_cnt"),
                        rs.getInt("del_yn"),
                        rs.getString("category"),
                        rs.getString("writer"),
                        rs.getString("pw"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("file1"),
                        rs.getString("file2"),
                        rs.getString("file3"),
                        rs.getDate("del_dt"),
                        rs.getDate("mod_dt"),
                        rs.getDate("reg_dt")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 조회수 증가
     * @param bId
     */
    public void upHit(int bId) {
        String sql = "UPDATE my_board SET hit = hit+1 WHERE board_id=?";
        try {
            ConnectionTest ct = new ConnectionTest();
            conn = ct.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 전체 게시물 갯수 조회
     * @return
     */
    public int countArticles() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM my_board";
        try {
            ConnectionTest ct = new ConnectionTest();
            conn = ct.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
