urlBase = "http://172.18.0.9:8081";
//urlBase = "http://deti-engsoft-11.ua.pt:8081";

async function getData(url1, url2){
    const response1 = await fetch(url1);
    var data1 = await response1.json();
    const response2 = await fetch(url2);
    var data2 = await response2.json();
    console.log(data2)
    console.log(data1)
    
    change_doc(data1);
    
    show_data(data2);

    console.log("load complete");
}

async function sendReservation(url1, class_id, client_id){
    const response1 = await fetch(url1, {
        method: 'POST',
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        },
        body: JSON.stringify({"classId": class_id, "clientId": client_id}),
        });
    var data1 = await response1.json();

    console.log(data1)
}

async function getEnlistedClasses(url, data){
    const response3 = await fetch(url + "/" + data, {
        method: 'GET',
        headers: {
        'Accept': 'application/json'
        //'Content-Type': 'application/json'
        }})
        .then(response => response.json())
        .then(data => (enlistedClasses = data));
    //var data3 = await response3.json();
    //console.log(response3);
    //return data3;
    console.log(enlistedClasses);
}

async function removeReservation(url1, class_id, client_id){
    const response1 = await fetch(url1, {
        method: 'DELETE',
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        },
        body: JSON.stringify({"classId": class_id, "clientId": client_id}),
        });
    var data1 = await response1.json();
    console.log(class_id);
    console.log(client_id);
    console.log(data1)
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

function show_data(data, info = null){

    console.log(enlistedClasses);

    console.log("continue");
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
        var btn = "";
        var inList;
        for (let i = 0; i < data.length; i++) {
            let cls = data[i];
            
            if(localStorage.getItem("loggedIn") == 1 ){
            inList = false;
                for( let i = 0; i < enlistedClasses.length; i++){
                    console.log(enlistedClasses[i].id);
                    if(enlistedClasses[i].id == cls.id){
                        inList = true;
                    }
                }
            }

            if(!inList){ 
                btn = `<td><button type="button" id="button_no` + cls.id + `" class="btn btn-success" style="border-radius: 100%;"  onclick="joinClass(this)" value="go">GO</button></td>`;
            }
            else{
                btn = `<td><button type="button" id="button_no` + cls.id + `" class="btn btn-danger" style="border-radius: 100%;"  onclick="joinClass(this)" value="cancel">X</button></td>`;
            }

            tab += `<tr> 
            <td>${cls.local} </td>
            <td>${cls.starting}</td>
            <td>${cls.ending}</td> 
            <td>${cls.currentCapacity}/${cls.maxCapacity}</td>
            ` + btn + `
            </tr>`;

        }
    }
    document.getElementById("table_body").innerHTML = tab;
}

function joinClass(button){
    logged = localStorage.getItem("loggedIn");
    isTrainer = localStorage.getItem("isTrainer");
    if(logged == 1){
        if( isTrainer == 0){
            console.log("button click");
            if (button.value == "go"){
                button.value = "cancel";
                button.innerHTML = "X";
                button.classList.remove("btn-success");
                button.classList.add("btn-danger");
                no = button.id.split("button_no")[1];
                sendReservation(urlBase + "/classes/makeReservation", no, localStorage.getItem("Id"));
            }
            else{
                button.value = "go";
                button.classList.remove("btn-danger");
                button.classList.add("btn-success");
                button.innerHTML = "GO";
                no = button.id.split("button_no")[1];
                removeReservation(urlBase + "/classes/cancelReservation", no, localStorage.getItem("Id"));
            }
        }

    }
    else{
        window.location.href = "sign-in.html";
    }
}



console.log("control");
const params = (new URL(document.location)).searchParams;
let type = params.get('type');
let str1 = urlBase + "/classes/";
let str2 = urlBase + "/types/";
let url1 = str1.concat(type);
if (localStorage.getItem("isTrainer")==1){
    let trainer_id = localStorage.getItem("Id"); 
    let s = "/" + trainer_id.toString();
    url1 = url1.concat(s);
    let tab = `trainer.html?type=${type}`;
    window.location.href = tab;
}
let url2 = str2.concat(type);

var enlistedClasses;
if(localStorage.getItem("loggedIn") == 1 ){
    var data = localStorage.getItem("Id");
    enlistedClasses = getEnlistedClasses(urlBase + "/classes/getEnlistedClasses", data);
    console.log(enlistedClasses);
}

//getData(url2, url1);

//const promiseOfSomeData = fetch("some.json").then(r=>r.json()).then(data => {
//    console.log('in async');
//    return data;
//});
window.onload = async () => {
    await getData(url2, url1);
};
