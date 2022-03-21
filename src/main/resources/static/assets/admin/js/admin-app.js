app = angular.module("admin-app",["ngRoute"]);

app.config(function($routeProvider){
    $routeProvider
    .when("/product",{
        templateUrl: "product",
        controller: "product-ctrl"
    })
    .when("/category",{
        templateUrl: "category",
        controller: "category-ctrl"
    })
    .when("/brand",{
        templateUrl: "brand",
        controller: "brand-ctrl"
    })
    .when("/order",{
        templateUrl: "order",
        controller: "order-ctrl"
    })
    .when("/account",{
        templateUrl: "account",
        controller: "account-ctrl"
    })
    .when("/authority",{
        templateUrl: "authority",
        controller: "authority-ctrl"
    })
    .otherwise({
        templateUrl: "index"
    });
})