// 选择需要操作的元素
const formUpload = document.querySelector('#form-upload');
const inputUpload = document.querySelector('#file');
const formDownload = document.querySelector('#form-download');
const inputKey = document.querySelector('#key');
const messageBox = document.querySelector('#message');

// 监听上传文件表单提交事件
formUpload.addEventListener('submit', (event) => {
    event.preventDefault(); // 阻止表单默认提交行为

    // 创建 FormData 对象，用于将表单数据序列化成键值对
    const formData = new FormData();
    formData.append('file', inputUpload.files[0]);

    // 发送 AJAX 请求
    fetch('/api/upload', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            // 显示密钥提示信息
            messageBox.innerHTML = `上传成功，密钥为：${data.key}`;
        })
        .catch(error => {
            messageBox.innerHTML = `上传失败：${error}`;
        });
});

// 监听获取文件表单提交事件
formDownload.addEventListener('submit', (event) => {
    event.preventDefault(); // 阻止表单默认提交行为

    // 发送 AJAX 请求
    fetch(`/api/download?key=${inputKey.value}`, {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {
            // 下载文件
            const link = document.createElement('a');
            link.href = data.url;
            link.download = data.filename;
            link.click();
        })
        .catch(error => {
            messageBox.innerHTML = `获取文件失败：${error}`;
        });
});
