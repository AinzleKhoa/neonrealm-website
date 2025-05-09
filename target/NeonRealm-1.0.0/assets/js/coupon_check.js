document.addEventListener("DOMContentLoaded", function () {
    document.querySelector(".form.form--first.form--coupon").addEventListener("submit", function (event) {
        event.preventDefault(); // Ngăn chặn reload trang

        let couponInput = document.querySelector(".form__input");
        let couponCode = couponInput.value.trim();

        if (couponCode === "") {
            showCartNotification("Please enter a coupon code!", "yellow");
            return;
        }

        fetch("/NeonRealm/PayServlet", {
            method: "POST",
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            body: "action=couponCheck&couponCode=" + encodeURIComponent(couponCode)
        })
                .then(response => response.json())
                .then(data => {
                    if (!data)
                        return;

                    console.log("Server Response:", data); // Kiểm tra JSON trả về

                    if (data.status === "success") {
                        let discountElement = document.getElementById("discount-percent");
                        let totalPriceElement = document.getElementById("cart-total-price");

                        if (discountElement) {
                            discountElement.innerText = `(${data.discountPercent}%)`;
                        } else {
                            console.error("Element #discount-percent not found!");
                        }

                        if (totalPriceElement) {
                            totalPriceElement.innerText = `$${data.newTotal.toFixed(2)}`;
                        } else {
                            console.error("Element #cart-total-price not found!");
                        }

                        showCartNotification("Valid coupon", "green");
                    } else if (data.status === "exists") {
                        showCartNotification("Invalid coupon code", "yellow");
                    } else {
                        showCartNotification("Unknown error!", "red");
                    }
                })
                .catch(error => {
                    console.error("Fetch Error:", error);
                    showCartNotification("Connection error!", "red");
                });
    });
});



function showCartNotification(message, color) {
    let notification = document.getElementById("cart-notification-coupon");
    let messageElement = document.getElementById("cart-notification-message");
    let closeButton = document.getElementById("cart-notification-close");

    // Update the notification message
    messageElement.innerText = message;

    // Adjust the width dynamically based on the message length
    notification.style.width = "auto";
    notification.style.minWidth = "150px";
    notification.style.padding = "12px 15px";
    notification.style.maxWidth = "90%";
    notification.style.backgroundColor = color;
    // Display the notification with a fade-in effect
    notification.style.display = "flex";
    setTimeout(() => {
        notification.style.opacity = "1";
    }, 10);

    // Auto-hide the notification after 3 seconds with a fade-out effect
    setTimeout(() => {
        notification.style.opacity = "0";
        setTimeout(() => {
            notification.style.display = "none";
        }, 300);
    }, 3000);

    // Close the notification when clicking the ✖ button
    closeButton.onclick = () => {
        notification.style.opacity = "0";
        setTimeout(() => {
            notification.style.display = "none";
        }, 300);
    };
}


