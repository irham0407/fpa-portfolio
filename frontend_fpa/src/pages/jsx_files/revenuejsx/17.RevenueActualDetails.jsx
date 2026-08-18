import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../../css_files/revenuecss/17.RevenueActualDetails.css";

const RevenueActualDetails = () => {
    const navigate = useNavigate();
    const [isImportModalOpen, setIsImportModalOpen] = useState(false);

    // Data sampel untuk tabel detail REVENUE Actual
    const detailsData = [
        { code: "ACT-REV-001", desc: "Pendapatan Jasa Konsultasi", year: "2025", month: "Jan", branch: "Pusat", coa: "40001100", amount: "300,000,000" },
        { code: "ACT-REV-002", desc: "Penjualan Produk Digital", year: "2025", month: "Jan", branch: "Pusat", coa: "40001200", amount: "150,000,000" },
    ];

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

                {/* Area Isi REVENUE Actual Details */}
                <section className="content-section">
                    <div className="page-title-banner">
                        <h2>REVENUE Actual Details</h2>
                    </div>

                    {/* Tabel Detail */}
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

                    {/* Tombol Aksi Bawah */}
                    <div className="action-buttons-row">
                        <button className="action-btn-custom" onClick={() => navigate("/revenue/actual")}>
                            Actual REVENUE
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

            {/* Modal Kotak Import File */}
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

export default RevenueActualDetails;