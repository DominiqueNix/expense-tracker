
//pass through data for all expenes(either for income or an expense), and title 
export const Expenses =({expenses, title}) =>{
    return(
        <main className="expenses">
            <h1 className="display-4 text-center">{title}</h1>
            {
                expenses.map(exp => (
                    <div key={exp.id}>
                    <button className="btn each-exp">{exp.name.toUpperCase()}.....${exp.price}</button>
                    </div>
                ))                
            }
        </main>
    )
}