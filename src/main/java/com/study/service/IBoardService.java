package com.study.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IBoardService {
    void execute(HttpServletRequest request, HttpServletResponse response);
}
