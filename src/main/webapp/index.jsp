<html>
<body>
	 <h2>Book Management</h2>

    <!-- Book Registration Form -->
    <form action="registerBook" method="post">
        <!-- Book Title -->
        <label for="title">Book Title:</label>
        <input type="text" id="title" name="title" placeholder="Enter book title" required>

        <!-- Author -->
        <label for="author">Author:</label>
        <input type="text" id="author" name="author" placeholder="Enter author name" required>

        <!-- Price -->
        <label for="price">Price:</label>
        <input type="number" step="0.01" id="price" name="price" placeholder="Enter book price" required>

        <!-- Submit Button -->
        <button type="submit">Register Book</button>
    </form>

    <a href="displayBooks">View All Books</a>

    <!-- Book Search Form -->
    <form action="searchBooks" method="get">
        <label for="searchTitle">Search by Title:</label>
        <input type="text" id="searchTitle" name="title" placeholder="Enter book title" required>
        <button type="submit">Search</button>
    </form>
</body>
</html>

