(function(){
    angular.module('XmlConfigEditor')
    .constant('serviceUrl', 'http://localhost:8080/XmlConfigEditorRest/rest/config/')
    .factory('editorService', function($http, $q, serviceUrl){

        var factory = {
            fetchConfig: fetchConfig,
            saveConfig: saveConfig
        };

        return factory;

        function fetchConfig() {
            var deferred = $q.defer();
            $http.get(serviceUrl+'get')
                .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching config');
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;

        }


        function saveConfig(configInfo) {
        var deferred = $q.defer();
        $http(
            {   url: serviceUrl+'save',
                method: 'PUT',
                data: configInfo
            }).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while saving configuration');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
        }
    })
})()