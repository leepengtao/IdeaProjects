
// 通用的ajax(get)
function table_get(url,data) {               // 调用ajax进行异步查询
    console.log(url);
    console.log(data);
    jq.ajax({
        url: url,         // 请求地址
        contentType:"application/json;charset=UTF-8",   // 请求数据形式: json
        data:data,             // 请求体:     ?????????????
        dataType:"json",                    // 返回数据类型
        type:"get",                        // 请求方式
        success:function (userPage) {       // 回调函数
            each(userPage);                 // 调用用户列表结果集的处理方法
        }
    });
}
// 通用的ajax(get)
function table_post(url,data) {               // 调用ajax进行异步查询
    console.log(url);
    console.log(data);
    jq.ajax({
        url: url,         // 请求地址
        contentType:"application/json;charset=UTF-8",   // 请求数据形式: json
        data:data,             // 请求体:     ?????????????
        dataType:"json",                    // 返回数据类型
        type:"post",                        // 请求方式
        success:function (userPage) {       // 回调函数
            each(userPage);                 // 调用用户列表结果集的处理方法
        }
    });
}

// 后补: $POST
function table_post2(url1,data1) {
    console.log(url1);
    console.log(data1);
    jq.post(url1,data1,function (data) {
        each(data);
    },JSON);
}