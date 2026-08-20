import React from "react";
import { useNavigate } from "react-router-dom";
import "../css_files/20.FinalReport.css";
import TopHeader from "./21.TopHeader.jsx";

const FinalReport = () => {
    const navigate = useNavigate();

    // Data mockup untuk chart
    const chartData = [
        { month: "Jan", value: 300, height: "60%" },
        { month: "Feb", value: 400, height: "80%" },
        { month: "Mar", value: 350, height: "70%" },
        { month: "Apr", value: 450, height: "90%" },
    ];

    // Data kolom tabel spreadsheet
    const tableColumns = [
        "Jan 2025", "Feb 2025", "Mar 2025", "Apr2025", "May 2025", "Jun 2025", "Jul 2025"
    ];

    return (
        <div className="dashboard-container">
            {/* Sidebar Navigasi Kiri */}
            <aside className="sidebar">
                <h2 className="sidebar-title">DASHBOARD</h2>
                <ul className="sidebar-menu">
                    <li className="menu-item" onClick={() => navigate("/opex")}>OPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/capex")}>CAPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/revenue")}>REVENUE Financial</li>
                    <li className="menu-item active" onClick={() => navigate("/report")}>Final Report</li>
                </ul>
            </aside>

            <main className="main-area">
                {/* Konten Utama (Kanan) */}
                <TopHeader username="admin_fpa" role="ADMIN" />

                {/* Area Konten */}
                <section className="content-section">
                    <div className="page-title-banner">
                        <h2>Final Report</h2>
                    </div>

                    {/* Chart Area */}
                    <div className="chart-wrapper">
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
                                {/* Grid lines background */}
                                <div className="grid-lines">
                                    <div className="line"></div>
                                    <div className="line"></div>
                                    <div className="line"></div>
                                    <div className="line"></div>
                                    <div className="line"></div>
                                </div>
                                {/* Bars */}
                                {chartData.map((data, index) => (
                                    <div className="bar-group" key={index}>
                                        <span className="bar-value">{data.value}</span>
                                        <div className="bar" style={{ height: data.height }}></div>
                                        <span className="bar-label">{data.month}</span>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>

                    {/* Select Year & Table Section */}
                    <div className="table-section">
                        <div className="select-year-box">
                            Select Year
                        </div>

                        <div className="spreadsheet-container">
                            <div className="spreadsheet-title-bar">Title</div>
                            <table className="spreadsheet-table">
                                <thead>
                                <tr>
                                    <th className="row-number-header"></th>
                                    <th>A</th>
                                    <th>B</th>
                                    <th>C</th>
                                    <th>D</th>
                                    <th>E</th>
                                    <th>F</th>
                                    <th>G</th>
                                    <th>H</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td className="row-number">1</td>
                                    <td></td>
                                    {tableColumns.map((col, idx) => (
                                        <td key={idx} className="col-header-text">{col}</td>
                                    ))}
                                </tr>
                                <tr>
                                    <td className="row-number">2</td>
                                    <td>OPEX</td>
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr>
                                    <td className="row-number">3</td>
                                    <td>CAPEX</td>
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>
            </main>
        </div>
    );
};

export default FinalReport;
