/**
 * Main script for rendering and interacting with the chess board.
 * Handles board setup, piece placement, drag-and-drop movement, and square highlighting.
 */

const board = document.getElementById("board");

/**
 * Initializes the chess board and pieces when the window loads.
 * Sets up drag-and-drop event listeners for pieces and squares.
 */
window.addEventListener("load", () => {
    loadBoard();
    placePieces();

    const pieces = document.querySelectorAll('img');

    // Enable drag for each piece
    pieces.forEach(piece => {
        piece.addEventListener("dragstart", e => {
            // Store the element's parent's dataset for identifying the piece's location
            e.dataTransfer.setData('text/plain', JSON.stringify(e.target.parentElement.dataset));
        });
    });

    const squares = document.querySelectorAll('.square');

    // Set up drag-and-drop for each square
    squares.forEach(square => {

        square.addEventListener("dragover", e => {
            e.preventDefault();
        });

        square.addEventListener("drop", e => {
            e.preventDefault();
            const data = e.dataTransfer.getData('text/plain');
            const dataset = JSON.parse(data);
            const el = document.querySelector(`[data-square="${dataset.square}"]`);
            // Moves the img piece into the dropped square
            square.appendChild(el.querySelector('img'));
        });
    });
});


/**
 * Creates the chess board grid (8x8) and adds square elements to the board.
 * Each square is assigned a color and a dataset attribute for identification.
 * Also sets up click event for highlighting squares.
 */
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

/**
 * Removes highlight class from all squares.
 */
function clearHighlights() {
    document.querySelectorAll(".square.highlight").forEach(sq =>
    sq.classList.remove("highlight")
    );
}

/**
 * Places chess pieces on their initial squares according to standard chess setup.
 * Pawns and major pieces are placed for both colors.
 */
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

/**
 * Places a major piece (rook, knight, bishop, queen, king) on the given square.
 * @param {string} color - The color of the piece ('blue' or 'black').
 * @param {HTMLElement} square - The square element to place the piece on.
 * @param {string} block - The square's identifier (e.g., 'a1', 'e8').
 */
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