package java_scratchpad;

/**
 * Created by darwinmorales on 26/07/2016.
 */
public class Stack<E> {
    private int top = -1;
    private E[] data;


    public Stack(int size) {
        data = (E[]) new Object[size];
    }

    public void push(E e) {
        if (top == data.length - 1) {
            return;
        }
        data[++top] = e;
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E temp = data[top];
        data[top] = null;
        top--;
        return temp;
    }

    public boolean isEmpty() {
        return top < 0;
    }


    public static void main(String[] args) {
        Stack<String> stack = new Stack<>(3);

        System.out.println( stack.isEmpty());

        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        System.out.println( stack.isEmpty());


    }

}
