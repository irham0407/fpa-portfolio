import React from "react";
import { useNavigate } from "react-router-dom";
import "../../css_files/revenuecss/18.RevenueBudget.css";

const RevenueBudget = () => {
    const navigate = useNavigate();

    // Data sampel untuk tabel bulanan REVENUE Budget
    const tableData = [
        { coa: "40001100 - Pendapatan Jasa", jan: "300", feb: "400", mar: "350", apr: "450", may: "-", jun: "-", jul: "-" },
        { coa: "40001200 - Pendapatan Produk", jan: "150", feb: "150", mar: "150", apr: "150", may: "-", jun: "-", jul: "-" },
    ];

    return (
        <div className="dashboard-container">
            {/* Sidebar Navigasi Kiri (Tampilan USER - Tanpa User List & COA List) */}
            <aside className="sidebar">
                <h2 className="sidebar-title">DASHBOARD</h2>
                <ul className="sidebar-menu">
                    <li className="menu-item" onClick={() => navigate("/opex")}>OPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/capex")}>CAPEX Financial</li>
                    <li className="menu-item active" onClick={() => navigate("/revenue")}>REVENUE Financial</li>
                    <li className="menu-item" onClick={() => navigate("/report")}>Final Report</li>
                </ul>
            </aside>

            {/* Konten Utama (Kanan) */}
            <main className="main-area">
                {/* Header Profil Atas (Akun User) */}
                <header className="top-header">
                    <div className="profile-box">
                        <div className="profile-info">
                            <span className="profile-name">alexander</span>
                            <span className="profile-role">USER</span>
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

                {/* Area Isi REVENUE Budget */}
                <section className="content-section">
                    <div className="page-title-banner">
                        <h2>REVENUE Budget</h2>
                    </div>

                    {/* Bagian Grafik Batang (Tengah) */}
                    <div className="budget-chart-container">
                        <div className="chart-card">
                            <div className="chart-y-axis">
                                <span>500</span>
                                <span>400</span>
                                <span>300</span>
                                <span>200</span>
                                <span>100</span>
                                <span>0</span>
                            </div>
                            <div className="chart-bars-area">
                                <div className="bar-group">
                                    <span className="bar-value">300</span>
                                    <div className="bar" style={{ height: "60%" }}></div>
                                    <span className="bar-label">Jan</span>
                                </div>
                                <div className="bar-group">
                                    <span className="bar-value">400</span>
                                    <div className="bar" style={{ height: "80%" }}></div>
                                    <span className="bar-label">Feb</span>
                                </div>
                                <div className="bar-group">
                                    <span className="bar-value">350</span>
                                    <div className="bar" style={{ height: "70%" }}></div>
                                    <span className="bar-label">Mar</span>
                                </div>
                                <div className="bar-group">
                                    <span className="bar-value">450</span>
                                    <div className="bar" style={{ height: "90%" }}></div>
                                    <span className="bar-label">Apr</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    {/* Panel Tombol Aksi di Bawah Grafik */}
                    <div className="budget-action-row">
                        <div className="dropdown-year-box">2027 ▼</div>
                        <button className="action-btn select-coa-btn" onClick={() => alert("Membuka Modal Select COA")}>
                            Select COA
                        </button>
                        <button className="action-btn import-budget-btn" onClick={() => navigate("/revenue/budget/details")}>
                            Import Budget
                        </button>
                    </div>

                    {/* Tabel Data Bulanan */}
                    <div className="table-wrapper dark-table">
                        <table className="user-table">
                            <thead>
                            <tr>
                                <th>COA</th>
                                <th>Jan 2025</th>
                                <th>Feb 2025</th>
                                <th>Mar 2025</th>
                                <th>Apr 2025</th>
                                <th>May 2025</th>
                                <th>Jun 2025</th>
                                <th>Jul 2025</th>
                            </tr>
                            </thead>
                            <tbody>
                            {tableData.map((row, index) => (
                                <tr key={index}>
                                    <td className="font-semibold">{row.coa}</td>
                                    <td>{row.jan}</td>
                                    <td>{row.feb}</td>
                                    <td>{row.mar}</td>
                                    <td>{row.apr}</td>
                                    <td>{row.may}</td>
                                    <td>{row.jun}</td>
                                    <td>{row.jul}</td>
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

export default RevenueBudget;