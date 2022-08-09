<%@ page import="db.MemberService" %>
<%@ page import="java.util.List" %>
<%@ page import="db.Member" %>
<%--
  Created by IntelliJ IDEA.
  User: isihwa
  Date: 2022/07/06
  Time: 2:35 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            width: 100%;
        }

        th, td {
            border: solid 1px #000;
        }
    </style>
</head>
<body>
<%
    MemberService memberService = new MemberService();
    List<Member> memberList = memberService.list();
%>

<h1>회원 목록</h1>
<table>
    <thead>
    <tr>
        <th>회원 구분</th>
        <th>아이디</th>
        <th>비밀번호</th>
        <th>이름</th>
    </tr>
    </thead>
    <tbody>
    <tr>
            <%
                    for (Member m : memberList) {
                %>
    <tr>
        <td>
            <%=m.getMemberType()%>
        </td>
        <td>
            <a href="detail.jsp?memberType=<%=m.getMemberType() %>&userId=<%=m.getUserId()%>">
                <%=m.getUserId()%>
            </a>
        </td>
        <td>
            <%=m.getPassword()%>
        </td>
        <td>
            <%=m.getName()%>
        </td>
    </tr>
    <%
        }

    %>
    </tbody>
</table>
</body>
</html>
