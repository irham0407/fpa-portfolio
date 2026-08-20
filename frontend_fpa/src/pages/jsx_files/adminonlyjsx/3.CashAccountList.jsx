import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import TopHeader from "../21.TopHeader";
import "../../css_files/adminonlycss/3.CashAccountList.css";

const CashAccountList = () => {
    const navigate = useNavigate();
    const [coas, setCoas] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        const controller = new AbortController();

        const loadCoas = async () => {
            try {
                const response = await fetch("/api/coas", { signal: controller.signal });
                if (!response.ok) {
                    throw new Error("Data COA tidak dapat dimuat.");
                }
                setCoas(await response.json());
            } catch (err) {
                if (err.name !== "AbortError") {
                    setError(err.message || "Data COA tidak dapat dimuat.");
                }
            } finally {
                if (!controller.signal.aborted) {
                    setIsLoading(false);
                }
            }
        };

        loadCoas();
        return () => controller.abort();
    }, []);

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
                            {isLoading && (
                                <tr>
                                    <td colSpan="3" className="table-message">Memuat data COA...</td>
                                </tr>
                            )}
                            {!isLoading && error && (
                                <tr>
                                    <td colSpan="3" className="table-message table-error">{error}</td>
                                </tr>
                            )}
                            {!isLoading && !error && coas.length === 0 && (
                                <tr>
                                    <td colSpan="3" className="table-message">Belum ada data COA.</td>
                                </tr>
                            )}
                            {!isLoading && !error && coas.map((item) => (
                                <tr key={item.coaCode}>
                                    <td className="font-semibold">{item.coaCode}</td>
                                    <td>{item.accountType}</td>
                                    <td>{item.coaName}</td>
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
