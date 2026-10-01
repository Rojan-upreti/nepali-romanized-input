<%-- JSP view: the complete Lekhai single-page interface rendered by Spring Boot MVC. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Lekhai — Nepali Romanized Input</title>
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link href="https://fonts.googleapis.com/css2?family=DM+Mono:wght@400;500&family=DM+Sans:wght@400;500;600;700&family=Noto+Serif+Devanagari:wght@500;600&display=swap" rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" />
</head>
<body>
  <main class="shell">
    <nav class="nav"><div class="brand"><span class="brand-mark">ले</span><span>Lekhai</span></div><span class="status"><i></i> Spring Boot connected</span></nav>
    <section class="hero">
      <div class="eyebrow"><span></span> ROMANIZED NEPALI INPUT</div>
      <h1>Write it your way.<br /><em>Read it in Nepali.</em></h1>
      <p class="intro">Type Nepali the way it sounds. Lekhai transforms your Romanized text into beautiful Devanagari, instantly.</p>
      <form id="translator-form" class="translator-card">
        <div class="card-head"><label for="romanized">Your Romanized Nepali</label><span id="counter">0 / 500</span></div>
        <textarea id="romanized" maxlength="500" placeholder="e.g. Namaste, tapai lai kasto chha?"></textarea>
        <div class="card-foot"><span class="hint"><kbd>⌘</kbd><kbd>↵</kbd> to translate</span><button id="translate-button" type="submit" disabled>Translate <span>→</span></button></div>
      </form>
      <div class="result-card" id="result-card"><div class="result-head"><span class="result-label">DEVANAGARI OUTPUT</span><button type="button" class="copy" id="copy-button" hidden>Copy text</button></div><p class="empty" id="result">Your Nepali translation will appear here.</p></div>
    </section>
    <footer><span>Built for the Nepali language</span><span>देवनागरी, made simple.</span></footer>
  </main>
  <script>
    const input = document.getElementById('romanized');
    const form = document.getElementById('translator-form');
    const button = document.getElementById('translate-button');
    const counter = document.getElementById('counter');
    const result = document.getElementById('result');
    const copy = document.getElementById('copy-button');
    input.addEventListener('input', () => { counter.textContent = `${input.value.length} / 500`; button.disabled = !input.value.trim(); });
    form.addEventListener('submit', async (event) => {
      event.preventDefault(); if (!input.value.trim()) return;
      button.disabled = true; button.firstChild.textContent = 'Translating…'; result.className = 'empty'; result.textContent = 'Processing your text…'; copy.hidden = true;
      try {
        const response = await fetch('${pageContext.request.contextPath}/api/transliterate', { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({text: input.value.trim()}) });
        const data = await response.json(); if (!response.ok) throw new Error(data.message || 'Could not translate your text.');
        result.className = 'result-text'; result.textContent = data.output; copy.hidden = false;
      } catch (error) { result.className = 'error'; result.textContent = error.message; }
      finally { button.disabled = !input.value.trim(); button.firstChild.textContent = 'Translate '; }
    });
    copy.addEventListener('click', () => navigator.clipboard?.writeText(result.textContent));
  </script>
</body>
</html>
