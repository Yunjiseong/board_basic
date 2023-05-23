package com.study.model;

import java.util.List;

public interface iBoardDAO {

    // 게시글 등록
    boolean insert(BoardVO vo);

    // 게시글 리스트
    List<BoardVO> selectAll();

    // 게시글 상세
    BoardVO selectOne(int bId);

    // 게시글 수정
    boolean update(BoardVO vo);

    // 게시글 삭제
    boolean delete(int bId, String pw);

    // 카테고리 조회
    List<String> selectCategory();

}
