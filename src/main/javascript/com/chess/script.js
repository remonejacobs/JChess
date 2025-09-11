/**
 * Main script for rendering and interacting with the chess board.
 * Handles board setup, piece placement, drag-and-drop movement, and square highlighting.
 * Communicates with a backend server to validate moves and process bot responses.
 */

const board = document.getElementById("board"); // The main board container

/**
 * Sets up the chess board and pieces when the window loads.
 * Adds drag-and-drop event listeners for pieces and squares.
 */
window.addEventListener("load", () => {
    loadBoard();    // Create the board squares
    placePieces();  // Place all chess pieces

    const pieces = document.querySelectorAll('img'); // Select all piece images

    // Enable drag for each piece
    pieces.forEach(piece => {
        piece.addEventListener("dragstart", e => {
            // Store the element's parent's dataset for identifying the piece's location
            e.dataTransfer.setData('text/plain', JSON.stringify(e.target.parentElement.dataset));
        });
    });

    const squares = document.querySelectorAll('.square'); // Select all squares

    // Set up drag-and-drop for each square
    squares.forEach(square => {
        // Allow dropping by preventing default dragover behavior
        square.addEventListener("dragover", e => {
            e.preventDefault();
        });

        // Handle drop event: send move to backend and update board if valid
        square.addEventListener("drop", e => {
            e.preventDefault();
            const data = e.dataTransfer.getData('text/plain');
            const dataset = JSON.parse(data);
            executeMove(dataset.square, square.dataset.square);
        });
    });
});


/**
 * Creates the chess board grid (8x8) and adds square elements to the board.
 * Each square is assigned a color and a dataset attribute for identification.
 * Also sets up click event for highlighting squares.
 */
function loadBoard() {
    // Loop from row 8 to 1 and column 8 to 1 to match chess notation
    for (let row = 8; row > 0; row--) {
        for (let col = 8; col > 0; col--) {
            const square = document.createElement("div");
            square.classList.add("square");
            // Alternate square color based on position
            square.classList.add((row + col) % 2 === 0 ? "light" : "dark");
            // Assign chess notation (e.g., 'a1', 'h8') as dataset attribute
            square.dataset.square = String.fromCharCode(105 - col) + row;

            // Add click event to highlight the selected square
            square.addEventListener("click", () => {
                clearHighlights();
                square.classList.add("highlight");
            });

            board.appendChild(square); // Add square to board
        };
    };
}

/**
 * Removes highlight class from all squares.
 * Used to clear previous highlights before highlighting a new square.
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

        // Place blue pawns on row 2
        if (block.includes("2")) {
            square.innerHTML = '<img src="pieces/blue-pawn.png" class="pawn" draggable="true"/>';
        // Place blue major pieces on row 1
        } else if (block.includes("1")) {
            placePiece("blue", square, block);
        // Place black major pieces on row 8
        } else if (block.includes("8")) {
            placePiece("black", square, block);
        // Place black pawns on row 7
        } else if (block.includes("7")) {
            square.innerHTML = '<img src="pieces/black-pawn.png" class="pawn" draggable="false"/>';
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
    // Only blue pieces are draggable (human player)
    let draggable = false;
    if (color === "blue") {
        draggable = true;
    }

    // Place the correct piece based on column
    if (block.includes("a") || block.includes("h")) {
        square.innerHTML = `<img src="pieces/${color}-rook.png" class="rook" draggable="${draggable}" />`;
    } else if (block.includes("b") || block.includes("g")) {
        square.innerHTML = `<img src="pieces/${color}-knight.png" class="knight" draggable="${draggable}" />`;
    } else if (block.includes("c") || block.includes("f")) {
        square.innerHTML = `<img src="pieces/${color}-bishop.png" class="bishop" draggable="${draggable}" />`;
    } else if (block.includes("d")) {
        square.innerHTML = `<img src="pieces/${color}-queen.png" class="queen" draggable="${draggable}" />`;
    } else if (block.includes("e")) {
        square.innerHTML = `<img src="pieces/${color}-king.png" class="${color}-king" draggable="${draggable}" />`;
    }
}

/**
 * Executes a move by sending it to the backend server for validation.
 * If the move is valid, updates the board and processes bot's response move.
 * @param {string} from - The source square (e.g., 'e2').
 * @param {string} to - The destination square (e.g., 'e4').
 */
function executeMove(from, to) {
    const url = 'http://localhost:7000/move';
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        // Send move in algebraic notation
        body: JSON.stringify({move: createMove(from, to)})
    };

    fetch(url, options)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            // If move is valid, update board visually
            if (data.move === "valid") {

                const fromSquare = document.querySelector(`[data-square="${from}"]`);
                const toSquare = document.querySelector(`[data-square="${to}"]`);
                const piece = fromSquare.querySelector('img');
                if (piece) {
                    toSquare.innerHTML = ''; // Clear destination square
                    toSquare.appendChild(piece);
                    fromSquare.innerHTML = '';
                }

                if (data.checkmate === true) {
                    const element = document.querySelector('.game');
                    const king = document.querySelector('.black-king');
                    king.classList.add('in-checkmate');
                    const checkmateMessage = document.createElement('div');

                    checkmateMessage.classList.add('checkmate-message');
                    checkmateMessage.innerHTML = '<h2>Checkmate! You Win!</h2>';

                    element.appendChild(checkmateMessage);
                }

            } else if (data.checkmate === true) {

            }

            // Handle bot's move if present in response
            const botMoves = data.botMoves;
            try {
                const fromBot = botMoves.fromBot;
                console.log(fromBot)
                const toBot = botMoves.toBot;
                const fromSquare = document.querySelector(`[data-square="${fromBot}"]`);
                const toSquare = document.querySelector(`[data-square="${toBot}"]`);
                const piece = fromSquare.querySelector('img');
                if (piece) {
                    toSquare.innerHTML = ''; // Clear destination square
                    toSquare.appendChild(piece);
                    fromSquare.innerHTML = '';
                }
            } catch (ignore) {
                // No bot move to process
            }
        })
        .catch(error => {
            console.error('Error executing move:', error);
        });
}

/**
 * Converts a move from source and destination squares to algebraic notation.
 * Determines piece type from its class and formats move string accordingly.
 * @param {string} from - The source square (e.g., 'e2').
 * @param {string} to - The destination square (e.g., 'e4').
 * @returns {string} The move in algebraic notation (e.g., 'e4', 'Nf3').
 */
function createMove(from, to) {
    const fromSquare = document.querySelector(`[data-square="${from}"]`);
    const classes = fromSquare.querySelector('img').classList;

    // Return move notation based on piece type
    if (classes.contains('pawn')) {
        return `${to}`;
    } else if (classes.contains('rook')) {
        return `R${to}`;
    } else if (classes.contains('knight')) {
        return `N${to}`;
    } else if (classes.contains('bishop')) {
        return `B${to}`;
    } else if (classes.contains('queen')) {
        return `Q${to}`;
    } else if (classes.contains('king')) {
        return `K${to}`;
    }
    return '';
}