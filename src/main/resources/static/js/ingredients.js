$(document).ready( async function () {
    // Fetch ingredient info from ingredients.json and return result/response in json format
    const data = await fetch("/ingredients.json").then(result => result.json());

    // Using DOM to target the parent element of the recipe's ingredients that will
    // contain the ingredient names (Edit form)
    const recipeIngredientNames = document.getElementById("testHiddenIngredientNames");

    // Using DOM to target the element of the notes property of the recipe
    const recipeNotes = document.getElementById("notes");

    // Declared a Map to store the data from ingredients.json
    const dtSelection = new Map();
    // Declared an empty array to store the ids of the ingredients
    const selectedIngredients = [];

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
            data.forEach(obj => {
                // store the each ingredients id and name into the map
                dtSelection.set(obj.name, obj.id);
            });

            // if ingredients are defined for a recipe target the children of the ingredients parent element
            if (recipeIngredientNames != null) {
                const ingredientNames = recipeIngredientNames.children;

                // loop through the ingredientNames, if the ingredient is in the Map store the ingredient's id
                // into selectedIngredients array
                for(let i = 0; i < ingredientNames.length; i++) {
                    const name = ingredientNames[i].value;
                    if (dtSelection.has(name)) {
                        selectedIngredients.push(dtSelection.get(name));
                    }
                }
            }
        }
    });

    // Declared a Set to store the id(s) of the selected ingredient from the DataTable
    const mySetIds = new Set();
    const mySetNames = new Set();

    if (selectedIngredients.length > 0) {
        selectedIngredients.forEach((elem) => {
            table.row( `${elem - 1}` ).select();
            mySetIds.add(elem);
        });
    }

    // Event Listener for the DataTable select event which stores the currently selected
    // ingredient id into the Set
    table.on( 'select', function ( e, dt, type, indexes ) {
        if ( type === 'row' ) {
            const dtInfo = table.rows( indexes ).data();

            const dtInfoId = dtInfo.pluck( 'id' );
            const dtInfoName = dtInfo.pluck('name');

            // add ingredient id and name to the set(s)
            mySetIds.add(dtInfoId[0]);
            mySetNames.add(dtInfoName[0]);
            recipeNotes.innerText = Array.from(mySetNames).join(", ");
        }
    } );

    // Event Listener for the DataTable deselect event which removes the currently selected
    // ingredient id from the Set
    table.on( 'deselect', function ( e, dt, type, indexes ) {
        if ( type === 'row' ) {
            const dtInfo = table.rows( indexes ).data();

            const dtInfoId = dtInfo.pluck( 'id' );
            const dtInfoName = dtInfo.pluck('name');

            // remove ingredient id and name from the set(s)
            mySetIds.delete(dtInfoId[0]);
            mySetNames.delete(dtInfoName[0]);
            recipeNotes.innerText = Array.from(mySetNames).join(", ");
        }
    } );

    // Event Listener for the submit button when clicked
    const submitBtn = document.getElementById('submitBtn');
    const ingredients = document.getElementById('hiddenIngredients');
    submitBtn.addEventListener('click',() => {
        let html = "";
        mySetIds.forEach((elem) => {
            html += `<input type="hidden" name="ingredients" value="${elem}" >`
        });
        ingredients.innerHTML = html;
    })
} );

