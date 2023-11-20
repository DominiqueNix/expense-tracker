import React, {useState, useEffect} from "react"
import { useParams, useNavigate } from "react-router-dom";
import apiURL from "../api";
import { Nav } from "./Nav";
import { Expenses } from "./Expenses";
import { AddTransaction } from "./AddTransaction";
import "bootstrap-icons/font/bootstrap-icons.css";
import dayjs from "dayjs";
import { PieChart } from '@mui/x-charts/PieChart'

export const UserPage = () => {

    const [success, setSuccess] = useState(false)
    const [userData, setUserData] = useState("");
    const [expenses, setExpenses] = useState([]);
    const [income, setIncome] = useState([]);
    const [total, setTotal] = useState(0);
    const [totalExpPrice, setTotalExpPrice] = useState(0);
    const [totalIncPrice, setTotalIncPrice] = useState(0);
    const [categories, setCategories] = useState([]);
    const { id } = useParams();
    const navigate = useNavigate();



    const [addTrans, setAddTrans] = useState({
        // id: 0, 
        type: "",
        name:"", 
        price: "",
        date: dayjs().format('YYYY-MM-DD'),
        category: "",
        userId: id
    })

    // console.log(dayjs().format('YYYY-MM-DD'))

    async function fetchUserData(){
        // console.log(id)
        const res = await fetch(`${apiURL}/user/${id}`)
        const data = await res.json();

        let cat = [];
        let expArr = [];
        let incArr = [];
        let totOut = 0;
        let totIn = 0
        for(let i=0; i < data.expenses.length; i++){
            if(!cat.includes(data.expenses[i].category)){
                cat.push(data.expenses[i].category)
            }
            if(data.expenses[i].type==="expense"){
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
        setCategories(cat)
        setTotalExpPrice(totOut)
        setTotalIncPrice(totIn)
        setTotal(totIn-totOut)
        setUserData(data)
    }

    useEffect(()=>{
        fetchUserData();
    }, [success])

    return(
        
        <main className="userPage">
        {userData ? (
             <> 
             { userData.loggedIn === 1 ? ( 
            <>
            {/* Pass through username and id */}
                <Nav userData={userData}/>
                <div className="d-flex main-content justify-content-around mx-auto">
                  {/* pass through an object of expenses */}

                <Expenses title={"Income"} expenses={income} categories={categories}/>

                <div className="middle-wrapper d-flex flex-column ">
                  <section className="total-wrapper d-flex align-items-center justify-content-center">
                    <div className="total d-flex">                    
                        <section className="total-balace d-flex flex-column align-items-center justify-content-center p-2">
                            <h1 className="display-6">Total Balance</h1>
                            { total !== 0 && (
                                <p className="display-6">{total}</p>
                            )}
                             { totalIncPrice !== 0 && (
                                <p><i className="bi bi-arrow-up-circle"></i> Income: ${totalIncPrice}</p>
                            )}
                             { totalExpPrice !== 0 && (
                                <p><i className="bi bi-arrow-down-circle"></i> Expenses: ${totalExpPrice}</p>
                            )}
                        </section>
                        <section className="budget"></section>
                    </div>
                </section>
                <AddTransaction addTrans={addTrans} setAddTrans={setAddTrans} setSuccess={setSuccess} success={success} id={id} categories={categories} setCategories={setCategories}/>
                <section className="chart d-flex justify-content-center align0items-center">
                <PieChart
                    series={[
                            {
                            data: [{ value: 100, label: "test1" }, { value: 50, label: "test2" }, { value: 50, label: "test3" }],
                            innerRadius: 30,
                            outerRadius: 100,
                            paddingAngle: 0,
                            cornerRadius: 5,
                            startAngle: -180,
                            endAngle: 180,
                            // cx: 150,
                            // cy: 150,
                            }
                        ]}
                        />
                </section>  
                </div>
                

                {/* pass through an object of income */}
                <Expenses title={"Expenses"} expenses={expenses} categories={categories}/>  
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