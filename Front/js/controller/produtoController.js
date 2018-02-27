angular.module("myApp").controller("produtoController",produtoController);

produtoController.$inject = ['$scope'];

function produtoController($scope){

    $scope.titulo = "Cadastro de Produtos"
    $scope.categorias = ["Alimentício","Limpeza","Cosméticos"]
    $scope.produtos = [{nome:"Feijão", quantidadeProduto:20,codigoDeBarra:"019912177",dataDeValidade:"31-01-2018", categoria:"Alimentício", fabricante:"Nestle"}];

    $scope.cadastraProduto = function(produto){
    
        $scope.produtos.push(produto);
        $scope.produto = {};
        
    }

    $scope.darPrecoAoProduto = function(produto, preco) {
        var precoDoProduto = parseFloat(preco);
    	produto.precoProduto = precoDoProduto;
    	console.log(produto);
    }

}