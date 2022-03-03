const app = angular.module("myApp", [])

app.controller("myCtrl", function($scope, $http){

    $scope.itemsP = [];
    $scope.tong = 0;

    $scope.initialize = function(){
        $http.get(`/rest/shop`).then(resp => {
            $scope.itemsP = resp.data;
        })
    }

    $scope.initialize();

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
            $scope.tong = $scope.cart.count
        }
    }

    $scope.cart.loadFromLocalStorage();

    $scope.order = {
        orderDate: new Date(),
        address: "",
        accounts: { username: "admin" },
        // accounts: { username: $("#username").text() },
        status: 1,
        amount: $scope.tong,
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    products: {id: item.id},
                    price: (item.price*item.qty),
                    quantity: item.qty
                }
            });
        },
        purchase() {
            var order = angular.copy(this);
            //Thực hiện đặt hàng
            $http.post("/rest/orders", order).then(resp => {
                $scope.cart.clear();
                $("#modalTitle").text("Thông báo");
                $("#modalBody").text("Đặt hàng thành công!");
                $("#myModal").modal("show");
            }).catch(error => {
                $("#modalTitle").text("Thông báo");
                $("#modalBody").text("Đặt hàng lỗi!");
                $("#myModal").modal("show");
                console.log(error)
            })
        }
    }

})