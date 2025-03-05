import React, { useEffect, useState } from "react";

const ViewCart = () => {
    const [cart, setCart] = useState(null);
    const [loading, setLoading] = useState(true);
    const [deliveryAddress, setDeliveryAddress] = useState("");

    useEffect(() => {
        const fetchCart = async () => {
            try {
                const response = await fetch("http://localhost:8001/gateway/cart/view", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: `Bearer ${getToken()}`, // Assuming authentication is needed
                    },
                    body: JSON.stringify({cartId: localStorage.getItem("email")})
                });

                if (!response.ok) {
                    throw new Error("Failed to fetch cart data");
                }

                const data = await response.json();
                setCart(data);
            } catch (error) {
                console.error("Error fetching cart:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchCart();
    }, []);

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


    const handleCheckout = async () => {
        if (!deliveryAddress) {
            alert("Please enter a delivery address.");
            return;
        }

        const checkoutData = {
            customerEmail: localStorage.getItem("email"), // Assuming cart.id is the customer email
            cartId: localStorage.getItem("email"), // Assuming cartId is the same as the user email or unique ID
            deliveryAddress: deliveryAddress,
        };

        try {
            const response = await fetch("http://localhost:8001/gateway/cart/checkout", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${getToken()}`,
                },
                body: JSON.stringify(checkoutData),
            });

            if (!response.ok) {
                throw new Error("Checkout failed");
            }

            alert("Checkout successful! Your order is being processed.");
            setCart(null); // Clear the cart after successful checkout
        } catch (error) {
            console.error("Error during checkout:", error);
            alert("Checkout failed. Please try again.");
        }
    };

    if (loading) {
        return <div className="container mt-5"><h5>Loading cart...</h5></div>;
    }

    if (!cart) {
        return <div className="container mt-5"><h5>No items in cart.</h5></div>;
    }

    return (
        <div className="container mt-5">
            <h3>Your Cart</h3>
            <table className="table table-bordered mt-3">
                <thead className="thead-dark">
                <tr>
                    <th>Item Name</th>
                    <th>Quantity</th>
                    <th>Actual Price</th>
                    <th>Total Price</th>
                    <th>Total Discount</th>
                </tr>
                </thead>
                <tbody>
                {cart.itemList.map((item, index) => (
                    <tr key={index}>
                        <td>{item.itemName}</td>
                        <td>{item.quantity}</td>
                        <td>${item.actualPrice}</td>
                        <td>${item.totalPrice}</td>
                        <td>${item.totalDiscount}</td>
                    </tr>
                ))}
                </tbody>
            </table>

            <div className="mt-4">
                <h5>Total Bill Amount: ${cart.totalBillAmount}</h5>
                <h5>Total Discount: ${cart.totalDiscountAmount}</h5>
                <h4 className="text-primary">Amount to be Paid: ${cart.amountToBePaid}</h4>
            </div>

            {/* Delivery Address Input */}
            <div className="mt-3">
                <label className="form-label">Delivery Address:</label>
                <input
                    type="text"
                    className="form-control"
                    placeholder="Enter delivery address"
                    value={deliveryAddress}
                    onChange={(e) => setDeliveryAddress(e.target.value)}
                />
            </div>

            <button className="btn btn-success mt-3" onClick={handleCheckout}>
                Proceed to Checkout
            </button>
        </div>
    );
};

export default ViewCart;
