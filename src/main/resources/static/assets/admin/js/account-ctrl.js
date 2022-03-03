app.controller("account-ctrl", function($scope, $http){
    $scope.items = [];
    $scope.form = {};

    $scope.initialize = function(){
        $http.get(`/rest/admin/account`).then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.register_date = new Date(item.register_date);
            })
        });
    }

    //xóa form
    $scope.reset = function () {
        $scope.form = {};
    }

    $scope.initialize();

    $scope.detail = function (item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(1)").tab('show')
    }

    //Thêm 
    $scope.create = function () {
        var item = angular.copy($scope.form);
        $http.post(`/rest/admin/account`, item).then(resp => {
            $scope.items.push(resp.data);
            $scope.reset();
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Thêm mới thành công!");
            $("#myModal").modal("show");
        }).catch(error => {
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Lỗi thêm mới!");
            $("#myModal").modal("show");
            console.log("Error", error);
        })
    }

    //Cập nhật 
    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.put(`/rest/admin/account/${item.username}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.username == item.username);
            $scope.items[index] = item;
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

    //Xóa  
    $scope.delete = function (item) {
        $http.delete(`/rest/admin/account/${item.username}`).then(resp => {
            var index = $scope.items.findIndex(p => p.username == item.username);
            $scope.items.splice(index, 1);
            $scope.reset();
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Xóa thành công!");
            $("#myModal").modal("show");
        }).catch(error => {
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Lỗi xóa!");
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