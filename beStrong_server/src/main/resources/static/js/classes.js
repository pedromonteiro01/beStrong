//urlBase = "http://172.18.0.9:8081";
urlBase = "http://deti-engsoft-11.ua.pt:8081";

async function getData(url){
    const response = await fetch(url);
    var data = await response.json();
    console.log(data)
    
    show_data(data, url);
}

var c = 0;

function show_data(data){
    var list;
    if (data.length <= 1)
        list = data;
    else
        list = data.list;
    var type = [];
    var tab = ``;
    for (let i = 0; i < data.length; i++) {

    }
    document.getElementById("classes").innerHTML = tab;
}




console.log("control");
getData(urlBase + "/classes");

