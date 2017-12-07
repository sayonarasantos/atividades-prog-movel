angular.module('starter.api', []) 
.factory('WebApi', ['$http', function($http) {

    function getData(cep){
        return $http.get('http://viacep.com.br/ws/'+ cep + '/json/')
        .success(function(data,status){            
            console.log(data);
        })
        .error(function(data, status){
            console.log("Erro:" + status)
        });
    }

    return {
        getData : getData

    };

}]);
