import React from "react";
import { useNavigate } from "react-router-dom";
import "../../css_files/adminonlycss/3.CashAccountList.css";

const CashAccountList = () => {
    const navigate = useNavigate();

    // Data COA sesuai desain Figma
    const coaData = [
        { code: "50001100", type: "OPEX", name: "Beban Gaji & Tunjangan" },
        { code: "50001200", type: "OPEX", name: "Beban Sewa Gedung & Kantor" },
        { code: "50001300", type: "OPEX", name: "Beban Listrik, Air & Internet" },
        { code: "50001400", type: "OPEX", name: "Beban Perjalanan Dinas" },
        { code: "50001500", type: "OPEX", name: "Beban Pemasaran & Promosi" },
        { code: "61010001", type: "OPEX", name: "Beban Gaji & Tunjangan" },
        { code: "61020002", type: "OPEX", name: "Beban Sewa Gedung & Kantor" },
    ];

    return (
        <div className="dashboard-container">
            {/* Sidebar Navigasi Kiri */}
            <aside className="sidebar">
                <h2 className="sidebar-title">DASHBOARD</h2>
                <ul className="sidebar-menu">
                    <li className="menu-item" onClick={() => navigate("/users")}>User List</li>
                    <li className="menu-item active" onClick={() => navigate("/coa")}>COA List</li>
                    <li className="menu-item" onClick={() => navigate("/opex")}>OPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/capex")}>CAPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/revenue")}>REVENUE Financial</li>
                    <li className="menu-item" onClick={() => navigate("/report")}>Final Report</li>
                </ul>
            </aside>

            {/* Konten Utama (Kanan) */}
            <main className="main-area">
                {/* Header Profil Atas */}
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

                {/* Area Isi Cash of Account List */}
                <section className="content-section">
                    <div className="page-title-banner">
                        <h2>Cash of Account List</h2>
                    </div>

                    {/* Kartu Tabel COA */}
                    <div className="table-wrapper">
                        <table className="user-table">
                            <thead>
                            <tr>
                                <th>COA Code</th>
                                <th>Account Type</th>
                                <th>COA Name</th>
                            </tr>
                            </thead>
                            <tbody>
                            {coaData.map((item, index) => (
                                <tr key={index}>
                                    <td className="font-semibold">{item.code}</td>
                                    <td>{item.type}</td>
                                    <td>{item.name}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                </section>
            </main>
        </div>
    );
};

export default CashAccountList;