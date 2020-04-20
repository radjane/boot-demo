/***
 * @param text 展示信息
 * @param type 提示类型
 */
function showNotice(text, type, time = 2000, hide = true) {
    PNotify.prototype.options.styling = "fontawesome";
    new PNotify({
        title: '消息',
        text: text,
        type: type,
        delay: time,
        hide: hide,       //是否自动关闭
        mouse_reset: true,//鼠标悬浮的时候，时间重置
    });
}
