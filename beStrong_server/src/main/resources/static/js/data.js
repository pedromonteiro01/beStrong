'user strict';

var stompClient = null;

function connect() {
    var socket = new SockJS('/gymEntries');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    stompClient.subscribe('/entry/num', onMessageReceived);
}

function onError(error) {
    window.alert(error);
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    console.log("message: ", message);
    if (message.hasOwnProperty("cap")){
        var id = "capacity" + message.id;
        var element = document.getElementById(id);
        if (element!=null){
            element.innerHTML = message.cap + "/" + message.max; 
        }
    }
    else if(message.hasOwnProperty("type")){
        var element = document.getElementById("table_body");
        if ((element!=null) && (document.getElementById("title-up").innerText == message.type.toUpperCase())){
            document.getElementById("no-classes").style.display = 'none';
            var btn = `<td><button type="button" id="button_no` + message.id + `" class="btn btn-success" style="border-radius: 100%;"  onclick="joinClass(this)" value="go">GO</button></td>`;
            element.innerHTML += ` <tr> 
            <td>` + message.local + ` </td>
            <td>` + message.starting + `</td>
            <td>` + message.ending + `</td> 
            <td id="capacity`+ message.id + `">` + message.caps + `/`+ message.max + `</td>
            ` + btn + `
            </tr>`;
        }
    }
    else{
        document.getElementById("occupation").innerHTML = "Occupancy: " + message.num_entries;
        console.log("abc");
    }
} 