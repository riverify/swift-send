const githubLink = document.querySelector('#github a');
githubLink.addEventListener('click', (event) => {
    event.preventDefault(); // 阻止默认行为
    window.open(githubLink.href, '_blank'); // 在新标签页中打开链接
});
