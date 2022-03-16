const app = angular.module("myApp", [])

app.controller("myCtrl", function($scope, $http){
    $scope.items = {};
    $scope.message = [];

    $scope.showRegister = function () {
        $("#registerModal").modal("show");
    }

    $scope.showForgotPass = function () {
        $("#forgotPass").modal("show");
    }

    //Thêm 
    $scope.register = function () {
        var fullname = document.getElementById("fullname").value;
        var username = document.getElementById("username").value;
        var email = document.getElementById("email").value;
        var password = document.getElementById("password").value;
        var phone = document.getElementById("phone").value;
        $scope.items = {fullname, username, email, password, phone};
        var item = JSON.stringify(angular.copy($scope.items));
        $http.post(`/rest/register`, item).then(resp => {
            $scope.message = (resp.data);
            $("#registerModal").modal("hide");
            $("#modalTitle").text("Notification");
            $("#modalBody").text($scope.message.mess);
            $("#myModal").modal("show");
        }).catch(error => {
            $("#registerModal").modal("hide");
            $("#modalTitle").text("Notification");
            $("#modalBody").text("Error!");
            $("#myModal").modal("show");
            console.log("Error", error);
        })
    }

    $scope.forgot = function () {
        var mail = document.getElementById("email_forgot").value;
        $http.post(`/rest/forgot/${mail}`).then(resp => {
            $scope.message = (resp.data);
            $("#forgotPass").modal("hide");
            $("#modalTitle").text("Notification");
            $("#modalBody").text($scope.message.mess);
            $("#myModal").modal("show");
        }).catch(error => {
            $("#forgotPass").modal("hide");
            $("#modalTitle").text("Notification");
            $("#modalBody").text("Error!");
            $("#myModal").modal("show");
            console.log("Error", error);
        })
    }
})
