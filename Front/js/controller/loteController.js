angular.module("myApp").controller("loteController",loteController);

loteController.$inject = ['$scope', 'produtoService'];

function loteController($scope, produtoService){

    $scope.titulo = "Cadastro de Lote";
    $scope.lotes = [];
    $scope.lote = []

    $scope.produtos = [{nomeProduto:"Feijão", quantidadeProduto:20,validadeProduto:"31-01-2018",categoriaProduto:"Alimentício", fabricante:"Nestle"}];

    /**
    var carregaProdutos = function () {

		produtoService.get().then(function (data) {
			$scope.produtos = data;
			console.log("Produtos Carregados")
			console.log($scope.produtos);
		}).catch(function onRejected(errorResponse) {
			console.log('status: ', errorResponse.status);
		});
	}

	carregaProdutos();
	*/

    $scope.cadastrarLote = function(lote){
    	console.log(lote);
        $scope.lotes.push(lote);
        $scope.lote = {};
        
    }

    $scope.decrementaNoLote = function(lote, quantidade) {
    	var produto = lote.produto;

    	produto.quantidadeProduto -= quantidade;
    }

    

}