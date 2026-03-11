import {Link} from 'react-router';
import './layout.css';

const Header = () => {
    return (

        <header className="header">
            <img src="/images/logo.SVG" alt="Memory Nest logo" id="header-logo"/>
            <nav>
                <ul className="menu">
                    <li><Link to="/contactus" className="link">Contact Us</Link> |</li>
                    <li><Link to="/aboutus" className="link">About Us</Link> |</li>
                </ul>
            </nav>

        </header>
    )
}

export default Header;