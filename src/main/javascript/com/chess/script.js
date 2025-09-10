const board = document.getElementById("board");

window.addEventListener("load", () => {
    loadBoard();
    placePieces();

    const pieces = document.querySelectorAll('img');

    pieces.forEach(piece => {
        piece.addEventListener("dragstart", e => {
            e.dataTransfer.setData('text/plain', JSON.stringify(e.target.parentElement.dataset)); // Store the element's parent's dataset
        });
    });

    const squares = document.querySelectorAll('.square');

    squares.forEach(square => {

        square.addEventListener("dragover", e => {
            e.preventDefault();
        });

        square.addEventListener("drop", e => {
            e.preventDefault();
            const data = e.dataTransfer.getData('text/plain');
            const dataset = JSON.parse(data);
            const el = document.querySelector(`[data-square="${dataset.square}"]`);
            square.appendChild(el.querySelector('img')); // moves the img piece into the dropped square
        });
    });
});


function loadBoard() {
    for (let row = 1; row < 9; row++) {
        for (let col = 1; col < 9; col++) {
            const square = document.createElement("div");
            square.classList.add("square");
            square.classList.add((row + col) % 2 === 0 ? "light" : "dark");
            square.dataset.square = String.fromCharCode(96 + col) + row;

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

function placePieces() {
    const squares = board.children;

    for (const square of squares) {
        const block = square.dataset.square;

        if (block.includes("7")) {
            square.innerHTML = '<img src="pieces/blue-pawn.png" draggable="true"/>';
        } else if (block.includes("8")) {
            placePiece("blue", square, block);
        } else if (block.includes("1")) {
            placePiece("black", square, block);
        } else if (block.includes("2")) {
            square.innerHTML = '<img src="pieces/black-pawn.png" draggable="true" />';
        }
    }
}

function placePiece(color, square, block) {
    if (block.includes("a") || block.includes("h")) {
        square.innerHTML = `<img src="pieces/${color}-rook.png" draggable="true" />`;
    } else if (block.includes("b") || block.includes("g")) {
        square.innerHTML = `<img src="pieces/${color}-knight.png" draggable="true" />`;
    } else if (block.includes("c") || block.includes("f")) {
        square.innerHTML = `<img src="pieces/${color}-bishop.png" draggable="true" />`;
    } else if (block.includes("d")) {
        square.innerHTML = `<img src="pieces/${color}-queen.png" draggable="true" />`;
    } else if (block.includes("e")) {
        square.innerHTML = `<img src="pieces/${color}-king.png" draggable="true" />`;
    }
}