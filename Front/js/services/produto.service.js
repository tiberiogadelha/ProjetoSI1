MyApp.service('produtoService', produtoService);

function produtoService($http) {

  this.get = function () {
    return (
      $http.get("http://127.0.0.1:8080/#/produtos")
        .then(function (response) {
          return response.data;
        })
    )

  }


}