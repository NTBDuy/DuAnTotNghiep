app.controller("account-ctrl", function ($scope, $http) {
    // Dữ liệu tài khoản
    $scope.items = [];
    // Dữ liệu chi tiết tài khoản
    $scope.form = {};
    // Dữ liệu xuất thông báo
    $scope.message = [];

    // Khởi tạo
    $scope.initialize = function () {
        $http.get(`/rest/admin/account`).then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.register_date = new Date(item.register_date);
            })
        });
    }
    $scope.initialize();

    // Xóa dữ liệu trên form
    $scope.reset = function () {
        $scope.form = {};
    }

    // Thông tin chi tiết 
    $scope.detail = function (item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(1)").tab('show')
    }

    // Upload hình ảnh cho tài khoản
    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]);
        var item = angular.copy($scope.form);
        $http.post(`/rest/upload/${item.username}`, data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.fileData = resp.data;
            $scope.form.image = $scope.fileData.filename;
            console.log("File Data: ", $scope.fileData);
        }).catch(error => {
            $("#modalTitle").text("Notification");
            $("#modalBody").text("Error!");
            $("#myModal").modal("show");
            console.log("Error", error)
        })
    }

    // Tìm kiếm bằng tài khoản bằng chuỗi ký tự
    $scope.search = function () {
        var x = document.getElementById("searchName").value;
        if (x == '') {
            $http.get(`/rest/admin/account`).then(resp => {
                $scope.items = resp.data;
            });
        } else {
            $http.get(`/rest/admin/account/search/${x}`).then(resp => {
                $scope.items = resp.data;
                $scope.pager.first()
            });
        }
    }

    // Thêm tài khoản
    $scope.create = function () {
        var item = angular.copy($scope.form);
        $http.post(`/rest/admin/account`, item).then(resp => {
            $scope.items.push(resp.data);
            $scope.message = (resp.data);
            $("#modalTitle").text("Notification");
            $("#modalBody").text($scope.message.mess);
            $("#myModal").modal("show");
        }).catch(error => {
            $("#modalTitle").text("Notification");
            $("#modalBody").text("Error!");
            $("#myModal").modal("show");
            console.log("Error", error);
        })
    }

    // Cập nhật tài khoản
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

    // Xóa tài khoản
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