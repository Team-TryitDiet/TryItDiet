// const client = filestack.init(FS_KEYS);
// const options = {
//
//     displayMode: 'inline',
//
//     container: '#inline',
//
// };

// client.picker(options).open();
//
// const openBtn = document.getElementById('open');
//
// const closeBtn = document.getElementById('close');

//filepicker.setKey(FS_KEYS);
// function show(){
//     document.getElementById('uploadedImage').src = event.fpfile.url;
// }


//add event listner
//
// openBtn.addEventListener("click", () => {
//
//     client.picker(options).open();
//
// });
//
// closeBtn.addEventListener("click", () => {
//
//
//     client.picker(options).close();
//
// })


// Set up the picker
const client = filestack.init(FS_KEYS);
const options = {
    onUploadDone: updateForm,
    maxSize: 10 * 1024 * 1024,
    accept: 'image/*',
    uploadInBackground: false,
};
const picker = client.picker(options);

// Get references to the DOM elements
//
// const form = document.getElementById('pick-form');
// const fileInput = document.getElementById('fileupload');
// const btn = document.getElementById('picker');
// const nameBox = document.getElementById('nameBox');
// const urlBox = document.getElementById('urlBox');

// Add our event listeners
//
// btn.addEventListener('click', function (e) {
//     e.preventDefault();
//     picker.open();
// });
//
// form.addEventListener('submit', function (e) {
//     e.preventDefault();
//     alert('Submitting: ' + fileInput.value);
// });

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