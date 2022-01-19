'user strict';

var stompClient = null;
var username = localStorage.getItem("Username");
var messageForm = document.querySelector('#messageForm');


function connect() {
    console.log(username);

    if(username) {
        //usernamePage.classList.add('hidden');
        //chatPage.classList.remove('hidden');
        
        var socket = new SockJS('/javatechie');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
}

function onConnected() {
    //stompClient.subscribe('/topic/public', onMessageReceived);

    stompClient.send("/app/chat.register",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )
}

function onError(error) {
    window.alert('Could not connect to WebSocket server. Please refresh this page to try again!');
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    console.log(message);

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else if (message.type === 'CHAT'){
        console.log("chat message incoming")
        //var avatarElement = document.createElement('i');
        //var avatarText = document.createTextNode(message.sender[0]);
        //avatarElement.appendChild(avatarText);
        //avatarElement.style['background-color'] = getAvatarColor(message.sender);

        //messageElement.appendChild(avatarElement);

        //var usernameElement = document.createElement('span');
        //var usernameText = document.createTextNode(message.sender);
        //usernameElement.appendChild(usernameText);
        //messageElement.appendChild(usernameElement);
    } else {
        console.log("nothing...")
    }
}

function send() {
    var messageInput = document.querySelector('#message');
    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };

        stompClient.send("/app/chat.send", {}, JSON.stringify(chatMessage));
        messageInput.value = '';

        var msgDisplay = ``;
        msgDisplay+=`   <div class="chat outgoing">
                            <div class="details">
                                <p>${chatMessage.content}</p>
                            </div>
                        </div>
                    `;
        document.getElementById("chat-box").innerHTML += msgDisplay;
        var chat_box = document.getElementById("chat-box");
        chat_box.scrollTop = chat_box.scrollHeight;
    }
}