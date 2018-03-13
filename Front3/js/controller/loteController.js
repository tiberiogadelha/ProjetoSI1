app.controller("loteController", loteController);

loteController.$inject = ['$scope', 'produtoService', 'loteService', '$filter'];

function loteController($scope, produtoService, loteService, $filter) {

    $scope.titulo = "Cadastro de Lote";
    $scope.lotes = [];
    $scope.lote = {};
    $scope.tipoDeOrdenacao = "+dataDeValidade";

    $scope.produtos = [{ id: 1, nome: "Feijão", numeroDeItens: 20, dataDeValidade: "31-01-2018", categoria: "Alimentício", fabricante: "Nestle" }];


    var carregaProdutos = function () {

        produtoService.get().then(function (data) {
            $scope.produtos = data;
            console.log("Produtos finalmente carregados")
            console.log($scope.produtos);
        }).catch(function onRejected(errorResponse) {
            console.log('status: ', errorResponse.status);
        });
    }

    carregaProdutos();

    var carregarLotes = function () {

        loteService.get().then(function (data) {
            $scope.lotes = data;
            console.log("Lotes finalmente carregados!!!")
        }).catch(function onRejected(errorResponse) {
            console.log('Erro em loteService');
            console.log('status: ', errorResponse.status);
        });
    }

    carregarLotes();

    $scope.cadastrarLote = function (lote) {
        var dataNova = $filter('date')(new Date(lote.dataDeValidade), 'yyyy-MM-dd');
        lote.dataDeValidade = dataNova;
        loteService.post(lote);
        $scope.lote = {};
        carregarLotes();

    }

    $scope.decrementaNoLote = function (lote, quantidade) {
        var produto = lote.produto;

        produto.quantidadeProduto -= quantidade;
    }

    $scope.ordenar = function (tipo) {
        console.log(tipo);
        $scope.tipoDeOrdenacao = tipo;

    }
}
