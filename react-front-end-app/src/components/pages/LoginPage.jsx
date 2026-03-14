import React from 'react';
import Button from '../common/Button';
import './pages.css';

const LoginPage = () => {
    return (
        <main>
           <div className="login-page">
                <h1>Welcome to Memory Nest - where memories find a home, and every moment matters</h1>
                    <form>
                <div className="login-button-container">
                    <Button type="button"
                     onClick={(event) => {
                         window.location.href = "http://localhost:8080/oauth2/authorization/google";
                     }} label="Login as Google user"></Button>
                </div>
                </form>
        </div>
        </main>
    )
}

export default LoginPage;
