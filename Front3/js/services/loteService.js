app.service('loteService', loteService);

function loteService($http) {

    this.get = function () {
        return (
            $http.get("http://localhost:8080/api/lote")
                .then(function (response) {
                    return response.data;
                }).catch(function (response) {
                    console.error('Lotes não carregados');
                })
        )

    }

    this.post = function (lote) {
        $http.post("http://localhost:8080/api/lote/" + lote.produto.id + "/", lote).then(function (data) {
            return lote;
        }).catch(function (response) {
            console.error('Erro ao adicionar ao sistema', response.status, response.data);
            window.alert("Erro ao adicionar lote!");
            return response.status;
        });
    }

}