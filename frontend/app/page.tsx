// Main client-side page: collects Romanized Nepali and displays the Spring Boot response.
"use client";

import { FormEvent, useState } from "react";

const API_URL = process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:8080";

export default function Home() {
  const [text, setText] = useState("");
  const [result, setResult] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (!text.trim()) return;

    setLoading(true);
    setError("");
    try {
      const response = await fetch(`${API_URL}/api/transliterate`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ text: text.trim() }),
      });
      const data = await response.json();
      if (!response.ok) throw new Error(data.message ?? "Something went wrong.");
      setResult(data.output ?? "");
    } catch (err) {
      setError(err instanceof Error ? err.message : "Could not reach the translator.");
    } finally {
      setLoading(false);
    }
  }

  return (
    <main className="shell">
      <nav className="nav">
        <div className="brand"><span className="brand-mark">ले</span><span>Lekhai</span></div>
        <span className="status"><i /> Spring Boot connected</span>
      </nav>

      <section className="hero">
        <div className="eyebrow"><span /> ROMANIZED NEPALI INPUT</div>
        <h1>Write it your way.<br /><em>Read it in Nepali.</em></h1>
        <p className="intro">Type Nepali the way it sounds. Lekhai transforms your Romanized text into beautiful Devanagari, instantly.</p>

        <form onSubmit={handleSubmit} className="translator-card">
          <div className="card-head"><label htmlFor="romanized">Your Romanized Nepali</label><span>{text.length} / 500</span></div>
          <textarea id="romanized" maxLength={500} value={text} onChange={(event) => setText(event.target.value)} placeholder="e.g. Namaste, tapai lai kasto chha?" />
          <div className="card-foot"><span className="hint"><kbd>⌘</kbd><kbd>↵</kbd> to translate</span><button type="submit" disabled={loading || !text.trim()}>{loading ? "Translating…" : <>Translate <span>→</span></>}</button></div>
        </form>

        <div className={`result-card ${result ? "has-result" : ""}`}>
          <div className="result-head"><span className="result-label">DEVANAGARI OUTPUT</span>{result && <button type="button" className="copy" onClick={() => navigator.clipboard?.writeText(result)}>Copy text</button>}</div>
          {error ? <p className="error">{error}</p> : result ? <p className="result-text">{result}</p> : <p className="empty">Your Nepali translation will appear here.</p>}
        </div>
      </section>

      <footer><span>Built for the Nepali language</span><span>देवनागरी, made simple.</span></footer>
    </main>
  );
}
