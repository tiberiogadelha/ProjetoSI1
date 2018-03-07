app.controller("categoriaController", categoriaController);

categoriaController.$inject = ['$scope'];

function categoriaController($scope) {

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

	$scope.categorias = [
		{
			tipo: "Alimenticio",
			desconto: 0
		},
		{
			tipo: "Higiene",
			desconto: 0
		},
		{
			tipo: "Bebida",
			desconto: 0
		},
		{
			tipo: "Limpeza",
			desconto: 0
		}, {
			tipo: "Hortifruti",
			desconto: 0
		}
	];

	$scope.categoria = {};
	$scope.tipoDeDesconto = {};

	

	$scope.definirDesconto = function(categoria, tipoDeDesconto) {
		var porcentDeDesconto = buscaDesconto(tipoDeDesconto);
		angular.forEach($scope.categorias, function(categoriaSistema){
			if(categoriaSistema.tipo == categoria){
				categoriaSistema.desconto = porcentDeDesconto;
			}
		});

		$scope.categoria = {};
		$scope.tipoDeDesconto = {};
	}
	
	buscaDesconto = function(tipoDeDesconto) {
		var porcent = 0;
		angular.forEach($scope.descontos, function(desconto){
			if(desconto.tipo == tipoDeDesconto) {
				porcent = desconto.porcent;
			}
		});

		return porcent;
	}
}