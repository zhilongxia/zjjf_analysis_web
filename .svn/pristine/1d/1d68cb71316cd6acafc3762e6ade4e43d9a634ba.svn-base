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
<body class="wrap-bd" ng-controller="tableController" data-ng-init="loadPage()">
<input type="hidden" name="menuId" class="input input-default" value=${menuId } id="menuId">
	<div class="mb-default">
	    <a href="" class="crumb">财务数据分析</a>
	    <a href="" class="crumb crumb-active">合作商费用</a>
	</div>
	<div class="op-section">

	    <select class="select" ng-model="selectCity"  ng-disabled="cityReadOnly"  ng-options="v.code as v.name for v in citySelect" ng-change="changeCityOrArea(1)">
		    <option value="">全部城市</option>
		</select>
        <select class="select" ng-model="selectArea" ng-options="v.code as v.name for v in areaSelect" ng-change="changeCityOrArea(2)">
        	<option value="">全部区域</option>
		</select>
        <select class="select" ng-model="selectSpGroupId" ng-options="v.code as v.name for v in spGroupIdSelect">
        	<option value="">全部定格</option>
		</select>
	    <input type="text" placeholder="合作商" class="input input-search-text" ng-model="supplierName">&nbsp;&nbsp;&nbsp;&nbsp;
	           查询时间: &nbsp;&nbsp;<input type="text" class="input input-date" onchange="" date-picker ng-model="dayTime">
	</div>
	<div class="op-section clearfix">
	    <div class="fr">
	        <input type="button" value="查询" ng-click="queryOrders(1);" class="input input-search-button">
	        <input type="button" value="重置" ng-click="clearQuery();" class="input input-search-button-white">
	        <input type="button" value="导出" ng-click="excelExport();" class="input input-search-button-white">
	    </div>
	</div>
	<div class="table-contain">
	    <pre>
	    <table class="table-list">
	        <thead class="table-thead">
	 	        <tr><th colspan="{{a.titleCount}}" ng-repeat="a in parentTitle">{{a.parentTitle}}</th></tr>
		        <tr><td ng-repeat="a in cn_keys">{{a}}</td> </tr>
	        </thead>
	        <tbody class="table-tbody">
	       		<tr ng-repeat="d_row in key_dataList">
	       		<td ng-repeat="b in d_row track by $index">
	       			<span ng-if="$index == '12' || $index == '7'" ng-class="{true: 'txt-success', false: 'txt-warn'}[b >= 0]">{{b}}</span>
	       			<span ng-if="!($index == '12' || $index == '7')">
	       				{{b}}
	       			</span>
	       		</td>
	       		</tr>
	        </tbody>
	    </table>
        </pre>
	</div>
    <tm-pagination conf="paginationConf"></tm-pagination>
    <div class="cover-loading" ng-show="isLoading"></div>
	<script>

	    var root = '<%=request.getContextPath() %>';

	    var tableController_url = root + '/api/sp_order/financial/spOrderFinancialList.do';
	    var export_url = root + '/api/sp_order/financial/portExcel.do';
	    var changeCity_url = root + '/api/base_core/getCityAreaGroupById.do';
	    var getQueryParam_url = root + '/api/sp_order/financial/getQueryParam.do';

	    var app = angular.module('orderTable', ['My97Ext', 'tm.pagination']); // 第二个参数定义了Module依赖

	    app.controller('tableController', ['$scope', 'spOrderSevrices', function ($scope, spOrderSevrices) {

	    	$scope.items = [{'code':"yestoday", 'name':'昨天'}, {'code':"week", 'name':'7天前'}, {'code':"month", 'name':'30天前'},];

	    	$scope.loadPage = function () {
	        	$scope.isLoading = true;
				var data = getParam();
				spOrderSevrices.getQueryParam(data).success(function (result) {
					$scope.citySelect = result.optionList[0];
		    		$scope.areaSelect = result.optionList[1];
		    		$scope.spGroupIdSelect = result.optionList[2];
					if(result.level == 1){
			    		$scope.cityReadOnly = false;
		    		}else if(result.level == 2){
		    			$scope.cityReadOnly = true;
		    			$scope.selectCity = result.optionList[0][0].code;
		    		}
	            });
				defaultDayTime();
	        }
	        
			$scope.queryOrders = function(flag){
	        	if(flag && flag ==1){
	        		$scope.paginationConf.currentPage = 1;
	        	}
				$scope.isLoading = true;
				var data = getParam();
				spOrderSevrices.getOrderList(data).success(function (result) {
		    		$scope.cn_keys = result.key_cn;
		    		$scope.key_dataList = result.dataList;
		    		$scope.parentTitle = result.parentTitle;
		    		$scope.paginationConf.totalItems = result.totalCount;
		    		$scope.isLoading = false;
	            });
	    	}
	 
	    	$scope.excelExport = function(){
	    		var areaId = $scope.selectArea || ''; 
	    		var cityId = $scope.selectCity || '';
	    		var spGroupId = $scope.selectSpGroupId || ''; 
	    		var supplierName = $scope.supplierName || '';
	    		var dayTime = $scope.dayTime || '';
	    		var param = "cityId=" + cityId + "&areaId=" + areaId + "&spGroupId=" + spGroupId + "&supplierName=" + supplierName + "&dayTime=" + dayTime;
	    		console.log(param);
	    		location.href = export_url + "?" + param;
	    	}
	    	
	    	$scope.changeCityOrArea = function(type){
	    		var data = {};
	    		data["type"] = type;
	    		data["cityId"] = ($scope.selectCity || null);
	    		data["areaId"] = ($scope.selectArea || "");
	    		console.log(data);
	    		spOrderSevrices.changeCity(data).success(function (result) {
	    			if(type == 1){
	    				$scope.areaSelect = result.areaSelect;
	    			}
	    			$scope.spGroupIdSelect = result.spGroupIdSelect;
	            });
	    	}
	    	
	    	$scope.clearQuery = function(){
	    		$scope.selectCity = "";
	    		$scope.selectArea = "";
	    		$scope.selectSpGroupId = "";
	    		$scope.supplierName = "";
	    		defaultDayTime();
	    	}
	    	
	    	var getParam = function() {
	    		
	    		var paramMap = {};
	    		paramMap["cityId"] = $scope.selectCity || null;
	    		paramMap["areaId"] = $scope.selectArea || null;
	    		paramMap["spGroupId"] = $scope.selectSpGroupId || null;
	    		paramMap["supplierName"] = $scope.supplierName || null;
	    		paramMap["dayTime"] = $scope.dayTime || null;
	    		paramMap["limit"] = $scope.paginationConf.itemsPerPage || null;
	    		if($scope.paginationConf.itemsPerPage != null && $scope.paginationConf.currentPage != null){
	    			paramMap["offset"] = $scope.paginationConf.itemsPerPage * ($scope.paginationConf.currentPage - 1);
	    		}else{
	    			paramMap["offset"] = 0;
	    		}
	    		return paramMap;
	    	}

	    	
	    	$scope.checkTab = function(addDayCount) {
	    		$scope.selectDate(addDayCount);
	    	    $scope.queryOrders(1);
	    	}
	    	
	    	var defaultDayTime = function() {
	            var today = new Date();
	            var year = today.getFullYear();
	            var month = today.getMonth() + 1;
	            if(month < 10) {month = '0' + month;}
	            var date = today.getDate() - 1;
	            if(date < 10) {date = '0' + date;}
	            $scope.dayTime = year + "-" + month + "-" + date;
	        }
	    	
 	        $scope.paginationConf = {
	            currentPage: 1,
	            itemsPerPage: 10,
	        };
	    	
	    	var pagenationQuery = function() {
	        	$scope.queryOrders(0);
	        }
	        /***************************************************************
			        当页码和页面记录数发生变化时监控后台查询; 如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。
	        ***************************************************************/
	        $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', pagenationQuery);
	    }]);

	    app.service('spOrderSevrices', ['$http', function ($http) {
	    	
	        this.changeCity = function (postData) {
	        	return $http.post(changeCity_url, postData);
	        }
	        this.getQueryParam = function (postData) {
	        	return $http.post(getQueryParam_url, postData);
	        }
	        this.getOrderList = function (postData) {
	            return $http.post(tableController_url, postData);
	        }
	    }]);
	</script>
</body>
</html>
