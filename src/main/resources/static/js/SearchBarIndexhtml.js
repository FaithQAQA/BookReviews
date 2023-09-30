
    // Get the input element and table
    var input = document.getElementById("searchInput");
    var table = document.querySelector("table");

    // Add an event listener to the input element
    input.addEventListener("input", function () {
        var filter = input.value.toLowerCase();

        // Get all the rows in the table body
        var rows = table.querySelectorAll("tbody tr");

        // Loop through the rows and hide those that don't match the search input
        rows.forEach(function (row) {
            var cells = row.getElementsByTagName("td");
            var shouldHide = true;

            for (var i = 0; i < cells.length; i++) {
                var cellText = cells[i].textContent.toLowerCase();
                if (cellText.indexOf(filter) > -1) {
                    shouldHide = false;
                    break;
                }
            }

            if (shouldHide) {
                row.style.display = "none";
            } else {
                row.style.display = "";
            }
        });
    });
