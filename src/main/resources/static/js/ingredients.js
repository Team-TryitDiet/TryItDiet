$(document).ready( async function () {
    const data = await fetch("/ingredients.json").then(result => result.json());
    const table = $('#ingredients').DataTable({
        scrollY: 200,
        data: data,
        columns: [
            { data: "id" },
            { data: "name" }
        ]
    });

} );