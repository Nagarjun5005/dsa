package stack.medium;

import java.util.*;

/*
 =============================================================================
 PROBLEM:
 Asteroid Collision (LeetCode 735)

 We are given an array of integers representing asteroids in a row.

 - Positive value  -> asteroid moving to the RIGHT
 - Negative value  -> asteroid moving to the LEFT
 - Absolute value  -> size of the asteroid

 All asteroids move at the same speed.

 COLLISION RULES:
 - A collision can happen ONLY when a right-moving asteroid (+)
   meets a left-moving asteroid (-).
 - The smaller asteroid explodes.
 - If both have the same size, both explode.
 - Asteroids moving in the same direction never collide.

 GOAL:
 Return the state of the asteroids after all collisions.

 -----------------------------------------------------------------------------
 EXAMPLE:
 Input:  [4, 7, 1, 1, 2, -3, -7, 17, 15, -18, -19]
 Output: [-19]

 =============================================================================
*/

class AsteroidCollision {

    /*
     =========================================================================
     MAIN METHOD
     Used for local testing
     =========================================================================
    */
    public static void main(String[] args) {
        int[] nums = {4, 7, 1, 1, 2, -3, -7, 17, 15, -18, -19};
        int[] result = asteroidCollision(nums);
        System.out.println(Arrays.toString(result));
    }

    /*
     =========================================================================
     APPROACH: STACK AS SIMULATION / CONFLICT RESOLUTION

     PATTERN USED:
     - Stack-based simulation (NOT monotonic stack)

     WHY STACK?
     - We need to compare the current asteroid with the most recent
       surviving asteroid.
     - Stack naturally keeps track of "what is in front".

     KEY INSIGHT (VERY IMPORTANT):
     A collision can happen ONLY when:
         stack.peek() > 0   (right-moving asteroid)
         AND
         current asteroid < 0 (left-moving asteroid)

     No other situation can cause a collision.

     TIME COMPLEXITY:
     - O(n)
       Each asteroid is pushed and popped at most once.

     SPACE COMPLEXITY:
     - O(n)
       Stack can store all asteroids in the worst case.
     =========================================================================
    */
    public static int[] asteroidCollision(int[] asteroids) {

        Stack<Integer> stack = new Stack<>();

        /*
         Iterate through each asteroid one by one
        */
        for (int ast : asteroids) {

            // Flag to check whether current asteroid gets destroyed
            boolean destroyed = false;

            /*
             Handle collisions:
             - Only when top of stack is moving right (> 0)
             - And current asteroid is moving left (< 0)
            */
            while (!stack.isEmpty() && stack.peek() > 0 && ast < 0) {

                int top = stack.peek();

                /*
                 Case 1:
                 Top asteroid is smaller -> it explodes
                 Current asteroid may continue to collide further
                */
                if (Math.abs(top) < Math.abs(ast)) {
                    stack.pop();
                    continue;
                }

                /*
                 Case 2:
                 Both asteroids are equal in size -> both explode
                */
                else if (Math.abs(top) == Math.abs(ast)) {
                    stack.pop();
                    destroyed = true;
                    break;
                }

                /*
                 Case 3:
                 Top asteroid is larger -> current asteroid explodes
                */
                else {
                    destroyed = true;
                    break;
                }
            }

            /*
             If the current asteroid was not destroyed,
             it survives and is pushed onto the stack
            */
            if (!destroyed) {
                stack.push(ast);
            }
        }

        /*
         Convert stack to array.
         Stack is LIFO, so we pop elements in reverse order.
        */
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }
}
