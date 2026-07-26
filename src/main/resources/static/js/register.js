async function register(){

    let username =
        document.getElementById("username").value.trim();

    let password =
        document.getElementById("password").value.trim();

    let role =
        document.getElementById("role").value;


    if(username === "" || password === ""){

        alert("Please enter username and password");
        return;
    }


    let user = {

        username: username,
        password: password,
        role: role

    };


    try{

        let response =
            await fetch("/auth/register", {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(user)

            });


        let msg =
            await response.text();


        if(response.ok){

            alert("Registered Successfully");

            window.location.href =
                "/login-page";

        }
        else{

            alert(msg);

        }

    }
    catch(error){

        console.error(error);

        alert("Registration failed. Please try again.");

    }

}