<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>街坊数据分析</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@ include file="../common/head.jsp"%>
    <link rel="stylesheet" href="${root}/resources/css/login.css?v">
	<!--[if lt IE 8 ]>
		<script>alert("建议您升级浏览器或更换浏览器获得更好的体验！祝您生活愉快！");</script>
	<![endif]-->
</head>
<body>
<div class="headbox">
	<div class="head wrap">欢迎光临街坊店宝！</div>
</div>
<div class="contontbox">
	<div class="wrap login-info">
		<div class="login-img"><img src="${root}/resources/images/login-img.png" width="494" height="458" alt=""></div>
		<div class="login">
			<div class="loginbox">
				<h1>登陆系统</h1>
				<div class="tip" style="display:none"></div>
				<form id="entryform" >
					<div class="text"><input type="text" value='' class="form-control" id="userName" name="userName" placeholder="帐号"></div>
					<div class="text"><input type="password" value='' class="form-control" id="password" name="password" placeholder="密码"></div>
					<div class="text">
						<input type="text" style="padding: 0 3px; width: 80px; height: 30px; border: 1px solid #ccc" name="checkCode" placeholder="验证码">
						<img src="${root}/checkcode.do" id="J_checkCodeImg" width="100" height="30">
					</div>
					<button type="button" class="login-btn" id="J_submitBtn">登　录</button>
				</form>
		     </div>
		</div>
	</div>
</div>
<script>
	$(function() {
		$('#J_checkCodeImg').on('click', function() {
			$(this).attr('src', '${root}/checkcode.do?' + Math.random());
		});
		$('#J_submitBtn').on('click', function() {
			var username = $("#userName").val().trim();
			var password = $("#password").val().trim();
			if("" == username) {
				$('.tip').text("用户名不能为空");
		    	$('.tip').show();
		    	return false;
			} else if ("" == password) {
				$('.tip').text("密码不能为空");
		    	$('.tip').show();
		    	return false;
			} else {
				$.ajax({
					type: "POST",
					url: "${root}/analysis/authority/userLoginIn.do",
					async: true,
					data: encodeURI($("#entryform").serialize()),
					success: function(date) {
						if(date.success) {
							location.href = "${root}"+date.url;
							//location.href = "${root}/toMain.do";
						} else {
							if(date.message == '验证码错误') {
								$('#J_checkCodeImg').trigger('click');
							};
							$(".tip").text(date.message);
							$(".tip").show();
						}
					},
					error: function(date) {
					}
				});
			}
		});
		$("input").on("focus", function(e) {
			$(".tip").hide();
		});
		// 回车
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
		    	$("#J_submitBtn").click();
			}
		}
	});
</script>
</body>
</html>