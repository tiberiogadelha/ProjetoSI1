app.service('registroService', registroService);

function registroService($http) {

    this.get = function () {
        return (
            $http.get("http://localhost:8080/registro")
                .then(function (response) {
                    return response.data;
                }).catch(function (response) {
                    console.error('Registros não foram carregados');
                })
        )

    }

    this.getUltimo = function () {
        return (
            $http.get("http://localhost:8080/registro/ultimo")
                .then(function (response) {
                    return response.data;
                }).catch(function (response) {
                    console.error('Registro não foram carregados');
                })
        )

    }

    this.post = function (registro) {
        $http.post("http://localhost:8080/registro", registro).then(function (data) {
            window.alert("Venda com suceso.");
            return registro;
        }).catch(function (response) {
            console.error('Erro ao registrar no sistema', response.status, response.data);
            window.alert("Erro ao adicionar o registro!");
            return response.status;
        });
    }

}
