<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh" ng-app="scOrderTable">
<head>
    <meta charset="UTF-8">
    <title>数据分析</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
</head>
<body class="wrap-bd" ng-controller="tableController" data-ng-init="loadPage()">
<input type="hidden" name="menuId" class="input input-default" value=${menuId } id="menuId">
<div class="mb-default">
    <a href="" class="crumb">联合采购数据分析</a>
    <a href="" class="crumb crumb-active">商品信息明细报表</a>
</div>
<div class="op-section">
    <select class="select" ng-model="selectCity"  ng-disabled="cityReadOnly"  ng-options="v.code as v.name for v in citySelect" ng-change="changeCityOrArea(1)">
	    <option value="">全部城市</option>
	</select>
       <select class="select" ng-model="selectArea" ng-options="v.code as v.name for v in areaSelect" ng-change="changeCityOrArea(2)">
       	<option value="">全部区域</option>
	</select>
	<select class="select" ng-model="selectStatus"  ng-options="v.code as v.name for v in productStatusSelect">
	    <option value="">全部商品状态</option>
	</select>
       <select class="select" ng-model="selectIsProfit" ng-options="v.code as v.name for v in isProfitSelect">
       	<option value="">是否盈利</option>
	</select>
</div>
<div class="op-section">
    <input type="text" placeholder="商品名称/商品编码" class="input input-search-text"  ng-model="name_mdseId">
    <input type="text" placeholder="品牌" class="input input-search-text"  ng-model="brand">
    <input type="text" placeholder="配送商" class="input input-search-text"  ng-model="supplierName">
</div>
<div class="op-section clearfix">
    <div class="fl">
                    查询时间
        <input id="timeStart" class="input input-date" type="text" ng-model="addTimeBegin" date-picker> 至 
        <input id="timeEnd" class="input input-date" type="text" ng-model="addTimeEnd" date-picker>
      <!--   <span class="pills ml-default" ng-class="{'pills-active':isYestertay}" ng-click="checkTab(-1);">昨天</span>
        <span class="pills" ng-class="{'pills-active':isWeek}" ng-click="checkTab(-7);">最近7天</span>
        <span class="pills" ng-class="{'pills-active':isMonth}" ng-click="checkTab(-30);">最近30天</span> -->
    </div>
    <div class="fr">
        <input type="button" value="确定" ng-click="queryOrders(1);" class="input input-search-button">
	    <input type="button" value="重置查询条件" ng-click="clearQuery();" class="input input-search-button-white">
	    <input type="button" value="导出" ng-click="excelExport();" class="input input-search-button-white">
    </div>
</div>
<div class="table-contain">
    <pre>
    <table class="table-list">
        <thead class="table-thead">
        <tr>
            <th colspan="3">商品归属信息</th>
            <th colspan="8">商品基本信息</th>
            <th colspan="2">商品价格数据</th>
            <th colspan="5">商品销售数据</th>
            <th rowspan="2" width="60">操作</th>
        </tr>
        <tr>
            <th>城市</th>
            <th>区域</th>
            <th>经销商名称</th>
            <th>商品条码</th>
            <th>一级分类</th>
            <th>二级分类</th>
            <th>品牌</th>
            <th>商品名称</th>
            <th>规格</th>
            <th>商品状态</th>
            <th>商品上下架时间</th>
            <th>转角销售价</th>
            <th>转角采购价</th>
            <th>下单次数</th>
            <th>销售数量</th>
            <th>交易额</th>
            <th>利润</th>
            <th>利润率</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <tr>
            <td>深圳</td>
            <td>南山</td>
            <td>鸿业经销商</td>
            <td>234232</td>
            <td>副食</td>
            <td>饮料</td>
            <td>娃哈哈</td>
            <td>娃哈哈矿泉水</td>
            <td>550ml</td>
            <td>上架</td>
            <td>2016-2-1</td>
            <td>10</td>
            <td>9</td>
            <td>5</td>
            <td>600</td>
            <td>6000</td>
            <td>600</td>
            <td>10%</td>
            <th>
                <a href="item-detail.html">查看</a>
            </th>
        </tr>
        </tbody>
    </table>
    </pre>
</div>
<div>
    <tm-pagination conf="paginationConf"></tm-pagination>
    <div class="cover-loading" ng-show="isLoading"></div>
</div>
<script>

	var root = '<%=request.getContextPath() %>';

	var tableController_url = root + '/api/sc_order/scOrderList.do';
    var changeCity_url = root + '/api/base_core/getCityAreaGroupById.do';
    var getQueryParam_url = root + '/api/base_core/getQueryParam.do';
    
	var app = angular.module('scOrderTable', ['My97Ext', 'tm.pagination']); // 第二个参数定义了Module依赖
	
	/* app.config(function($httpProvider) {
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
    }); */
    
	app.controller('tableController', ['$scope', 'getScOrderlistService', 'getCityAreaGroupByIdService', 'getQueryParamService',
	    function ($scope, getScOrderlistService, getCityAreaGroupByIdService, getQueryParamService) {

    	$scope.loadPage = function () {
        	$scope.isLoading = true;
			var data = {"dispatch" : "sc_order"}
			getQueryParamService.getInit(data).success(function (result) {
				if(result.level == 1){
		    		$scope.cityReadOnly = false;
	    		}else if(result.level == 2){
	    			$scope.cityReadOnly = true;
	    		}
				$scope.citySelect = result.optionList.city_option;
	    		$scope.areaSelect = result.optionList.area_option;
	    		$scope.selectStatus = result.optionList.sc_item_status_option;
	    		$scope.isProfitSelect = result.optionList.sc_isprofit_option;
	    		$scope.isLoading = false;
            });
        }
        
        //配置分页基本参数
	        $scope.paginationConf = {
            currentPage: 1,
            itemsPerPage: 5,
        };
        
        var pagenationQuery = function() {
        	
        	$scope.queryOrders(0);
        }
        
        // 查询订单
		$scope.queryOrders = function(flag){
        	
        	if(flag && flag ==1){
        		$scope.paginationConf.currentPage = 1;
        	}
			$scope.isLoading = true;
			var data = getParam();
			getScOrderlistService.list(data).success(function (result) {
	    		// table
	    		$scope.cn_keys = result.key_cn;
	    		$scope.key_dataList = result.dataList;
	    		// pagination
	    		$scope.paginationConf.totalItems = result.totalCount;
	    		$scope.isLoading = false;
            });
    	}
   
    	// 根据城市获取区域
    	$scope.changeCityOrArea = function(type){
    		var data = {};
    		data["type"] = type;
    		data["cityId"] = ($scope.selectCity || null);
    		data["areaId"] = ($scope.selectArea || "");

    		getCityAreaGroupByIdService.list(data).success(function (result) {
    			if(type == 1){
    				$scope.areaSelect = result.areaSelect;
    			}
            });
    	}
    	
    	$scope.clearQuery = function(){
    		
    		$scope.selectCity = "";
    		$scope.selectArea = "";
    		$scope.selectStatus = "";
    		$scope.selectIsProfit = "";
    		$scope.name_mdseId = "";
    		$scope.brand = "";
    		$scope.supplierName = "";
    		$scope.addTimeBegin = "";
    		$scope.addTimeEnd = "";
    	}
    	
    	// 获取参数
    	var getParam = function() {
    		var paramMap = {};
    		
    		paramMap["cityId"] = $scope.selectCity || null;
    		paramMap["areaId"] = $scope.selectArea || null;
    		paramMap["status"] = $scope.selectStatus || null;
    		paramMap["isProfit"] = $scope.selectIsProfit || null;
    		paramMap["name_mdseId"] = $scope.name_mdseId || null;
    		paramMap["brand"] = $scope.brand || null;
    		paramMap["supplierName"] = $scope.supplierName || null;
    		paramMap["addTimeBegin"] = $scope.addTimeBegin || null;
    		paramMap["addTimeEnd"] = $scope.addTimeEnd || null;

    		paramMap["menuId"] = document.getElementById("menuId").value;
    		paramMap["pageNo"] = $scope.paginationConf.currentPage || null;
    		paramMap["offset"] = $scope.paginationConf.itemsPerPage || null;

    		return paramMap;
    	}
        /***************************************************************
		        当页码和页面记录数发生变化时监控后台查询; 如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。
        ***************************************************************/
        $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', pagenationQuery);
    }]);
	
	// 获取订单列表
    app.factory('getScOrderlistService', ['$http', function ($http) {
        var list = function (postData) {
            return $http.post(tableController_url, postData);
        }
        return {
            list: function (postData) {
                return list(postData);
            }
        }
    }]);
	
    app.factory('getQueryParamService', ['$http', function ($http) {
        var getInit = function (postData) {
            return $http.post(getQueryParam_url, postData);
        }
        return {
        	getInit: function (postData) {
                return getInit(postData);
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
