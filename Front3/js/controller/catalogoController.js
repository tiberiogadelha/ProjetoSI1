app.controller("catalogoController", catalogoController);

catalogoController.$inject = ['$scope', 'produtoService','userService'];

function catalogoController($scope, produtoService,userService) {

	$scope.titulo = "Lojão";

	$scope.produtosDisponiveis = [];

	var carregarProdutos = function () {

		produtoService.get().then(function (data) {			
			$scope.produtosDisponiveis = data;
			console.log("Produtos Carregados!!!")
		}).catch(function onRejected(errorResponse) {
			console.log('Erro em produtoService');
			console.log('status: ', errorResponse.status);
		});
	}

	$scope.cadastrarUsuario = function (usuario) {
		userService.cadastrar(usuario);
		$scope.usuarioCadastravel = {};
	  }
	
	  $scope.login = function (usuario) {
		userService.logar(usuario);
	  }
	
	  $scope.logout = function () {
		userService.deslogar();
	  }
	

	carregarProdutos();

}