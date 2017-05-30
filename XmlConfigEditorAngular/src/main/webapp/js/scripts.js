(function(){
angular.module('XmlConfigEditor', ['ui.bootstrap', 'ngAnimate'])

    .controller('ConfigViewController', ['$scope', 'editorService', function($scope, editorService){


        fetchConfigInfo();


        function fetchConfigInfo(){
            editorService.fetchConfig().then(
                function(result) {
                     $scope.configInfo = result;
                     $scope.changedEntriesCount = 0;
                     console.log($scope.configInfo );
                }
            );
        }

        $scope.saveConfigInfo = function(){
            console.log('Saving:');
            console.log($scope.configInfo);
            editorService.saveConfig($scope.configInfo).then(fetchConfigInfo);
        }

        $scope.configEntryValueChanged = function(entry, oldValue){
            console.log(entry.originalValue+ '->' + entry.value);
            var backToOriginal = entry.value == entry.originalValue;
            var oneMoreNewValue =  oldValue != entry.originalValue;
            if (backToOriginal) {
                $scope.changedEntriesCount--;
            }
            if (!oneMoreNewValue) {
                $scope.changedEntriesCount++;
            }
            console.log($scope.changedEntriesCount);
        }
    }])
})();