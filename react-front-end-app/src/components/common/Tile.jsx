import './tile.css'

const Tile = ({text, children}) => {

    return (
        <div className="box-container">
            {text ? (<h2>{text}</h2>) : ""}
            {children}
        </div>
    )
}
export default Tile;