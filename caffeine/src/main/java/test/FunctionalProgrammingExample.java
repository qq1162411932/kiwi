package test;

@FunctionalInterface
interface Calculator {
    double calculate(double x, double y);
}

public class FunctionalProgrammingExample {

    public static void main(String[] args) {
        Calculator add = (x, y) -> x + y; // 使用Lambda表达式实现加法
        Calculator subtract = (x, y) -> x - y; // 使用Lambda表达式实现减法

        double result1 = calculate(add, 10, 5); // 计算 10 + 5
        double result2 = calculate(subtract, 10, 5); // 计算 10 - 5

        System.out.println("Result1 = " + result1);
        System.out.println("Result2 = " + result2);
    }

    public static double calculate(Calculator calculator, double x, double y) {
        return calculator.calculate(x, y); // 调用Calculator接口中的抽象方法计算结果
    }
}
