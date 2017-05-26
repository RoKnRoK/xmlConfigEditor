(function(){
  angular.module('XmlConfigEditor', ['ui.bootstrap', 'ngAnimate'])
    .constant('servicePath', 'http://localhost:8080/XmlConfigEditorREST/rest/config/get')

  .controller('ConfigViewController', function($scope, $http, servicePath){

    $http.get(servicePath).
            then(function(response) {
                console.log(response.data);
                $scope.configRootBlock = response.data.configBlock;
                console.log(response.data.configBlock);
                $scope.tabLevelBlocks = $scope.configRootBlock.childNodes
                console.log($scope.tabLevelBlocks);
            });
  })
})();