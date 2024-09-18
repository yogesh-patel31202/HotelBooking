// JavaScript for handling slider and user interactions

document.addEventListener("DOMContentLoaded", () => {
    // Example interaction for search bar
    const searchButton = document.querySelector(".search-bar button");

    searchButton.addEventListener("click", () => {
        const searchInput = document.querySelector(".search-bar input").value;
        if (searchInput) {
            alert("Searching for: " + searchInput);
            // Add search logic here
        } else {
            alert("Please enter a search term!");
        }
    });
});
