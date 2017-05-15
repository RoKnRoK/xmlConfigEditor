(function(){
  angular.module('XmlConfigEditor', ['ui.bootstrap'])
    .constant('servicePath', 'http://localhost:8080/XmlConfigEditorREST/rest/config/get')

  .controller('ConfigViewController', function($scope, $http, servicePath){

    $http.get(servicePath).
            then(function(response) {
                $scope.configRootBlock = response.data.configModificationInfo.configBlock;
                $scope.tabLevelBlocks = $scope.configRootBlock.childNodes
            });
  })
})();