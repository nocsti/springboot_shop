//  通用ajaxPost，返回json
function ajaxPost(url,data,succFunc,errFunc,beforeFunc){
    if( data instanceof HTMLElement){
        data = $(data).serialize();
    }
    if( typeof errFunc == "undefined" ){
        errFunc = function(){
            if( typeof tipShow != "undefined" ) tipShow({msg:"连接服务器失败！",btns:[{t:"确定",c:"tipHide()"}] });
            else alert("连接服务器失败！");
        };
    }
    $.ajax({ type: 'POST', url: url, dataType: 'json', data: data, success: succFunc, error: errFunc, beforeSend: beforeFunc });
}

function clog(ele){
    return console.log(ele);
}

//  本地存储
function getLocalStore(key){

}
function setLocalStore(key,value){

}