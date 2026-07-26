async function loadBooks() {

    try{

        let response = await fetch("/books",

        {

            headers:{

                Authorization:
                "Bearer " +
                localStorage.getItem(
                        "token")

            }

        });


        if(!response.ok){

            alert(
            "Cannot load books");

            return;
        }


        let books =
        await response.json();

        let table = "";


        books.forEach(book=>{

            table += `

            <tr>

            <td>${book.id}</td>

            <td>${book.title}</td>

            <td>${book.author}</td>

            <td>${book.category}</td>

            <td>${book.quantity}</td>

            <td>

            <button
            onclick="deleteBook(${book.id})">

            Delete

            </button>

            </td>

            </tr>

            `;
        });


        document
        .getElementById(
                "bookTable")

        .innerHTML=

        table;

    }

    catch(error){

        console.log(error);

    }

}



async function addBook(){

let book={

title:
document.getElementById("title").value,

author:
document.getElementById("author").value,

category:
document.getElementById("category").value,

price:
document.getElementById("price").value,

quantity:
document.getElementById("quantity").value

};


let response=

await fetch(

"/books",

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
JSON.stringify(book)

});


if(response.ok){

alert(
"Book Added Successfully");

loadBooks();

}
else{

alert(
"Book not added");

console.log(
await response.text());

}

}



async function deleteBook(id){

await fetch(

"/books/"+id,

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


loadBooks();

}


loadBooks();
function logout(){

localStorage.removeItem(
"token");

window.location.href="/";

}