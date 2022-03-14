const app = angular.module("myApp", [])

app.controller("myCtrl", function ($scope, $http) {
    $scope.items = [];
    $scope.itemOrders = [];

    $scope.initialize = function () {
        var username = $("#username").text();

        $http.get(`/rest/profile/${username}`).then(resp => {
            $scope.items = resp.data;
            $scope.items.register_date = new Date($scope.items.register_date)
        });

        $http.get(`/rest/profile/order/${username}`).then(resp => {
            $scope.itemOrders = resp.data;
            $scope.itemOrders.forEach(item => {
                item.orderDate = new Date(item.orderDate);
            })
        })
    }

    $scope.initialize();

    $scope.detailItemsFunc = function (item) {
        $("#orderDetailModal").modal("show");
        $http.get(`/rest/profile/order/detail/${item.id}`).then(resp => {
            $scope.detailItems = resp.data;
        })
    }

    //Cập nhật 
    $scope.update = function () {
        var item = angular.copy($scope.items);
        $scope.initialize();
        $http.put(`/rest/profile/${item.username}`, item).then(resp => {
            $scope.initialize();
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Cập nhật thành công!");
            $("#myModal").modal("show");
        }).catch(error => {
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Lỗi cập nhật!");
            $("#myModal").modal("show");
            console.log("Error", error);
        })
    }

    // Phân trang
    $scope.pager = {
        page: 0,
        size: 10,
        get itemOrders() {
            var start = this.page * this.size;
            return $scope.itemOrders.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.itemOrders.length / this.size);
        },

        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }
})
