document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".details__buy").forEach(function (button) {
        button.addEventListener("click", function () {
            let gameId = this.getAttribute("game-buy-now-id");

            if (!gameId) {
                console.error("Game ID is missing!");
                return;
            }
            console.log("Game id: " + gameId);
            // Tạo form ẩn để gửi dữ liệu đến servlet
            let form = document.createElement("form");
            form.method = "POST";
            form.action = "/NeonRealm/BuyNowServlet"; // Servlet xử lý

            let input = document.createElement("input");
            input.type = "hidden";
            input.name = "id";
            input.value = gameId;

            form.appendChild(input);
            document.body.appendChild(form);
            form.submit(); // Gửi form
        });
    });
});
