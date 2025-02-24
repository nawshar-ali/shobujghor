import React, { useEffect, useState } from "react";

const ViewCart = () => {
    const [cart, setCart] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchCart = async () => {
            try {
                const response = await fetch("http://localhost:8001/gateway/cart/view", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: `Bearer ${localStorage.getItem("token")}`, // Assuming authentication is needed
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

            <button className="btn btn-success mt-3">Proceed to Checkout</button>
        </div>
    );
};

export default ViewCart;
