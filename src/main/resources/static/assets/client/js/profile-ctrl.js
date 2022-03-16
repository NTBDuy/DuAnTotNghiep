const app = angular.module("myApp", [])

app.controller("myCtrl", function ($scope, $http) {
    $scope.items = [];
    $scope.itemPass = {};
    $scope.itemOrders = [];
    $scope.message = [];

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

    $scope.showChangePass = function() {
        $("#changPassModal").modal("show");
    }

    $scope.changePass = function() {
        var current_pass = document.getElementById("currentP").value;
        var new_pass = document.getElementById("newP").value;
        var confirm_pass = document.getElementById("confirmP").value;
        var username = document.getElementById("inputUsername").value;
        $scope.itemPass = {current_pass, new_pass, confirm_pass, username};
        var itemP = JSON.stringify(angular.copy($scope.itemPass));
        console.log($scope.itemPass);
        console.log("Json: ", itemP);
        $http.post(`/rest/profile/changePass`, itemP).then(resp => {
            $scope.message = (resp.data);
            $("#changPassModal").modal("hide");
            $("#modalTitle").text("Notification");
            $("#modalBody").text($scope.message.mess);
            $("#myModal").modal("show");
        }).catch(error => {
            $("#changPassModal").modal("hide");
            $("#modalTitle").text("Notification");
            $("#modalBody").text("Error!");
            $("#myModal").modal("show");
            console.log("Error", error);
        })
    }

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
            $("#modalTitle").text("Notification");
            $("#modalBody").text("Success!");
            $("#myModal").modal("show");
        }).catch(error => {
            $("#modalTitle").text("Notification");
            $("#modalBody").text("Error!");
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



    //QUẢN LÝ GIỎ HÀNG
    $scope.cart = {
        items: [],
        //Thêm sản phẩm vào giỏ hàng
        add(id) {
            var item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/cart/product/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Thêm vào giỏ hàng thành công!");
            $("#myModal").modal("show");
        },
        //Xóa sản phẩm khỏi giỏ hàng
        remove(id) {
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Đã xóa sản phẩm khỏi giỏ hàng!");
            $("#myModal").modal("show");
        },
        //Xóa sạch các mặt hàng trong giỏ
        clear() {
            this.items = [];
            this.saveToLocalStorage();
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Đã xóa toàn bộ sản phẩm khỏi giỏ hàng!");
            $("#myModal").modal("show");
        },

        // //Tính thành tiền của một sản phẩm
        // amt_of(item) { },

        //Tính tổng số lượng các mặt hàng trong giỏ
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },

        //Tổng thành tiền các mặt hàng trong giỏ
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },

        //Lưu giỏ hàng vào local storage
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },

        //Đọc giỏ hàng từ local storage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
            $scope.tong = $scope.cart.amount
        }
    }

    $scope.cart.loadFromLocalStorage();
})
