<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page import="com.study.model.BoardVO" %>
<%@ page import="com.study.model.BoardDAO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    int bId = Integer.parseInt(request.getParameter("bId"));
    BoardVO vo = BoardDAO.getInstance().selectOne(bId);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    String reg_dt = sdf.format(vo.getRegDt());
    String mod_dt = vo.getModDt() == null ? "-" : sdf.format(vo.getModDt());

    if (vo == null) {%>
    <h2>글 내용이 존재하지 않습니다.</h2>
    <%} else {%>



<html>
<head>
    <title>게시판 - 보기</title>
</head>
<body>
    <h1>게시판 - 보기</h1>
    <p>
        <%=vo.getWriter()%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 등록일시 <%=reg_dt%> &nbsp;&nbsp; 수정일시 <%=mod_dt%> <br>
        [<%=vo.getCategory()%>] &nbsp; <%=vo.getTitle()%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 조회수: <%=vo.getViewCnt()%><br>
        <textarea rows="5" readonly><%=vo.getContent()%></textarea> <br>
        <%=vo.getFile1()%> <br>
        <%=vo.getFile2()%> <br>
        <%=vo.getFile3()%> <br>
    </p>
    <a href="list.jsp">목록</a> &nbsp;
    <a href="modify.jsp?bId=<%=vo.getbId()%>">수정</a>
    <a href="delete.jsp?bId=<%=vo.getbId()%>">삭제</a>
</body>
</html>

<%}%>