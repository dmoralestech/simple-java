package java_data_structures;

/**
 * Created by darwinmorales on 13/06/2016.
 */
public class ArrayStack<E> implements Stack<E> {

    public static int CAPACITY = 1000;
    private E[] data;
    private int topIndex = -1;

    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public ArrayStack() {
        this(CAPACITY);
    }

    @Override
    public int size() {
        return data.length + 1;
    }

    @Override
    public boolean isEmpty() {
        return data.length < 0;
    }

    @Override
    public void push(E e) throws Exception {
        if (size() == data.length) throw new Exception("Stack is full");
        data[++topIndex] = e;

    }

    @Override
    public E top() {
        return data[topIndex];
    }

    @Override
    public E pop() {
        if (isEmpty()) return null;
        E answer = data[topIndex];
        data[topIndex] = null;
        topIndex--;
        return answer;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack<>();
        try {
            stack.push(3);
            stack.push(4);
            stack.push(6);
            stack.push(9);
            System.out.println(stack.size());
            System.out.println(stack.top());
            System.out.println(stack.size());
            System.out.println(stack.isEmpty());
            stack.pop();
            stack.pop();
            stack.pop();
            stack.pop();
            System.out.println(stack.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
