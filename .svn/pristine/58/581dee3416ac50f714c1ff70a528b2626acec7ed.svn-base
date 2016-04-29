<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>转角店宝</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
    <%@ include file="../../common/pagination.jsp"%>
    <script>
        var app = angular.module('app', ['My97Ext', 'tm.pagination']);
        app.controller('paginationCtrl', function($scope, $http){
            $scope.paginationConf = {
                currentPage: 1,
                totalItems: 8000,
                itemsPerPage: 15,
                pagesLength: 15,
                perPageOptions: [10, 20, 30, 40, 50],
                onChange: function(){
//                    $http.get('xxx', function(data) {
//                        $scope.paginationConf.totalItems = data.totalItems;
//                    })
                }
            };
        })
    </script>
</head>
<body class="wrap-bd" ng-app="app">
<div class="mb-default">
    <a href="" class="crumb">订单数据分析</a>
    <a href="" class="crumb crumb-active">订单明细报表</a>
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
    <select class="select">
        <option value="">全部支付方式</option>
    </select>
    <select class="select">
        <option value="">全部支付状态</option>
    </select>
    <select class="select">
        <option value="">全部配送状态</option>
    </select>
</div>
<div class="op-section">
    <input type="text" placeholder="订单号/子订单号" class="input input-search-text">
    <input type="text" placeholder="便利店名称" class="input input-search-text">
    <input type="text" placeholder="配送商" class="input input-search-text">
</div>
<div class="op-section clearfix">
    <div class="fl">
        下单时间
        <input id="timeStart" class="input input-date" type="text" ng-model="timeStart" date-picker>
        至
        <input id="timeEnd" class="input input-date" type="text" ng-model="timeEnd" date-picker>
        <span class="pills pills-active ml-default">昨天</span>
        <span class="pills">最近7天</span>
        <span class="pills">最近30天</span>
    </div>
    <div class="fr">
        <input type="button" class="input input-search-button" value="确定">
        <input type="button" class="input input-search-button-white" value="导出">
    </div>
</div>
<div>
    <table class="table-list">
        <thead class="table-thead">
        <tr>
            <th colspan="4">订单归属信息</th>
            <th colspan="5">订单基本信息</th>
            <th colspan="5">订单金额信息</th>
            <th colspan="4">订单状态信息</th>
        </tr>
        <tr>
            <th>城市</th>
            <th>区域</th>
            <th>定格</th>
            <th>便利店名称</th>
            <th>下单时间</th>
            <th>订单号</th>
            <th>子订单号</th>
            <th>商品数量</th>
            <th>订单SKU</th>
            <th>订单商品价格</th>
            <th>优惠券金额</th>
            <th>满减</th>
            <th>订单金额</th>
            <th>订单费用</th>
            <th>订单支付方式</th>
            <th>支付状态</th>
            <th>配送商</th>
            <th>订单配送状态</th>
        </tr>
        </thead>
        <tbody class="table-tbody">
        <tr>
            <td>深圳</td>
            <td>南山</td>
            <td>滨海</td>
            <td>
                <a href="">发发发便利店</a>
            </td>
            <td>2016-3-10 12:20:30</td>
            <td>123456</td>
            <td>123456</td>
            <td>50</td>
            <td>5</td>
            <td>550.5</td>
            <td>10</td>
            <td>5</td>
            <td>535.5</td>
            <td>1</td>
            <td>微信支付</td>
            <td>已支付</td>
            <td>
                <a href="">祥发批发部</a>
            </td>
            <td>已派单</td>
        </tr>
        </tbody>
    </table>
</div>
<div ng-controller="paginationCtrl">
    <tm-pagination conf="paginationConf"></tm-pagination>
</div>
</body>
</html>
