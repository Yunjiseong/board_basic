<%@ page import="java.util.List" %>
<%@ page import="com.study.model.BoardDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%
        List<String> categories = BoardDAO.getInstance().selectCategory();
    %>

<html>
<head>
    <h1>게시판 - 등록</h1>
    <form action="write_con.jsp" method="post">
        카테고리* &nbsp;&nbsp;&nbsp; <select name="category">
                                    <%
                                        for (String category : categories) { %>
                                            <option><%=category%></option>
                                        <%} %>
                                     </select> <br>
        작성자* &nbsp;&nbsp;&nbsp; <input type="text" name="writer"> <br>
        비밀번호* &nbsp;&nbsp;&nbsp; <input type="password" name="pw" placeholder="비밀번호"> <input type="password" name="pwCheck" placeholder="비밀번호 확인"><br>
        제목* &nbsp;&nbsp;&nbsp; <input type="text" name="title"><br>
        내용* &nbsp;&nbsp;&nbsp; <textarea name="content" rows="10" cols="40"></textarea><br>
        파일 첨부 &nbsp;&nbsp;&nbsp; <input type="text" id="filePath1" disabled> <input type="file" name="file1" onchange="displayFilePath(this, 'filePath1')"> <br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" id="filePath2" disabled> <input type="file" name="file2" onchange="displayFilePath(this, 'filePath2')"> <br>
                &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;<input type="text" id="filePath3" disabled> <input type="file" name="file3" onchange="displayFilePath(this, 'filePath3')"> <br>

        <button type="button" onclick="window.history.back()">취소</button> <input type="submit" value="등록">
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
