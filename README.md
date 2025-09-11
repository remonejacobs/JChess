# ♟️ JChess

JChess is a web-based chess game built with Java and JavaScript.  
It features a graphical chess board, drag-and-drop piece movement, and communicates with a backend server for move validation and bot responses.
Chess game using java
---

## ✨ Features

- 🎲 **Interactive chess board** rendered in the browser
- 🖱️ **Drag-and-drop movement** for chess pieces
- 🛡️ **Move validation** and game logic handled by a Java backend
- 🤖 **Bot opponent** with automatic move responses
- 🔦 **Visual highlighting** of squares and checkmate detection

---

## 🛠️ Technologies Used

- ☕ Java (backend server)
- 📜 JavaScript (frontend logic)
- 🖼️ HTML/CSS (board and UI rendering)

---

## 🚀 Setup

### Backend ☕
1. Make sure you have Java installed (Java 8+ recommended).
2. Compile the backend Java source files:
   ```sh
   javac -d bin src/main/java/com/chess/*.java
   ```
3. Start the backend server:
   ```sh
   java -cp bin com.chess.Main
   ```
   *(Replace `com.chess.Main` with your actual main class if different.)*

### Frontend 🖼️
1. Open `index.html` in your browser.
2. The chess board will be displayed and ready for play.

**Note:**  
The frontend expects the backend server to run at `http://localhost:7000`.
