app.service('produtoService', produtoService);

function produtoService($http) {

    this.get = function () {
        return (
            $http.get("http://localhost:8080/api/produto")
                .then(function (response) {
                    return response.data;
                }).catch(function (response) {
                    console.error('Produtos não carregados');
                })
        )

    }

    this.post = function (produto) {
        $http.post("http://localhost:8080/api/produto", produto).then(function (data) {
          return produto;
        }).catch(function (response) {
          console.error('Erro ao adicionar ao sistema', response.status, response.data);
          window.alert("Erro ao adicionar produto!");
          return response.status;
        });
    }

    this.put = function(produto) {
      var uri = "http://localhost:8080/api/produto";

      var action = $http({
        method: 'PUT',
        url: uri,
        headers: {"Content-Type": "application/json;charset=UTF-8" },
        data: produto
      });
      console.log(action);
      return action;
    }

}