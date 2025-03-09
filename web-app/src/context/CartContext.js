import React, { createContext, useContext } from "react";

const CartContext = createContext();

export const CartProvider = ({ children }) => {
    const addToCart = async (data) => {
        try {
            const response = await fetch("http://localhost:8001/gateway/cart/add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${getToken()}`,
                },

                body: JSON.stringify({ itemName: data.itemId, categoryName: data.categoryName, quantity: "1", cartId: localStorage.getItem("email")}),
            });

            if (!response.ok) {
                throw new Error("Failed to add to cart");
            }
            alert("Product added to cart!");
        } catch (error) {
            console.error(error);
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

    return <CartContext.Provider value={{ addToCart }}>{children}</CartContext.Provider>;
};

export const useCart = () => useContext(CartContext);
