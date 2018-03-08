app.controller('dashBoardController',dashBoardController);

dashBoardController.$inject = ['$scope','userService'];

function dashBoardController($scope,userService) { 

    $scope.titulo = "Controle do Sistema";


    $scope.logout = function () {
        console.log("oi");
        
		userService.logout();
	}

}