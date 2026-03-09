import "./common.css"

const Alert = ({message}) => {
    return (
        <>
            <div className="alert-box-container">{message}</div>
        </>
    );
}

export default Alert;