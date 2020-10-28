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

const form = document.getElementById('pick-form');
const fileInput = document.getElementById('fileupload');
const btn = document.getElementById('picker');
const nameBox = document.getElementById('nameBox');
const urlBox = document.getElementById('urlBox');

// Add our event listeners

btn.addEventListener('click', function (e) {
    e.preventDefault();
    picker.open();
});

form.addEventListener('submit', function (e) {
    e.preventDefault();
    alert('Submitting: ' + fileInput.value);
});

$(document).ready(function () {

    //filestack
    console.log(url);
    $("#profile-btn").click(function () {
        stackClient.picker(options).open();
    });

})


// Helper to overwrite the field input value

function updateForm (result) {
    const fileData = result.filesUploaded[0];
    console.log(fileData);
    $("#profileUrl").val(fileData.url);
    //const photoUrl = fileData.url;

    // Some ugly DOM code to show some data.
    const name = document.createTextNode('Selected: ' + fileData.filename);
    $("#uploadedImage").src(fileData.url);
    $("#fileupload").val(fileData.url);
    const url = document.createElement('a');
    url.href = fileData.url;
    url.appendChild(document.createTextNode(fileData.url));
    nameBox.appendChild(name);
    urlBox.appendChild(document.createTextNode('Uploaded to: '));
    urlBox.appendChild(url);
};