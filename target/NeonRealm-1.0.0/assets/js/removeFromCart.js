document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".delete-cart-item").forEach(function (button) {
        button.addEventListener("click", function (event) {
            event.preventDefault(); // Ngăn form gửi yêu cầu mặc định

            let gameId = this.getAttribute("data-game-id");

            fetch("CheckoutServlet", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: "action=delete&gameId=" + encodeURIComponent(gameId)
            })
            .then(response => response.json())
            .then(data => {
                console.log("Server Response:", data); // Debug response

                if (data.status === "success") {
                    // Xóa dòng sản phẩm khỏi bảng HTML
                    let row = document.querySelector(`tr[data-game-id="${gameId}"]`);
                    if (row) {
                        row.remove();
                    }

                    // Cập nhật số lượng giỏ hàng
                    document.getElementById("cart-count").innerText = `(${data.cartCount})`;

                    let totalPriceElement = document.getElementById("cart-total-price");
                    if (totalPriceElement) {
                        totalPriceElement.innerText = `$${data.totalPrice.toFixed(2)}`;
                    } else {
                        console.error("Element #cart-total-price not found!");
                    }

                    showCartNotification("Product removed successfully!", "green");
                } else {
                    showCartNotification("Failed to remove product!", "red");
                }
            })
            .catch(error => {
                console.error("Error:", error);
            }); 
        });
    });
});
