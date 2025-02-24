import React, { createContext, useContext } from "react";

const CartContext = createContext();

export const CartProvider = ({ children }) => {
    const addToCart = async (data) => {
        try {
            const response = await fetch("http://localhost:8001/gateway/cart/add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
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

    return <CartContext.Provider value={{ addToCart }}>{children}</CartContext.Provider>;
};

export const useCart = () => useContext(CartContext);
