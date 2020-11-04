

// Set up the picker
const client = filestack.init(FS_KEYS);
const options = {
    onUploadDone: updateForm,
    maxSize: 10 * 1024 * 1024,
    accept: 'image/*',
    uploadInBackground: false,
};
const picker = client.picker(options);


$(document).ready(function () {

    //filestack
    console.log(url);
    $("#picker").click(function () {
        client.picker(options).open();
    });

})


// Helper to overwrite the field input value

function updateForm (result) {
    const fileData = result.filesUploaded[0];
    console.log(fileData);

    const formData = new FormData();

    formData.append('url', fileData.url)
    fetch('/profile/pic', { method: 'POST', body: formData }).catch((err) => { console.log(err); })

    $("#uploadedImage").attr("src", fileData.url)
};


