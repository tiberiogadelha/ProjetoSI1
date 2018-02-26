app.config(
    function ($routeProvider) {
        $routeProvider
            .when('/index', {
                templateUrl: 'index.html',
                controller: ''
            })
            .when('/produto', {
                templateUrl: 'produto.html',
                controller: 'produtoController'
            }).when('/vendas', {
                templateUrl: 'vendas.html',
                controller: 'vendasController'
            }).otherwise({
                redirectTo: '/'
            });
    }
);