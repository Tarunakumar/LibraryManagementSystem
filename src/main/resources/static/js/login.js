async function login(){

let user={

username:
document
.getElementById(
"username").value,

password:
document
.getElementById(
"password").value

};


let response=

await fetch(

"http://localhost:8080/auth/login",

{

method:"POST",

headers:{

"Content-Type":
"application/json"

},

body:
JSON.stringify(user)

});


if(response.ok){

let token=

await response.text();


localStorage.setItem(
"token",
token);


alert(
"Login Successful");


window.location.href=
"/dashboard-page";

}

else{

alert(
"Invalid Username or Password");

}

}