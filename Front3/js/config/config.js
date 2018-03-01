app.config(
    function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/templates/catalogo.html',
                controller: 'catalogoController'
            })
            .when('/admin', {
                templateUrl: '/templates/admin.html',
                controller: 'userController'
            })
            .
            when('/produto', {
                templateUrl: '/templates/produto.html',
                controller: 'produtoController'
            })
            .when('/lote', {
                templateUrl: '/templates/lote.html',
                controller: 'loteController'
            })
            .when('/venda', {
                templateUrl: '/templates/venda.html',
                controller: 'vendasController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }
);