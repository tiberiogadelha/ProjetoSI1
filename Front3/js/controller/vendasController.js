angular.module("myApp").controller("vendasController", vendasController);

vendasController.$inject = ['$scope', 'produtoService','registroService'];

function vendasController($scope, produtoService,registroService) {

    $scope.titulo = "Venda de Produtos"
    $scope.produtos = [];
    $scope.produtosCarrinho = [];
    $scope.ultimoRegistro = {};

    var carregarProdutos = function () {

        produtoService.get().then(function (data) {
            $scope.produtos = data;
            console.log("Produtos finalmente carregados!!!")
        }).catch(function onRejected(errorResponse) {
            console.log('Erro em produtoService');
            console.log('status: ', errorResponse.status);
        });
    }

    $scope.venderProdutos = function () {
        if ($scope.produtosCarrinho[0] == null) {
            window.alert("Nenhum produto no carrinho :(");
        }else {
            registro = {};
            registro.nomeCliente = $scope.registro.nomeCliente
            registro.data = new Date();
            registro.produtos = $scope.produtosCarrinho;
            registroService.post(registro);
        }
    }

    var ultimoRegistro = function () {

        registroService.getUltimo().then(function (data) {
            $scope.ultimoRegistro = data;
            console.log("Registro finalmente carregado!!!")
        }).catch(function onRejected(errorResponse) {
            console.log('Erro em registroService');
            console.log('status: ', errorResponse.status);
        });
    }

    $scope.adicionarNoCarrinho = function (produto) {
        if (produto != null) {
            $scope.produtosCarrinho.push(produto);
            console.log($scope.produtosCarrinho);

        }
    }

    carregarProdutos();
    ultimoRegistro();
}
