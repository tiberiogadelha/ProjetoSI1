angular.module("myApp").controller("produtoController",produtoController);

produtoController.$inject = ['$scope'];

function produtoController($scope){

    $scope.titulo = "Cadastro de Produtos"
    $scope.categorias = ["Alimentício","Limpeza","Cosméticos"]
    $scope.produtos = [{nomeProduto:"Feijão", quantidadeProduto:20,validadeProduto:"31-01-2018",categoriaProduto:"Alimentício", fabricante:"Nestle"}];

    $scope.cadastraProduto = function(produto){
    
        $scope.produtos.push(produto);
        $scope.produto = {};
        
    }

    $scope.darPrecoAoProduto = function(produto, preco) {

    	produto.precoProduto = preco;
    	console.log(produto);
    }

}