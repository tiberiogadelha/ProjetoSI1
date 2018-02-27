app.config(
    function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'index.html'
            })
            .when('/produto', {
                templateUrl: 'produto.html',
                controller: 'produtoController'
            })
            .when('/lote', {
                templateUrl: 'lote.html',
                controller: 'loteController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }
);