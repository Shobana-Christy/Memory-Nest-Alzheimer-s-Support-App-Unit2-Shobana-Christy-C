import { Link } from "react-router";
import Tile from "../../common/Tile";
import {useState, useEffect} from 'react';
import {fetchEngagements} from '../../common/dataCollection';

const EngagementCenterPage = () => {

    let [engagements, setEngagements] = useState([]);
    useEffect(() => {
        let responsePromise = fetchEngagements();
        responsePromise.then(response => {
          if(response) {
            setEngagements(response);
          }
        });
    }, []);

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
                    engagements.map((engagement, index) => (
                        <Link className="link" key={index} to={engagement.src} target="_blank">
                            <Tile text={engagement.name} ></Tile>
                        </Link>
                    ))
                }
            </div>
        </main>
    )
}
export default EngagementCenterPage;