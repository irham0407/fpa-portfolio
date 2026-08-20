import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import TopHeader from "../21.TopHeader";
import "../../css_files/adminonlycss/2.UserList.css";

const UserList = () => {
    const navigate = useNavigate();

    // State untuk mengontrol muncul/tembusnya modal popup
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [users, setUsers] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        const controller = new AbortController();

        const loadUsers = async () => {
            try {
                const response = await fetch("/api/users", { signal: controller.signal });
                if (!response.ok) {
                    throw new Error("Data pengguna tidak dapat dimuat.");
                }
                setUsers(await response.json());
            } catch (err) {
                if (err.name !== "AbortError") {
                    setError(err.message || "Data pengguna tidak dapat dimuat.");
                }
            } finally {
                if (!controller.signal.aborted) {
                    setIsLoading(false);
                }
            }
        };

        loadUsers();
        return () => controller.abort();
    }, []);

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
                                <th>Full Name</th>
                                <th>Role</th>
                                <th>Job Title</th>
                                <th>Email</th>
                                <th>Phone Number</th>
                            </tr>
                            </thead>
                            <tbody>
                            {isLoading && (
                                <tr>
                                    <td colSpan="5" className="table-message">Memuat data pengguna...</td>
                                </tr>
                            )}
                            {!isLoading && error && (
                                <tr>
                                    <td colSpan="5" className="table-message table-error">{error}</td>
                                </tr>
                            )}
                            {!isLoading && !error && users.length === 0 && (
                                <tr>
                                    <td colSpan="5" className="table-message">Belum ada data pengguna.</td>
                                </tr>
                            )}
                            {!isLoading && !error && users.map((item) => (
                                <tr key={item.id}>
                                    <td className="font-semibold">{item.fullName}</td>
                                    <td>{item.role}</td>
                                    <td>{item.jobTitle || "-"}</td>
                                    <td>{item.email}</td>
                                    <td>{item.phoneNumber || "-"}</td>
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
