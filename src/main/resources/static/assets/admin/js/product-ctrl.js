app.controller("product-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.form = {};
    $scope.cates = [];
    $scope.brands = [];

    $scope.initialize = function(){
        $http.get(`/rest/admin/product`).then(resp => {
            $scope.items = resp.data;
        });

        //load categories
        $http.get("/rest/admin/category").then(resp => {
            $scope.cates = resp.data;
        })

        //load brands
        $http.get("/rest/admin/brand").then(resp => {
            $scope.brands = resp.data;
        })
    }

    //xóa form
    $scope.reset = function () {
        $scope.form = {
            createDate: new Date(),
        };
    }

    $scope.search = function () {
        var x = document.getElementById("searchName").value;
        if (x == '') {
            $http.get(`/rest/admin/product`).then(resp => {
                $scope.items = resp.data;
            });
        } else {
            $http.get(`/rest/admin/product/search/${x}`).then(resp => {
                $scope.items = resp.data;
                $scope.pager.first()
            });
        }
    }

    $scope.initialize();

    $scope.detail = function (item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(1)").tab('show')
    }

    //Thêm sản phẩm mới
    $scope.create = function () {
        var item = angular.copy($scope.form);
        $http.post(`/rest/admin/product`, item).then(resp => {
            $scope.items.push(resp.data);
            $scope.reset();
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Thêm mới sản phẩm thành công!");
            $("#myModal").modal("show");
        }).catch(error => {
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Lỗi thêm mới sản phẩm!");
            $("#myModal").modal("show");
            console.log("Error", error);
        })
    }

    //Cập nhật sản phẩm
    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.put(`/rest/admin/product/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Cập nhật sản phẩm thành công!");
            $("#myModal").modal("show");
        }).catch(error => {
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Lỗi cập nhật sản phẩm!");
            $("#myModal").modal("show");
            console.log("Error", error);
        })
    }

    //Xóa sản phẩm
    $scope.delete = function (item) {
        $http.delete(`/rest/admin/product/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Xóa sản phẩm thành công!");
            $("#myModal").modal("show");
        }).catch(error => {
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Lỗi xóa sản phẩm!");
            $("#myModal").modal("show");
            console.log("Error", error);
        })
    }

    // Phân trang
    $scope.pager = {
        page: 0,
        size: 10,
        get items() {
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.items.length / this.size);
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