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

async function sendSubmission(url1, data){
    const response1 = await fetch(url1, {
        method: 'POST',
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
        });
    //var data1 = await response1.json();
    console.log(response1);
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
            <td><button type="button" id="class_no` + cls.id + `\" data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn btn-danger" style="border-radius: 100%;" onclick="setClassId(this)"><i class="fas fa-pencil-alt"></i></i></button></td>          
            </tr>`;
        }
    }
    document.getElementById("table_body").innerHTML = tab;
}

function addClass(){
    var room = document.getElementById("input_room").value;
    var capacity = document.getElementById("capacity").value;
    
    var startDate = document.getElementById("input_start_date").value;
    var endDate = document.getElementById("input_end_date").value;
    console.log(startDate);
    var data = {"trainerId": localStorage.getItem("Id"),"type": type, "date": startDate.split("T")[0], "start_hour": startDate.split("T")[1] + ":00", "ending_hour": endDate.split("T")[1] + ":00", "local": room, "max_capacity": capacity};
    sendSubmission("http://172.18.0.9:8081" + "/classes/addClass", data);

    location.reload();
}

function setClassId(button){
    variableClassId = button.id.split("class_no")[1];
}

function editClass(button){
    var room = document.getElementById("input_room_edit").value;
    
    var startDate = document.getElementById("input_start_date_edit").value;
    var endDate = document.getElementById("input_end_date_edit").value;
    console.log(startDate);
    var data = {"classId": variableClassId, "trainerId": localStorage.getItem("Id"),"type": type, "date": startDate.split("T")[0], "start_hour": startDate.split("T")[1] + ":00", "ending_hour": endDate.split("T")[1] + ":00", "local": room};
    sendSubmission("http://172.18.0.9:8081" + "/classes/updateClass", data);

    location.reload();
}

function deleteClass(button){
    var data = {"classId": variableClassId};
    sendSubmission("http://172.18.0.9:8081" + "/classes/removeClass", data);

    location.reload();
}

console.log("control");
const params = (new URL(document.location)).searchParams;
var type = params.get('type');
var variableClassId;
let str1 = "http://172.18.0.9:8081/classes/";
let str2 = "http://172.18.0.9:8081/types/";
let url1 = str1.concat(type);
let trainer_id = localStorage.getItem("Id"); 
let s = "/" + trainer_id.toString();
let url3 = url1.concat(s);
let url2 = str2.concat(type);
console.log(url3);
getData(url2, url3);

