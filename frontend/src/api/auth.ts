const backendUrl = 'http://localhost:8080';

export async function login(username: string, password: string) {
  const res = await fetch(`${backendUrl}/innskra`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ username, password }),
  });

  const text = await res.text();
  if (!res.ok) throw new Error(text);

  let data;
  try {
    data = JSON.parse(text);
  } catch {
    data = { token: text };
  }

  return data.token as string;
}
