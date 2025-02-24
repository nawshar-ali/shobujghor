import React, { useEffect, useState } from "react";

const EmailVerification = () => {
    const [message, setMessage] = useState("");

    useEffect(() => {
        const params = new URLSearchParams(window.location.search);
        const token = params.get("token");

        const verifyEmail = async () => {
            try {
                const response = await fetch(`http://localhost:8001/gateway/auth/verify-email?token=${token}`);
                const data = await response.text();
                setMessage(data);
            } catch (err) {
                setMessage("Verification failed.");
            }
        };

        verifyEmail();
    }, []);

    return (
        <div className="container mt-5">
            <h3>Email Verification</h3>
            <p>{message}</p>
        </div>
    );
};

export default EmailVerification;