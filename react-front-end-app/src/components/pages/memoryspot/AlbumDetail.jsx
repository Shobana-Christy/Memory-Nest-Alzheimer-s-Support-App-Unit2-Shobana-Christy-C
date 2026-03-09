import { Link } from "react-router";

const AlbumDetail = ({ album }) => {

    return (
        <>
        <div className="album-container">
            {
                album.src.map((url, index) => 
                        (<img src={url} alt={album.name} key={index} className="album-picture"/>)
                )
            }
            </div>
        </>
    )
}
export default AlbumDetail;