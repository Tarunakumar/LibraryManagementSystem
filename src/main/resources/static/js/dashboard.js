async function loadDashboard(){

let token =
localStorage.getItem(
"token");


let response=

await fetch(

"http://localhost:8080/dashboard",

{

headers:{

Authorization:

"Bearer "+token

}

});


let data=
await response.json();


document
.getElementById(
"books")

.innerText=

data.totalBooks;



document
.getElementById(
"students")

.innerText=

data.totalStudents;



document
.getElementById(
"issued")

.innerText=

data.totalIssuedBooks;

}

loadDashboard();
function logout(){

localStorage.removeItem(
"token");

window.location.href="/";

}