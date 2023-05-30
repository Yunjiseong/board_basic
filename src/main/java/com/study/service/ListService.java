package com.study.service;

import com.study.model.BoardDAO;
import com.study.model.BoardVO;
import com.study.common.PageCreator;
import com.study.common.PageVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class ListService implements IBoardService {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        BoardDAO dao = BoardDAO.getInstance();
        PageVO paging = new PageVO();

        if(request.getParameter("page") == null)
            paging.setPage(1);
        else // 한줄도 {}
            paging.setPage(Integer.parseInt(request.getParameter("page")));

        List<BoardVO> bList = dao.selectAll(paging);

        PageCreator pc = new PageCreator();
        pc.setPaging(paging);
        pc.setArticleTotalCount(dao.countArticles());


        request.setAttribute("boardList", bList);
        request.setAttribute("pc", pc);
    }
}
