<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh" ng-app="orderTable">
<head>
    <meta charset="UTF-8">
    <title>街坊店宝</title>
    <%@ include file="../../common/head.jsp"%>
    <script src="../../resources/js/vendor/My97DatePicker/WdatePicker.js"></script>
    <script src="../../resources/js/comm.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/angular/angular.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/pagination/tm.pagination.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/pagination_boostrap.css">
</head>
<body class="wrap-bd" ng-controller="tableController">
	<div class="mb-default">
	    <a href="" class="crumb">订单数据分析</a>
	    <a href="" class="crumb crumb-active">订单明细报表</a>
	</div>
	<div class="op-section"> 
	
	    <select class="select"  ng-model="selectCity" ng-options="v.code as v.name for v in citySelect" ng-change="changeCity()">
		    <option value="">全部城市</option>
		</select>
        <select class="select" ng-model="selectArea" ng-options="v.code as v.name for v in areaSelect">
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
	        <input type="text" class="input input-date J_timeS" onchange="" ng-model="addTimeBegin"> 至  <input type="text" class="input input-date J_timeE" onchange="" ng-model="addTimeEnd">
	        <span class="pills pills-active ml-default" >昨天</span>
	        <span class="pills">最近7天</span>
	        <span class="pills">最近30天</span>
	    </div>
	    <div class="fr">
	        <input type="button" class="input input-search-button" value="确定" ng-click="queryOrders();">
	        <input type="button" class="input input-search-button-white" value="导出" ng-click="excelExport();">
	    </div>
	</div>
	<div>
	    <table class="table-list">
	        <thead class="table-thead">
	 	        <tr><th colspan="4">订单归属信息</th><th colspan="5">订单基本信息</th><th colspan="5">订单金额信息</th><th colspan="4">订单状态信息</th></tr>
		        <tr><td ng-repeat="a in cn_keys">{{a}}</td> </tr>
	        </thead>
	        <tbody class="table-tbody">
	       		<tr ng-repeat="d_row in key_dataList"><td ng-repeat="b in d_row track by $index">{{b}}</td></tr>
	        </tbody>
	    </table>
	</div>
<%-- 	<%@ include file="../../common/pagination.jsp"%> --%>
    <tm-pagination conf="paginationConf"></tm-pagination>
	<script>
	    var root = '<%=request.getContextPath() %>';
	    
	    $(function() {
	        dateRangeSimple('.J_timeS', '.J_timeE');
	    });
	    
	    var tableController_url = root + '/api/sp_order/spOrderList.do';
	    var export_url = root + '/api/sp_order/portExcel.do';
	    var changeCity_url = root + '/api/base_core/getAreaByCityId.do';
	    
	    var app = angular.module('orderTable', ['tm.pagination']); // 第二个参数定义了Module依赖 
	    app.config(function($httpProvider) {
	        $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
	        $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
	        $httpProvider.defaults.transformRequest = [function(data) {
	            var param = function(obj) {
	                var query = '';
	                var name, value, fullSubName, subName, subValue, innerObj, i;
	                for (name in obj) {
	                    value = obj[name];
	                    if (value instanceof Array) {
	                        for (i = 0; i < value.length; ++i) {
	                            subValue = value[i];
	                            fullSubName = name + '[' + i + ']';
	                            innerObj = {};
	                            innerObj[fullSubName] = subValue;
	                            query += param(innerObj) + '&';
	                        }
	                    } else if (value instanceof Object) {
	                        for (subName in value) {
	                            subValue = value[subName];
	                            fullSubName = name + '[' + subName + ']';
	                            innerObj = {};
	                            innerObj[fullSubName] = subValue;
	                            query += param(innerObj) + '&';
	                        }
	                    } else if (value !== undefined && value !== null) {
	                        query += encodeURIComponent(name) + '='
	                                + encodeURIComponent(value) + '&';
	                    }
	                }
	                return query.length ? query.substr(0, query.length - 1) : query;
	            };
	            return angular.isObject(data) && String(data) !== '[object File]'
	                    ? param(data)
	                    : data;
	        }];
	    });

	    app.controller('tableController', ['$scope', 'getOrderlistService', 'getAreaByCityIdService', 'excelExportOrderlistService', 
	                                       function ($scope, getOrderlistService, getAreaByCityIdService, excelExportOrderlistService) {
	    	
	        var loadPage = function () {
	            $scope.queryOrders();
	        }
	        //配置分页基本参数
 	        $scope.paginationConf = {
	            currentPage: 1,
	            itemsPerPage: 5,
	            onChange: function(){
	            	//$scope.queryOrders();
	            }
	        }; 
	        // 查询订单
			$scope.queryOrders = function(){
				getOrderlistService.list(getParam()).success(function (result) {
					 // option 
		    		$scope.citySelect = result.optionList[0];
		    		$scope.areaSelect = result.optionList[1];
		    		$scope.spGroupIdSelect = result.optionList[2];
		    		$scope.supportmethoSelect = result.optionList[3];
		    		$scope.supportStatusSelect = result.optionList[4];
		    		$scope.statusSelect = result.optionList[5];
		    		// table
		    		$scope.cn_keys = result.key_cn;
		    		$scope.key_dataList = result.dataList;
		    		// pagination
		    		$scope.paginationConf.totalItems = result.totalCount;
	            });	
	    	}
	    	// 导出Excel
	    	$scope.excelExport = function(){
	    		
	    		//window.open(tableController_url, target="_blank");
	    		excelExportOrderlistService.list(getParam()).success(function (result) {
	    			window.open(result);
	            });	  	
	    	}
	    	// 根据城市获取区域
	    	$scope.changeCity = function(){
	    		var data = {"cityId" : ($scope.selectCity || null)};
	    		getAreaByCityIdService.list(getParam()).success(function (result) {
	    			$scope.areaSelect = result;
	            });	
	    		var data = {"cityId" : $scope.selectCity || null};
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
	    		
	    		paramMap["currentPage"] = $scope.paginationConf.currentPage || null;
	    		paramMap["itemsPerPage"] = $scope.paginationConf.itemsPerPage || null;
	            
	    		return paramMap;
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
	    app.factory('getAreaByCityIdService', ['$http', function ($http) {
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
