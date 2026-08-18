import React from "react";
import "../../css_files/adminonlycss/1.Login.css";

const Login = () => {
  return (
      <div className="login-wrapper">

        {/* Bagian Ikon Lingkaran di Sebelah Kiri */}
        <div className="login-avatar-container">
          <div className="avatar-circle">
            <svg viewBox="0 0 24 24" fill="none" stroke="#3bb2f6" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
              <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
              <circle cx="12" cy="7" r="4"></circle>
            </svg>
          </div>
        </div>

        {/* Bagian Kotak Putih Form Login */}
        <div className="login-card">
          <h2 className="login-title">User Login</h2>

          {/* Contoh Notifikasi Error (Bisa disembunyikan jika tidak ada error) */}
          <div className="error-box">
            <span className="error-icon">⚠️</span>
            <p>Invalid username or password.<br />Please try again.</p>
          </div>

          <form className="login-form">
            {/* Input Username */}
            <div className="input-group">
              <div className="input-icon">👤</div>
              <input type="text" placeholder="Username" />
            </div>

            {/* Input Password */}
            <div className="input-group">
              <div className="input-icon">🔒</div>
              <input type="password" placeholder="*************" />
            </div>

            {/* Opsi Tambahan */}
            <div className="form-actions">
              <label className="remember-me">
                <input type="checkbox" /> Remember me
              </label>
              <a href="#" className="forgot-password">Forgot Password?</a>
            </div>

            {/* Tombol Login */}
            <button type="submit" className="btn-login">LOGIN</button>
          </form>
        </div>

      </div>
  );
};

export default Login;