import { Link } from "react-router";
import Tile from "../../common/Tile";


const EngagementCenterPage = () => {
    const engagements = [
        {
            id: 1,
            name: "Chess",
            source: "https://chess.com"
        },
        {
            id: 2,
            name: "Sudoku",
            source: "https://sudoku.com/"
        },

    ];
    return (
        <main>
            <div className="main-page-header">

                <div className="sub-nav">
                    <Link to="/home" className="link">Home</Link>
                </div>

                <h3>Engagement Center</h3>
            </div>
            <div className="engagement-detail-container">
                {
                    engagements.map((engagement) => (
                        <Link className="link" key={engagement.id} to={engagement.source} target="_blank">
                            <Tile text={engagement.name} ></Tile>
                        </Link>
                    ))
                }
            </div>
        </main>
    )
}
export default EngagementCenterPage;