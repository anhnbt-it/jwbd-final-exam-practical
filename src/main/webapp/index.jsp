<%--
  Created by IntelliJ IDEA.
  User: anhnbt
  Date: 07/12/2020
  Time: 08:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="title" scope="request" value="Home"></c:set>
<jsp:include page="inc/header.jsp"></jsp:include>
<c:out value="${title}"></c:out>
<jsp:include page="inc/footer.jsp"></jsp:include>
