// 选择需要操作的元素
const formUpload = document.querySelector('#form-upload');
const inputUpload = document.querySelector('#file');
const formDownload = document.querySelector('#form-download');
const inputKey = document.querySelector('#key');
const messageBox = document.querySelector('#message');
const notification = document.getElementById('notification');

// swal alert
document.write('<link rel="stylesheet" type="text/css" href="../css/sweetalert.css"></script>');
document.write('<script type="text/javascript" src="sweetalert-dev.js"></script>');
// 监听上传文件表单提交事件
formUpload.addEventListener('submit', (event) => {
    event.preventDefault(); // 阻止表单默认提交行为

    // make sure files are not too large
    if (inputUpload.files.length > 0 && inputUpload.files[0].size > 104857600) {
        // 文件超过了40MB的限制
        swal('文件大小超过了50MB的限制，请重新选择！');
        return;
    }
    // 在没有上传完全前请等待
    messageBox.innerHTML = '正在上传，根据文件大小程度和网络情况，这可能需要一点时间，请耐心等待......';
    // 禁用上传按钮
    formUpload.querySelector('button').disabled = true;

    // 创建 FormData 对象，用于将表单数据序列化成键值对
    const formData = new FormData();
    formData.append('file', inputUpload.files[0]);

    // 发送 AJAX 请求
    fetch('/file/upload', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            // 结束，启用上传按钮
            formUpload.querySelector('button').disabled = false;
            // 显示密钥提示信息
            if (data.key.length === 4) {
                messageBox.innerHTML = `上传成功，密钥为：${data.key}`;
                // 告知用户密钥
                copyContent(data.key, 'textarea');
                swal(`密钥：${data.key} 已复制到剪贴板，请妥善保存`);
            } else {
                messageBox.innerHTML = `上传失败`;
                swal(`上传失败`)
            }
        })
        .catch(error => {
            messageBox.innerHTML = `上传失败：${error.message}`;  // this I can not reach
        });
});

// 监听获取文件表单提交事件
formDownload.addEventListener('submit', (event) => {
    event.preventDefault(); // 阻止表单默认提交行为

    if (inputKey.value.length !== 4) {
        swal('请输入正确的密钥');
        messageBox.innerHTML = `请输入正确的密钥`;
        return;
    }

    // 发送 AJAX 请求
    // fetch(`/file/download?key=${inputKey.value}`, {
    //     method: 'GET'
    // })
    //     .then(response => response.json())
    //     .then(data => {
    //         // 下载文件
    //         const link = document.createElement('a');
    //         link.href = data.url;
    //         link.download = data.filename;
    //         link.click();
    //     })
    //     .catch(error => {
    //         messageBox.innerHTML = `获取文件失败：${error}`;
    //     });
    fetch(`/file/download?key=${inputKey.value}`, {
        method: 'GET'
    })
        .then(
            response => {
                if (response.ok) {
                    return response;
                } else {
                    swal('获取文件失败，请检查密钥是否正确、文件已经被取走或已经失效');
                    messageBox.innerHTML = `获取文件失败，请检查密钥是否正确、文件已经被取走或已经失效`;
                    throw new Error('获取文件失败');
                }
            }
        )
        .then(response => response.blob())
        .then(blob => {
            // 创建 URL 对象
            const url = URL.createObjectURL(blob);
            // 创建 a 标签
            const a = document.createElement('a');
            // 设置 a 标签属性
            a.href = url;
            a.download = '';
            // 触发点击事件
            a.click();
            // 释放 URL 对象
            URL.revokeObjectURL(url);
            swal('获取成功');
            messageBox.innerHTML = `获取文件成功`;
        })
        .catch(error => console.error(error));

});


/**
 * 复制内容
 * @param {String} value 需要复制的内容
 * @param {String} type 元素类型 input, textarea
 */
function copyContent(value, type = 'input') {
    const input = document.createElement(type);
    input.setAttribute('readonly', 'readonly'); // 设置为只读, 防止在 ios 下拉起键盘
    // input.setAttribute('value', value); // textarea 不能用此方式赋值, 否则无法复制内容
    input.value = value;
    document.body.appendChild(input);
    input.setSelectionRange(0, 9999); // 防止 ios 下没有全选内容而无法复制
    input.select();
    document.execCommand('copy');
    document.body.removeChild(input);
}

