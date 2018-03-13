app.service('userService', userService);

userService.$inject = ['$http', '$location', '$window']

function userService($http, $location, $window) {

    this.cadastrar = function (usuario) {
        $http.post('http://localhost:8080/usuarios', usuario).then(function (result) {
            window.alert("Cadastrado com sucesso!!! :)");
        }).catch(function onRejected(errorResponse) {
            window.alert("Usuario já cadastrado!!! :(");
            console.log('Erro em userController');
            console.log('status: ', errorResponse);
        });
    }

    this.logar = function (usuario) {

        $window.localStorage.setItem('usuario', JSON.stringify(usuario));

        $http.post("http://localhost:8080/login", usuario).then(function (result) {
            $window.localStorage.setItem("token", result.data);

            $location.path("/produto");
            window.alert("Logado com sucesso!!! :)");
            window.location.reload();
        }).catch(function onRejected(errorResponse) {
            window.alert("Você tem problemas em logar :|");
            console.log('Erro em userService');
            console.log('status: ', errorResponse);
        });
    }

    this.logout = function () {
        $window.localStorage.setItem('token', "");
    }
}
