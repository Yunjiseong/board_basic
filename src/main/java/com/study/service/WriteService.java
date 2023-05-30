package com.study.service;

import com.study.model.BoardDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WriteService implements IBoardService {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
//        BoardDAO.getInstance().insert(
//            request.getParameter("writer"),
//            request.getParameter("title"),
//            request.getParameter("content"), //여기부 짜기
//        )
    }
}
