

document.getElementById("getStatusButton").addEventListener("click", function() {
    document.getElementById("checkStatus").submit();
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
