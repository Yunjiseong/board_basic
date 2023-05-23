<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.study.model.BoardVO" %>
<%@ page import="com.study.model.BoardDAO" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>

    <%
        request.setCharacterEncoding("utf-8");

        BoardVO vo = new BoardVO();
        vo.setCategory(request.getParameter("category"));
        vo.setWriter(request.getParameter("writer"));
        vo.setPw(request.getParameter("pw"));
        vo.setTitle(request.getParameter("title"));
        vo.setContent(request.getParameter("content"));
        vo.setFile1(request.getParameter("file1"));
        vo.setFile2(request.getParameter("file2"));
        vo.setFile3(request.getParameter("file3"));

        if(!request.getParameter("pw").equals(request.getParameter("pwCheck"))) { %>
            <script>
                alert('비밀번호가 일치하지 않습니다.');
                window.history.back();
            </script>
        <%}
        else if(BoardDAO.getInstance().insert(vo)) { %>
            <script>
                alert('게시글이 등록되었습니다.');
                location.href="list.jsp"
            </script>
        <%} else {%>
                <script>
                    alert("게시글 등록에 실패했습니다.")
                    location.href="write.jsp"
                </script>
            <%} %>

