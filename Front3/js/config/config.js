app.config(
    function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/templates/catalogo.html',
                controller: 'catalogoController'
            })
            .when('/dashboard', {
                templateUrl: '/templates/dashboard.html',
                controller: 'dashBoardController'
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
            .when('/descontos', {
                templateUrl: '/templates/descontos.html',
                controller: 'categoriaController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }
);