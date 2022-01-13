async function getData(url1, url2){
    const response1 = await fetch(url1);
    var data1 = await response1.json();
    const response2 = await fetch(url2);
    var data2 = await response2.json();
    console.log(data2)
    console.log(data1)
    
    change_doc(data1);
    
    show_data(data2);
}

var c = 0;

function change_doc(data){
    var list;
    if (data.length <= 1)
        list = data;
    else
        list = data.list;
        var desc = ``;
        var goals = ``;
        var name=``;
        var duration=``;
        var type=``;
        var inten = ``;
        var images =``;
        for (let i = 0; i < data.length; i++) {
            let cls = data[i];
            let goalsArray = cls.goals.split(".");
            let imagesArray = cls.images.split(";");
            inten+=cls.intensity;
            type+=cls.type;
            name+=cls.name;
            duration+=cls.duration;
            desc+=`<p>${cls.description}</p>`;
            for (let i=0; i<goalsArray.length; i++){
                goals+=`
                <li>${goalsArray[i]}</li>
            `;
            }
            for (let i=0; i<imagesArray.length; i++){
                if (i==0){
                    images+=`
                    <div class="carousel-item active">
                        <div class="overlay-image img-fluid">
                            <img src="${imagesArray[i]}" alt="">
                        </div>
                    </div>
                    `;
                }
                else{
                    images+=`
                        <div class="carousel-item">
                            <div class="overlay-image img-fluid">
                                <img src="${imagesArray[i]}" alt="">
                            </div>
                        </div>
                    `;
                }
            }
        }
        document.getElementById("desc").innerHTML = desc;
        document.getElementById("goals").innerHTML = goals;
        document.getElementById("title").innerHTML = name.toUpperCase();
        document.getElementById("duration").innerHTML = duration;
        document.getElementById("type").innerHTML = type;
        document.getElementById("intensity").innerHTML = inten;
        document.getElementById("images").innerHTML = images;

}

function show_data(data){
    var list;
    if (data.length <= 1)
        list = data;
    else
        list = data.list;
    var tab = ``;
    if (data.length == 0){
        tab+=`
            <p>No avaiable classes at this moment...</p>
        `;
    }
    else{
        for (let i = 0; i < data.length; i++) {
            let cls = data[i];
            
            tab += `<tr> 
            <td>${cls.local} </td>
            <td>${cls.starting}</td>
            <td>${cls.ending}</td> 
            <td>${cls.currentCapacity}/${cls.maxCapacity}</td>
            <td><button type="button" class="btn btn-success" style="border-radius: 100%;"  onclick="myFunction()">GO</button></td>          
            </tr>`;
        }
    }
    document.getElementById("table_body").innerHTML = tab;
}

console.log("control");
const params = (new URL(document.location)).searchParams;
let type = params.get('type');
let str1 = "http://172.18.0.9:8081/classes/";
let str2 = "http://172.18.0.9:8081/types/";
let url1 = str1.concat(type);
let url2 = str2.concat(type);
getData(url2, url1);

