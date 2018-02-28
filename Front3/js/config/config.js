app.config(
    function ($routeProvider) {
        $routeProvider
            .when('/produto', {
                templateUrl: 'produto.html',
                controller: 'produtoController'
            })
            .when('/lote', {
                templateUrl: 'lote.html',
                controller: 'loteController'
            })
            .when('/catalogo', {
                templateUrl: 'catalogo.html',
                controller: 'catalogoController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }
);