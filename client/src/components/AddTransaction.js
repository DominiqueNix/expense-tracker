import React, { useState } from "react"
import apiURL from "../api";


export const AddTransaction =({addTrans, setAddTrans, setSuccess, success}) => {

    // function handleChange(e){
    //     console.log()
    //     setAddTrans({...addTrans, name: e.target.value  })
    // }

    async function handleAddTransaction(e){
        e.preventDefault();
        const postTrans = await fetch(`${apiURL}/addexpense`, {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json'
            }, 
            body: JSON.stringify(addTrans)
        })

        const status = await postTrans.status;
        
        if(status ==200){
            setSuccess(true)
            setTimeout(() => {
                setSuccess(false)
            }, 3000)
        }
    }

    return(
        <>
        <button type="button" className=" add-exp" data-toggle="modal" data-target="#addTrans">ADD TRANSACTION</button>
                {/* ADD TRANSACTION MODAL SECTION */}
        <div className="modal fade" id="addTrans" tabIndex={-1} role="dialog">
        { success && (
                <div class="alert alert-success w-75 mx-auto mt-5" role="alert">
                     Transaction Added!
                </div>
             )} 
            <div className="modal-dialog modal-dialog-centered" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="display-5">Add Transaction</h5>
                    </div>
                    <div className="modal-body">
                    <form className="form text-white p-1 d-flex flex-column justify-content-center">
                        <div className="form-group p-1">
                            <label htmlFor="type">Select Type</label>
                            <select class="form-control"  id="type" name="type"
                            onChange={(e) => setAddTrans({...addTrans, type: e.target.value  })}
                            >
                                <option value="income">Income</option>
                                <option value="expense">Expense</option>
                            </select>
                        </div>
                        <div className="form-group p-1">
                            <label htmlFor="name">Name</label>
                            <input type="text" className="form-control" id="name" placeholder="Enter transaction name" name="name"
                            value={addTrans.name}
                            onChange={(e) => setAddTrans({...addTrans, name: e.target.value  })}
                            />
                        </div>
                        <div className="form-group p-1">
                            <label htmlFor="price">Price</label>
                            <input type="text" className="form-control" id="price" placeholder="Enter transaction price" name="price"
                            value={addTrans.price}
                            onChange={(e) => setAddTrans({...addTrans, price: e.target.value  })}
                            />
                        </div>
                        {/* <div className="form-group p-1">
                            <label htmlFor="category">Category</label>
                            <input type="text" className="form-control" id="category" placeholder="Enter transaction's category" 
                            // value={login.password}
                            // onChange={(e) => setlogin({...login, password: e.target.value})}
                            />
                        </div> */}
                       
                    </form>
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" className="btn btn-primary" onClick={handleAddTransaction}>Save changes</button>
                    </div>
                </div>
            </div>
        </div>
        </>
    )
    
}