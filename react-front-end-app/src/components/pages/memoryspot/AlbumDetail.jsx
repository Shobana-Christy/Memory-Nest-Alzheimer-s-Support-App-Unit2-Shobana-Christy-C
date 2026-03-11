import { Link } from "react-router";
import { useState } from "react";
import "./memoryspot.css"
import LoadingPage from "../LoadingPage";
import { useEffect } from "react";
import { fetchAlbumContent } from "../../common/dataCollection";

const AlbumDetail = ({ album }) => {

    let [albumContent, setAlbumContent] = useState([]);

    useEffect(() => {
        console.log("album: ", album);
        let albumContentPromise = fetchAlbumContent(album.name);
        albumContentPromise.then((contentAsList) => {
            setAlbumContent(contentAsList);
        });
    }, [album]);

    return (
        <>
        <div className="album-container">
            {
                albumContent.map((conent, index) =>
                        (<img src={conent.url} alt={conent.name} key={index} className="album-picture" />)
                )
            }
            </div>
        </>
    )
}
export default AlbumDetail;