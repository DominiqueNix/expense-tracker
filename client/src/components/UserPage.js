import React, {useState, useEffect} from "react"
import { useParams, useNavigate } from "react-router-dom";
import apiURL from "../api";
import { Nav } from "./Nav";
import { Expenses } from "./Expenses";
import { AddTransaction } from "./AddTransaction";

export const UserPage = () => {

    const [success, setSuccess] = useState(false)
    const [userData, setUserData] = useState("");
    const [expenses, setExpenses] = useState([]);
    const [income, setIncome] = useState([]);
    const [total, setTotal] = useState(0);
    //create state for total expenes
    //create state for total income
    const { id } = useParams();
    const navigate = useNavigate();

    const [addTrans, setAddTrans] = useState({
        id: 0, 
        type: "",
        name:"", 
        price: "",
        categoryId: 0,
        userId: id
    })

    async function fetchUserData(){
        // console.log(id)
        const res = await fetch(`${apiURL}/user/${id}`)
        const data = await res.json();
        
        let expArr = [];
        let incArr = [];
        let totOut = 0;
        let totIn = 0
        for(let i=0; i < data.expenses.length; i++){
            if(data.expenses[i].type=="expense"){
                expArr.push(data.expenses[i])
                setExpenses(expArr)
                totOut += data.expenses[i].price
                setTotal(totOut)
            } else {
                incArr.push(data.expenses[i]);
                setIncome(incArr)
                totIn += data.expenses[i].price
                setTotal(totIn)
            }
        }
        setTotal(totIn-totOut)
        setUserData(data)
    }


    useEffect(()=>{
        fetchUserData();
    }, [])

    return(
        
        <main className="userPage">
        {userData ? (
             <> 
             { userData.loggedIn = 1 ? ( 
            <>
            {/* Pass through username and id */}
                <Nav userData={userData}/>
                <div className="d-flex main-content justify-content-around mx-auto">
                  {/* pass through an object of expenses */}

                <Expenses title={"Income"} expenses={income}/>

                <div className="middle-wrapper d-flex flex-column ">
                  <section className="total-wrapper d-flex align-items-center justify-content-center">
                    <div className="total d-flex">                    
                        <section className="total-balace d-flex flex-column">
                            <h1 className="display-6">Total Balace</h1>
                            { total != 0 && (
                                <p>{total}</p>
                            )}
                        </section>
                        <section className="budget"></section>
                    </div>
                </section>
                <AddTransaction addTrans={addTrans} setAddTrans={setAddTrans} setSuccess={setSuccess} success={success}/>
                <section className="chart">

                </section>  
                </div>
                

                {/* pass through an object of income */}
                <Expenses title={"Expenses"} expenses={expenses}/>  
                </div>
                
            </>
             ) : (
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
        )} 
        </main>
    )
}