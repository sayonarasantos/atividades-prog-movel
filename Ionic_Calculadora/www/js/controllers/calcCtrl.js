angular.module('starter.controllers')

	.controller('calcCtrl', function($scope, $state, WebApi) {
		$scope.input = {};
		$scope.campo = {};

		$scope.op = 0;

        $scope.soma = function(a,b) {
        	if (b!=null && a!==null) {
        		$scope.op = 1;
           		$scope.campo.resultado = parseFloat(a)+parseFloat(b);
        	}
        	else{
        		$scope.campo.resultado = "Entradas inválidas"
        		$scope.op = 0;
        	}
		}

		$scope.subtracao = function(a,b) {
			if (b!=null && a!==null) {
        		$scope.op = 2;
           		$scope.campo.resultado = parseFloat(a)-parseFloat(b);
        	}
        	else{
        		$scope.campo.resultado = "Entradas inválidas"
        		$scope.op = 0;
        	}
		}

		$scope.divisao = function(a,b) {
        	if (parseFloat(b)==0.0){
        		$scope.campo.resultado = "Zero é entrada inválida"
        		$scope.op = 0;
        	}
        	else if (b!=null && a!==null) {
        		$scope.op = 3;
        		$scope.campo.resultado = parseFloat(a)/parseFloat(b);
        	}
        	else{
        		$scope.campo.resultado = "Entradas inválidas"
        		$scope.op = 0;
        	}
           	
		}

		$scope.multiplicacao = function(a,b) {
        	if (b!=null && a!==null) {
        		$scope.op = 4;
           		$scope.campo.resultado = Math.round(parseFloat(a)*parseFloat(b));
        	}
        	else{
        		$scope.campo.resultado = "Entradas inválidas"
        		$scope.op = 0;
        	}
		}
	
	});
