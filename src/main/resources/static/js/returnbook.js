async function loadIssuedBooks(){

let response=

await fetch(

"/issue",

{

headers:{

Authorization:

"Bearer "+

localStorage.getItem(
"token")

}

});


console.log(
response.status);

let issues=

await response.json();

console.log(
issues);

let table="";


issues.forEach(issue=>{

if(!issue.returned){

table += `

<tr>

<td>${issue.id}</td>

<td>${issue.student.name}</td>

<td>${issue.book.title}</td>

<td>${issue.issueDate}</td>

<td>

<button
onclick=
"returnBook(${issue.id})">

Return

</button>

</td>

</tr>

`;

}

});


document
.getElementById(
"issueTable")
.innerHTML=
table;

}



async function returnBook(id){

let response=

await fetch(

"/issue/return/"+id,

{

method:"PUT",

headers:{

Authorization:

"Bearer "+

localStorage.getItem(
"token")

}

});

let msg=

await response.text();

alert(msg);

loadIssuedBooks();

}



function logout(){

localStorage.removeItem(
"token");

window.location.href="/";

}


loadIssuedBooks();