$(document).ready( async function () {
    // Fetch ingredient info from ingredients.json and return result/response in json format
    const data = await fetch("/ingredients.json").then(result => result.json());

    // Build table based off of fetch call above
    const table = $('#ingredientTable').DataTable({
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

    // Event Listener for the submit button when clicked
    const submitBtn = document.getElementById('submitBtn');
    const ingredients = document.getElementById('hiddenIngredients');
    submitBtn.addEventListener('click',() => {
        let html = "";
        mySet.forEach((elem) => {
            html += `<input type="hidden" name="ingredients" value="${elem}" >`
        });
        ingredients.innerHTML = html;
    })
} );

