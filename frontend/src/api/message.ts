const backendUrl = 'http://localhost:8080';

export async function getHallo(token: string) {
  const res = await fetch(`${backendUrl}/hallo`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  if (!res.ok) throw new Error('Failed to fetch');

  const data = await res.json(); // backend returns { "message": "..." }
  return data.message as string;
}
