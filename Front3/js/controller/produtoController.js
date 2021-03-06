app.controller("produtoController", produtoController);

produtoController.$inject = ['$scope','produtoService', 'categoriaService'];

function produtoController($scope,produtoService, categoriaService){

    $scope.tipoDeOrdenacao = "+nome";
    $scope.produto = {};
    $scope.titulo = "Cadastro de Produtos"
    $scope.produtos = [];
    $scope.preco = 0.0;
    $scope.produtoModificado = {};
    $scope.categorias = [];

    var carregarProdutos = function () {

		produtoService.get().then(function (data) {
			$scope.produtos = data;
			console.log("Produtos finalmente carregados!!!")
		}).catch(function onRejected(errorResponse) {
			console.log('Erro em produtoService');
			console.log('status: ', errorResponse.status);
		});
    }

    var carregarCategorias = function () {

		categoriaService.get().then(function (data) {
			$scope.categorias = data;
			console.log("Categorias carregadas!");
		}).catch(function onRejected(errorResponse) {
			console.log('Erro em categoriaService');
			console.log('status: ', errorResponse.status);
		});
    }
    
    carregarCategorias();

    $scope.cadastrarProduto = function (produto) {


		produtoService.post(produto);
		$scope.produto = {};
		carregarProdutos();
	}

    carregarProdutos();

    $scope.ordenar = function(tipo) {
    	$scope.tipoDeOrdenacao = tipo;

    }

    $scope.configPreco = function(produto, preco) {
        produto.preco = preco;
        produtoService.put(produto);
        $scope.preco = 0.0;
        $scope.produtoModificado = {};
        carregarProdutos();
    }
}
