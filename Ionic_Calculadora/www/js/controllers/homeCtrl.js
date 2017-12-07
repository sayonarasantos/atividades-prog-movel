angular.module('starter.controllers', [])

	.controller('homeCtrl', function($scope, $state, WebApi) {
		$scope.input = {};
		$scope.campos ={};

		$scope.click = false;
		$scope.getCep = function (cepInformado){
			$scope.click != $scope.click;
            return WebApi.getData(cepInformado)
            .then(function (res){
        		$scope.campos = res.data;
                console.log("Resposta obtida com sucesso")
                console.log(res)
            })
            .catch(function (error){
                console.log("Um erro aconteceu: " + error)
            })
        }



		//Code Here



		$scope.firstButton = function() {
			console.log("Meu primeiro botão")
		}

		$scope.segundaFuncao = function(a,b) {
			console.log("Meu primeiro parâmetro: " + a + " Meu segundo parâmetro: " + b)
		}

		//$scope.variavel = "Lucas";

		$scope.nome = '';

		$scope.trocarPagina = function() {
			$state.go("calc");
		}

	});
