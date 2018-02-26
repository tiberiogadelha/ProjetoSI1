angular.module("myApp").config(
    function ($routeProvider) {
        $routeProvider
            .when('/produtos', {
                templateUrl: 'produtos.html',
                controller: 'produtoController'
            }).when('/vendas', {
                templateUrl: 'vendas.html',
                controller: 'vendasController'
            })
            .when('/login', {
            	templateUrl: 'login.html',
            	controller: 'loginController'
            })
            .when('/lote', {
            	templateUrl: 'lote.html',
            	controller: 'loteController'
            })
            .when('/preco', {
                templateUrl: 'preco.html',
                controller: 'produtoController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }
);