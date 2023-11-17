import React, {useState, useEffect} from "react"
import { useParams, useNavigate } from "react-router-dom";
import apiURL from "../api";
import { Nav } from "./Nav";
import { Expenses } from "./Expenses";

export const UserPage = () => {
    //userdata
    //fetch and set userData
    //effecthook
    //if data {
        //if isloggged if
            //render stuff
    //}:loader

    const [userData, setUserData] = useState("");
    const { id } = useParams();
    const navigate = useNavigate();

    async function fetchUserData(){
        console.log(id)
        const res = await fetch(`${apiURL}/user/${id}`)
        const data = await res.json();
        setUserData(data)
    }

    if(userData){
        console.log(userData)
    }


    useEffect(()=>{
        // fetchUserData();
    }, [])

    return(
        
        <main className="userPage">
        {/* {userData ? ( */}
           {/* <> */}
           {/* { userData.loggedIn == 1 ? ( */}
            <>
            {/* Pass through username and id */}
                <Nav />
                <div className="d-flex main-content justify-content-around mx-auto">
                  {/* pass through an object of expenses */}
                <Expenses />

                <div className="middle-wrapper d-flex flex-column ">
                  <section className="total-wrapper d-flex align-items-center justify-content-center">
                    <div className="total">                    
                        <section className="total-balace"></section>
                        <section className="budget"></section>
                    </div>
                </section>
                <button className="add-exp"></button>
                <section className="chart">

                </section>  
                </div>
                

                {/* pass through an object of income */}
                <Expenses />  
                </div>
                
            </>
           {/* ) : (
            <>
            <h1>Please Login</h1>
            <button onClick={() => navigate("/")} className="btn">GO HOME</button>
            </>
           )}
           </>
        ) : (
            <div class="text-center">
                <div class="spinner-border" role="status">
                    <span class="sr-only">Loading...</span>
                </div>
            </div>
        )} */}
        </main>
    )
}