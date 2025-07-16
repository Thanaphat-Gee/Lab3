package Lib;

import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * เขียน Javadoc ที่นี่เพื่ออธิบายกฎการทำงานและกรณีพิเศษ:
     * - ถ้า items เป็น null หรือ empty จะคืนค่า 0.0
     * - ถ้า CartItem มี price หรือ quantity ติดลบจะทำการแจ้งว่าสินค้าผิดพลาด,Error
     * - ถ้าสินค้ามีรหัส BOGO จะทำการคิดเป็นโปรโมชั่นซื้อ 1 แถม 1
     * - ถ้าสินค้ามีรหัส BULK จะทำการคิดเป็นโปรโมชั่นซื้อมากกว่า 6 ชิ้นจะลดลง 10%
     * @parem หมายเลขชื่อและจำนวนสินค้าภายในตะกร้า
     * @return ราคาของสินค้าที่ซื้อทั้งหมด
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        if (items == null || items.isEmpty()) {
            return 0;
        }
        double total = 0;
        for (CartItem item : items) {
            if (item.price() < 0 || item.quantity() < 0) {
                throw new IllegalArgumentException("ERROR");
            }
            double itemTotal = 0;
            int count = item.quantity();
            double price = item.price();
            String sku = item.sku();
            if ("BOGO".equals(sku)) {
                int free1 = count / 2 + count % 2;
                itemTotal = free1 * price;
            } else if ("BULK".equals(sku)) {
                itemTotal = count * price;
                if (count >= 6) {
                    itemTotal *= 0.9; 
                }
            } else {
                itemTotal = count * price;
            }

            total += itemTotal;
        }

        return total;
    }
}