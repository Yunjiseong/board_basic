package com.study.controller;

import com.study.service.IBoardService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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

    protected void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        uri = uri.substring(contextPath.length() + 1, uri.lastIndexOf("."));
        logger.debug(uri);

        switch(uri) {
            case "write":
                logger.debug("글작성 요청");
                resp.sendRedirect("/board/write.jsp");
        }

    }


}
