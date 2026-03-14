const Button = ({ id, name, label, value, type, classes,onClick }) => {

    return (
        <button id={id} name={name} value={value} type={type} className={classes? classes: "button-class"} onClick={onClick}>
            {label}
        </button>
    );
}
export default Button;