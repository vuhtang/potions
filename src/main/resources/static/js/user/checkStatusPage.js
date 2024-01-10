document.getElementById("getStatusButton").addEventListener("click", function() {
    document.getElementById('orderForm').submit();
});

document.getElementById("redirectBackButton").addEventListener("click", function() {
    var previousPage = document.referrer;

    if (previousPage) {
        window.location.href = previousPage;
    } else {
        window.history.back();
    }
});
