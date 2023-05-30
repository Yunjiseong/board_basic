package com.study.service;

import com.study.model.BoardDAO;
import com.study.model.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateService implements IBoardService {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int bId = Integer.parseInt(request.getParameter("bId"));
        BoardVO vo = BoardDAO.getInstance().selectOne(bId);
        request.setAttribute("article", vo);

    }
}
