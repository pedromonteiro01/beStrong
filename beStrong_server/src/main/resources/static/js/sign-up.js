async function sendData(url, email1, password1, user1, phone1, weight1, height1){
    const response = await fetch(url, {
    method: 'POST',
    headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
    },
    body: JSON.stringify({email: email1, password: password1, phone: phone1, weight: weight1, height: height1, name: user1}),
    });
    console.log(response);

    response.json().then(data => {
    console.log(data);
    });
}

function signUp(){
    console.log("control");
    var email=document.getElementById("exampleInputEmail1").value;
    var password=document.getElementById("exampleInputPassword").value;
    var phone = document.getElementById("input-phone").value;
    var weight = document.getElementById("input-weight").value;
    var height = document.getElementById("input-height").value;
    var user = document.getElementById("input-username").value;



    if(email=="" || password=="" || phone=="" || user=="" || weight=="" || height==""){
        alert("You need to fill all fields!");
        return; 
    }

    sendData("http://172.18.0.9:8081/clients", email, password, user, phone, weight, height);

}