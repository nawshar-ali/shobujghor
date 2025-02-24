import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const Home = () => {
    const [categories, setCategories] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await fetch("http://localhost:8001/gateway/home/load");
                const data = await response.json();
                setCategories(data.categories);
            } catch (error) {
                console.error("Error fetching categories", error);
            }
        };

        fetchCategories();
    }, []);

    return (
        <div className="container mt-5">
            <h3>Categories</h3>
            <div className="row">
                {categories.map((category) => (
                    <div className="col-md-4 mb-4" key={category.name}>
                        <div className="card">
                            <img src={category.logoUrl} className="card-img-top" alt={category.name} />
                            <div className="card-body">
                                <h5 className="card-title">{category.name}</h5>
                                <p className="card-text">{category.shortDescription}</p>
                                <button
                                    className="btn btn-primary"
                                    onClick={() => navigate(`/category/${category.name}`)}
                                >
                                    View Category Items
                                </button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Home;
