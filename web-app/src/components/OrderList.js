import React, { useEffect, useState } from "react";
import {useAuth} from "../context/AuthContext";
import {useCart} from "../context/CartContext";
import {useNavigate} from "react-router-dom";

const OrderList = () => {
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    const { user, setRedirectPath } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchOrders = async () => {
            const token = getToken();

            if (!token) {
                setRedirectPath(`/order-list`);
                navigate("/login");
                setLoading(false);
                return;
            }

            const request = {
                customerEmail: localStorage.getItem("email")
            };

            try {
                const response = await fetch("http://localhost:8001/gateway/order/list", {
                    method: "POST",
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(request)
                });

                if (!response.ok) {
                    throw new Error("Failed to fetch orders. Please try again.");
                }

                const data = await response.json();
                setOrders(data.orders);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchOrders();
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

    return (
        <div className="container">
            <h2 className="my-4 text-center">Order List</h2>

            {loading && <div className="alert alert-info text-center">Loading...</div>}
            {error && <div className="alert alert-danger">{error}</div>}

            {!loading && !error && orders.length === 0 && (
                <div className="alert alert-warning text-center">No orders found.</div>
            )}

            {!loading && !error && orders.length > 0 && (
                <div className="table-responsive">
                    <table className="table table-bordered table-hover">
                        <thead className="table-dark">
                        <tr>
                            <th>#</th>
                            <th>Order ID</th>
                            <th>Original Price</th>
                            <th>Discount Price</th>
                            <th>Actual Price</th>
                            <th>Order Date</th>
                            <th>Delivery Address</th>
                            <th>Status</th>
                            <th>Items</th>
                        </tr>
                        </thead>
                        <tbody>
                        {orders.map((order, index) => (
                            <tr key={order.id}>
                                <td>{index + 1}</td>
                                <td>{order.id}</td>
                                <td>${order.originalPrice}</td>
                                <td>${order.discountPrice}</td>
                                <td>${order.actualPrice}</td>
                                <td>{new Date(order.orderDateTime).toLocaleString()}</td>
                                <td>{order.deliveryAddress}</td>
                                <td>
                    <span className={`badge bg-${getStatusColor(order.status)}`}>
                      {order.status}
                    </span>
                                </td>
                                <td>
                                    <ul className="list-unstyled mb-0">
                                        {order.items.map((item, i) => (
                                            <li key={i}>- {item}</li>
                                        ))}
                                    </ul>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

// Function to assign Bootstrap badge color based on order status
const getStatusColor = (status) => {
    switch (status) {
        case "PROCESSING":
            return "warning";
        case "SHIPPED":
            return "primary";
        case "DELIVERED":
            return "success";
        case "CANCELLED":
            return "danger";
        default:
            return "secondary";
    }
};

export default OrderList;
