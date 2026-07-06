package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_ATTEMPTS = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        printWelcome();

        boolean playAgain;
        do {
            playOneRound(scanner, random);
            playAgain = askYesOrNo(scanner, "再来一局吗？输入 y 继续，其他键退出：");
        } while (playAgain);

        System.out.println();
        System.out.println("游戏结束，感谢游玩。把你的最高分截图发给组员比一比吧！");
    }

    private static void playOneRound(Scanner scanner, Random random) {
        int answer = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        int score = 100;
        int previousGuess = -1;

        System.out.println();
        System.out.println("我已经想好了一个 " + MIN_NUMBER + " 到 " + MAX_NUMBER + " 之间的数字。");
        System.out.println("你有 " + MAX_ATTEMPTS + " 次机会，越快猜中分数越高。");

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            int guess = readNumber(scanner, "第 " + attempt + " 次，请输入你的猜测：");

            if (guess == answer) {
                int finalScore = Math.max(score, 10);
                System.out.println("猜中了！答案就是 " + answer + "。");
                System.out.println("本局得分：" + finalScore);
                printRank(finalScore, attempt);
                return;
            }

            if (guess < answer) {
                System.out.println("太小了。");
            } else {
                System.out.println("太大了。");
            }

            printDistanceHint(answer, guess, previousGuess);
            previousGuess = guess;
            score -= 12;
        }

        System.out.println();
        System.out.println("机会用完了，本局答案是 " + answer + "。");
        System.out.println("下次可以先从 50 附近开始，用二分法一点点缩小范围。");
    }

    private static int readNumber(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int number = Integer.parseInt(input);
                if (number >= MIN_NUMBER && number <= MAX_NUMBER) {
                    return number;
                }
                System.out.println("请输入 " + MIN_NUMBER + " 到 " + MAX_NUMBER + " 之间的整数。");
            } catch (NumberFormatException e) {
                System.out.println("这不是有效数字，请重新输入。");
            }
        }
    }

    private static boolean askYesOrNo(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }

    private static void printDistanceHint(int answer, int guess, int previousGuess) {
        int distance = Math.abs(answer - guess);

        if (distance <= 5) {
            System.out.println("提示：非常接近了！");
        } else if (distance <= 15) {
            System.out.println("提示：已经比较接近。");
        } else {
            System.out.println("提示：还差得有点远。");
        }

        if (previousGuess != -1) {
            int previousDistance = Math.abs(answer - previousGuess);
            if (distance < previousDistance) {
                System.out.println("方向不错，比上一次更接近。");
            } else if (distance > previousDistance) {
                System.out.println("离答案更远了，换个方向试试。");
            }
        }
    }

    private static void printRank(int score, int attempt) {
        if (attempt == 1) {
            System.out.println("评级：天选之手，一发入魂！");
        } else if (score >= 76) {
            System.out.println("评级：高手，很稳。");
        } else if (score >= 52) {
            System.out.println("评级：不错，思路在线。");
        } else {
            System.out.println("评级：惊险过关。");
        }
    }

    private static void printWelcome() {
        System.out.println("================================");
        System.out.println("        数字猜谜挑战赛");
        System.out.println("================================");
        System.out.println("规则：猜出系统随机生成的数字。");
        System.out.println("范围：" + MIN_NUMBER + " - " + MAX_NUMBER);
        System.out.println("次数：" + MAX_ATTEMPTS + " 次");
    }
}
