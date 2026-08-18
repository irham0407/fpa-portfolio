import React from "react";
import { useNavigate } from "react-router-dom";
import "../../css_files/capexcss/5.CapexFinancial.css";

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

            {/* Konten Utama (Kanan) */}
            <main className="main-area">
                {/* Header Profil Atas (Akun Admin) */}
                <header className="top-header">
                    <div className="profile-box">
                        <div className="profile-info">
                            <span className="profile-name">admin_fpa</span>
                            <span className="profile-role">ADMIN</span>
                        </div>
                        <div className="avatar-icon">
                            <svg viewBox="0 0 24 24" fill="#3bb2f6">
                                <circle cx="12" cy="8" r="4" />
                                <path d="M12 14c-6.1 0-8 4-8 4v2h16v-2s-1.9-4-8-4z" />
                            </svg>
                        </div>
                        <span className="arrow-icon">▼</span>
                    </div>
                </header>

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