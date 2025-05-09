document.getElementById("pay-button").addEventListener("click", function () {
    fetch("/NeonRealm/PayServlet", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: "action=complete"
    }).then(response => {
        if (response.redirected) {
            window.location.href = response.url;
        }
    }).catch(error => console.error("Payment Error:", error));
});
