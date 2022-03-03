app.controller("order-ctrl", function($scope, $http){

    $scope.items = [];
    $scope.form = {};
    $scope.detailItems = [];

    $scope.initialize = function(){
        $http.get(`/rest/admin/order`).then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.orderDate = new Date(item.orderDate);
            })
        });
    }

    $scope.initialize();

    $scope.detailItemsFunc = function (item) {
        $(".nav-tabs a:eq(1)").tab('show')
        $http.get(`/rest/admin/order/detail/${item.id}`).then(resp => {
            $scope.detailItems = resp.data;
        })
    }

    $scope.delete = function (item) {
        $http.delete(`/rest/admin/order/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
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