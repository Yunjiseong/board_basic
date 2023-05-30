<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.study.model.BoardVO" %>
<%@ page import="com.study.model.BoardDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    List<String> categories = BoardDAO.getInstance().selectCategory();
    BoardVO vo = BoardDAO.getInstance().selectOne(Integer.parseInt(request.getParameter("bId")));
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    String reg_dt = sdf.format(vo.getRegDt());
    String mod_dt = vo.getModDt() == null ? "-" : sdf.format(vo.getModDt());
%>
<html>
<head>
    <h1>게시판 - 수정</h1>
    <form action="update_con.jsp" method="post">
        <input type="hidden" name="bId" value="<%=vo.getbId()%>">
        카테고리* &nbsp;&nbsp;&nbsp; <select name="category" disabled>
            <%
                for (String category : categories) { %>
            <option <%=category == vo.getCategory() ? "selected": ""%>><%=category%></option>
            <%} %>
        </select> <br>
        등록 일시&nbsp; <input type="text" name="regDt" value="<%=reg_dt%>" disabled> <br>
        수정 일시&nbsp; <input type="text" name="modDt" value="<%=mod_dt%>" disabled> <br>
        조회수&nbsp; <input type="text" name="viewCnt" value="<%=vo.getViewCnt()%>" disabled> <br>
        작성자* &nbsp;&nbsp;&nbsp; <input type="text" name="writer" value="<%=vo.getWriter()%>"> <br>
        비밀번호 &nbsp;&nbsp;&nbsp<input type="password" name="pw" placeholder="비밀번호"><br>
        제목* &nbsp;&nbsp;&nbsp; <input type="text" name="title" value="<%=vo.getTitle()%>"><br>
        내용* &nbsp;&nbsp;&nbsp; <textarea name="content" rows="10" cols="40"><%=vo.getContent()%></textarea><br>
        파일 첨부 &nbsp;&nbsp;&nbsp; <input type="text" id="filePath1" disabled> <input type="file" name="file1" value="<%=vo.getFile1()%>" onchange="displayFilePath(this, 'filePath1')"> <br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" id="filePath2" disabled> <input type="file" name="file2" value="<%=vo.getFile2()%>" onchange="displayFilePath(this, 'filePath2')"> <br>
        &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;<input type="text" id="filePath3" disabled> <input type="file" name="file3" value="<%=vo.getFile3()%>" onchange="displayFilePath(this, 'filePath3')"> <br>

        <button type="button" onclick="location.href='list.jsp'">취소</button> <input type="submit" value="등록">
    </form>
</head>
<body>
    <script>
        function displayFilePath(input, targetId) {
            var name = input.value.split('\\').pop();
            document.getElementById(targetId).value = name;
        }
    </script>
</body>
</html>
