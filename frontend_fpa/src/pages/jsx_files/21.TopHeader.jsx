import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css_files/21.TopHeader.css";

const TopHeader = ({ username, role }) => {
    const navigate = useNavigate();
    const [isDropdownOpen, setIsDropdownOpen] = useState(false);

    const toggleDropdown = () => {
        setIsDropdownOpen(!isDropdownOpen);
    };

    const handleSettingClick = () => {
        setIsDropdownOpen(false);
        navigate("/profile"); // Arahkan ke halaman profil
    };

    const handleLogoutClick = () => {
        setIsDropdownOpen(false);
        navigate("/login"); // Arahkan ke halaman login
    };

    return (
        <header className="top-header">
            <div className="profile-box">
                <div className="profile-info">
                    <span className="profile-name">{username}</span>
                    <span className="profile-role">{role}</span>
                </div>
                <div className="avatar-icon">
                    <svg viewBox="0 0 24 24" fill="#007bff">
                        <circle cx="12" cy="8" r="4" />
                        <path d="M12 14c-6.1 0-8 4-8 4v2h16v-2s-1.9-4-8-4z" />
                    </svg>
                </div>
                <div className="dropdown-container">
                    {/* Tombol Segitiga Hitam */}
                    <span className="arrow-icon" onClick={toggleDropdown}>
                        ▼
                    </span>

                    {/* Menu Dropdown Muncul Jika isDropdownOpen = true */}
                    {isDropdownOpen && (
                        <div className="dropdown-menu">
                            <div className="dropdown-item" onClick={handleSettingClick}>
                                Setting
                            </div>
                            <div className="dropdown-item" onClick={handleLogoutClick}>
                                Logout
                            </div>
                        </div>
                    )}
                </div>
            </div>
        </header>
    );
};

export default TopHeader;
