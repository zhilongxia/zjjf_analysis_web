<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setAttribute("root", request.getContextPath()); %>

<nav id="nav">
    <h1 class="logo"></h1>
    <ul>
        <c:forEach var="menuLevel" items="${menuTree}" varStatus="status">
        	<li>
	            <div class="category" data-direction="down"><i class="${menuLevel.treeClass}"></i>${menuLevel.menuName}<i class="icon-direction"></i></div>
	            <div class="subcategory">
	            	<c:forEach var="tree" items="${menuLevel.level2Tree}">
	                	<a href="${root }${tree.menuUrl}" target="mainiframe">${tree.menuName}${menuLevel.authorityStr}</a>
	                </c:forEach>
	            </div>
	        </li>
        </c:forEach>
    </ul>
</nav>