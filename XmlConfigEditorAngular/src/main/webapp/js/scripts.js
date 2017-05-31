(function(){
angular.module('XmlConfigEditor', ['ui.bootstrap', 'ngAnimate'])

    .controller('ConfigViewController', ['$scope', '$timeout', '$uibModal', 'editorService', function($scope, $timeout, $uibModal, editorService){

        var editingTimer;
        fetchConfigInfo();


        function fetchConfigInfo(){
            editorService.fetchConfig().then(
                function(result) {
                     $scope.configInfo = result;
                     $scope.changedEntriesCount = 0;
                     $scope.savedAfterTimeout = false;
                     console.log($scope.configInfo );
                     editingTimer = $timeout(function(){
                        var modalInstance = $uibModal.open({
                           animation: true,
                           ariaLabelledBy: 'modal-title',
                           ariaDescribedBy: 'modal-body',
                           templateUrl: 'templates/ErrorMessageTemplate.html',
                           controller: 'ModalInstanceController'
                        });
                        return $scope.saveConfigInfo(false)
                     }, $scope.configInfo.lockInfo.lockDuration);
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
                $timeout.cancel(editingTimer);
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

        $(window).on('beforeunload', function() {
            if ($scope.changedEntriesCount != 0) {
                return 'Your own message goes here...';
            }
        });
    }])
})();