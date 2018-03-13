app.service('categoriaService', categoriaService);

function categoriaService($http) {

    this.get = function () {
        return (
            $http.get("http://localhost:8080/categoria")
                .then(function (response) {
                    return response.data;
                }).catch(function (response) {
                    console.error('As categorias não foram carregados');
                })
        )

    }

    

}