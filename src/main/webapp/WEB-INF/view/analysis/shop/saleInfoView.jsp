<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh" ng-app="app">
<head>
    <meta charset="UTF-8">
    <title>转角店宝 - 数据分析</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
</head>
<body class="wrap-bd" ng-controller="tableController">
<div class="mb-default clearfix">
    <div class="fl">
        <a href="" class="crumb">便利店数据分析</a>
        <a href="" class="crumb">便利店销售明细</a>
        <a href="" class="crumb crumb-active">便利店销售详细分析</a>
    </div>
    <div class="fr">
        <a href="javascript: history.go(-1);">返回</a>
    </div>
</div>
<div class="op-section clearfix">
    <div class="fl">
                    查询时间
        <input id="timeStart" type="text" ng-model="addTimeBegin" date-picker class="input input-date"> 至 
        <input id="timeEnd" type="text" ng-model="addTimeEnd" date-picker class="input input-date">
        <span class="pills ml-default" ng-class="{'pills-active':isYestertay}" ng-click="checkTab(-1);">昨天</span>
        <span class="pills" ng-class="{'pills-active':isWeek}" ng-click="checkTab(-7);">最近7天</span>
        <span class="pills" ng-class="{'pills-active':isMonth}" ng-click="checkTab(-30);">最近30天</span>
    </div>
    <div class="fr">
        <input type="button" class="input input-search-button" value="搜索">
        <input type="button" class="input input-search-button-white" value="导出">
    </div>
</div>
<div class="table-contain">
    <pre>
    <table class="table-list">
        <thead class="table-thead">
        <tr>
            <th colspan="3">便利店信息</th>
            <th colspan="6">订单基本信息</th>
        </tr>
        <tr>
            <th>便利店ID</th>
            <th>便利店编号</th>
            <th>便利店名称</th>
            <th>下单次数</th>
            <th>销量SKU</th>
            <th>销售数量</th>
            <th>交易额</th>
            <th>费用金额</th>
            <th>费用率</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
    </pre>
</div>
<div>
    <tm-pagination conf="paginationConf"></tm-pagination>
</div>
<script>
var app = angular.module('app', ['My97Ext', 'tm.pagination']);
app.controller('tableController', function($scope) {
    $scope.checkTab = function(addDayCount) {
        if(-1 == addDayCount) {
            $scope.isYestertay = true;
            $scope.isWeek = false;
            $scope.isMonth = false;
        } else if(-7 == addDayCount) {
            $scope.isYestertay = false;
            $scope.isWeek = true;
            $scope.isMonth = false;
        } else if(-30 == addDayCount) {
            $scope.isYestertay = false;
            $scope.isWeek = false;
            $scope.isMonth = true;
        }
        var today = new Date();
        var year = today.getFullYear();
        var month = today.getMonth() + 1;
        if(month < 10) {month = '0' + month;}
        var date = today.getDate();
        if(date < 10) {date = '0' + date;}
        $scope.addTimeEnd = year + "-" + month + "-" + date;
        today.setDate(today.getDate() + addDayCount);
        year = today.getFullYear();
        month = today.getMonth() + 1;
        if(month < 10) {month = '0' + month;}
        date = today.getDate();
        if(date < 10) {date = '0' + date;}
        $scope.addTimeBegin = year + "-" + month + "-" + date;
    }
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 800,
        itemsPerPage: 15,
        pagesLength: 15,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function(){
            //$http.get('xxx', function(data) {
            //    $scope.paginationConf.totalItems = data.totalItems;
            //})
        }
    };
});
</script>
</body>
</html>
