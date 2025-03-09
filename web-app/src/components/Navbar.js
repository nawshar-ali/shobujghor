import React from 'react'
import { Link } from 'react-router-dom'

const Navbar = () => {
    function getToken() {
        const itemStr = localStorage.getItem("token");
        if (!itemStr) {
            return null;
        }

        const item = JSON.parse(itemStr);
        const now = Date.now();

        if (now > item.expiry) {
            localStorage.removeItem("token"); // Remove expired token
            return null;
        }

        return item.token;
    }

    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <div className="container-fluid">
                <Link to="/" className="navbar-brand">
                    Shobujghor
                </Link>
                <button
                    className="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                    aria-controls="navbarNav"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav ms-auto">
                        <li className="nav-item">
                            <Link className="nav-link" to="/">
                                Home
                            </Link>
                        </li>
                        {getToken() === null && (
                            <li className="nav-item">
                                <Link className="nav-link" to="/login">
                                    Login
                                </Link>
                            </li>
                        )}
                        {getToken() === null && (
                            <li className="nav-item">
                                <Link className="nav-link" to="/register">
                                    Signup
                                </Link>
                            </li>
                        )}
                        <li className="nav-item">
                            <Link className="nav-link" to="/cart">
                                View Cart
                            </Link>
                        </li>
                        {getToken() && (
                            <li className="nav-item">
                                <Link className="nav-link" to="/order-list">View Orders</Link>
                            </li>
                        )}
                    </ul>
                </div>
            </div>
        </nav>
    );
}

export default Navbar
