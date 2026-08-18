import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import TopHeader from "../21.TopHeader";
import "../../css_files/adminonlycss/2.UserList.css";

const UserList = () => {
    const navigate = useNavigate();

    // State untuk mengontrol muncul/tembusnya modal popup
    const [isModalOpen, setIsModalOpen] = useState(false);

    // Data pengguna
    const users = [
        { username: "admin_fpa", role: "ADMIN", email: "admin@fpa-portfolio.com", fullName: "Irham Manthiqo Noor", phone: "", active: "" },
        { username: "alexander", role: "USER", email: "alexander@fpa-portfolio.com", fullName: "Alexander Smith", phone: "", active: "" },
        { username: "benjamin", role: "USER", email: "benjamin@fpa-portfolio.com", fullName: "Benjamin Carter", phone: "", active: "" },
        { username: "christopher", role: "USER", email: "christopher@fpa-portfolio.com", fullName: "Christopher Wilson", phone: "", active: "" },
        { username: "daniel", role: "USER", email: "danieln@fpa-portfolio.com", fullName: "Daniel Anderson", phone: "", active: "" },
        { username: "ethan", role: "USER", email: "ethan@fpa-portfolio.com", fullName: "Ethan Thompson", phone: "", active: "" },
    ];

    return (
        <div className="dashboard-container">
            {/* Sidebar Navigasi Kiri */}
            <aside className="sidebar">
                <h2 className="sidebar-title">DASHBOARD</h2>
                <ul className="sidebar-menu">
                    <li className="menu-item active" onClick={() => navigate("/users")}>User List</li>
                    <li className="menu-item" onClick={() => navigate("/coa")}>COA List</li>
                    <li className="menu-item" onClick={() => navigate("/opex")}>OPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/capex")}>CAPEX Financial</li>
                    <li className="menu-item" onClick={() => navigate("/revenue")}>REVENUE Financial</li>
                    <li className="menu-item" onClick={() => navigate("/report")}>Final Report</li>
                </ul>
            </aside>

            <main className="main-area">
            {/* Konten Utama (Kanan) */}
                <TopHeader username="admin_fpa" role="ADMIN" />

                {/* Area Isi User List */}
                <section className="content-section">
                    <div className="page-title-banner">
                        <h2>User List</h2>
                    </div>

                    {/* Kartu Tabel */}
                    <div className="table-wrapper">
                        <table className="user-table">
                            <thead>
                            <tr>
                                <th>Username</th>
                                <th>Role</th>
                                <th>Email</th>
                                <th>Full Name</th>
                                <th>Phone Number</th>
                                <th>Active</th>
                            </tr>
                            </thead>
                            <tbody>
                            {users.map((item, index) => (
                                <tr key={index}>
                                    <td className="font-semibold">{item.username}</td>
                                    <td>{item.role}</td>
                                    <td>{item.email}</td>
                                    <td>{item.fullName}</td>
                                    <td>{item.phone}</td>
                                    <td>{item.active}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>

                    {/* Tombol Tambah User */}
                    <div className="button-area">
                        <button className="btn-tambah" onClick={() => setIsModalOpen(true)}>
                            Tambah User
                        </button>
                    </div>
                </section>
            </main>

            {/* --- MODAL POPUP TAMBAH USER --- */}
            {isModalOpen && (
                <div className="modal-overlay">
                    <div className="modal-card">
                        <h3 className="modal-title">Profil user baru</h3>

                        <div className="modal-form">
                            <div className="modal-field">
                                <label>Nama Lengkap</label>
                                <input type="text" placeholder="Masukkan nama lengkap" />
                            </div>
                            <div className="modal-field">
                                <label>Email</label>
                                <input type="email" placeholder="Masukkan email" />
                            </div>
                            <div className="modal-field">
                                <label>Nomor Hp</label>
                                <input type="text" placeholder="Masukkan nomor HP" />
                            </div>
                            <div className="modal-field">
                                <label>Job Title</label>
                                <input type="text" placeholder="Masukkan job title" />
                            </div>
                            <div className="modal-field">
                                <label>Role</label>
                                <select defaultValue="USER">
                                    <option value="USER">USER</option>
                                    <option value="ADMIN">ADMIN</option>
                                </select>
                            </div>
                        </div>

                        {/* Tombol Aksi Modal (Done & Cancel dengan gaya abu-abu modern) */}
                        <div className="modal-buttons">
                            <button className="btn-modal-action btn-done" onClick={() => setIsModalOpen(false)}>
                                Done
                            </button>
                            <button className="btn-modal-action btn-cancel" onClick={() => setIsModalOpen(false)}>
                                Cancel
                            </button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default UserList;