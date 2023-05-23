<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.study.model.BoardVO" %>
<%@ page import="com.study.model.BoardDAO" %>
<%
    request.setCharacterEncoding("utf-8");
    BoardVO vo = new BoardVO();
    vo.setbId(Integer.parseInt(request.getParameter("bId")));
    vo.setWriter(request.getParameter("writer"));
    vo.setTitle(request.getParameter("title"));
    vo.setContent(request.getParameter("content"));
    vo.setFile1(request.getParameter("file1"));
    vo.setFile1(request.getParameter("file2"));
    vo.setFile1(request.getParameter("file3"));
    vo.setPw(request.getParameter("pw"));

    if(BoardDAO.getInstance().update(vo)) {%>
        <script>
            alert('게시글이 수정되었습니다.');
            location.href="content.jsp?bId=<%=vo.getbId()%>"
        </script>
    <%} else {%>
        <script>
            alert('게시글 수정에 실패했습니다.');
            window.history.back();
        </script>
    <%}%>





