async function getData(url1, user, pw){
    const response1 = await fetch(url1);
    var data1 = await response1.json();
    console.log(data1);
    
    verify_login(data1, user, pw);
}

function login(){
    console.log("control");
    var username=document.getElementById("input-email").value;
    var password=document.getElementById("input-password").value;

    if(username=="" || password==""){
        alert("You need to fill all fields!");
        return;
    }

    console.log("user: ", username);
    console.log("pass: ", password);

    getData("http://172.18.0.9:8081/clients", username, password);

}

function verify_login(data, user, pw){
    var list;
    if (data.length <= 1)
        list = data;
    else
        list = data.list;
    for (let i = 0; i < data.length; i++) {
        let cls = data[i];
        if (cls.email == user && cls.password==pw){
            console.log("user:",cls.email);
            localStorage.setItem("loggedIn", 1);
            localStorage.setItem("Username", cls.name);
            localStorage.setItem("Password", pw);
            window.location.href="index.html";
            return;
        }
    }
    alert("Wrong credentials!");
    return;
}