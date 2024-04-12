package Stack;

import java.util.Stack;

public class Monotonic {

    public Stack<Integer> inc(int[] arr){
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++){
            while(!st.isEmpty() && st.peek() < arr[i]){
                st.pop();
            }
            st.push(arr[i]);
        }
        return st;
    }

    public Stack<Integer> dec(int[] arr){
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++){
            while(!st.isEmpty() && st.peek() > arr[i]){
                st.pop();
            }
            st.push(arr[i]);
        }
        return st;
    }

}
