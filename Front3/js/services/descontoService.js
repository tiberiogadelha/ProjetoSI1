app.service('descontoService', descontoService);

function descontoService($http) {

    this.post = function (categoria, tipoDeDesconto) {
        categoria.desconto = tipoDeDesconto.porcent;
        $http.post("http://localhost:8080/desconto/" + categoria.id , categoria).then(function (data) {
        }).catch(function (response) {
          console.error('Erro ao adicionar ao sistema', response.status, response.data);
          window.alert("Erro ao modificar o desconto!");
          return response.status;
        });
    }


}