
# Flipkart Daily Essentials

This project simulates a system for managing an inventory of daily essentials and groceries, allowing users to search, filter, and sort items before placing orders. It's built with Java and demonstrates object-oriented design principles.

---

## Features

### Inventory Management
- **Add Items:** Define items in the inventory with category, brand, and price.
- **Update Inventory:** Add or update the available quantity of items.

### Search Functionality
- Search items by:
  - **Category**
  - **Brand**
  - **Price Range** (optional `from` and `to` values)
- Combine multiple filters, such as:
  - Search by both category and brand.
  - Search by category and price range.
  - Search by brand and price range.
- **Sorting:**
  - Lowest price first (default).
  - Highest price first.
  - Least quantity first.

### Order Management
- Users can add items to the cart and place orders.
- Orders include details like the user, order ID, and list of items.

---

## Design Principles
- Modular and extensible architecture.
- Proper separation of concerns using services and repositories.
- Designed for extensibility with new features like filters or sorting criteria.

---

## Project Structure

```

FlipKart/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.example.flipkart/
│   │   │   │   ├── App.java              # Entry point of the application
│   │   │   │   ├── model/                # Data models (e.g., Item, User, Order)
│   │   │   │   ├── service/              # Business logic (e.g., InventoryService, SearchService)
│   │   │   │   ├── repository/           # Data storage and retrieval
│   │   │   │   └── exceptions/           # Custom exceptions
├── README.md                              # Project documentation
└── pom.xml                                # Maven configuration

````

---

## Prerequisites

- **Java 8+**: Installed and configured.
- **Maven**: Installed and configured.

---

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/ujjwalzero9/Flipkart.git
   cd Flipkart


2. **Build the project:**

   ```bash
   mvn clean compile
   ```

3. **Run the application:**

   ```bash
   mvn exec:java -Dexec.mainClass=com.example.flipkart.App
   ```

---

## Example Usage

### Input:

```java
// Add items to inventory
service.addItem("Amul", "Milk", 100);
service.addItem("Nestle", "Curd", 90);

// Add inventory
service.addInventory("Amul", "Milk", 10);

// Search items
List<Item> results = service.searchItems(filters, priceRange, "price", true);
```

### Output:

```
Inventory:
Amul -> Milk -> 10
Nestle -> Curd -> 0

Search Results:
Amul, Milk, 10
```

---

## Contact

For questions or suggestions, please contact [Ujjwal Kumar](https://github.com/ujjwalzero9).
