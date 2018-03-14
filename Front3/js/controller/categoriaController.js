app.controller("categoriaController", categoriaController);

categoriaController.$inject = ['$scope','categoriaService'];

function categoriaController($scope, categoriaService) {

	$scope.titulo = "Configuração dos descontos";
	$scope.descontos = [
		{
			tipo: "Super Desconto",
			porcent: 0.5
		},
		{
			tipo: "Ótimo Desconto",
			porcent: 0.25
		},
		{
			tipo: "Bom Desconto",
			porcent: 0.1
		},
		{
			tipo: "Sem Desconto",
			porcent: 0
		}
	];

	$scope.categorias = [];

	$scope.categoria = {};
	$scope.tipoDeDesconto = {};

	

	$scope.definirDesconto = function(categoria, tipoDeDesconto) {
		angular.forEach($scope.categorias, function(categoriaSistema){
			if(categoriaSistema.nome == categoria.nome){
				window.alert("Oi");
				categoriaSistema.desconto = tipoDeDesconto;
			}
		});

		$scope.categoria = {};
		$scope.tipoDeDesconto = {};
	}
	
	var carregarCategorias = function () {

		categoriaService.get().then(function (data) {
			$scope.categorias = data;
			console.log("Categorias carregadas!");
			console.log($scope.categorias);
		}).catch(function onRejected(errorResponse) {
			console.log('Erro em categoriaService');
			console.log('status: ', errorResponse.status);
		});
	}
	
	carregarCategorias();
}