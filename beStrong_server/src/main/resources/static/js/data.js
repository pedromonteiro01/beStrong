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
    else{
        document.getElementById("occupation").innerHTML = "Occupancy: " + message.num_entries;
        console.log("abc");
    }
} 