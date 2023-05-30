package com.study.service;

import com.study.model.BoardDAO;
import com.study.model.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ContentService implements IBoardService{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("bId") == null) {
            request.setAttribute("article", null);
            return;
        }

        int bId = Integer.parseInt(request.getParameter("bId"));

        BoardDAO.getInstance().upHit(bId); //메서드명 변경


        BoardVO vo = BoardDAO.getInstance().selectOne(bId);

        vo.setContent(vo.getContent().replaceAll("\n", "<br>"));
        request.setAttribute("article", vo);

    }
}
