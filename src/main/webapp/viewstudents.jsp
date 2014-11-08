<%@page language="java" contentType="text/html; charset=ISO-8859-1" session="true" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <title> Students List </title>
  <link type="text/css" rel="stylesheet" href="csss/pagination.css"/>
 </head>
 <body>
  <div>
  <table class="paginate">
    <tr class="colrow">
	  <th class="col"><a href="viewstudents.do?page=${form.pageNum}&sort=1">Roll</a></th>
	  <th class="col"><a href="viewstudents.do?page=${form.pageNum}&sort=2">Dob</a></th>
	  <th class="col"><a href="viewstudents.do?page=${form.pageNum}&sort=3">FirstName</a></th>
	  <th class="col"><a href="viewstudents.do?page=${form.pageNum}&sort=4">LastName</a></th>
	  <th class="col"><a href="viewstudents.do?page=${form.pageNum}&sort=5">Marks1</a></th>
	  <th class="col"><a href="viewstudents.do?page=${form.pageNum}&sort=6">Marks2</a></th>
	  <th class="col"><a href="viewstudents.do?page=${form.pageNum}&sort=7">Marks3</a></th>
	  <th class="col"><a href="viewstudents.do?page=${form.pageNum}&sort=8">Percent</a></th>
	</tr>
	<c:forEach var="student" items="${form.students}">
	  <tr>
	    <td class="rdata">${student.roll}</td>
		<c:set var="cdob" value="${student.dob}"/>
		<td class="rdata"><fmt:formatDate type="date" pattern="dd-MM-YYYY" value="${cdob}"/></td>
		<td class="rdata">${student.firstName}</td>
		<td class="rdata">${student.lastName}</td>
		<td class="rdata">${student.marks1}</td>
		<td class="rdata">${student.marks2}</td>
		<td class="rdata">${student.marks3}</td>
		<td class="rdata">${student.percent}</td>
	  </tr>
	</c:forEach>
	<%-- footer --%>
	<tr class="footer">
	  <c:if test="${form.pageNum} != 1">
	    <td><a href="viewstudents.do?page=${form.pageNum - 1}&sort=1">&gt;</a></td>
	  </c:if>
	  <td>
	    <%-- For page numbers --%>
		<table>
		  <tr>
		    <c:forEach begin="1" end="${form.totalRex}" var="i">
			  <c:choose>
			    <c:when test="${form.pageNum eq i}">
				  <td>${i}</td>
				</c:when>
				<c:otherwise>
				  <td><a href="viewstudents.do?page=${i}&sort=1">${i}</td>
				</c:otherwise>
			  </c:choose>
			</c:forEach>
		  </tr>
		</table>
	  </td>
	  <c:if test="${form.pageNum lt form.numOfPages}">
		  <td><a href="viewstudents.do?page=${form.pageNum + 1}&sort=1">Next</a></td>
	  </c:if>
	</tr>
  </table>
  </div>
 </body>
</html>
