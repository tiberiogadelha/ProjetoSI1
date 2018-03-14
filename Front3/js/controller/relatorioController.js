app.controller("relatorioController", relatorioController);

relatorioController.$inject = ['$scope', 'produtoService','loteService','registroService'];

function relatorioController($scope, produtoService,loteService,registroService) {

    $scope.lotes = [];
    $scope.produtos = [];
    $scope.vendas = [];
    $scope.receita = 0;
    $scope.numeroTotalItens = 0;
    $scope.numeroTotalDeLotes = 0;
    $scope.tipoDeOrdenacao = "+id";
    var registro = [];

    var carregarRegitros = function () {

        registroService.get().then(function (data) {
            calcularReceitaTotal(data);
            $scope.vendas = data;
            console.log("Registros carregados!!!")
        }).catch(function onRejected(errorResponse) {
            console.log('Erro em RegistroService');
            console.log('status: ', errorResponse.status);
        });
    }

    function calcularReceitaTotal(data) {
        for (let index = 0; index < data.length; index++) {
            $scope.receita += data[index].precoTotal;            
        }        
    }

    function contarNumeroDeLotes(data) {
        for (let index = 0; index < data.length; index++) {
            $scope.numeroTotalDeLotes += 1;
            $scope.numeroTotalItens += data[index].numeroDeItens;            
        }
    }

    var carregarLotes = function () {

        loteService.get().then(function (data) {
            $scope.lotes = data;
            contarNumeroDeLotes(data);
            console.log("Lotes carregados!!!")
        }).catch(function onRejected(errorResponse) {
            console.log('Erro em loteService');
            console.log('status: ', errorResponse.status);
        });
    }

    $scope.ordenar = function (tipo) {
        $scope.tipoDeOrdenacao = tipo;

    }

    carregarLotes();
    carregarRegitros();
}
