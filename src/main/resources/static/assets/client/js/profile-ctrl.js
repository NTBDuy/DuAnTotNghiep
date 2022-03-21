const app = angular.module("myApp", [])

app.controller("myCtrl", function ($scope, $http) {
    // Thông tin người dùng
    $scope.items = [];
    // Mảng dữ liệu dùng để thay đổi mật khẩu
    $scope.itemPass = {};
    // Thông tin hoá đơn của người dùng
    $scope.itemOrders = [];
    // Dữ liệu xuất thông báo
    $scope.message = [];
    // Dữ liệu xuất FileData

    // Khởi tạo
    $scope.initialize = function () {
        var username = $("#username").text();
        // Lấy thông tin người dùng.
        $http.get(`/rest/profile/${username}`).then(resp => {
            $scope.items = resp.data;
            $scope.items.register_date = new Date($scope.items.register_date);
        });
        // Lấy danh sách hoá đơn người dùng.
        $http.get(`/rest/profile/order/${username}`).then(resp => {
            $scope.itemOrders = resp.data;
            $scope.itemOrders.forEach(item => {
                item.orderDate = new Date(item.orderDate);
            })
        })
    }
    $scope.initialize();

    // Upload hình ảnh cho người dùng
    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]);
        var item = angular.copy($scope.items);
        $http.post(`/rest/upload/${item.username}`, data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.fileData = resp.data;
            $scope.items.image = $scope.fileData.filename;
            console.log("File Data: ", $scope.fileData);
            // $scope.items.image = resp.data.name;
        }).catch(error => {
            $("#modalTitle").text("Notification");
            $("#modalBody").text("Error!");
            $("#myModal").modal("show");
            console.log("Error", error)
        })
    }

    // Cập nhật thông tin người dùng
    $scope.update = function () {
        var item = angular.copy($scope.items);
        $scope.initialize();
        $http.put(`/rest/profile`, item).then(resp => {
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

    // Hiển thị modal chi tiết đơn hàng
    $scope.detailItemsFunc = function (item) {
        $("#orderDetailModal").modal("show");
        $http.get(`/rest/profile/order/detail/${item.id}`).then(resp => {
            $scope.detailItems = resp.data;
        })
    }

    // Hiển thị modal thay đổi mật khẩu
    $scope.showChangePass = function() {
        $("#changPassModal").modal("show");
    }

    // Chức năng thay đổi mật khẩu
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
