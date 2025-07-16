package Lib;
import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run(){
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));      // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }

        // Test 4: สินค้าราคา/จำนวนติดลบ
        ArrayList<CartItem> ErrorCart = new ArrayList<>();
        ErrorCart.add(new CartItem("NORMAL", "InvalidItem", -10.0, 2));
        try {
            double total4 = ShoppingCartCalculator.calculateTotalPrice(ErrorCart);
            System.out.println("FAILED: Negative price should cause an exception, got " + total4);
            failedCount++;
        } catch (IllegalArgumentException e) {
            System.out.println("PASSED: Negative price throws exception as expected");
            passedCount++;
        }

        // Test 5: BOGO ซื้อ 1 แถม 1
        ArrayList<CartItem> bogoCart = new ArrayList<>();
        bogoCart.add(new CartItem("BOGO", "Toothbrush", 30.0, 3)); // จ่าย 2 ชิ้น = 60
        double total5 = ShoppingCartCalculator.calculateTotalPrice(bogoCart);
        if (total5 == 60.0) {
            System.out.println("PASSED: BOGO test passed (3 items → pay 2 × 30 = 60)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO expected 60.0 but got " + total5);
            failedCount++;
        }

        // Test 6: BULK ซื้อ 6 ลด 10%
        ArrayList<CartItem> bulkCart = new ArrayList<>();
        bulkCart.add(new CartItem("BULK", "Notebook", 100.0, 6)); // 600 - 10% = 540
        double total6 = ShoppingCartCalculator.calculateTotalPrice(bulkCart);
        if (total6 == 540.0) {
            System.out.println("PASSED: BULK discount applied correctly (6×100 -10%)");
            passedCount++;
        } else {
            System.out.println("FAILED: BULK expected 540.0 but got " + total6);
            failedCount++;
        }

        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}