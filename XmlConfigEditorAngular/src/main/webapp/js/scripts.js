(function(){
angular.module('XmlConfigEditor', ['ui.bootstrap', 'ngAnimate'])

    .controller('ConfigViewController', ['$scope', 'editorService', function($scope, editorService){

        $scope.changedEntriesCount = 0;
        fetchConfigInfo();


        function fetchConfigInfo(){
            editorService.fetchConfig().then(
                function(result) {
                     $scope.configInfo = result;
                     console.log($scope.configInfo );
                }
            );
        }

        $scope.saveConfigInfo = function(){
            editorService.saveConfig($scope.configInfo).then(fetchConfigInfo);
        }

        $scope.configEntryValueChanged = function(entry, oldValue){
            console.log(oldValue);
            var backToOriginal = entry.value == entry.originalValue;
            var oneMoreNewValue =  oldValue != entry.originalValue;
            entry.changed = backToOriginal;
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