angular.module("myApp").controller("vendasController", vendasController);

vendasController.$inject = ['$scope', 'produtoService'];

function vendasController($scope, produtoService) {

    $scope.titulo = "Venda de Produtos"
    $scope.produtos = [];
    $scope.produtosCarrinho = [];

    var carregarProdutos = function () {

        produtoService.get().then(function (data) {
            $scope.produtos = data;
            console.log("Produtos Carregados!!!")
        }).catch(function onRejected(errorResponse) {
            console.log('Erro em produtoService');
            console.log('status: ', errorResponse.status);
        });
    }

    $scope.venderProdutos = function () {
        if ($scope.produtosCarrinho[0] == null) {
            window.alert("Nenhum produto no carrinho");
        }
        console.log($scope.produtosCarrinho);

    }

    $scope.adicionarNoCarrinho = function (produto) {
        if (produto != null) {
            $scope.produtosCarrinho.push(produto);
            console.log($scope.produtosCarrinho);
            
        }
    }

    carregarProdutos();
}