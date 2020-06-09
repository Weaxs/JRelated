package virtualMachine;

public class StackStruTest {

    /**
     * 基于栈/寄存器的指令集架构
     *
     * 根据以下明显得出：jvm是基于栈的指令集架构
     * 可移植性、易于开发、指令集小
     * 但效率低，执行步骤长
     *
     * javap -v 字节码文件.class
     *
     * public static void main(java.lang.String[]);
     *     descriptor: ([Ljava/lang/String;)V
     *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
     *     Code:
     *       stack=2, locals=4, args_size=1
     *          0: iconst_2
     *          1: istore_1
     *          2: iconst_3
     *          3: istore_2
     *          4: iload_1
     *          5: iload_2
     *          6: iadd
     *          7: istore_3
     *          8: return
     */
    public static void main(String[] args) {
        int i = 2;
        int j = 3;
        int k = i + j;
    }

}
