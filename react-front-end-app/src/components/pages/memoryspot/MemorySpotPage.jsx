import { Link } from "react-router";
import Tile from "../../common/Tile";
import { useState } from "react";
import "./memoryspot.css"
import LoadingPage from "../LoadingPage";
import AlbumDetail from "./AlbumDetail";
import { useEffect } from "react";
import { fetchAlbums } from "../../common/dataCollection";

const MemorySpotPage = () => {
    let [isLoading, setIsLoading] = useState(true);
    let [albums, setAlbums] = useState([]);
    let [selectedAlbum, setSelectedAlbum] = useState(null);

    useEffect(() => {
        const responsePromiseOne = fetchAlbums();
        responsePromiseOne.then((albums) => {
            setIsLoading(false);
            setAlbums(albums);
            console.log("albums: ", albums);
        });
    }, []);

    const handleEvent = (event, album) => {
        event.preventDefault();
        setSelectedAlbum(album);
    }
    const goBack = () => {
        setSelectedAlbum(null);
    }

    return (
        <main>
            {
                !selectedAlbum &&
                (
                    <>
                        <div className="main-page-header">

                            <div className="sub-nav">
                                <Link to="/home" className="link">Home</Link>
                            </div>

                            <h3>Memory Spot</h3>
                        </div>

                    </>

                )

            }
            {
                selectedAlbum &&
                (
                    <div className="main-page-header">

                        <div className="sub-nav">
                            <Link to="/home" className="link">Home</Link> &gt;
                            <Link onClick={goBack}> Albums</Link> &gt; {selectedAlbum.name}
                        </div>

                        <h3>Memory Spot</h3>
                    </div>
                )
            }
            <div className="memoryspot-container">
                {
                    isLoading &&
                    (<LoadingPage dataName={albums} />)
                }

                {
                    !isLoading && albums.length == 0 &&
                    <p><em> No album is set</em></p>

                }

                {
                    !isLoading && albums.length > 0 && !selectedAlbum &&
                    albums.map((album) => (
                        <Link key={album.id} onClick={(event) => handleEvent(event, album)}>
                            <Tile>
                                <h2>
                                    {album.name}
                                </h2>
                                <h3>
                                    {album.description}
                                </h3>
                            </Tile>
                        </Link>
                    ))
                }

                {
                    selectedAlbum && (<AlbumDetail album={selectedAlbum} />)
                }
            </div>



        </main>
    )
}
export default MemorySpotPage;