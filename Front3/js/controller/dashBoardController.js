app.controller('dashBoardController', dashBoardController);

dashBoardController.$inject = ['$scope', 'userService','$window'];

function dashBoardController($scope, userService, $window) {

  var usuario = JSON.parse($window.localStorage.getItem('usuario'));

  $scope.titulo = "Controle do Sistema";
  $scope.admin = usuario.username;

  $scope.logout = function () {
    userService.logout();
  }

}
