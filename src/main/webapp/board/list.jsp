<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.study.model.BoardVO" %>
<%@ page import="com.study.model.BoardDAO" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    List<BoardVO> bList = BoardDAO.getInstance().selectAll();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 리스트</title>
</head>
<body>
<h2>게시글 리스트</h2>
<%if(bList.size() == 0) {%>
<h2>게시글이 존재하지 않습니다.</h2>
<%} else { %>
<table>
    <tr>
        <th>카테고리 </th>
        <th>제목 </th>
        <th>작성자 </th>
        <th>조회수 </th>
        <th>등록 일시 </th>
        <th>수정 일시 </th>
    </tr>
    <%
        for(BoardVO vo : bList){
            String regDt = sdf.format(vo.getRegDt());
            String modDt = vo.getModDt() == null ? "-" : sdf.format(vo.getModDt());%>
            <tr>
                <td><%=vo.getCategory() %> </td>
                <td><a href="content.jsp?bId=<%=vo.getbId()%>"><%=vo.getTitle()%></a></td>
                <td><%=vo.getWriter() %> </td>
                <td><%=vo.getViewCnt() %></td>
                <td><%=regDt %></td>
                <td><%=modDt %></td>
            </tr>
    <% }%>
</table>
<a href="write.jsp">등록</a>
<%} %>
</body>
</html>

