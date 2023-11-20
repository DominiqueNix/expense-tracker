import * as React from 'react';
import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import dayjs from 'dayjs';
import { useState } from 'react';
import "bootstrap-icons/font/bootstrap-icons.css";


export const Expenses =({expenses, title, categories}) =>{
    const [updating, setUpdating] = useState(false)

    //call expenses update function

    //call expenses delete funciton

    return(
        <main className="expenses">
            <h1 className="display-4 text-center">{title}</h1>
            {
                expenses.map(exp => (
                <div key={exp.id}>
                    <button className="btn each-exp" data-toggle="modal" data-target="#single-item">{exp.name.toUpperCase()}.....${exp.price}</button>
                    <div className="modal fade" id="single-item" tabIndex={-1} role="dialog" aria-hidden="true">
                        <div className="modal-dialog modal-dialog-centered" role="document">
                            <div className="modal-content">
                            {updating ? (
                                <>
                                <div className="modal-header">
                        <h5 className="display-5">Update {exp.name}</h5>
                    </div>
                                <form className="form p-1 d-flex flex-column justify-content-center">
                                    <div className="modal-body"> 
                                        <div className="form-group p-1">
                                            <label htmlFor="type">Select Type</label>
                                                <select class="form-control"  id="type" name="type" defaultValue="income"
                                                    // onChange={(e) => setAddTrans({...addTrans, type: e.target.value  })}
                                                >
                                                    <option value="income">Income</option>
                                                    <option value="expense">Expense</option>
                                                </select>
                                        </div>
                                        <div className="form-group p-1">
                                            <label htmlFor="name">Name</label>
                                            <input type="text" className="form-control" id="name" placeholder="Enter transaction name" name="name"
                                                // value={addTrans.name}
                                                // onChange={(e) => setAddTrans({...addTrans, name: e.target.value  })}
                                            />
                                        </div>
                                        <div className="form-group p-1">
                                            <label htmlFor="price">Price</label>
                                            <input type="text" className="form-control" id="price" placeholder="Enter transaction price" name="price"
                                                // value={addTrans.price}
                                                // onChange={(e) => setAddTrans({...addTrans, price: e.target.value  })}
                                            />
                                        </div>
                                        <label htmlFor='categories' className='mt-2'>Category</label>
                                        <Autocomplete 
                                            sx={{marginTop: "1%"}}
                                            freeSolo
                                            id="categories"
                                            // key={success}
                                            // disableClearable
                                            // defaultValue={null}
                                            // onChange={(e) => setAddTrans({...addTrans, category:e.target.textContent})}
                                            options={categories}
                                            renderInput={(params) => <TextField 
                                            onChange={(e) => {
                                                //    setAddTrans({...addTrans, category: e.target.value})
                                                // console.log(e)
                                            }}
                                            {...params} label="Search categories" 
                                            />}
                                        />
                                        <div className='mt-3'>
                                            {/* <label htmlFor='date'>Date</label> */}
                                            <LocalizationProvider  dateAdapter={AdapterDayjs}>
                                                <DatePicker id="date" defaultValue={dayjs()} onChange={(e) =>{                                           // console.log(e)
                                                            //    setAddTrans({...addTrans, date:`${e.$y}-${e.$M+1}-${e.$D}`})
                                                }}/>
                                            </LocalizationProvider>
                                        </div>
                                    </div>
                                    <div className="modal-footer">
                                        <button type="button" className="btn btn-secondary" onClick={() => setUpdating(false)}>Cancel</button>
                                        <button type="submit" className="btn btn-primary">Save changes</button>
                                    </div>
                                </form>
                                </>
                            ):(
                                <>
                                <div className="modal-header">
                                    <h1 className="modal-title">
                                        {exp.name}
                                    </h1>
                                    <button type="button" className="close btn" data-dismiss="modal">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div className="modal-body lh-1 p-5">
                                    <p>Type</p>
                                    <p className="border border-secondary rounded p-2">{exp.type}</p>
                                    <p>Name</p>
                                    <p className="border border-secondary rounded p-2">{exp.name}</p>
                                    <p>Price</p>
                                    <p className="border border-secondary rounded p-2">{exp.price}</p>
                                    <p>Category</p>
                                    <p className="border border-secondary rounded p-2">{exp.category}</p>
                                    <p>Date</p>
                                    <p className="border border-secondary rounded p-2">{exp.date}</p>
                                </div>
                                <div className="modal-footer">
                                    <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" className="btn btn-primary" onClick={() => setUpdating(true)}>Update {title}</button>
                                </div>
                                </>
                            )
                            }
                            </div> 
                        </div>
                    </div>
                </div>
                ))                
            }
        </main>
    )
}