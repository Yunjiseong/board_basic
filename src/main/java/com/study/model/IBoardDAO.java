package com.study.model;

import com.study.common.PageVO;

import java.util.List;

public interface IBoardDAO {

    // 게시글 등록
    boolean insert(BoardVO vo);

    // 게시글 리스트
    List<BoardVO> selectAll(PageVO paging);

    // 게시글 상세
    BoardVO selectOne(int bId);

    // 게시글 수정
    boolean update(BoardVO vo);

    // 게시글 삭제
    boolean delete(int bId, String pw);

    // 카테고리 조회
    List<String> selectCategory();

    //글 검색 요청을 처리할 메서드(제목 검색)
    List<BoardVO> searchBoard(String keyword, String category);

    //조회수를 올려주는 메서드
    void upHit(int bId);

    //총 게시물 수를 조회하는 메서드
    int countArticles();

}
