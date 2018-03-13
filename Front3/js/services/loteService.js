app.service('loteService', loteService);

function loteService($http) {

    this.get = function () {
        return (
            $http.get("http://localhost:8080/api/lote")
                .then(function (response) {
                    return response.data;
                }).catch(function (response) {
                    console.error('Lotes não foram carregados');
                })
        )

    }

    this.produtosAcabando = function () {
        return (
            $http.get("http://localhost:8080/api/produtoacabando")
                .then(function (response) {
                    return response.data;
                }).catch(function (response) {
                    console.error('Produtos Acabando não foram carregados');
                })
        )

    }

    this.produtosEmFalta = function () {
        return (
            $http.get("http://localhost:8080/api/produtoemfalta")
                .then(function (response) {
                    return response.data;
                }).catch(function (response) {
                    console.error('Produtos Esgotados não foram carregados');
                })
        )

    }

    this.produtosVencidos = function () {
        return (
            $http.get("http://localhost:8080/api/lotevencido")
                .then(function (response) {
                    return response.data;
                }).catch(function (response) {
                    console.error('Produtos Vencidos não foram carregados');
                })
        )

    }

    this.produtosVencimento = function () {
        return (
            $http.get("http://localhost:8080/api/lotevencimento")
                .then(function (response) {
                    return response.data;
                }).catch(function (response) {
                    console.error('Produtos em Vencimento não foram carregados');
                })
        )

    }

    

    this.post = function (lote) {
        $http.post("http://localhost:8080/api/lote/" + lote.produto.id + "/", lote).then(function (data) {
            return lote;
        }).catch(function (response) {
            console.error('Erro ao adicionar ao sistema', response.status, response.data);
            window.alert("Erro ao adicionar o lote!");
            return response.status;
        });
    }

}
