package com.study.service;

import com.study.model.BoardDAO;
import com.study.model.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SearchService implements IBoardService {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String category = request.getParameter("category");

        String searchWord = request.getParameter("search");
        if(request.getParameter("search") == "") { // equals 쓰고, "".equals 앞에 쓰면 nullexcept 예방
            try {
                response.sendRedirect("/list.board");
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        List<BoardVO> list = BoardDAO.getInstance().searchBoard(searchWord, category);

        if(list.size() == 0) {
            response.setContentType("text/html; charset=UTF-8");

            try {
                PrintWriter out = response.getWriter();

                String htmlCode = "<script> \r\n" +
                        "alert(\"검색 결과에 따른 게시물이 없습니다.\"); \r\n"
                        + "location.href='/list.board'; \r\n"
                        + "</script>";
                out.print(htmlCode);
                out.flush();

                return;

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        request.setAttribute("boardList", list);
    }
}
