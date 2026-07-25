async function loadStudents(){


let response=

await fetch(

"http://localhost:8080/students",

{

headers:{

Authorization:

"Bearer "+

localStorage
.getItem(
"token")

}

});


let students=

await response.json();


let table="";


students.forEach(student=>{


table+=`

<tr>

<td>${student.id}</td>

<td>${student.name}</td>

<td>${student.usn}</td>

<td>${student.email}</td>

<td>${student.mobile}</td>

<td>${student.age}</td>

<td>

<button

class="delete-btn"

onclick=

"deleteStudent(
${student.id})">

Delete

</button>

</td>

</tr>

`;

});


document
.getElementById(
"studentTable")

.innerHTML=

table;

}



async function addStudent(){
	
	let email =
	document.getElementById("email").value.trim();

	let gmailPattern =
	/^[A-Za-z0-9._%+-]+@gmail\.com$/;


	if(!gmailPattern.test(email)){

	    alert(
	        "Please enter a valid Gmail address"
	    );

	    return;
	}

let student={

name:
document.getElementById(
"name").value,

usn:
document.getElementById(
"usn").value,

email:
document.getElementById(
"email").value,

mobile:
document.getElementById(
"mobile").value,

age:
document.getElementById(
"age").value

};

console.log(
"Sending:",
student);


try{

let response=

await fetch(

"http://localhost:8080/students",

{

method:"POST",

headers:{

"Content-Type":
"application/json",

Authorization:
"Bearer "+
localStorage.getItem(
"token")

},

body:
JSON.stringify(student)

});


console.log(
"Status:",
response.status);


let result=

await response.text();

console.log(
"Response:",
result);


if(response.ok){

alert(
"Student Added Successfully");

loadStudents();

}
else{

alert(
"Error: "+result);

}

}

catch(error){

console.log(error);

alert(
"Check browser console");

}

}


async function deleteStudent(id){


await fetch(

"http://localhost:8080/students/"+id,

{

method:"DELETE",

headers:{

Authorization:

"Bearer "+

localStorage
.getItem(
"token")

}

});


loadStudents();

}


function logout(){

localStorage
.removeItem(
"token");

window.location.href="/";

}


loadStudents();