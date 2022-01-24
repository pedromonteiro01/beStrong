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
    if (response["status"]==422){
        window.alert("Username or email already exists!");
        return false;
    }
    else{
        window.alert("Account created successfully!");

        // after sign up stay logged in
        localStorage.setItem("loggedIn", 1);
        localStorage.setItem("isTrainer", 0);
        localStorage.setItem("Email", email1);
        localStorage.setItem("Username", user1);
        localStorage.setItem("Phonenumber", phone1);
        localStorage.setItem("Password", password1);

        window.location.href = "classes.html";
        return true;
    }
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

    password = sha512(password);

    var bol = sendData("http://172.18.0.9:8081/clients", email, password, user, phone, weight, height);
}