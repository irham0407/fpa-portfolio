import React from "react";
import { useNavigate } from "react-router-dom";
import TopHeader from "../21.TopHeader";
import "../../css_files/capexcss/10.CapexFinancial.css";

const CapexFinancial = () => {
    const navigate = useNavigate();

    return (
        <div className="dashboard-container">
            <aside className="sidebar">
                <h2 className="sidebar-title">DASHBOARD</h2>
                <ul className="sidebar-menu">
                    <li className="menu-item" onClick={() => navigate("/users")}>User List</li>
                    <li className="menu-item" onClick={() => navigate("/coa")}>COA List</li>
                    <li className="menu-item" onClick={() => navigate("/opex")}>OPEX Financial</li>
                    <li className="menu-item active" onClick={() => navigate("/capex")}>CAPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/revenue")}>REVENUE Financial</li>
                    <li className="menu-item" onClick={() => navigate("/report")}>Final Report</li>
                </ul>
            </aside>

            <main className="main-area">
                <TopHeader username="admin_fpa" role="ADMIN" />

                <section className="content-section">
                    <div className="page-title-banner">
                        <h2>CAPEX Financial</h2>
                    </div>

                    <div className="financial-cards-wrapper">
                        <button className="financial-card-btn" onClick={() => navigate("/capex/actual")}>
                            Actual
                        </button>
                        <button className="financial-card-btn" onClick={() => navigate("/capex/budget")}>
                            Budget
                        </button>
                    </div>
                </section>
            </main>
        </div>
    );
};

export default CapexFinancial;