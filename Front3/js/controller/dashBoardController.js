app.controller('dashBoardController', dashBoardController);

dashBoardController.$inject = ['$scope', 'userService','$window', 'loteService'];

function dashBoardController($scope, userService, $window, loteService) {

  var usuario = JSON.parse($window.localStorage.getItem('usuario'));

  $scope.titulo = "Controle do Sistema";
  $scope.admin = usuario.username;
  $scope.produtoVencimento = [];
  $scope.produtosVencidos = [];
  $scope.produtosEmFalta = [];
  $scope.produtosAcabando = [];

  $scope.logout = function () {
    userService.logout();
  }

  var carregaProdutosVencidos = function () {

    loteService.produtosVencidos().then(function (data) {
        $scope.produtosVencidos = data;
        console.log("Produtos vencidos carregados")
        console.log($scope.produtosVencidos);
    }).catch(function onRejected(errorResponse) {
        console.log('status: ', errorResponse.status);
    });
  }

  var carregaProdutosEmVencimento = function () {

    loteService.produtosVencimento().then(function (data) {
        $scope.produtosVencimento = data;
        console.log("Produtos em vencimento carregados")
        console.log($scope.produtosVencimento);
    }).catch(function onRejected(errorResponse) {
        console.log('status: ', errorResponse.status);
    });
  }

  var carregaProdutosEmFalta = function () {

    loteService.produtosEmFalta().then(function (data) {
        $scope.produtosEmFalta = data;
        console.log("Produtos vencidos carregados")
        console.log($scope.produtosVencidos);
    }).catch(function onRejected(errorResponse) {
        console.log('status: ', errorResponse.status);
    });
  }

  var carregaProdutosAcabando = function () {

    loteService.produtosAcabando().then(function (data) {
        $scope.produtosAcabando = data;
        console.log("Produtos acabando carregados")
        console.log($scope.produtosAcabando);
    }).catch(function onRejected(errorResponse) {
        console.log('status: ', errorResponse.status);
    });
  }

  carregaProdutosAcabando();
  carregaProdutosEmFalta();
  carregaProdutosEmVencimento();
  carregaProdutosVencidos();

}
