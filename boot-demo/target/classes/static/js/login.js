// 登陆
function doLogin() {
    $('#login_form').submit();
}

// 注册
function doRegister() {
    $.ajax({
        type: "post",
        url: webPath + "/user/doRegister",
        dataType: "json",
        data: $('#register_form').serialize(),
        success: function (data) {
            console.dir(data);
            showNotice(data.message, 'success', true);
        }, error: function (data) {
            console.dir(data);
            showNotice(data.message, 'error', true);
        }
    });
}