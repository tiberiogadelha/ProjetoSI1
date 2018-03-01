angular.module('myApp')
    .factory('AuthInterceptor', AuthInterceptor);

AuthInterceptor.$inject = ["$window"];

function AuthInterceptor($window) {

    return {
        // Send the Authorization header with each request
        'request': function (config) {
            config.headers = config.headers || {};
            var encodedString = $window.localStorage.getItem('token');
            if (encodedString) {
                config.headers.Authorization = 'Bearer ' + encodedString;
            }
            return config;
        }
    };

}
