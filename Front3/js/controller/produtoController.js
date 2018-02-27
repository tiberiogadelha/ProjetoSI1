app.controller("produtoController", produtoController);

produtoController.$inject = ['$scope','produtoService'];

function produtoController($scope,produtoService){

    $scope.produtos = [];

    $scope.titulo = 'Cadastro de Produtos'

    var carregarProdutos = function () {

		produtoService.get().then(function (data) {
			$scope.produtos = data;
			console.log("Produtos Carregados!!!")
		}).catch(function onRejected(errorResponse) {
			console.log('Erro em produtoService');
			console.log('status: ', errorResponse.status);
		});
    }

    $scope.cadastrarProduto = function (produto) {
		produtoService.post(produto);
		$scope.produto = {};
		carregarProdutos();
	}
    
    carregarProdutos();
}