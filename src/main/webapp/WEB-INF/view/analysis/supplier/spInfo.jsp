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
<div class="mb-default">
    <a href="" class="crumb">合作商数据分析</a>
    <a href="" class="crumb crumb-active">合作商明细报表</a>
</div>
<div class="op-section">
    <select class="select">
        <option value="">全部城市</option>
    </select>
    <select class="select">
        <option value="">全部区域</option>
    </select>
    <select class="select">
        <option value="">全部定格</option>
    </select>
    <input type="text" placeholder="合作商编号/名称" class="input input-search-text ml-default">
</div>
<div class="op-section clearfix">
    <div class="fl">
                   注册时间
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
            <th colspan="2">归属信息</th>
            <th colspan="5">合作商信息</th>
            <th colspan="4">配送便利店数据</th>
            <th colspan="5">销售数据</th>
            <th colspan="4">联合采购</th>
            <th rowspan="2" width="50">详情</th>
        </tr>
        <tr>
            <th>城市</th>
            <th>区域</th>
            <th>合作商编号</th>
            <th>合作商名称</th>
            <th>配送定格</th>
            <th>上架商品SKU</th>
            <th>无销量商品SKU</th>
            <th>分配配送店</th>
            <th>新增注册店</th>
            <th>活跃店</th>
            <th>高频店</th>
            <th>配送次数</th>
            <th>销售数量</th>
            <th>交易额</th>
            <th>费用金额</th>
            <th>费用率</th>
            <th>联合采购交易额</th>
            <th>利润</th>
            <th>利润率</th>
            <th>订单数</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <tr>
            <td>深圳</td>
            <td>南山</td>
            <td>22</td>
            <td>祥发批发部</td>
            <td>滨海</td>
            <td>800</td>
            <td>50</td>
            <td>50</td>
            <td>2</td>
            <td>25</td>
            <td>14</td>
            <td>250</td>
            <td>2700</td>
            <td>160000</td>
            <td>800</td>
            <td>0.5%</td>
            <td>32000</td>
            <td>160</td>
            <td>0.5%</td>
            <td>20</td>
            <td>
                <a href="sp-info-view.html">查看</a>
            </td>
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
