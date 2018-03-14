app.controller("relatorioController", relatorioController);

relatorioController.$inject = ['$scope', 'produtoService', 'loteService', '$filter'];

function relatorioController($scope, produtoService, loteService, $filter) {

    $scope.lotes = [];
    $scope.produtos = [];
    $scope.receita = 12591.93;
    $scope.tipoDeOrdenacao = "+id";


  

    var carregarLotes = function () {

        loteService.get().then(function (data) {
            $scope.lotes = data;
            console.log("Lotes carregados!!!")
        }).catch(function onRejected(errorResponse) {
            console.log('Erro em loteService');
            console.log('status: ', errorResponse.status);
        });
    }

    carregarLotes();

    $scope.ordenar = function (tipo) {
        $scope.tipoDeOrdenacao = tipo;

    }

}
