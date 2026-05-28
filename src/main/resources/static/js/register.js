async function register(){

let user={

username:
document.getElementById(
"username").value,

password:
document.getElementById(
"password").value,

role:
document.getElementById(
"role").value

};


let response=

await fetch(

"http://localhost:8080/auth/register",

{

method:"POST",

headers:{

"Content-Type":
"application/json"

},

body:
JSON.stringify(user)

});


let msg=

await response.text();


if(response.ok){

alert(
"Registered Successfully");

window.location.href=
"/login-page";

}

else{

alert(msg);

}

}