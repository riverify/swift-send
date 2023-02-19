const fileTransform = document.getElementById('fileTransform');
const sentenceTransform = document.getElementById('sentenceTransform');

const radioButtons = document.querySelectorAll('input[name="radioOption"]');
radioButtons.forEach(button => {
    button.addEventListener('change', (event) => {
        const selectedValue = event.target.value;
        if (selectedValue === 'fileType') {
            fileTransform.style.display = 'block';
            sentenceTransform.style.display = 'none';
        } else {
            fileTransform.style.display = 'none';
            sentenceTransform.style.display = 'block';
        }
    });
});
