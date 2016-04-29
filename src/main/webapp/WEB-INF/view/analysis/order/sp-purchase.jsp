<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh" ng-app="orderTable">
<head>
    <meta charset="UTF-8">
    <title>街坊店宝</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
</head>
<body class="wrap-bd" ng-controller="tableController">
<input type="hidden" name="menuId" class="input input-default" value=${menuId } id="menuId">
	<div class="mb-default">
	    <a href="" class="crumb">订单数据分析</a>
	    <a href="" class="crumb crumb-active">订单明细报表</a>
	</div>
	<div class="op-section">

	    <select class="select" ng-show="city_visible" ng-model="selectCity" ng-options="v.code as v.name for v in citySelect" ng-change="changeCity()">
		    <option value="">全部城市</option>
		</select>
        <select class="select" ng-show="area_visible" ng-model="selectArea" ng-options="v.code as v.name for v in areaSelect" ng-change="changeArea()">
        	<option value="">全部区域</option>
		</select>
        <select class="select" ng-model="selectSpGroupId" ng-options="v.code as v.name for v in spGroupIdSelect">
        	<option value="">全部定格</option>
		</select>
        <select class="select" ng-model="selectSupportmetho" ng-options="v.code as v.name for v in supportmethoSelect">
        	<option value="">全部支付方式</option>
		</select>
        <select class="select" ng-model="selectsupportStatus" ng-options="v.code as v.name for v in supportStatusSelect">
        	<option value="">全部支付状态</option>
		</select>
        <select class="select" ng-model="selectStatus" ng-options="v.code as v.name for v in statusSelect">
        	<option value="">全部配送状态</option>
		</select>
	</div>
	<div class="op-section">
	    <input type="text" placeholder="订单号/子订单号" class="input input-search-text" ng-model="orderNos">
	    <input type="text" placeholder="便利店名称" class="input input-search-text" ng-model="storeName">
	    <input type="text" placeholder="配送商" class="input input-search-text" ng-model="supplierName">
	</div>
	<div class="op-section clearfix">
	    <div class="fl">
	      	  下单时间
	        <input type="text" class="input input-date J_timeS" onchange="" date-picker ng-model="addTimeBegin"> 至  <input type="text" class="input input-date J_timeE" onchange="" date-picker ng-model="addTimeEnd">
		    <input type="hidden" ng-model="recentDay">
	        <span class="pills" ng-class="{'pills-active ml-default':isYesdotay}" ng-click="queryOrders(1);">昨天</span>
	        <span class="pills" ng-class="{'pills-active ml-default':isWeek}" ng-click="queryOrders(7);">最近7天</span>
	        <span class="pills" ng-class="{'pills-active ml-default':isMonth}" ng-click="queryOrders(30);">最近30天</span>
	    </div>
	    <div class="fr">
	        <input type="button" class="input input-search-button" value="确定" ng-click="queryOrders();">
	        <input type="button" class="input input-search-button-white" value="导出" ng-click="excelExport();">
	    </div>
	</div>
	<div>
	    <table class="table-list">
	        <thead class="table-thead">
	 	      <!--   <tr><th colspan="4">订单归属信息</th><th colspan="5">订单基本信息</th><th colspan="5">订单金额信息</th><th colspan="4">订单状态信息</th></tr> -->
		        <tr><td ng-repeat="a in cn_keys">{{a}}</td> </tr>
	        </thead>
	        <tbody class="table-tbody">
	       		<tr ng-repeat="d_row in key_dataList"><td ng-repeat="b in d_row track by $index">{{b}}</td></tr>
	        </tbody>
	    </table>
	</div>
    <tm-pagination conf="paginationConf"></tm-pagination>
	<script>

	    var root = '<%=request.getContextPath() %>';

	    var tableController_url = root + '/api/sp_order/spOrderList.do';
	    var export_url = root + '/api/sp_order/portExcel.do';
	    var changeCity_url = root + '/api/base_core/getCityAreaGroupById.do';

	    var app = angular.module('orderTable', ['My97Ext', 'tm.pagination']); // 第二个参数定义了Module依赖

	    app.controller('tableController', ['$scope', 'getOrderlistService', 'getCityAreaGroupByIdService', 'excelExportOrderlistService',
	           function ($scope, getOrderlistService, getCityAreaGroupByIdService, excelExportOrderlistService) {

	    	$scope.items = [{'code':"yestoday", 'name':'昨天'}, {'code':"week", 'name':'7天前'}, {'code':"month", 'name':'30天前'},];

	        var loadPage = function () {
	        	checkTab(1 || $scope.recentDay);
				var data = getParam();
				getOrderlistService.list(data).success(function (result) {
					 // option
		    		//$scope.areaSelect = result.optionList[1];
		    		$scope.spGroupIdSelect = result.optionList[1];
		    		$scope.supportmethoSelect = result.optionList[2];
		    		$scope.supportStatusSelect = result.optionList[3];
		    		$scope.statusSelect = result.optionList[4];
		    		// table
		    		$scope.cn_keys = result.key_cn;
		    		$scope.key_dataList = result.dataList;
		    		// pagination
		    		$scope.paginationConf.totalItems = result.totalCount;
		    		if(result.level == 1){
		    			$scope.citySelect = result.optionList[0];
		    			$scope.area_visible = true;
		    			$scope.city_visible = true;
		    		}else if(result.level == 2){
		    			$scope.areaSelect = result.optionList[0];
		    			$scope.area_visible = true;
		    			$scope.city_visible = false;
		    		}	
	            });
	        }
	        
	        //配置分页基本参数
 	        $scope.paginationConf = {
	            currentPage: 1,
	            itemsPerPage: 5,
	           /*  onChange: function(){
	            	//$scope.queryOrders();
	            } */
	        };
	        // 查询订单
			$scope.queryOrders = function(day){

				checkTab(day || $scope.recentDay);
				var data = getParam();
				getOrderlistService.list(data).success(function (result) {
		    		// table
		    		$scope.cn_keys = result.key_cn;
		    		$scope.key_dataList = result.dataList;
		    		// pagination
		    		$scope.paginationConf.totalItems = result.totalCount;
	            });
	    	}
	    	// 导出Excel
	    	$scope.excelExport = function(){

	    		var areaId = $scope.selectArea || ''; 
	    		var cityId = $scope.selectCity || '';
	    		var spGroupId = $scope.selectSpGroupId || ''; 
	    		var supportmetho = $scope.selectSupportmetho || '';
	    		var supportStatus =  $scope.selectsupportStatus || ''; 
	    		var status = $scope.selectStatus || '';
	    		var orderNos = $scope.orderNos || ''; 
	    		var storeName = $scope.storeName || '';
	    		var supplierName = $scope.supplierName || ''; 
	    		var addTimeBegin = $scope.addTimeBegin || '';
	    		var addTimeEnd = $scope.addTimeEnd || ''; 
	    		
	    		var param = "cityId=" + cityId + "&areaId=" + areaId + "&spGroupId=" + spGroupId + "&supportmetho=" + supportmetho
    				+ "&supportStatus=" + supportStatus + "&status=" + status + "&orderNos=" + orderNos + "&storeName=" + storeName + "&supplierName=" + supplierName
    				+ "&addTimeBegin=" + addTimeBegin + "&addTimeEnd=" + addTimeEnd;
	    		console.log(param);
	    		location.href = export_url + "?" + param;
	    	}
	    	// 根据城市获取区域
	    	$scope.changeCity = function(){
	    		var data = {"cityId" : ($scope.selectCity || null)};
	    		getCityAreaGroupByIdService.list(data).success(function (result) {
	    			$scope.area_visible = true;
	    			$scope.areaSelect = result;
	            });
	    	}
	    	
	    	$scope.changeArea = function(){
	    		var data = {"areaId" : ($scope.selectArea || null)};
	    		getCityAreaGroupByIdService.list(data).success(function (result) {
	    			$scope.spGroupIdSelect = result;
	            });
	    	}
	    	
	    	// 获取参数
	    	var getParam = function() {
	    		var paramMap = {};
	    		paramMap["cityId"] = $scope.selectCity || null;
	    		paramMap["areaId"] = $scope.selectArea || null;
	    		paramMap["spGroupId"] = $scope.selectSpGroupId || null;
	    		paramMap["supportmetho"] = $scope.selectSupportmetho || null;
	    		paramMap["supportStatus"] = $scope.selectsupportStatus || null;
	    		paramMap["status"] = $scope.selectStatus || null;
	    		paramMap["orderNos"] = $scope.orderNos || null;
	    		paramMap["storeName"] = $scope.storeName || null;
	    		paramMap["supplierName"] = $scope.supplierName || null;
	    		paramMap["addTimeBegin"] = $scope.addTimeBegin || null;
	    		paramMap["addTimeEnd"] = $scope.addTimeEnd || null;

	    		paramMap["menuId"] = document.getElementById("menuId").value;
	    		paramMap["pageNo"] = $scope.paginationConf.currentPage || null;
	    		paramMap["offset"] = $scope.paginationConf.itemsPerPage || null;

	    		return paramMap;
	    	}

	    	var checkTab = function(day) {

	    		day = day || 1;
	    		$scope.recentDay = day;
	    		if(day == 1){
	    			$scope.isYesdotay = true;
	 	  	    	$scope.isWeek = false;
	 	  	    	$scope.isMonth = false;
	    		}else if(day == 7){
	    			$scope.isYesdotay = false;
	 	  	    	$scope.isWeek = true;
	 	  	    	$scope.isMonth = false;
	    		}else if(day == 30){
	    			$scope.isYesdotay = false;
	 	  	    	$scope.isWeek = false;
	 	  	    	$scope.isMonth = true;
	    		}
	    		//set_calendar(day);
	    	}

			var set_calendar = function(day) {

				var myDate = new Date();
				var time1 = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" +  (myDate.getDate() - 1);
				if(day == 1){
					$scope.addTimeBegin = time1;
		    		$scope.addTimeEnd = time1;
	    		}else if(day == 7){
	    			var time2 = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" +  (myDate.getDate() - 7);
	    			$scope.addTimeBegin = time2;
		    		$scope.addTimeEnd = time1;
	    		}else if(day == 30){
	    			var time2 = myDate.getFullYear() + "-" + myDate.getMonth() + "-" +  myDate.getDate();
	    			$scope.addTimeBegin = time2;
		    		$scope.addTimeEnd = time1;
	    		}
	    	}
	        /***************************************************************
			        当页码和页面记录数发生变化时监控后台查询; 如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。
	        ***************************************************************/
	        $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', loadPage);
	    }]);

	    // 获取订单列表
	    app.factory('getOrderlistService', ['$http', function ($http) {
	        var list = function (postData) {
	            return $http.post(tableController_url, postData);
	        }
	        return {
	            list: function (postData) {
	                return list(postData);
	            }
	        }
	    }]);
	    // excel导出功能
	    app.factory('excelExportOrderlistService', ['$http', function ($http) {
	        var list = function (postData) {
	            return $http.get(export_url, postData);
	        }
	        return {
	            list: function (postData) {
	                return list(postData);
	            }
	        }
	    }]);
	    // 获取城市区域
	    app.factory('getCityAreaGroupByIdService', ['$http', function ($http) {
	        var list = function (postData) {
	            return $http.post(changeCity_url, postData);
	        }
	        return {
	            list: function (postData) {
	                return list(postData);
	            }
	        }
	    }]);
	</script>
</body>
</html>