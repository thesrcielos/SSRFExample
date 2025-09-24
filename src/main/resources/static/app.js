document.addEventListener('DOMContentLoaded', () => {
const urlInput = document.getElementById('url');
const go = document.getElementById('go');
const output = document.getElementById('output');
const examples = document.querySelectorAll('.example');


async function doFetch(u) {
output.textContent = 'Cargando...';
try {
const resp = await fetch('/fetch?url=' + encodeURIComponent(u));
const text = await resp.text();
output.textContent = text;
} catch (err) {
output.textContent = 'Error: ' + err.message;
}
}


go.addEventListener('click', () => doFetch(urlInput.value));


examples.forEach(b => b.addEventListener('click', (e) => {
urlInput.value = e.currentTarget.dataset.url;
}));


// allow pressing Enter in the input
urlInput.addEventListener('keydown', (e) => {
if (e.key === 'Enter') doFetch(urlInput.value);
});
});