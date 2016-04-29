angular.module('My97Ext', []).directive('datePicker', function () {
    return {
        restrict: 'A',
        require: '?ngModel',
        scope: {},
        link: function (scope, element, attr, ngModel) {
            element.val(ngModel.$viewValue);
            element.bind('click', function () {
                WdatePicker({
                    dateFmt: 'yyyy-MM-dd',
                    isShowClear: false,
                    isShowWeek: true,
                    onpicking: function onpicking(dp) {
                        var date = dp.cal.getNewDateStr();
                        scope.$apply(function () {
                            ngModel.$setViewValue(date);
                        });
                    }
                })
            });
        }
    };
});
