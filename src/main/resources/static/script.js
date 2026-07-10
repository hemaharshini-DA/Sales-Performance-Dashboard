// =======================
// Global Variables
// =======================
let editingId = null;

// =======================
// Total Sales
// =======================
fetch("http://localhost:8080/total-sales")
.then(response => response.json())
.then(data => {
    document.getElementById("totalSales").innerHTML = "₹" + data;
});

// =======================
// Total Profit
// =======================
fetch("http://localhost:8080/total-profit")
.then(response => response.json())
.then(data => {
    document.getElementById("totalProfit").innerHTML = "₹" + data;
});

// =======================
// Sales Count
// =======================
fetch("http://localhost:8080/sales-count")
.then(response => response.json())
.then(data => {
    document.getElementById("salesCount").innerHTML = data;
});

// =======================
// Top Selling Product
// =======================
fetch("http://localhost:8080/top-product")
.then(response => response.json())
.then(data => {

    document.getElementById("topProduct").innerHTML =
        data.productName + "<br>₹" + data.sales;

});
// =======================
// Sales By Region Table + Charts
// =======================
fetch("http://localhost:8080/sales-by-region")
.then(response => response.json())
.then(data => {

    let tableBody = document.getElementById("regionBody");

    let regions = [];
    let sales = [];

    tableBody.innerHTML = "";

    data.forEach(region => {

        let row = `
            <tr>
                <td>${region.region}</td>
                <td>₹${region.totalSales}</td>
            </tr>
        `;

        tableBody.innerHTML += row;

        regions.push(region.region);
        sales.push(region.totalSales);

    });

    // =======================
    // Bar Chart
    // =======================
    const ctx = document.getElementById("salesChart").getContext("2d");

    new Chart(ctx, {
        type: "bar",
        data: {
            labels: regions,
            datasets: [{
                label: "Sales",
                data: sales,
                backgroundColor: [
                    "#0d6efd",
                    "#198754",
                    "#dc3545",
                    "#ffc107",
                    "#6f42c1"
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    // =======================
    // Pie Chart
    // =======================
    const pieCtx = document.getElementById("pieChart").getContext("2d");

    new Chart(pieCtx, {
        type: "pie",
        data: {
            labels: regions,
            datasets: [{
                data: sales,
                backgroundColor: [
                    "#0d6efd",
                    "#198754",
                    "#ffc107",
                    "#dc3545",
                    "#6f42c1",
                    "#20c997"
                ]
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: "bottom"
                }
            }
        }
    });

});

// =======================
// All Products Table
// =======================
fetch("http://localhost:8080/sales")
.then(response => response.json())
.then(data => {

    let productBody = document.getElementById("productBody");

    productBody.innerHTML = "";

    data.forEach(product => {

        let row = `
            <tr>
                <td>${product.productName}</td>
                <td>₹${product.sales}</td>
                <td>₹${product.profit}</td>
                <td>${product.region}</td>
                <td>
				<button onclick="editSale(
				    ${product.id},
				    '${product.productName}',
				    ${product.sales},
				    ${product.profit},
				    '${product.region}',
				    '${product.saleDate}'
				)">Edit</button>

                    <button onclick="deleteSale(${product.id})">Delete</button>
                </td>
            </tr>
        `;

        productBody.innerHTML += row;

    });

});

// =======================
// Save Sale
// =======================
function saveSale() {

    let sale = {

        productName: document.getElementById("productName").value,
        sales: document.getElementById("sales").value,
        profit: document.getElementById("profit").value,
		region: document.getElementById("region").value,
		saleDate: document.getElementById("saleDate").value

    };

    fetch("http://localhost:8080/save", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(sale)

    })

    .then(response => response.json())

    .then(data => {

        alert("Sale Saved Successfully!");

        location.reload();

    });

}

// =======================
// Update Sale
// =======================
function updateSale() {

    let sale = {

        productName: document.getElementById("productName").value,
        sales: document.getElementById("sales").value,
        profit: document.getElementById("profit").value,
		region: document.getElementById("region").value,
		saleDate: document.getElementById("saleDate").value

    };

    fetch("http://localhost:8080/update/" + editingId, {

        method: "PUT",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(sale)

    })

    .then(response => response.json())

    .then(data => {

        alert("Sale Updated Successfully!");

        location.reload();

    });

}

// =======================
// Delete Sale
// =======================
function deleteSale(id) {

    if(confirm("Are you sure you want to delete this product?")) {

        fetch("http://localhost:8080/delete/" + id, {

            method: "DELETE"

        })

        .then(response => response.text())

        .then(data => {

            alert(data);

            location.reload();

        });

    }

}

// =======================
// Edit Sale
// =======================
function editSale(id, productName, sales, profit, region, saleDate) {

    editingId = id;

    document.getElementById("productName").value = productName;
    document.getElementById("sales").value = sales;
    document.getElementById("profit").value = profit;
    document.getElementById("region").value = region;
	document.getElementById("saleDate").value = saleDate;

    let button = document.getElementById("saveButton");

    button.innerHTML = "Update Sale";

    button.setAttribute("onclick", "updateSale()");

}

// =======================
// Search Product
// =======================
function searchProduct() {

    let input = document.getElementById("searchInput").value.toUpperCase();

    let table = document.getElementById("productTable");

    let tr = table.getElementsByTagName("tr");

    for (let i = 1; i < tr.length; i++) {

        let td = tr[i].getElementsByTagName("td")[0];

        if (td) {

            let textValue = td.textContent || td.innerText;

            tr[i].style.display =
                textValue.toUpperCase().indexOf(input) > -1 ? "" : "none";

        }

    }

}

// =======================
// Filter Region
// =======================
function filterRegion() {

    let filter = document.getElementById("regionFilter").value.toUpperCase();

    let table = document.getElementById("productTable");

    let tr = table.getElementsByTagName("tr");

    for (let i = 1; i < tr.length; i++) {

        let td = tr[i].getElementsByTagName("td")[3];

        if (td) {

            let textValue = td.textContent || td.innerText;

            if (filter === "ALL" || textValue.toUpperCase() === filter) {

                tr[i].style.display = "";

            } else {

                tr[i].style.display = "none";

            }
			 
        }
		 

    }
}
	// =======================
	// Monthly Sales Chart
	// =======================
	async function loadMonthlySales() {

	    const response = await fetch("http://localhost:8080/monthly-sales");
	    const data = await response.json();

	    const labels = data.map(item => item.month);
	    const sales = data.map(item => item.totalSales);

	    const ctx = document.getElementById("monthlyChart").getContext("2d");

	    new Chart(ctx, {
	        type: "line",
	        data: {
	            labels: labels,
	            datasets: [{
	                label: "Monthly Sales",
	                data: sales,
	                borderColor: "#0d6efd",
	                backgroundColor: "rgba(13,110,253,0.2)",
	                fill: true,
	                tension: 0.4
	            }]
	        },
	        options: {
	            responsive: true
	        }
	    });

	}

	loadMonthlySales();


