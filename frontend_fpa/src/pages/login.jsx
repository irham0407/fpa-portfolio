import React from 'react';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault(); // Mencegah halaman reload saat tombol diklik

    // Nanti kita akan tambahkan logika koneksi ke backend Spring Boot di sini.
    // Untuk saat ini, kita buat agar tombol langsung mengarahkan ke halaman Dashboard.
    navigate('/dashboard');
  };

  return (
      <div className="flex items-center justify-center min-h-screen bg-gray-100">
        <div className="w-full max-w-md p-8 space-y-6 bg-white rounded-xl shadow-lg border border-gray-200">

          <div className="text-center">
            <h2 className="text-3xl font-bold text-gray-800">FPA Portfolio</h2>
            <p className="mt-2 text-sm text-gray-500">Silakan login ke akun Anda</p>
          </div>

          <form className="space-y-5" onSubmit={handleLogin}>
            {/* Input Username */}
            <div>
              <label className="block mb-1 text-sm font-semibold text-gray-700">
                Username
              </label>
              <input
                  type="text"
                  className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all"
                  placeholder="Masukkan username"
                  required
              />
            </div>

            {/* Input Password */}
            <div>
              <label className="block mb-1 text-sm font-semibold text-gray-700">
                Password
              </label>
              <input
                  type="password"
                  className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all"
                  placeholder="Masukkan password"
                  required
              />
            </div>

            {/* Tombol Login */}
            <button
                type="submit"
                className="w-full px-4 py-2 text-white font-semibold bg-blue-600 rounded-lg hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-1 transition-all"
            >
              Masuk
            </button>
          </form>

        </div>
      </div>
  );
};

export default Login;