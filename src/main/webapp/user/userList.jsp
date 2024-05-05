<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.user.model.*"%>

<%
    UserService userSvc = new UserService();
    List<UserVO> list = userSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
	  table#table-1 {
		background-color: #CCCCFF;
	    border: 2px solid black;
	    text-align: center;
        width: 100%;
        max-width: 800px;
        margin: 0 auto;
	  }
	  table#table-1 h4 {
	    color: red;
	    display: block;
	    margin-bottom: 1px;
	  }
	  h4 {
	    color: blue;
	    display: inline;
	  }
	  table {
		max-width: 100%;
		background-color: white;
		margin-top: 5px;
		margin-bottom: 5px;
	  }
	  table, th, td {
	    border: 1px solid #CCCCFF;
	  }
	  th, td {
	    padding: 5px;
	    text-align: center;
	    max-width: 100px;
	    max-height: 20px;
	    overflow: hidden;
	    text-overflow: ellipsis;
	    white-space: nowrap;
	  }
	  tr th:first-child{
	  	width: 50px;
	  }
	  
	  @media (max-width: 800px) {
        table, th, td {
            font-size: 12px;
        }
    	}
    
	</style>
</head>

<body>
    
    <table>
    	<tr>
    		<th>id</th>
    		<th>email</th>
    		<th>password</th>
    		<th>identity</th>
    		<th>company_name</th>
    		<th>register_date</th>
    		<th>last_login</th>
    		<th>last_ip</th>
    		<th>nickname</th>
    		<th>avatar</th>
    		<th>intro</th>
    	</tr>
    	<%@ include file="pagination.jsp" %>
		<c:forEach var="user" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			
			<tr>
				<td>${user.userId}</td>
				<td>${user.userEmail}</td>
				<td>${user.userPassword}</td>
				<td>${user.userIdentity}</td>
				<td>${user.userCompanyName}</td>
				<td>${user.userRegisterDate}</td>
				<td>${user.userLastLogin}</td>
				<td>${user.userLastIp}</td>
				<td>${user.userNickname}</td>
				<td>${user.userAvatar}</td>
				<td>${user.userIntro}</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/user/update" style="margin-bottom: 0px;">
				     <input type="submit" value="­×§ï">
				     <input type="hidden" name="userId"  value="${user.userId}">
				     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/user/delete" style="margin-bottom: 0px;">
				     <input type="submit" value="§R°£">
				     <input type="hidden" name="userId"  value="${user.userId}">
				     <input type="hidden" name="action" value="delete"></FORM>
				</td>
			</tr>
		</c:forEach>
    </table>
    <%@ include file="pageLink.jsp" %>
    
</body>

</html>