
//pass thourgh username and id
export const Nav = ({userData}) => {
    return(
        <div className="navbar navbar-light m-3 rounded-pill">
            <div className="container-fluid">
                <p className="navbar-brand  m-2">WELCOME, {userData.username}</p>
                <button className="btn log-out p-2 m-2 display-4">Log Out</button>
            </div>
        </div>
    )
}