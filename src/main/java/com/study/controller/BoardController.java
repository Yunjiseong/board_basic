package com.study.controller;

import com.study.service.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
    private RequestDispatcher dp;
    private IBoardService sv;
    private Logger logger;

    public BoardController() {};


    @Override
    public void init() throws ServletException {
        super.init();
        this.logger = LoggerFactory.getLogger("BoardController");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        doRequest(req, resp);
    }

    protected void doRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        uri = uri.substring(contextPath.length() + 1, uri.lastIndexOf("."));
        logger.debug(uri);

        switch(uri) {
            case "write-page":
                logger.debug("글쓰기 페이지 이동 요청");
                res.sendRedirect("/board/write.jsp");
                break;
            case "write":
                logger.debug("글 작성 요청");
                sv = new WriteService();
                sv.execute(req, res);
                res.sendRedirect("/list.board");
                break;
            case "content":
                logger.debug("글 상세보기 요청이 들어옴!");
                sv = new ContentService();
                sv.execute(req, res);

                dp = req.getRequestDispatcher("board/content.jsp");
                dp.forward(req, res);
                break;
            case "update-page":
                logger.debug("글 수정페이지로 이동 요청!");
                sv = new UpdateService();
                sv.execute(req, res);
                dp = req.getRequestDispatcher("board/update.jsp");
                dp.forward(req, res);
                break;

            case "update":
                logger.debug("글 수정 요청이 들어옴!");
                sv = new UpdateService();
                sv.execute(req, res);

                res.sendRedirect("/content.board?bId=" + req.getParameter("bId"));
                break;

            case "delete":
                logger.debug("글 삭제 요청이 들어옴!");
                sv = new DeleteService();
                sv.execute(req, res);
                res.sendRedirect("/content.board");
                break;

            case "search":
                logger.debug("글 검색 요청이 들어옴!");
                sv = new SearchService();
                sv.execute(req, res);
                if(req.getAttribute("boardList") != null) {
                    dp = req.getRequestDispatcher("board/board_list.jsp");
                    dp.forward(req, res);
                }
                break;
        }

    }


}
