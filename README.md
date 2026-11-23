# Restaurant Management System

A simple object-oriented Java console application for managing restaurant menus, customer orders, payments, and receipts.

## Features
- **Menu Management:** Supports food (`Dish`) and drink (`Beverage`) items extending `MenuItem`.
- **Order Processing:** Tracks customers (`Customer`), orders (`Order`), and individual line items (`OrderItem`).
- **Payments & Billing:** Polymorphic payment handling via `Payable` (`CashPayment`, `CardPayment`) and formatted receipt generation (`ReceiptPrinter`).

## Run Locally
Compile and run from the project root:

```bash
javac -d out src/*.java
java -cp out Main
```
