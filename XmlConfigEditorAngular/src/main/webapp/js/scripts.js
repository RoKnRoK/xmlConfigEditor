(function(){
angular.module('XmlConfigEditor', ['ui.bootstrap', 'ngAnimate'])

    .controller('ConfigViewController', ['$scope', '$timeout', 'editorService', function($scope, $timeout, editorService){


        fetchConfigInfo();


        function fetchConfigInfo(){
            editorService.fetchConfig().then(
                function(result) {
                     $scope.configInfo = result;
                     $scope.changedEntriesCount = 0;
                     $scope.savedAfterTimeout = false;
                     console.log($scope.configInfo );
                     $timeout(function(){return $scope.saveConfigInfo(false)}, $scope.configInfo.lockInfo.lockDuration);
                }
            );
        }

        $scope.saveConfigInfo = function(fetchAfterSave){
            console.log('Saving:');
            console.log($scope.configInfo);
            editorService.saveConfig($scope.configInfo).then(function(){
                if (!fetchAfterSave) {
                    $scope.changedEntriesCount = 0;
                    $scope.savedAfterTimeout = true;
                    return;
                }
                fetchConfigInfo();
            });

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