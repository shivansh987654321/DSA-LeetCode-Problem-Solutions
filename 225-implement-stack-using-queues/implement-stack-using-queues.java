import java.util.*;

class MyStack {

    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack() {

        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();

        System.out.println("Stack Created");
        System.out.println("q1 = " + q1);
        System.out.println("q2 = " + q2);
        System.out.println("--------------------------------");
    }
    
    public void push(int x) {

        System.out.println("PUSH OPERATION STARTED FOR : " + x);

        // Step 1
        // New element ko q2 me daalo
        q2.offer(x);

        System.out.println("Step 1 -> Added element into q2");
        System.out.println("q1 = " + q1);
        System.out.println("q2 = " + q2);

        // Step 2
        // q1 ke saare old elements q2 me shift karo
        while(!q1.isEmpty()) {

            int removed = q1.poll();

            System.out.println("Removed from q1 : " + removed);

            q2.offer(removed);

            System.out.println("Added into q2 : " + removed);

            System.out.println("Current q1 = " + q1);
            System.out.println("Current q2 = " + q2);
        }

        // Step 3
        // q1 aur q2 ko swap karo
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;

        System.out.println("Step 3 -> Swapped q1 and q2");

        System.out.println("Final q1 = " + q1);
        System.out.println("Final q2 = " + q2);

        System.out.println("PUSH OPERATION COMPLETED");
        System.out.println("--------------------------------");
    }
    
    public int pop() {

        System.out.println("POP OPERATION STARTED");

        int removed = q1.poll();

        System.out.println("Removed Element : " + removed);

        System.out.println("Current q1 = " + q1);

        System.out.println("POP OPERATION COMPLETED");
        System.out.println("--------------------------------");

        return removed;
    }
    
    public int top() {

        System.out.println("TOP OPERATION");

        int topElement = q1.peek();

        System.out.println("Top Element : " + topElement);

        System.out.println("--------------------------------");

        return topElement;
    }
    
    public boolean empty() {

        System.out.println("EMPTY OPERATION");

        boolean ans = q1.isEmpty();

        System.out.println("Is Stack Empty ? " + ans);

        System.out.println("--------------------------------");

        return ans;
    }
}