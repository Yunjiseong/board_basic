<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.study.model.BoardDAO" %>

<%
    String pw = request.getParameter("pw");
    if(BoardDAO.getInstance().delete(Integer.parseInt(request.getParameter("bId")), pw)){ %>
<script>
    alert("삭제가 정상 처리되었습니다.");
    location.href="list.jsp";
</script>
<%} else{%>
<script>
    alert("게시글 삭제에 실패했습니다.")
    location.href="list.jsp";
</script>
<%}%>
