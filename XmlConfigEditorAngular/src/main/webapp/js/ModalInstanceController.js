(function(){
    angular.module('XmlConfigEditor').controller('ModalInstanceController', function ($scope, $uibModalInstance) {
      $scope.ok = function () {
        $uibModalInstance.close();
      };
    });
})()