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
    var tab = ``;
    for (let i = 0; i < data.length; i++) {
        let cls = data[i];
        
        tab += `<tr> 
        <td>${cls.local} </td>
        <td>${cls.starting}</td>
        <td>${cls.ending}</td> 
        <td>${0}/${cls.capacity}</td>
        <td><button type="button" class="btn btn-success" style="border-radius: 100%;"  onclick="myFunction()">GO</button></td>          
        </tr>`;
    }
    document.getElementById("table_body").innerHTML = tab;
}




console.log("control");
getData("http://172.18.0.9:8081/classes");

