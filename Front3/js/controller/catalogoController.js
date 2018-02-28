app.controller("catalogoController", catalogoController);

catalogoController.$inject = ['$scope'];

function catalogoController($scope){
	  $scope.titulo = "Lojão";

   	$scope.produtosDisponiveis = [
   		{
   			id:1,
   			nome:"arroz",
   			preco: 1.25,
   			fabricante: "nestle",
   			numeroDeItens: 20,
   			dataDeValidade:"12-05-2015"
   		},
   		{
   			id:2,
   			nome:"feijao",
   			preco: 2.19,
   			fabricante: "seu Joao",
   			numeroDeItens: 60,
   			dataDeValidade:"12-05-2015"
   		}


  	 ];

    

}