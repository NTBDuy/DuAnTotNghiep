
app.controller("authority-ctrl",function($scope,$http,$location){
    $scope.roles = [];
    $scope.items = [];
    $scope.searchName = '';
    $scope.authorities = [];

    $scope.initialize = function(){
        $http.get("/rest/admin/authority/role").then(resp =>{
            $scope.roles = resp.data;
            console.log($scope.roles);
        })
        $http.get("/rest/admin/authority/account").then(resp =>{
            $scope.items = resp.data;
            console.log($scope.items);
        })
        $http.get("/rest/admin/authority/authority").then(resp =>{
            $scope.authorities = resp.data;
            console.log($scope.authorities);
        }).catch(error =>{
            $("#modalTitle").text("Notification");
            $("#modalBody").text("You do not have access!");
            $("#myModal").modal("show");
            console.log("Error",error);
        })
    }

    $scope.search = function () {
        var x = document.getElementById("searchName").value;
        if (x == '') {
            $scope.initialize();
        } else {
            $http.get(`/rest/admin/account/search/${x}`).then(resp => {
                $scope.items = resp.data;
                $scope.pager.first()
            });
        }
    }

    $scope.authority_of = function(acc,role){
        if($scope.authorities){
            return $scope.authorities.find(ur => ur.accounts.username == acc.username && ur.roles.id == role.id);
        }
    }

    $scope.redirect = function() {
        $location.path("/unauthorized");
    }

    $scope.authority_changed= function(acc,role){
        var authority = $scope.authority_of(acc,role);
        if(authority){
            //đã cấp quyền => thu hồi quyền (xóa)
            $scope.revoke_authority(authority);
        }else{
            //chưa được cấp quyền => cấp quyền (Thêm mới)
            authority = {accounts: acc, roles: role};
            $scope.grant_authority(authority);
        }
    }

    //Thêm mới authority
    $scope.grant_authority = function(authority){
        $http.post(`/rest/admin/authority/`,authority).then(resp =>{
            $scope.authorities.push(resp.data)
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Cấp quyền sử dụng thành công!");
            $("#myModal").modal("show");
        }).catch(error =>{
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Lỗi cấp quyền sử dụng!");
            $("#myModal").modal("show");
            console.log("Error",error);
        })
    }

    //Xóa Authority
    $scope.revoke_authority = function(authority){
        $http.delete(`/rest/admin/authority/${authority.id}`).then(resp =>{
            var index = $scope.authorities.findIndex(a => a.id == authority.id);
            $scope.authorities.splice(index,1);
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Thu hồi quyền sử dụng thành công!");
            $("#myModal").modal("show");
        }).catch(error =>{
            $("#modalTitle").text("Thông báo");
            $("#modalBody").text("Lỗi thu hồi quyền sử dụng!");
            $("#myModal").modal("show");
            console.log("Error",error);
        })
    }
    
    $scope.initialize();
})