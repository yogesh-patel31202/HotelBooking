// Hotel Insert Form JavaScript

document.getElementById("hotelForm").addEventListener("submit", function(event) {
    let hotelName = document.getElementById("hotelName").value;
    let address = document.getElementById("address").value;
    let city = document.getElementById("city").value;
    let price = document.getElementById("pricePerNight").value;
    let image = document.getElementById("image").value;

    if (!hotelName || !address || !city || !price || !image) {
        alert("Please fill out all fields.");
        event.preventDefault();
    } else {
        alert("Hotel details submitted successfully!");
    }
});
