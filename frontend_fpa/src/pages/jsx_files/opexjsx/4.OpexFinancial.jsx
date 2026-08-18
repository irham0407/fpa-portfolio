import React from "react";
import { useNavigate } from "react-router-dom";
import TopHeader from "../21.TopHeader";
import "../../css_files/opexcss/4.OpexFinancial.css";

const OpexFinancial = () => {
    const navigate = useNavigate();

    return (
        <div className="dashboard-container">
            {/* Sidebar Navigasi Kiri */}
            <aside className="sidebar">
                <h2 className="sidebar-title">DASHBOARD</h2>
                <ul className="sidebar-menu">
                    <li className="menu-item" onClick={() => navigate("/users")}>User List</li>
                    <li className="menu-item" onClick={() => navigate("/coa")}>COA List</li>
                    <li className="menu-item active" onClick={() => navigate("/opex")}>OPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/capex")}>CAPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/revenue")}>REVENUE Financial</li>
                    <li className="menu-item" onClick={() => navigate("/report")}>Final Report</li>
                </ul>
            </aside>

            <main className="main-area">
                {/* Konten Utama (Kanan) */}
                <TopHeader username="admin_fpa" role="ADMIN" />

            {/* Area Isi OPEX List */}
                <section className="content-section">
                    <div className="page-title-banner">
                        <h2>OPEX LIST</h2>
                    </div>

                    {/* Tombol Pilihan Actual & Budget */}
                    <div className="opex-buttons-container">
                        <button className="opex-btn" onClick={() => navigate("/opex/actual")}>
                            Actual
                        </button>
                        <button className="opex-btn" onClick={() => navigate("/opex/budget")}>
                            Budget
                        </button>
                    </div>
                </section>
            </main>
        </div>
    );
};

export default OpexFinancial;