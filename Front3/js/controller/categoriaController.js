app.controller("categoriaController", categoriaController);

categoriaController.$inject = ['$scope','categoriaService','descontoService'];

function categoriaController($scope, categoriaService,descontoService) {
	
	$scope.categorias = [];
	$scope.tipoDeDesconto = {};
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
			porcent: 1
		}
	];

	$scope.definirDesconto = function(categoria, tipoDeDesconto) {
		descontoService.post(categoria,tipoDeDesconto);	
		console.log(categoria);
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