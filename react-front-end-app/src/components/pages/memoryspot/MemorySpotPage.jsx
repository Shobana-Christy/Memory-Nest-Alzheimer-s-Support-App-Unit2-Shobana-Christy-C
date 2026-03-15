import { Link } from "react-router";
import Tile from "../../common/Tile";
import { useState } from "react";
import "./memoryspot.css"
import LoadingPage from "../LoadingPage";
import AlbumDetail from "./AlbumDetail";
import { useEffect } from "react";
import AlbumForm from "./AlbumForm";
import { fetchAlbums, createAlbum } from "../../common/dataCollection";

const MemorySpotPage = () => {
    let [isLoading, setIsLoading] = useState(true);
    let [albums, setAlbums] = useState([]);
    let [selectedAlbum, setSelectedAlbum] = useState(null);
    let [showAddEditAlbum, setShowAddEditAlbum] = useState(false);
    const initialAlbumData = {
        id: null,
        name: "",
        description: ""
    };
    let [album, setAlbum] = useState(initialAlbumData);

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

    const showEditAlbum = (event, album) => {
        event.preventDefault();
        setAlbum(album);
        setShowAddEditAlbum(true);
    }

    const toggleAlbum = () => {
        setShowAddEditAlbum(!showAddEditAlbum);
        if (!showAddEditAlbum) {
            setAlbum(initialAlbumData);
        }
    }

    const handleSave = (album) => {
        setShowAddEditAlbum(false);
        let responsePromise = createAlbum(album);
        responsePromise.then(response => {
            if (response) {
                let albumFromExistingList = albums.filter(al => al.name == album.name);
                if(albumFromExistingList.length == 0) {
                    setAlbums([...albums, album]);
                }

            }
        });
    }

    const showAlbumDetails = (event, album) => {
        setSelectedAlbum(album);
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
                        <div className="add-link">
                            <a className="link" onClick={toggleAlbum}><span>+</span>Create album</a>
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
                    albums.map((album, index) => (
                        <Link key={index} onClick={() => showAlbumDetails(event, album)}>
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
                    (showAddEditAlbum) &&
                        <AlbumForm album={album} onClose={toggleAlbum} onSave={handleSave} />
                }
                {
                    selectedAlbum && (<AlbumDetail album={selectedAlbum} />)
                }
            </div>



        </main>
    )
}
export default MemorySpotPage;