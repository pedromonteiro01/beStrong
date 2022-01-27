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
    document.getElementById("occupation").innerHTML = "Occupation " + message;
    console.log("abc");
    console.log("message: ", message);
} 