import React from "react";
import {Route, BrowserRouter, Routes} from "react-router-dom";
import {AuthProvider} from "./context/AuthContext";
import {CartProvider} from "./context/CartContext";
import Home from "./components/Home";
import Login from "./components/Login";
import CategoryItems from "./components/CategoryItems";
import Navbar from "./components/Navbar";
import Register from "./components/Register";
import EmailVerification from "./components/EmailVerification";
import ViewCart from "./components/ViewCart";
import OrderList from "./components/OrderList";

const EmailVerificationPage = () => (
    <div className="container mt-5 text-center">
        <h3>Check your email for the verification link</h3>
    </div>
);

const App = () => {
    return (
        <>
            <BrowserRouter>
                <Navbar/>
                <AuthProvider>
                    <CartProvider>
                        <Routes>
                            <Route path="/" element={<Home/>}/>
                            <Route path="/login" element={<Login/>}/>
                            <Route path="/category/:categoryName" element={<CategoryItems/>}/>
                            <Route path="/register" element={<Register />} />
                            <Route path="/email-verification" element={<EmailVerificationPage />} />
                            <Route path="/verify-email" element={<EmailVerification />} />
                            <Route path="/cart" element={<ViewCart />} />
                            <Route path="/order-list" element={<OrderList />} />
                        </Routes>
                    </CartProvider>
                </AuthProvider>
            </BrowserRouter>
        </>
    );
};

export default App;
