document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".details__favorite").forEach(function (button) {
        button.addEventListener("click", function () {
            let gameId = this.getAttribute("game-id");

            fetch("/NeonRealm/AddToCartServlet", {
                method: "POST",
                headers: {"Content-Type": "application/x-www-form-urlencoded"},
                body: "id=" + encodeURIComponent(gameId)
            })
            .then(response => response.json())
            .then(data => {
                console.log("Server Response:", data); // Debug để xem phản hồi từ server

                if (data.status === "success") {
                    console.log("Thêm vào giỏ hàng thành công!");
                    document.getElementById("cart-count").innerText = `(${data.cartCount})`;
                    document.getElementById("cart-total-price").innerText = "$" + data.totalPrice.toFixed(2);
                    showCartNotification("Product has been added to cart!", "green");
                } else if (data.status === "exists") {
                    console.log("Sản phẩm đã có trong giỏ hàng!"); // Debug
                    showCartNotification("Product is already in cart!", "yellow");
                } else {
                    showCartNotification("Unknown error!", "red");
                }
            })
            .catch(error => {
                console.error("Error sending request:", error);
                showCartNotification("Connection error!", "red");
            });
        });
    });
});


// Hàm hiển thị thông báo với màu sắc tùy chỉnh
function showCartNotification(message, color) {
    let notification = document.getElementById("cart-notification");
    notification.innerText = message;
    notification.style.display = "block";
    notification.style.backgroundColor = color; // Đổi màu theo trạng thái
    notification.style.color = "black"; // Đảm bảo chữ dễ đọc
    notification.style.padding = "10px";
    notification.style.borderRadius = "5px";

    setTimeout(() => {
        notification.style.display = "none";
    }, 2000);
}
