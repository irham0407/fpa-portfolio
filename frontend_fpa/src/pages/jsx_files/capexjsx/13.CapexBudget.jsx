import React from "react";
import { useNavigate } from "react-router-dom";
import "../../css_files/capexcss/13.CapexBudget.css";

const CapexBudget = () => {
    const navigate = useNavigate();

    const tableData = [
        { coa: "12001100 - Pembelian Mesin Utama", jan: "500", feb: "200", mar: "150", apr: "600", may: "-", jun: "-", jul: "-" },
        { coa: "12001200 - Renovasi Gedung", jan: "200", feb: "200", mar: "200", apr: "200", may: "-", jun: "-", jul: "-" },
    ];

    return (
        <div className="dashboard-container">
            <aside className="sidebar">
                <h2 className="sidebar-title">DASHBOARD</h2>
                <ul className="sidebar-menu">
                    <li className="menu-item" onClick={() => navigate("/opex")}>OPEX Financial</li>
                    <li className="menu-item active" onClick={() => navigate("/capex")}>CAPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/revenue")}>REVENUE Financial</li>
                    <li className="menu-item" onClick={() => navigate("/report")}>Final Report</li>
                </ul>
            </aside>

            <main className="main-area">
                <header className="top-header">
                    <div className="profile-box">
                        <div className="profile-info">
                            <span className="profile-name">alexander</span>
                            <span className="profile-role">USER</span>
                        </div>
                        <div className="avatar-icon">
                            <svg viewBox="0 0 24 24" fill="none" stroke="#0f172a" strokeWidth="1.5">
                                <circle cx="12" cy="8" r="4" />
                                <path d="M12 14c-6.1 0-8 4-8 4v2h16v-2s-1.9-4-8-4z" />
                            </svg>
                        </div>
                        <span className="arrow-icon">▼</span>
                    </div>
                </header>

                <section className="content-section">
                    <div className="page-title-banner">
                        <h2>CAPEX Budget</h2>
                    </div>

                    <div className="budget-chart-container">
                        <div className="chart-card">
                            <div className="chart-y-axis">
                                <span>600</span><span>400</span><span>200</span><span>0</span>
                            </div>
                            <div className="chart-bars-area">
                                <div className="bar-group"><span className="bar-value">500</span><div className="bar" style={{ height: "80%" }}></div><span className="bar-label">Jan</span></div>
                                <div className="bar-group"><span className="bar-value">200</span><div className="bar" style={{ height: "35%" }}></div><span className="bar-label">Feb</span></div>
                                <div className="bar-group"><span className="bar-value">150</span><div className="bar" style={{ height: "25%" }}></div><span className="bar-label">Mar</span></div>
                                <div className="bar-group"><span className="bar-value">600</span><div className="bar" style={{ height: "95%" }}></div><span className="bar-label">Apr</span></div>
                            </div>
                        </div>
                    </div>

                    <div className="budget-action-row">
                        <div className="dropdown-year-box">2027 ▼</div>
                        <button className="action-btn select-coa-btn">Select COA</button>
                        <button className="action-btn import-budget-btn" onClick={() => navigate("/capex/budget/details")}>
                            Import Budget
                        </button>
                    </div>

                    <div className="table-wrapper dark-table">
                        <table className="user-table">
                            <thead>
                            <tr>
                                <th>COA</th><th>Jan 2025</th><th>Feb 2025</th><th>Mar 2025</th><th>Apr 2025</th><th>May 2025</th><th>Jun 2025</th><th>Jul 2025</th>
                            </tr>
                            </thead>
                            <tbody>
                            {tableData.map((row, index) => (
                                <tr key={index}>
                                    <td className="font-semibold">{row.coa}</td>
                                    <td>{row.jan}</td><td>{row.feb}</td><td>{row.mar}</td><td>{row.apr}</td><td>{row.may}</td><td>{row.jun}</td><td>{row.jul}</td>
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

export default CapexBudget;