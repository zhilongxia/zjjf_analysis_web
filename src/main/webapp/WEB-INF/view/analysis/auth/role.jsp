
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh" ng-app="role_manager">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>系统角色管理</title>
    <%@ include file="../../common/head.jsp"%>
    <%@ include file="../../common/datepicker.jsp"%>
</head>
<body class="wrap-bd" ng-controller="roleTableController">
<div class="mb-default">
    <a href="" class="crumb">权限设置</a>
    <a href="" class="crumb crumb-active">角色管理</a>
</div>
<div class="op-section mb-small clearfix">
	<div class="fr">
	    <a href="<%=request.getContextPath() %>/api/authority/role/role_edit.do?menuId=25&edit_type=0"><button class="button button-white">创建角色</button></a>
	</div>
</div>
<table class="table-list table-border">
    <thead class="table-thead">
    <tr>
        <th>角色名</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody class="table-tbody">
    	<tr ng-repeat="d_row in roleList">
    		<td>{{d_row.roleName}}</td>
    		<td>{{d_row.createTimeStr}}</td>
    		<td class="table-op">
    			<a ng-if="d_row.roleName != 'admin'" ng-href="<%=request.getContextPath() %>/api/authority/role/role_edit.do?menuId=25&id={{d_row.id}}&edit_type=1" class="ml-default"><i class="icon-op icon-op-edit"></i> 编辑 </a>
                <a ng-if="d_row.roleName != 'admin'" href="#" ng-click="deleteRolefun(d_row.id)" class="ml-default" ng-confirm-click="确定要删除这个角色么?"><i class="icon-op icon-op-del"></i> 删除</a>
                <a ng-if="d_row.roleName != 'admin'" ng-href="<%=request.getContextPath() %>/api/authority/role/role_edit.do?menuId=25&id={{d_row.id}}&edit_type=2" class="ml-default"><i class="icon-op icon-op-copy"></i> 角色复制 </a>
            </td>
    	</tr>
    </tbody>
</table>
<script>
    
    var root = '<%=request.getContextPath() %>';

    var loadRoleController_url = root + '/api/authority/role/getRoleList.do';
    var deleteRoleController_url = root + '/api/authority/role/deleteRole.do'
    
    var app = angular.module('role_manager', ['My97Ext']); // 第二个参数定义了Module依赖 
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
    
    app.directive('ngConfirmClick', [
        function(){
            return {
                priority: 1,
                terminal: true,
                link: function (scope, element, attr) {
                    var msg = attr.ngConfirmClick || "Are you sure?";
                    var clickAction = attr.ngClick;
                    element.bind('click',function (event) {
                        if ( window.confirm(msg) ) {
                            scope.$eval(clickAction)
                        }
                    });
                }
            };
    	}]
    )

    app.controller('roleTableController', ['$scope', 'loadRoleService', 'deleteRoleService',  function ($scope, loadRoleService, deleteRoleService) {

		var loadPage = function(){
			loadRoleService.list({}).success(function (result) {
	    		$scope.roleList = result.roleList;
            });	
    	}
		
		$scope.deleteRolefun = function(id) {
			
			var data = {"id" : id};
			deleteRoleService.deleteRole(data).success(function (result) {
	    		alert("删除成功！");
	    		loadPage();
            });	
		}
		
		$scope.$watch('$viewContentLoaded', loadPage);
    }]);
    
    app.factory('loadRoleService', ['$http', function ($http) {
        var list = function (postData) {
            return $http.post(loadRoleController_url, postData);
        }
        return {
            list: function (postData) {
                return list(postData);
            }
        }
    }]);
    
    app.factory('deleteRoleService', ['$http', function ($http) {
        var deleteRole = function (postData) {
            return $http.post(deleteRoleController_url, postData);
        }
        return {
        	deleteRole: function (postData) {
                return deleteRole(postData);
            }
        }
    }]);
</script>
</body>
</html>
