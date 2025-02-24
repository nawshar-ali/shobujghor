import React, { useState } from "react";
import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const [formData, setFormData] = useState({ email: "", password: "" });
    const { login, redirectPath, setRedirectPath } = useAuth();
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch("http://localhost:8001/gateway/auth/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                const data = await response.json();
                login(data.accessToken); // Save user info in context
                localStorage.setItem("token", data.accessToken); // Save JWT
                localStorage.setItem("email", formData.email)

                if (redirectPath) {
                    navigate(redirectPath); // Redirect back to the saved path
                    setRedirectPath(null); // Clear redirect path
                } else {
                    navigate("/");
                }
            } else {
                alert("Login failed. Please try again.");
            }
        } catch (error) {
            console.error("Login error", error);
        }
    };

    return (
        <div className="container mt-5">
            <h3>Login</h3>
            <form onSubmit={handleSubmit}>
                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={handleChange}
                    className="form-control mb-3"
                    required
                />
                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    value={formData.password}
                    onChange={handleChange}
                    className="form-control mb-3"
                    required
                />
                <button type="submit" className="btn btn-primary">Login</button>
            </form>
        </div>
    );
};

export default Login;
