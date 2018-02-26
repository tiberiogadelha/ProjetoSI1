app.config(
    function ($routeProvider) {
        $routeProvider
            .when('/produto', {
                templateUrl: 'produto.html',
                controller: 'produtoController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }
);