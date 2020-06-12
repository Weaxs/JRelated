package leetcode._155_minStack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *  
 *
 * 示例:
 *
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 */
public class MinStack {

    /**
     * 不使用辅助栈
     */
    private Deque<Integer> stack;
    private Integer min;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        stack.push(x);
        if (min > x)
            min = x;
    }

    public void pop() {
        Integer x = stack.pop();
        if (x.equals(min))
            min = getMinInStack();

    }

    public int top() {
        if(!stack.isEmpty()){
            return stack.peek();
        }
        throw new RuntimeException("栈中元素为空，此操作非法");
    }

    public int getMin() {
        return min;
    }

    private Integer getMinInStack() {
        Integer min = Integer.MAX_VALUE;
        for (Integer x:stack) {
            if (x < min)
                min = x;
        }
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());


    }

}

/**
 * 辅助栈(同步)
 */
class MinStack2 {

    // 数据栈
    private Deque<Integer> data;
    // 辅助栈
    private Deque<Integer> helper;

    /**
     * initialize your data structure here.
     */
    public MinStack2() {
        data = new LinkedList<>();
        helper = new LinkedList<>();
    }

    // 思路 1：数据栈和辅助栈在任何时候都同步

    public void push(int x) {
        // 数据栈和辅助栈一定会增加元素
        data.add(x);
        if (helper.isEmpty() || helper.peek() >= x) {
            helper.add(x);
        } else {
            helper.add(helper.peek());
        }
    }

    public void pop() {
        // 两个栈都得 pop
        if (!data.isEmpty()) {
            helper.pop();
            data.pop();
        }
    }

    public int top() {
        if(!data.isEmpty()){
            return data.peek();
        }
        throw new RuntimeException("栈中元素为空，此操作非法");
    }

    public int getMin() {
        if(!helper.isEmpty()){
            return helper.peek();
        }
        throw new RuntimeException("栈中元素为空，此操作非法");
    }

}

/**
 * 辅助栈(不同步)
 */
class MinStack3 {
    // 数据栈
    private Deque<Integer> data;
    // 辅助栈
    private Deque<Integer> helper;

    /**
     * initialize your data structure here.
     */
    public MinStack3() {
        data = new LinkedList<>();
        helper = new LinkedList<>();
    }


    public void push(int x) {
        data.add(x);
        // x比辅助栈的栈顶小再压入  否则不压
        if (helper.isEmpty() || helper.peek() >= x) {
            helper.add(x);
        }
    }

    public void pop() {
        if (!data.isEmpty()) {
            int top = data.pop();
            //此次出栈的和辅助栈栈顶相同  则辅助栈也出栈
            if(!helper.isEmpty() && top == helper.peek()){
                helper.pop();
            }
        }
    }

    public int top() {
        if(!data.isEmpty()){
            return data.peek();
        }
        throw new RuntimeException("栈中元素为空，此操作非法");
    }

    public int getMin() {
        if(!helper.isEmpty()){
            return helper.peek();
        }
        throw new RuntimeException("栈中元素为空，此操作非法");
    }
}
