package com.study.service;

import com.study.model.BoardDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteService implements IBoardService {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        BoardDAO.getInstance().delete(Integer.parseInt(request.getParameter("bId")), request.getParameter("pw"));
    }
}
