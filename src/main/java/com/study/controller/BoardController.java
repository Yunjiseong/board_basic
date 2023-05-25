package com.study.controller;

import com.study.service.IBoardService;
import com.study.service.WriteService;
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
        }

    }


}
