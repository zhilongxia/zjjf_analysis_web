<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN" ng-app="user_grant">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>系统用户添加/编辑</title>
    <%@ include file="../../common/head.jsp"%>
</head>
<body class="wrap-bd" ng-controller="userInfoController">
<input type="hidden" name="name" class="input input-default" value=${id } id="user_id">
<div class="mb-default">
    <a href="" class="crumb">权限设置</a>
    <a href="" class="crumb">用户管理</a>
    <a href="" class="crumb crumb-active">用户创建/编辑</a>
</div>
<div class="wrap-bd bg">
    <div>
        <label class="label">用户名：</label>
        <select class="select" ng-model="userInfo.userName" ng-disabled="userNameReadOnly"  ng-options="userMap.userName as userMap.userName for userMap in userList">
		    <option value="">全部用户</option>
		</select>
       <!--  <input type="text" name="name" class="input input-default" ng-model="userInfo.userName" ng-blur="userName_change()"> -->
    </div>
    <p>
        <label class="label">角色：</label>
        <select class="select" ng-model="userInfo.roleId" ng-options="row_map.id as row_map.roleName for row_map in roleList" ng-change="changeRole()">
		    <option value="">全部角色</option>
		</select>
    </p>
    <div class="clearfix">
        <input type="hidden" ng-model="userSelected">
        <input type="hidden" ng-model="userLevel">
        <label class="label fl">数据权限：</label>
        <div class="fl" style="min-width: 200px; width: 85%; margin-left: 4px;">
        	<select ng-show="city_visible" class="select" ng-model="selectCity" ng-options="v.code as v.name for v in cityList" ng-change="changeCity()">
			    <option value="">全部城市</option>
			</select>
            <p ng-show="area_visible"> 
                <span style="display: inline-block; width: 100px; height: 20px; overflow:hidden;">
                	 <input type="checkbox" name="selected" class="checkbox" ng-click="selectAll($event)"/>全选
                </span>
                <span style="display: inline-block; width: 100px; height: 20px; overflow:hidden;" ng-repeat="b in areaList">
                	<input type="checkbox" ng-model="selected" ng-checked="isSelected(b.code)" ng-click="updateSelection($event, b.code)"/>{{b.name}}
                </span>
            </p>            
        </div>
    </div>
    <br/>
    <div>
        <button class="button button-ok" ng-click="addUser();">保存</button>
        <button class="button button-cancel" onclick="window.history.go(-1)">取消</button>
    </div>
</div>
<script>
    
    var root = '<%=request.getContextPath() %>';
    var loadUserInfo_url = root + '/api/authority/user/getEditUserView.do';
    var changeCity_url = root + '/api/authority/user/getAreaByCityId.do';
    var changeRole_url = root + '/api/authority/role/getByRoleId.do';
    var saveUserInfo_url = root + '/api/authority/user/saveUserInfo.do';
    var userNameRegular_url = root + '/api/authority/user/userNameRegular.do';
    
    var app = angular.module('user_grant',[]); // 第二个参数定义了Module依赖 
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

    app.controller('userInfoController', ['$scope', 'userNameRegularService',  'loadUserInfoService', 'getAreaByCityIdService', 'saveUserInfoService', 'getRoleService',
                   function ($scope, userNameRegularService, loadUserInfoService, getAreaByCityIdService, saveUserInfoService, getRoleService) {

    	var id = document.getElementById("user_id").value;
    	var add_or_update = id ==0 ? true : false;
    	
		var loadPage = function(){
			$scope.getUserInfo();
    	}
		
		$scope.getUserInfo = function() {
			var data = {id : id};
			loadUserInfoService.list(data).success(function (result) {

				$scope.roleList = result.roleList;
				$scope.userList = result.userList;
	    		$scope.userInfo = result.userInfo;
	    		$scope.userSelected = result.userSelected;
	    		$scope.userLevel = result.userInfo.level;
	    		
	    		if(!add_or_update){
		    		$scope.userNameReadOnly = true;
		    	}else{
		    		$scope.userNameReadOnly = false;
		    	}
	    		
	    		if(add_or_update){
	    			$scope.area_visible = false;
	    			$scope.city_visible = false;
	    			//$scope.cityList = result.listDict;
	    		}else{
	    			/* if(result.userInfo.level == 2){ */
		    			$scope.area_visible = true;
		    			$scope.city_visible = false;
		    			$scope.areaList = result.listDict;
		    		/* }else{
		    			$scope.area_visible = true;
		    			$scope.city_visible = false;
		    			$scope.areaList = result.listDict;
		    		} */
	    		}
	    		console.log($scope.userSelected);
            });	
		}
		
/* 		$scope.userName_change = function() {
			
			var data = {"userName" : $scope.userInfo.userName};
	        userNameRegularService.regular(data).success(function (result) {
	        	if(result.code !=1){
	        		alert("请输入已经注册过的用户名！");
	        	}
            });	
		} */
		
		// 保存
		$scope.addUser = function() {
			if(!check_submit()){
				return false;
			}
			var data = getParam();
			saveUserInfoService.save(data).success(function (result) {
				alert("新增用户成功！");
				window.history.go(-1);
            });	
		}
		
		// 根据城市获取区域
    	$scope.changeCity = function(){
    		var data = {};
    		data["cityId"] = ($scope.selectCity || null);
    		data["id"] = document.getElementById("user_id").value;
    		getAreaByCityIdService.list(data).success(function (result) {
    			$scope.areaList = result.areaList;
    			$scope.userSelected = result.userSelected;
    			$scope.area_visible = true;
            });	
    	}
		
    	$scope.changeRole = function(){
    		var data = {};
    		data["roleId"] = ($scope.userInfo.roleId || null);
    		getRoleService.list(data).success(function (result) {
    			console.log(result);
    			if(result.dataLevel == 1){
    				$scope.area_visible = true;
    				$scope.city_visible = false;
    				$scope.areaList = result.dataList;
    			}else if(result.dataLevel == 2){
    				$scope.city_visible = true;
    				$scope.area_visible = false;
    				$scope.cityList = result.dataList;
    			}
            });
    	}
    	
    	// 获取参数
    	var getParam = function() {
  
    		var paramMap = {};
    		paramMap["userName"] = $scope.userInfo.userName || null;
    		paramMap["roleId"] = $scope.userInfo.roleId || null;
    		paramMap["id"] = document.getElementById("user_id").value;
    		paramMap["areaCodes"] = $scope.userSelected +  ""|| null;
    		console.log("paramMap", paramMap);
    		return paramMap;
    	}	
    	
   	    $scope.updateSelection = function ($event, code) {
   	        var checkbox = $event.target;
   	        var action = (checkbox.checked ? 'add' : 'remove');
   	        updateSelected(action, code);
   	    };
   	    
        var updateSelected = function (action, code) {
            if (action == 'add' && $scope.userSelected.indexOf(code) == -1) {
            	$scope.userSelected.push(code);
            }
            if (action == 'remove' && $scope.userSelected.indexOf(code) != -1) {
            	$scope.userSelected.splice($scope.userSelected.indexOf(code), 1);
            }
            console.log($scope.userSelected);
        }
        
        $scope.selectAll = function ($event) {
            var checkbox = $event.target;
            var action = (checkbox.checked ? 'add' : 'remove');
            for (var i = 0; i < $scope.areaList.length; i++) {
                var entity = $scope.areaList[i];
                updateSelected(action, entity.code);
            }
        };

        $scope.isSelected = function (code) {
            return $scope.userSelected.indexOf(code) > -1;
        };
        
        // 提交校验
    	var check_submit = function () {
    		
    		if($scope.userInfo.userName == null){
    			alert("您还未输入用户名称！");
    			return false;
    		}
    		if($scope.userInfo.roleId == null || $scope.userInfo.roleId == 0 ){
    			alert("您还未选择角色！");
    			return false;
    		}
    		if($scope.userLevel = 2){
	    		if($scope.userSelected == null || $scope.userSelected.length == 0){
	    			alert("您还未选择城市区域！");
	    			return false;
	    		}
    		}
    		return true;
    	}
     
        $scope.$watch('viewContentLoaded', loadPage);
    }]);
    
    app.factory('loadUserInfoService', ['$http', function ($http) {
        var list = function (postData) {
            return $http.post(loadUserInfo_url, postData);
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
    
    // 获取城市区域
    app.factory('getRoleService', ['$http', function ($http) {
        var list = function (postData) {
            return $http.post(changeRole_url, postData);
        }
        return {
            list: function (postData) {
                return list(postData);
            }
        }
    }]);
    
    app.factory('saveUserInfoService', ['$http', function ($http) {
        var save = function (postData) {
            return $http.post(saveUserInfo_url, postData);
        }
        return {
        	save: function (postData) {
                return save(postData);
            }
        }
    }]);
    
    app.factory('userNameRegularService', ['$http', function ($http) {
        var regular = function (postData) {
            return $http.post(userNameRegular_url, postData);
        }
        return {
        	regular: function (postData) {
                return regular(postData);
            }
        }
    }]);
    
</script>
</body>
</html>
