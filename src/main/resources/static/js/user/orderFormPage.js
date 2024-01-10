document.getElementById("createOrder").addEventListener("click", function() {
    document.getElementById("createOrderForm").submit();
});

document.getElementById("redirectBackButton").addEventListener("click", function() {
    goTo("user/start")
    // var previousPage = document.referrer;
    //
    // if (previousPage) {
    //     window.location.href = previousPage;
    // } else {
    //     window.history.back();
    // }
});

// document.getElementById("createOrder").addEventListener("click", function() {
//     submitCart();
// });