<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <thead>
        <tr>
            <th>카테고리 </th>
            <th>제목 </th>
            <th>작성자 </th>
            <th>조회수 </th>
            <th>등록 일시 </th>
            <th>수정 일시 </th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="b" items="${boardList}">
            String regDt = sdf.format(vo.getRegDt());
            String modDt = vo.getModDt() == null ? "-" : sdf.format(vo.getModDt());
            <tr>
                <td>${b.category} </td>
                <td><a href="/content.board?bId=${b.bId}&page=${pc.paging.page}">${b.title}</a></td>
                <td>${b.writer} </td>
                <td>${b.viewCnt} %></td>
                <td><fmt:formatDate value="${b.regDt}" pattern="yyyy.MM.dd HH:mm"/></td>
                <td><fmt:formatDate value="${b.modDt}" pattern="yyyy.MM.dd HH:mm"/></td>
            </tr>
        </c:forEach>
    </tbody>
    <tbody>
        <tr>
            <td>
                <ul class="pagination pagination-lg">

                    <c:if test="${pc.next}">
                        <li class="page-item"><a class="page-link"
                                                 href="/list.board?page=1"
                                                 style="background-color: #643691; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8"><<</a>
                        </li>
                    </c:if>

                    <%-- 이전 버튼 --%>

                    <c:if test="${pc.prev}">
                        <li class="page-item"><a class="page-link"
                                                 href="/list.board?page=${pc.beginPage-1}"
                                                 style="background-color: #643691; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8"><</a>
                        </li>
                    </c:if>

                    <%-- 페이지 버튼 (3항연산자로 왜 안되는지 재확인 -> 재작성하니 됨. 뭐지? ㅎ)--%>

                    <c:forEach var="p" begin="${pc.beginPage}" end="${pc.endPage}">
                        <li class="page-item"><a
                                href="/list.board?page=${p}"
                                class="page-link"
                                style="margin-top: 0; height: 40px; color: pink; border: 1px solid #643691; ${pc.paging.page==p ? 'background-color : orange; color: white' : ''}">${p}</a>
                        </li>
                    </c:forEach>

                    <%-- 다음 버튼 --%>

                    <c:if test="${pc.next}">
                        <li class="page-item"><a class="page-link"
                                                 href="/list.board?page=${pc.endPage+1}"
                                                 style="background-color: #643691; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">></a>
                        </li>
                    </c:if>

                    <c:if test="${pc.next}">
                        <li class="page-item"><a class="page-link"
                                                 href="/list.board?page=${pc.finalPage}"
                                                 style="background-color: #643691; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">>></a>
                        </li>
                    </c:if>

                </ul>
            </td>
        </tr>
    </tbody>
    <tbody>
    <tr>
        <td>
            <form action="/MyWeb/search.board" class="form-inline" >
                <div class="form-group">
                    <select name="category" class="form-control">
                        <option value="title">제목</option>
                        <option value="writer">작성자</option>
                        <option value="content">내용</option>
                    </select>
                    <input type="text" name="search" placeholder="제목검색" class="form-control" >
                    <input type="submit" value="검색" class="btn btn-default">
                    <input type="button" value="글 작성" class="btn btn-default" onclick="location.href='/MyWeb/write.board'">
                </div>
            </form>
        </td>
    </tr>
    </tbody>

</table>
<a href="write.jsp">등록</a>
<%} %>
</body>
</html>

