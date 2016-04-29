<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<header id="header">
    <div class="info"><span class="username mr-small">您好&nbsp;&nbsp;${logInVo.userName }</span> | <a class="ml-small" href="<%=request.getContextPath() %>/analysis/authority/doLoginOut.do">退出</a></div>
</header>