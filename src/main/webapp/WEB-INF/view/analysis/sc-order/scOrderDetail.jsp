<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh" ng-app="orderTable">
<head>
    <meta charset="UTF-8">
    <title>转角店宝 - 数据分析</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
    <link rel="stylesheet" href="${root}/resources/css/scorder-goods.css">
</head>
<body class="wrap-bd" ng-controller="tableController">
<input type="hidden" name="orderId" class="input input-default" value="${orderId }" id="orderId">
	<div class="mb-default">
	    <a href="" class="crumb">联合采购数据分析</a>
	    <a href="" class="crumb crumb-active">商品信息明细报表</a>
	    <a href="" class="crumb crumb-active">订单商品明细</a>
	</div>
	<div class="h2">订单明细</div>
	<div class="order mb-default">
	    <div class="order-info">
	        <p>
	            订单编号：{{orderInfo.orderNo}}
	            <label class="label"></label>
	            <label class="label"></label>
	            订单金额：{{orderInfo.orderPrice}}
	        </p>
	        <p>
	            城市：{{orderInfo.cityName}}
	            <span class="ml-default mr-default"></span>
	            区域：{{orderInfo.areaName}}
	            <span class="ml-default mr-default"></span>
	            支付方式：{{orderInfo.scSupportmetho}}
	            <span class="ml-default mr-default"></span>
	            订单时间：{{orderInfo.addTime}}
	            <span class="ml-default mr-default"></span>
	            收货时间：{{orderInfo.addTime}}
	        </p>
	    </div>
	    <div class="other-info">
	        <p>
	            <b class="h3">商品信息</b><br>
	            商品SKU数：{{orderInfo.sku}}
	            <label class="label"></label>
	            商品数量：{{orderInfo.quantity}}
	            <label class="label"></label>
	            订单状态：{{orderInfo.scStatus}}
	        </p>
	        <p class="delivery">
	            <b class="h3">订单配送信息</b><br>
	            合作商名称：{{orderInfo.supplierName}}
	            <label class="label"></label>
	            联系方式：{{orderInfo.mobile}}
	            <label class="label"></label>
<!-- 	            送货地址：{{orderInfo.supplierAddress}}
	            <label class="label"></label> -->
	            配 送 商：{{orderInfo.supplierName}}
	        </p>
	    </div>
	</div>
	<div class="h2">订单商品明细 &nbsp;&nbsp;<input type="button" value="导出" ng-click="excelExport();" class="input input-search-button-white fr"></div>
	<div class="table-contain">
	    <pre>
	    <table class="table-list">
	        <thead class="table-thead">
	 	        <tr><th colspan="{{a.titleCount}}" ng-repeat="a in parentTitle">{{a.parentTitle}}</th></tr>
		        <tr><td ng-repeat="a in cn_keys">{{a}}</td> </tr>
	        </thead>
	        <tbody class="table-tbody">
	       		<tr ng-repeat="d_row in orderItemList"><td ng-repeat="b in d_row track by $index">{{b}}</td></tr>
	        </tbody>
	    </table>
	    </pre>
	</div>
    <tm-pagination conf="paginationConf"></tm-pagination>
    <div class="cover-loading" ng-show="isLoading"></div>
	<script>

	    var root = '<%=request.getContextPath() %>';
	    var tableController_url = root + '/api/sc_order/getScOrderDetail.do?';
	    var export_url = root + '/api/sc_order/portItemExcel.do';
	    var app = angular.module('orderTable', ['tm.pagination']); // 第二个参数定义了Module依赖

	    app.controller('tableController', ['$scope', 'spOrderSevrices', function ($scope, spOrderSevrices) {
	        
			$scope.queryOrders = function(flag){
	        	if(flag && flag ==1){
	        		$scope.paginationConf.currentPage = 1;
	        	}
				$scope.isLoading = true;
				var data = getParam();
				spOrderSevrices.getOrderList(data).success(function (result) {
		    		$scope.cn_keys = result.key_cn;
		    		$scope.orderItemList = result.orderItemList;
		    		$scope.parentTitle = result.parentTitle;
		    		$scope.orderInfo = result.orderInfo;
		    		$scope.paginationConf.totalItems = result.totalCount;
		    		$scope.isLoading = false;
	            });
	    	}
	    	
	    	var getParam = function() {
	    		var paramMap = {};
	    		paramMap["orderId"] = document.getElementById("orderId").value;
	    		paramMap["pageNo"] = $scope.paginationConf.currentPage || null;
	    		paramMap["offset"] = $scope.paginationConf.itemsPerPage || null;
	    		console.log("paramMap", paramMap);
	    		return paramMap;
	    	}

 	        $scope.paginationConf = {
	            currentPage: 1,
	            itemsPerPage: 10,
	        };
	    	
	    	var pagenationQuery = function() {
	        	$scope.queryOrders(0);
	        }
	    	
	    	$scope.excelExport = function(){
	    		var orderId = document.getElementById("orderId").value || ''; 
	    		var param = "orderId=" + orderId;
	    		console.log(param);
	    		location.href = export_url + "?" + param;
	    	}

	        $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', pagenationQuery);
	    }]);

	    app.service('spOrderSevrices', ['$http', function ($http) {
	    	
	        this.getOrderList = function (postData) {
	            return $http.post(tableController_url, postData);
	        }
	    }]);
	</script>
</body>
</html>
