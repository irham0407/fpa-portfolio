import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import TopHeader from "../21.TopHeader";
import "../../css_files/capexcss/12.CapexActualDetails.css";

const CapexActualDetails = () => {
    const navigate = useNavigate();
    const [isImportModalOpen, setIsImportModalOpen] = useState(false);

    const detailsData = [
        { code: "CAP-001", desc: "Pembelian Server Baru", year: "2025", month: "Jan", branch: "Pusat", coa: "12001100", amount: "500,000,000" },
        { code: "CAP-002", desc: "Renovasi Ruangan Server", year: "2025", month: "Jan", branch: "Pusat", coa: "12001200", amount: "200,000,000" },
    ];

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
                        <h2>CAPEX Actual Details</h2>
                    </div>

                    <div className="table-wrapper dark-table">
                        <table className="user-table">
                            <thead>
                            <tr>
                                <th>Actual Code</th>
                                <th>Description</th>
                                <th>Period Year</th>
                                <th>Period Month</th>
                                <th>Branch</th>
                                <th>COA</th>
                                <th>Amount</th>
                            </tr>
                            </thead>
                            <tbody>
                            {detailsData.map((row, index) => (
                                <tr key={index}>
                                    <td className="font-semibold">{row.code}</td>
                                    <td>{row.desc}</td>
                                    <td>{row.year}</td>
                                    <td>{row.month}</td>
                                    <td>{row.branch}</td>
                                    <td>{row.coa}</td>
                                    <td>{row.amount}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>

                    <div className="action-buttons-row">
                        <button className="action-btn-custom" onClick={() => navigate("/capex/actual")}>
                            Actual CAPEX
                        </button>
                        <button className="action-btn-custom" onClick={() => setIsImportModalOpen(true)}>
                            Import
                        </button>
                        <button className="action-btn-custom delete-btn" onClick={() => alert("Hapus data actual terpilih")}>
                            Delete Actual
                        </button>
                    </div>
                </section>
            </main>

            {isImportModalOpen && (
                <div className="modal-overlay">
                    <div className="import-modal-box">
                        <div className="import-box-content" onClick={() => alert("Pilih file excel/csv untuk diimport")}>
                            <span>Import file</span>
                        </div>
                        <button className="close-modal-x" onClick={() => setIsImportModalOpen(false)}>×</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default CapexActualDetails;