import React from "react";
import { useNavigate } from "react-router-dom";
import TopHeader from "./21.TopHeader.jsx";
import "../css_files/22. ProfileUser.css";

const ProfileUser = () => {
    const navigate = useNavigate();

    // Data user sementara (bisa dari props atau API)
    const userData = {
        name: "Irham Manthiqo Noor",
        phone: "0811234567890",
        email: "irhammn@cth.com",
        role: "Admin",
        jobTitle: "System Administrator"
    };

    return (
        <div className="dashboard-container">
            {/* Sidebar Navigasi Kiri */}
            <aside className="sidebar">
                <h2 className="sidebar-title">DASHBOARD</h2>
                <ul className="sidebar-menu">
                    <li className="menu-item" onClick={() => navigate("/opex")}>OPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/capex")}>CAPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/revenue")}>REVENUE Financial</li>
                    <li className="menu-item" onClick={() => navigate("/report")}>Final Report</li>
                </ul>
            </aside>

            {/* Konten Utama */}
            <main className="main-area">
                <TopHeader username="admin_fpa" role="ADMIN" />

                <section className="profile-content">
                    <div className="profile-card">
                        <div className="profile-header-card">
                            <div className="avatar-large">
                                <svg viewBox="0 0 24 24" fill="#007bff">
                                    <circle cx="12" cy="8" r="4" />
                                    <path d="M12 14c-6.1 0-8 4-8 4v2h16v-2s-1.9-4-8-4z" />
                                </svg>
                            </div>
                            <h2>Profile User</h2>
                        </div>

                        <div className="profile-details">
                            <div className="detail-row">
                                <span className="detail-label">Nama :</span>
                                <span className="detail-value">{userData.name}</span>
                            </div>
                            <div className="detail-row">
                                <span className="detail-label">Phone :</span>
                                <span className="detail-value">{userData.phone}</span>
                            </div>
                            <div className="detail-row">
                                <span className="detail-label">Email :</span>
                                <span className="detail-value">{userData.email}</span>
                            </div>
                            <div className="detail-row">
                                <span className="detail-label">Role :</span>
                                <span className="detail-value">{userData.role}</span>
                            </div>
                            <div className="detail-row">
                                <span className="detail-label">Job Title :</span>
                                <span className="detail-value underline">{userData.jobTitle}</span>
                            </div>
                        </div>
                    </div>
                </section>
            </main>
        </div>
    );
};

export default ProfileUser;
