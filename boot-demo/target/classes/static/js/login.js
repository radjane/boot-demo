// 校验 用户名
function ckUserName() {
    let obj = $("input[name='username']")[0];
    if (obj.value.length < 1) {
        showNotice('用户名不为空', 'error', 2000, true)
        return false;
    }
    return true;
}

// 校验 密码
function ckPassword() {
    let obj = $("input[name='password']")[0];
    if (obj.value.length < 1) {
        showNotice('密码不为空', 'error', 2000, true);
        return false;
    }
    return true;
}

// 登陆
function doLogin() {
    if (ckUserName() && ckPassword()) {
        $('#login_form').submit();
    }
}


// 校验  注册 用户名
function ckRegisterUsername() {
    let obj = $("input[name='username']")[1];
    if (obj.value.length < 1) {
        showNotice('用户名不为空', 'error', 2000, true);
        return false;
    } else {
        let ajax_flag = false;
        $.ajax({
            type: "post",
            url: webPath + "/user/ifExistsUsername",
            dataType: "json",
            data: {username: obj.value},
            async: false,
            success: function (data) {
                if (data.success) {
                    showNotice(data.message, 'error', 3000, true);
                } else {
                    ajax_flag = true;
                }
            }, error: function () {
                showNotice(AJAX_ERROR_MSG, 'error', 5000, false);
            }
        });
        return ajax_flag;
    }
}


// 校验  注册 密码
function ckRegisterPassword() {
    let obj = $("input[name='password']")[1];
    if (obj.value.length < 1) {
        showNotice('密码不为空', 'error', 2000, true);
        return false;
    } else if (!REG_PASSWORD.test(obj.value)) {
        showNotice(REG_PASSWORD_MSG, 'error', 2000, true);
        return false;
    } else {
        return true;
    }
}


// 注册
function doRegister() {
    if (ckRegisterUsername() && ckRegisterPassword()) {
        $.ajax({
            type: "post",
            url: webPath + "/user/register",
            dataType: "json",
            data: $('#register_form').serialize(),
            async: false,
            success: function (data) {
                if (data.success) {
                    window.location.href = get_web_url() + '/index/index#signin';
                    $("input[name='username']")[0].value = data.data.username;
                    showNotice(data.message, 'success', 3000, true);
                    $("input[name='username']")[1].value='';
                    $("input[name='password']")[1].value='';
                }
            }, error: function (data) {
                showNotice(data.message, 'error', 2000, true);
            }
        });
    } else {
        showNotice('ddd', 'info', 3000, true);
    }

}

// 忘记密码
function forgetPassword() {
    showNotice(UNDONE_MSG, 'info', 3000, true);
}