$(document).ready( async function () {
    const data = await fetch("/ingredients.json").then(result => result.json());

    const table = $('#ingredients').DataTable({
        scrollY: 250,
        select: {
            style: "multi"
        },
        data: data,
        columns: [
            { data: "id" },
            { data: "name" }
        ]
    });

    // Declared a Set to store the id(s) of the selected ingredient from the DataTable
    const mySet = new Set()

    // Event Listener for the DataTable select event which stores the currently selected
    // ingredient id into the Set
    table.on( 'select', function ( e, dt, type, indexes ) {
        if ( type === 'row' ) {
            const data = table.rows( indexes ).data().pluck( 'id' );

            // add ingredient id to the set
            mySet.add(data[0]);
        }
    } );

    // Event Listener for the DataTable deselect event which removes the currently selected
    // ingredient id from the Set
    table.on( 'deselect', function ( e, dt, type, indexes ) {
        if ( type === 'row' ) {
            const data = table.rows( indexes ).data().pluck( 'id' );

            // delete ingredient id from the set
            mySet.delete(data[0]);
        }
    } );
} );

