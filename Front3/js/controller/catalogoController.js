app.controller("catalogoController", catalogoController);

catalogoController.$inject = ['$scope', 'produtoService', 'userService'];

function catalogoController($scope, produtoService, userService) {

	$scope.titulo = "Lojão";

	$scope.produtosDisponiveis = [];

	var carregarProdutos = function () {

		produtoService.get().then(function (data) {
			filtrarDisponibilidade(data);
			console.log("Produtos Carregados!!!")
		}).catch(function onRejected(errorResponse) {
			console.log('Erro em produtoService');
			console.log('status: ', errorResponse.status);
		});
	}

	var filtrarDisponibilidade = function (produtos) {
		for (let index = 0; index < produtos.length; index++) {
			if (produtos[index].situacao == 1) {
				$scope.produtosDisponiveis.push(produtos[index]);
			}
		}
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