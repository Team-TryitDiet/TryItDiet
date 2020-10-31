$(document).ready( async function () {
    const data = await fetch("/ingredients.json").then(result => result.json());
    console.log(data);
    $('#ingredients').DataTable({
        data: data,
        select: true,
        columns: [
            { data: "id" },
            { data: "name" }
        ]
    });
} );