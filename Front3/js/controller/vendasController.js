angular.module("myApp").controller("vendasController", vendasController);

vendasController.$inject = ['$scope', 'produtoService','registroService'];

function vendasController($scope, produtoService,registroService) {

    $scope.titulo = "Venda de Produtos"
    $scope.produtosDisponiveis = [];
    $scope.produtosCarrinho = [];
    $scope.ultimoRegistro = {};

    var carregarProdutos = function () {
        produtoService.get().then(function (data) {
            filtrarDisponibilidade(data);
            console.log("Produtos carregados!!!")
        }).catch(function onRejected(errorResponse) {
            console.log('Erro em produtoService');
            console.log('status: ', errorResponse.status);
        });
    }

    var filtrarDisponibilidade = function (produtos) {
		for (let index = 0; index < produtos.length; index++) {
			if (produtos[index].situacao === 1) {
				$scope.produtosDisponiveis.push(produtos[index]);
			}
		}
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
