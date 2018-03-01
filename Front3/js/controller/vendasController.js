angular.module("myApp").controller("vendasController", vendasController);

vendasController.$inject = ["$scope"];

function vendasController($scope){

    $scope.titulo = "Venda de Produtos"
    
}