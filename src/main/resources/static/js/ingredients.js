$(document).ready( async function () {
    // Fetch ingredient info from ingredients.json and return result/response in json format
    const data = await fetch("/ingredients.json").then(result => result.json());
    const recipeIngredients = document.getElementById("testHiddenIngredients");

    const selectIngredients = [];
    // Build table based off of fetch call above
    const table = $('#ingredientTable').DataTable({
        scrollY: 300,
        select: {
            style: "multi"
        },
        data: data,
        "rowId": function(a) {
            return a.id;
        },
        columns: [
            { data: "id",
                visible: false
            },
            { data: "name" }
        ],
        "initComplete": () => {
            if (recipeIngredients != null) {
                const ingredientsList = recipeIngredients.children;
                data.forEach(obj => {
                    for(let i = 0; i < ingredientsList.length; i++) {
                        if (ingredientsList[i].value == obj.id) {
                            selectIngredients.push(obj.id);
                        }
                    }
                });
            }
        }
    });

    // Declared a Set to store the id(s) of the selected ingredient from the DataTable
    const mySet = new Set();

    if (selectIngredients.length != 0) {
        selectIngredients.forEach((elem) => {
            table.row( `${elem - 1}` ).select();
            mySet.add(elem);
        });
    }

    // Event Listener for the DataTable select event which stores the currently selected
    // ingredient id into the Set
    table.on( 'select', function ( e, dt, type, indexes ) {
        if ( type === 'row' ) {
            const data = table.rows( indexes ).data().pluck( 'id' );
            console.log(data[0]);

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
