# Test Game

一个可以直接在 IntelliJ IDEA 里运行的 Java 控制台小游戏。

## 怎么运行

1. 用 IDEA 打开仓库。
2. 展开 `test-game/src/main/java/org/example/Main.java`。
3. 点击 `main` 方法左侧的绿色运行按钮。
4. 在底部 Run 控制台输入数字开始游戏。

也可以在终端运行：

```bash
cd test-game
mvn compile exec:java -Dexec.mainClass=org.example.Main
```

## 游戏规则

- 系统会随机生成一个 1 到 100 之间的数字。
- 玩家最多有 7 次机会。
- 每次猜错都会提示太大、太小，以及距离远近。
- 猜中越快，得分越高。
