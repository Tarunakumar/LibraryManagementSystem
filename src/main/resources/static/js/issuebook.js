async function loadData(){


let token=

localStorage
.getItem(
"token");



let students=

await fetch(

"/students",

{

headers:{

Authorization:

"Bearer "+token

}

});



let books=

await fetch(

"/books",

{

headers:{

Authorization:

"Bearer "+token

}

});



students=

await students.json();

books=

await books.json();


let studentOptions="";

students.forEach(s=>{

studentOptions+=

`<option value=${s.id}>

${s.name}

</option>`

});


document
.getElementById(
"student")

.innerHTML=

studentOptions;



let bookOptions="";

books.forEach(b=>{

bookOptions+=

`<option value=${b.id}>

${b.title}

</option>`

});


document
.getElementById(
"book")

.innerHTML=

bookOptions;

}



async function issueBook(){


let data={

studentId:

document
.getElementById(
"student")
.value,


bookId:

document
.getElementById(
"book")
.value

};



await fetch(

"/issue",

{

method:"POST",

headers:{

"Content-Type":
"application/json",

Authorization:

"Bearer "+

localStorage
.getItem(
"token")

},

body:
JSON.stringify(
data)

});


alert(
"Book Issued");

}



function logout(){

localStorage
.removeItem(
"token");

window.location.href="/";

}


loadData();