import React from "react";
import { useNavigate } from "react-router-dom";
import TopHeader from "../21.TopHeader";
import "../../css_files/revenuecss/15.RevenueFinancial.css";

const RevenueFinancial = () => {
    const navigate = useNavigate();

    return (
        <div className="dashboard-container">
            {/* Sidebar Navigasi Kiri */}
            <aside className="sidebar">
                <h2 className="sidebar-title">DASHBOARD</h2>
                <ul className="sidebar-menu">
                    <li className="menu-item" onClick={() => navigate("/users")}>User List</li>
                    <li className="menu-item" onClick={() => navigate("/coa")}>COA List</li>
                    <li className="menu-item" onClick={() => navigate("/opex")}>OPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/capex")}>CAPEX Financial</li>
                    <li className="menu-item active" onClick={() => navigate("/revenue")}>REVENUE Financial</li>
                    <li className="menu-item" onClick={() => navigate("/report")}>Final Report</li>
                </ul>
            </aside>

            {/* Konten Utama (Kanan) */}
            <main className="main-area">
                {/* Header Profil Atas (Akun Admin) */}
                <TopHeader username="admin_fpa" role="ADMIN" />

                {/* Area Isi REVENUE List */}
                <section className="content-section">
                    <div className="page-title-banner">
                        <h2>REVENUE LIST</h2>
                    </div>

                    {/* Tombol Pilihan Actual & Budget */}
                    <div className="opex-buttons-container">
                        <button className="opex-btn" onClick={() => navigate("/revenue/actual")}>
                            Actual
                        </button>
                        <button className="opex-btn" onClick={() => navigate("/revenue/budget")}>
                            Budget
                        </button>
                    </div>
                </section>
            </main>
        </div>
    );
};

export default RevenueFinancial;