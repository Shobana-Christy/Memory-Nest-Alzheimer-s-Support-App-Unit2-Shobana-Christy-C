import { FaFacebook, FaInstagram, FaTwitter, FaXTwitter } from 'react-icons/fa6';

const Footer = () => {

    let thisYear = new Date().getFullYear();

    return (
        <footer className='footer'>
            <div className='copy-right-col'>&copy; {thisYear} Memory Nest</div>

            <div className='social-icons'>
                <div>Follow Us</div>

                <a href="#" title="Coming Soon">
                    <FaInstagram size={24}/>
                </a>

                <a href="#" title="Coming Soon">
                    <FaFacebook size={24}/>
                </a>

                <a href="#" title="Coming Soon">
                    <FaXTwitter size={24}/>
                </a>

            </div>

        </footer>
    );
}

export default Footer;