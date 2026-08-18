import React from "react";
import { useNavigate } from "react-router-dom";
import "../../css_files/capexcss/5.CapexFinancial.css";
import TopHeader from "../21.TopHeader";

const CapexFinancial = () => {
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
                    <li className="menu-item active" onClick={() => navigate("/capex")}>CAPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/revenue")}>REVENUE Financial</li>
                    <li className="menu-item" onClick={() => navigate("/report")}>Final Report</li>
                </ul>
            </aside>
            <main className="main-area">
            {/* Konten Utama (Kanan) */}
                <TopHeader username="admin_fpa" role="ADMIN" />

                {/* Area Isi CAPEX List */}
                <section className="content-section">
                    <div className="page-title-banner">
                        <h2>CAPEX LIST</h2>
                    </div>

                    {/* Tombol Pilihan Actual & Budget */}
                    <div className="capex-buttons-container">
                        <button className="capex-btn" onClick={() => alert("Navigasi ke Actual CAPEX")}>
                            Actual
                        </button>
                        <button className="capex-btn" onClick={() => alert("Navigasi ke Budget CAPEX")}>
                            Budget
                        </button>
                    </div>
                </section>
            </main>
        </div>
    );
};

export default CapexFinancial;