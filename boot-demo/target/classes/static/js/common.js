// -------------------------------正则表达式-------------------------------
// 密码： 大小写字母,数字,特殊字符中的至少3种.8位以上
let REG_PASSWORD = /^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_]+$)(?![a-z0-9]+$)(?![a-z\W_]+$)(?![0-9\W_]+$)[a-zA-Z0-9\W_]{8,20}$/;

// -------------------------------公共的提示信息-------------------------------
let AJAX_ERROR_MSG='系统出错联系管理员...';
let UNDONE_MSG='稍等哦，程序员小哥哥正在开发...';

let REG_PASSWORD_MSG='大小写字母,数字,特殊字符中的至少3种.8位以上';


function logout(){
    window.location.href="/boot-demo/index/logout";
}