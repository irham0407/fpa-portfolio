import React from "react";
import { useNavigate } from "react-router-dom";
import TopHeader from "../21.TopHeader";
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

            <main className="main-area">
            {/* Konten Utama (Kanan) */}
                <TopHeader username="admin_fpa" role="ADMIN" />

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