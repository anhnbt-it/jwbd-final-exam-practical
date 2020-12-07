<%--
  Created by IntelliJ IDEA.
  User: anhnbt
  Date: 30/11/2020
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="title" scope="request" value="Product List"/>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <a href="${pageContext.request.contextPath}/admin/products?act=create"
                    class="btn btn-primary btn-sm"
                    data-toggle="tooltip" data-placement="top" title="Add New Product"><i class="fas fa-plus"></i> Add
                New Product</a>
        </div>
        <div class="col-md-6">
            <form action="${pageContext.request.contextPath}/admin/products" method="get" class="form-inline">
                    <input type="hidden" name="act" value="search">
                    <div class="form-group mb-2">
                        <label for="query" class="sr-only">Search:</label>
                        <input type="text" class="form-control" name="query" id="query"
                               placeholder="Search" required>
                    </div>
                    <button type="submit" class="btn btn-primary mb-2" data-toggle="tooltip" data-placement="top"
                            title="Filter"><i class="fas fa-search"></i> Search
                    </button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold"><i class="fas fa-list"></i> ${title}</h6>
                </div>
                <div class="card-body">
                    <% if (session.getAttribute("msg") != null) { %>
                    <%=session.getAttribute("msg") %>
                    <% session.removeAttribute("msg"); %>
                    <% } %>
                    <c:choose>
                        <c:when test="${requestScope['products'] == null}">
                            <div class="alert alert-info">No data.</div>
                        </c:when>
                        <c:otherwise>

                            <div class="table-responsive">
                                <table class="table table-striped table-bordered" id="dataTable">
                                    <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Color</th>
                                        <th scope="col">Category</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope['products']}" var="product">
                                        <tr>
                                            <th scope="row">${product.getId()}</th>
                                            <td>${product.getName()}</td>
                                            <td>${product.getPrice()}</td>
                                            <td>${product.getQty()}</td>
                                            <td>${product.getColor()}</td>
                                            <td>${product.getCategoryName()}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/admin/products?act=edit&id=${product.getId()}"
                                                   class="btn btn-info" data-toggle="tooltip" data-placement="top"
                                                   title="Edit"><i class="fas fa-pen"></i></a> | <a href="${pageContext.request.contextPath}/admin/products?act=delete&id=${product.getId()}"
                                                   class="btn btn-danger" data-toggle="tooltip" data-placement="top"
                                                   title="Delete"
                                                   onclick="return confirm('Are you sure you want to delete this item?');"><i
                                                        class="fas fa-trash"></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </c:otherwise>
                    </c:choose>
                </div><!-- /.card-body-->
            </div><!-- /.card -->
        </div><!-- /.col-md-12 -->
    </div><!-- /.row -->
</div><!-- /.container-fluid -->
<jsp:include page="../inc/footer.jsp"></jsp:include>
