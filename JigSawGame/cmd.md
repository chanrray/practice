javac -d . src/*.java
java App
jar cvfm puzzle.jar manifest.mf App.class com/ images/

new ImageIcon("./images/background.png")去工作目录下的images文件夹查找
new ImageIcon("images/background.png")同上，隐式写法
new ImageIcon("/images/background.png")去磁盘根目录查找，不使用这个方法
new ImageIcon(getClass().getResource("./images/background.png"))从类路径查找，对应clss文件夹路径
new ImageIcon(getClass().getResource("images/background.png"))同上
new ImageIcon(getClass().getResource("/images/background.png"))类路径的根目录，即CLASSPATH=.，推荐写法