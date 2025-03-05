import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { useCart } from "../context/CartContext";

const CategoryItems = () => {
    const { categoryName } = useParams();
    const [items, setItems] = useState([]);
    const { user, setRedirectPath } = useAuth();
    const { addToCart } = useCart();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchItems = async () => {
            try {
                const response = await fetch("http://localhost:8001/gateway/inventory/item/list", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ categoryName }),
                });
                const data = await response.json();
                setItems(data.items);
            } catch (error) {
                console.error("Error fetching items", error);
            }
        };

        fetchItems();
    }, [categoryName]);

    const handleAddToCart = async (itemId) => {
        if (getToken()) {

            await addToCart({itemId, categoryName});
        } else {
            setRedirectPath(`/category/${categoryName}`); // Save the current path
            navigate("/login");
        }
    };

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
        <div className="container mt-5">
            <h3>Items in {categoryName}</h3>
            <div className="row">
                {items.map((item) => (
                    <div className="col-md-4 mb-4" key={item.name}>
                        <div className="card">
                            <img src={item.logoUrl} className="card-img-top" alt={item.name} />
                            <div className="card-body">
                                <h5 className="card-title">{item.name}</h5>
                                <p className="card-text">{item.shortDescription}</p>
                                <p className="card-text">Price: ${item.price}</p>
                                <button
                                    className="btn btn-primary"
                                    onClick={() => handleAddToCart(item.name)}
                                >
                                    Add to Cart
                                </button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default CategoryItems;
