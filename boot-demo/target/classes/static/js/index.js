//例如 http://localhost:8080/projectname/index.action
//获取当前浏览器访问地址 http://localhost:8080/projectname/index.action
let strFullPath = window.document.location.href;
//获取去除域名的后面的访问地址  /projectname/index.action
const strPath = window.document.location.pathname;
const pos = strFullPath.indexOf(strPath);
//获取去除域名的后面的访问地址  http://localhost:8080
const prePath = strFullPath.substring(0, pos);
//获取去除域名的后面的访问地址  /projectname
const postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
// http://localhost:8080/projectname
const webPath = prePath + postPath;

function get_full_path() {
    return strFullPath;
}

function get_str_path() {
    return strPath;
}

function get_host_url() {
    return prePath;
}

function get_project_name() {
    return postPath;
}

function get_web_url() {
    return webPath;
}

function doSubimt() {
    $('#tableFormId').submit();
}






function doCollectionMovies(startYmd) {
    $.ajax({
        type: "POST",
        url: webPath + "/data/saveMaoYanDayBoxOffice?yyyymmdd=" + startYmd,
        dataType: "json",
        success: function (data) {
            showNotice(data.message, 'success', true);
        }
    });
}


function doCollectionNewsFlashes() {
    $.ajax({
        type: "POST",
        url: webPath + "/data/save36KrNewsFlash",
        dataType: "json",
        success: function (data) {
            showNotice(data.message, 'success', true);
        }
    });
}


