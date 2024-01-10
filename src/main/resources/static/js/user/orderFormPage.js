document.getElementById("redirectBackButton").addEventListener("click", function() {
    var previousPage = document.referrer;

    if (previousPage) {
        window.location.href = previousPage;
    } else {
        window.history.back();
    }
});

document.getElementById("createOrder").addEventListener("click", function() {
    submitCart();
});