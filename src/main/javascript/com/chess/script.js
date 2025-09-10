const board = document.getElementById("board");

window.addEventListener("load", () => {
    loadBoard();
});

function loadBoard() {
    for (let row = 0; row < 8; row++) {
        for (let col = 0; col < 8; col++) {
            const square = document.createElement("div");
            square.classList.add("square");
            square.classList.add((row + col) % 2 === 0 ? "light" : "dark");
            square.dataset.square = String.fromCharCode(97 + col) + row;

            square.addEventListener("click", () => {
                clearHighlights();
                square.classList.add("highlight");
            });

            board.appendChild(square);
        };
    };
}


function clearHighlights() {
    document.querySelectorAll(".square.highlight").forEach(sq =>
    sq.classList.remove("highlight")
    );
}