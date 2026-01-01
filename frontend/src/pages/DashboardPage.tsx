import { useState, useEffect } from 'react';
import { useAuth } from '../context/AuthContext';

export default function DashboardPage() {
  const { token, getCurrentUser, logout } = useAuth();
  const [user, setUser] = useState<{
    id: string;
    name: string;
    username: string;
    role: string;
  } | null>(null);

  useEffect(() => {
    (async () => {
      try {
        const data = await getCurrentUser();
        setUser(data);
      } catch (err) {
        console.error(err);
      }
    })();
  }, [getCurrentUser]);

  return (
    <div className="p-8 text-center">
      <h1 className="text-3xl font-bold mb-4">Dashboard</h1>
      {user ? (
        <div>
          <p>
            Welcome, <strong>{user.name}</strong>!
          </p>
          <p>Role: {user.role}</p>
        </div>
      ) : (
        <p>Loading user info...</p>
      )}
      <button
        onClick={logout}
        className="bg-red-500 text-white p-2 rounded mt-4"
      >
        Logout
      </button>
    </div>
  );
}
