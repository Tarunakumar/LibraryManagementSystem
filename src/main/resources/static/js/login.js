async function login(){

    let username =
        document.getElementById("username").value.trim();

    let password =
        document.getElementById("password").value.trim();


    if(username === "" || password === ""){

        alert("Please enter username and password");
        return;
    }


    let user = {

        username: username,
        password: password

    };


    try{

        let response =
            await fetch("/auth/login", {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(user)

            });


        if(response.ok){

            let token =
                await response.text();


            localStorage.setItem(
                "token",
                token
            );


            alert("Login Successful");


            window.location.href =
                "/dashboard-page";

        }
        else{

            let message =
                await response.text();

            console.error(message);

            alert(
                "Invalid Username or Password"
            );

        }

    }
    catch(error){

        console.error(error);

        alert(
            "Login failed. Please try again."
        );

    }

}