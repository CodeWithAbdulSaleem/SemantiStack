async function doSearch() {
  const query = document.getElementById('query').value;
  const resp = await fetch('http://localhost:8080/search', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ query })
  });
  const results = await resp.json();
  const div = document.getElementById('results');
  div.innerHTML = '';
  results.forEach(r => {
    div.innerHTML += `<div class='result'><b>${r.text}</b> (score: ${r.score.toFixed(3)})</div>`;
  });
}
