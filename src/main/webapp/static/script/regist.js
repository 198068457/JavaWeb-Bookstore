function $(id){
    return document.getElementById(id);
}
function preRegist(){
    let unameTxt = $("unameTxt");
    let unameReg = /^[0-9a-zA-Z]{6,16}$/;
    let uname = unameTxt.value;
    let unameSpan = $("unameSpan");
    if(!unameReg.test(uname)){
        unameSpan.style.visibility = "visible";
        return false;
    }else{
        unameSpan.style.visibility = "hidden";
    }
    return true;
}

var xmlHttpRequest;

function createXMLHttpRequest(){
    if(window.XMLHttpRequest){
        xmlHttpRequest = new XMLHttpRequest();
    }else if(window.ActiveXObject){
        try{
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }catch(e){
            xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
        }

    }
}
function ckUname(uname){
    createXMLHttpRequest();
    var url = "user.do?operate=ckUname&uname="+uname;
    xmlHttpRequest.open("GET",url,true);
    xmlHttpRequest.onreadystatechange = ckUnameCB;
    xmlHttpRequest.send();
}

function ckUnameCB(){
    if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
        var responseText = xmlHttpRequest.responseText;
        responseText.split(":");
    }
}