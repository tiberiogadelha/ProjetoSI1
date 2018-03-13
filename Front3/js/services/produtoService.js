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
          window.alert("Erro ao adicionar o produto!");
          return response.status;
        });
    }

    this.put = function(produto) {

    $http.put("http://localhost:8080/api/produto", produto).then(function (data) {
        return produto;
      }).catch(function (response) {
        console.error('Erro ao modificar no sistema', response.status, response.data);
        window.alert("Erro ao modificar o produto!");
        return response.status;
      });

    }
}
