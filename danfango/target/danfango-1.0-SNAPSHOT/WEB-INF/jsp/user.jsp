<%-- 
    Document   : user
    Created on : Apr 10, 2017, 4:25:02 PM
    Author     : charles
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>User Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add a User
</h1>

<c:url var="addAction" value="/user/add" ></c:url>

<form action="${addAction}" commandName="user">
<table>
	<c:if test="${!empty user.firstName}">
	<tr>
		<td>
			<label path="id">
                            <message text="ID"></message>
			</label>
		</td>
		<td>
			<input path="id" readonly="true" size="8"  disabled="true" />
			<hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<label path="firstName">
				<message text="First Name"/>
			</label>
		</td>
		<td>
			<input path="firstName" />
		</td> 
	</tr>
        <tr>
		<td>
			<label path="lastName">
				<message text="Last Name"/>
			</label>
		</td>
		<td>
			<input path="lastName" />
		</td> 
	</tr>
	<tr>
		<td>
			<label path="password">
				<message text="Password"/>
			</label>
		</td>
		<td>
			<input path="password" />
		</td>
	</tr>
        <tr>
		<td>
			<label path="email">
				<message text="Email"/>
			</label>
		</td>
		<td>
			<input path="email" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty user.firstName}">
				<input type="submit"
					value="<message text="Edit User"/>" />
			</c:if>
			<c:if test="${empty user.firstName}">
				<input type="submit"
					value="Add User"/>
			</c:if>
		</td>
	</tr>
</table>	
</form>
<br>
<h3>Users List</h3>
<c:if test="${!empty listUsers}">
	<table class="tg">
	<tr>
		<th width="80">User ID</th>
		<th width="120">User First Name</th>
		<th width="120">User Last Name</th>
                <th width="120">User Password</th>
                <th width="120">User Email</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listUsers}" var="user">
		<tr>
			<td>${user.id}</td>
			<td>${user.firstName}</td>
                        <td>${user.lastName}</td>
			<td>${user.password}</td>
                        <td>${user.email}</td>
			<td><a href="<c:url value='/edit/${user.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/remove/${user.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
