'user strict';

var stompClient = null;
var stompclient2 = null;
var username = localStorage.getItem("Username");
var messageForm = document.querySelector('#messageForm');

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function connect2() {
    await sleep(2000);
    console.log(username);

    if(username) {
        //usernamePage.classList.add('hidden');
        //chatPage.classList.remove('hidden');
        
        var socket = new SockJS('/javatechie');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected2, onError);
    }
}

function onConnected2() {
    stompClient.subscribe('/topic/public', onMessageReceived2);

    //stompClient.send("/app/chat.register",
     //   {},
     //   JSON.stringify({sender: username, type: 'JOIN'})
    //)
}

function onError(error) {
    window.alert('Could not connect to WebSocket server. Please refresh this page to try again!');
}

function onMessageReceived2(payload) {
    var message = JSON.parse(payload.body);
    //console.log(message);

    if (message.type === 'CHAT'){
        //console.log("chat message incoming");
        var img = '';
        if (localStorage.getItem("isTrainer") == 0) {
            //console.log("im trainer");
            img = 'img/pt.png';
        } else {
            //console.log("im client");
            img = 'img/user.png';
        }

        var msgDisplay = ``;
        if (message.sender == username){
            msgDisplay+=`   <div class="chat outgoing">
                                <div class="details">
                                    <p>${message.content}</p>
                                </div>
                            </div>
                        `;
        } else {
            msgDisplay+=`   <div class="chat incoming">
                                <img src="${img}" alt="">
                                <div class="details">
                                    <p>${message.content}</p>
                                </div>
                            </div>
            `;
        }
        document.getElementById("chat-box").innerHTML += msgDisplay;
        var chat_box = document.getElementById("chat-box");
        chat_box.scrollTop = chat_box.scrollHeight;
    } else {
        console.log("nothing...")
    }
}

function send2() {
    var messageInput = document.querySelector('#message');
    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };

        stompClient.send("/chat.send", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
}