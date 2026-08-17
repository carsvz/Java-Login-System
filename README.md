Java Login System

A simple authentication system built in pure Java (no frameworks, no GUI), designed for the console, featuring user registration, login, and file-based persistence.

✨ Features
User registration (login + password, with password confirmation)
Login with credential validation
Passwords stored using SHA-256 hashing (never in plain text)
Data persistence in a text file (usuarios.txt)
Input validation (duplicate login, empty password, mismatched passwords, etc.)
🏗️ Architecture

The project follows a layered separation of concerns (Model / Repository / Service / UI), a good Object-Oriented Programming practice:
src/
├── Usuario.java              → Model: represents the User entity
├── HashUtil.java              → Utility: SHA-256 password hashing
├── UsuarioRepositorio.java    → Repository: persistence (read/write to file)
├── AutenticadorService.java   → Service: business rules (registration & login)
└── SistemaLogin.java          → Console UI: menu and user interaction


🛠️ Tech stack
Java (pure, no external dependencies)
SHA-256 (java.security.MessageDigest) for password hashing
Plain text file for persistence
