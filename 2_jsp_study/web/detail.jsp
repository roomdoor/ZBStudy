<%@ page import="db.MemberService" %>
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
        String memberType = request.getParameter("memberType");
        String userId = request.getParameter("userId");
        MemberService memberService = new MemberService();
        Member member = memberService.detail(memberType, userId);
        System.out.print("memberType : ");
        System.out.println(memberType);
        System.out.print("userId : ");
        System.out.println(userId);
        System.out.println(member.getName());
    %>

        <h1>회원 상세 정보</h1>
        <table>
            <colgroup>
                <col style="width: 20%"/>
                <col style="width: 80%"/>
            </colgroup>
            <thead>
            </thead>
            <tbody>
                <tr>
                    <th>회원 구분</th>
                    <td><%=member.getMemberType()%></td>
                </tr>
                <tr>
                    <th>아이디</th>
                    <td><%=member.getUserId()%></td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td><%=member.getPassword()%></td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td><%=member.getName()%></td>
                </tr>
                <tr>
                    <th>전화번호</th>
                    <td><%=member.getMobile_no()%></td>
                </tr>
                <tr>
                    <th>마케팅 수신 여부</th>
                    <td><%=member.getMarketing_yn()%></td>
                </tr>
                <tr>
                    <th>가입일</th>
                    <td><%=member.getRegister_date()%></td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
