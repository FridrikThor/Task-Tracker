import { createContext, useContext, useState, useEffect } from 'react';
import { login as loginApi } from '../api/auth';
const backendUrl = 'http://localhost:8080';

interface UserInfo {
  id: string;
  name: string;
  username: string;
  role: string;
}

interface AuthContextType {
  token: string | null;
  login: (username: string, password: string) => Promise<void>;
  logout: () => void;
  getCurrentUser: () => Promise<UserInfo>;
}

const AuthContext = createContext<AuthContextType | null>(null);

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [token, setToken] = useState<string | null>(() =>
    localStorage.getItem('token')
  );

  const login = async (username: string, password: string) => {
    const newToken = await loginApi(username, password);
    setToken(newToken);
    localStorage.setItem('token', newToken);
  };

  const logout = () => {
    setToken(null);
    localStorage.removeItem('token');
  };

  const getCurrentUser = async () => {
    if (!token) return null;

    const res = await fetch(`${backendUrl}/me`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    if (!res.ok) throw new Error('Failed to fetch user info');
    return res.json();
  };

  return (
    <AuthContext.Provider value={{ token, login, logout, getCurrentUser }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) throw new Error('useAuth must be used inside AuthProvider');
  return context;
}
