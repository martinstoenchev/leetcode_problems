import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class Main {

    /*static class Node {
        int data;
        Node next;
        Node bottom;

        Node(int data) {
            this.data = data;
            next = null;
            bottom = null;
        }
    }*/

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.data);
            head = head.next;
            if (head != null) {
                System.out.print("->");
            }
        }
        System.out.println();
    }

    /*public static void printLinkedListWithBottomElements(Node head) {
        while (head != null) {
            System.out.print(head.data);
            head = head.bottom;
            if (head != null) {
                System.out.print("->");
            }
        }
        System.out.println();
    }*/

    public static int getMiddle(Node head) {
        int n = 0;
        Node result = head;

        while (head != null) {
            n++;
            head = head.next;
        }

        int mid = n / 2;

        for (int i = 0; i < mid; i++) {
            if (result != null) {
                result = result.next;
            }
        }

        return result != null ? result.data : 0;
    }

    public static Node reverseList(Node head) {
        Node prevHead = null;
        Node nextHead = head.next;

        while (head != null) {
            head.next = prevHead;
            prevHead = head;
            head = nextHead;
            if (head != null) {
                nextHead = head.next;
            }
        }

        return prevHead;
    }

    public static Node rotate(Node head, int k) {
        Node start = head;

        for (int i = 0; i < k - 1; i++) {
            head = head.next;
        }

        if (head.next == null) {
            return start;
        }

        Node head2 = head.next;
        head.next = null;
        Node start2 = head2;

        while (head2.next != null) {
            head2 = head2.next;
        }

        head2.next = start;

        return start2;
    }

    private static Node appendLinkedLists(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }

        Node result = head1;

        while (head1.next != null) {
            head1 = head1.next;
        }

        head1.next = head2;

        return result;
    }

    public static Node reverse(Node head, int k) {
        int i;
        Node result = null, start, end, toAppend;

        while (head != null) {
            start = head;
            i = 0;
            while (head != null && i < k - 1) {
                head = head.next;
                i++;
            }
            end = head;
            if (head != null) {
                head = head.next;
                end.next = null;
            }
            toAppend = reverseList(start);
            result = appendLinkedLists(result, toAppend);
        }

        return result;
    }

    public static int intersectPoint(Node head1, Node head2) {
        int c1 = 0, c2 = 0;
        Node start1 = head1;
        Node start2 = head2;

        while (start1 != null) {
            c1++;
            start1 = start1.next;
        }

        while (start2 != null) {
            c2++;
            start2 = start2.next;
        }

        if (c1 > c2) {
            int c = c1 - c2;

            while (c > 0) {
                if (head1 != null) {
                    head1 = head1.next;
                }
                c--;
            }
        } else {
            int c = c2 - c1;

            while (c > 0) {
                if (head2 != null) {
                    head2 = head2.next;
                }
                c--;
            }
        }

        while (head1 != null && head2 != null) {
            if (head1 == head2) {
                return head1.data;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return -1;
    }

    public static boolean detectLoop(Node head) {
        Node turtle = head;
        Node rabbit = head.next;

        while (turtle != null && rabbit != null) {
            if (turtle == rabbit) {
                return true;
            }

            turtle = turtle.next;
            if (rabbit.next != null) {
                rabbit = rabbit.next.next;
            } else {
                return false;
            }
        }

        return false;
    }

    public static void removeLoop(Node head) {
        Node start = head;
        if (!detectLoop(start)) {
            return;
        }

        return;
    }

    public static int getNthFromLast(Node head, int n) {
        Node last = reverseList(head);

        while (n > 1 && last != null) {
            last = last.next;
            n--;
        }

        if (last == null) {
            return -1;
        }

        return last.data;
    }

    /*private static Node mergeSortedLinkedListsWithBottomElements(Node head1, Node head2) {
        if (head2 == null) {
            return head1;
        }
        Node head, start;

        if (head1.data <= head2.data) {
            head = head1;
            head1 = head1.bottom;
        } else {
            head = head2;
            head2 = head2.bottom;
        }

        start = head;

        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                head.bottom = head1;
                head1 = head1.bottom;
            } else {
                head.bottom = head2;
                head2 = head2.bottom;
            }
            head = head.bottom;
        }

        if (head1 == null) {
            head.bottom = head2;
        } else {
            head.bottom = head1;
        }

        return start;
    }*/

    /*public static Node flatten(Node head) {
        Node merge = head;

        while (head.next != null) {
            merge = mergeSortedLinkedListsWithBottomElements(head, head.next);
            head = head.next;
            merge.next = head.next;
            head = merge;
        }

        return merge;
    }*/

    public static Node sortedMerge(Node head1, Node head2) {
        if (head2 == null) {
            return head1;
        }
        Node head, start;

        if (head1.data <= head2.data) {
            head = head1;
            head1 = head1.next;
        } else {
            head = head2;
            head2 = head2.next;
        }

        start = head;

        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                head.next = head1;
                head1 = head1.next;
            } else {
                head.next = head2;
                head2 = head2.next;
            }
            head = head.next;
        }

        if (head1 == null) {
            head.next = head2;
        } else {
            head.next = head1;
        }

        return start;
    }

    static class QueueNode {
        int data;
        QueueNode next;

        QueueNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class MyQueue {
        int size;
        QueueNode front, rear;

        MyQueue() {
            front = null;
            rear = null;
            size = 0;
        }

        void push(int a) {
            QueueNode node = new QueueNode(a);
            if (size == 0) {
                front = node;
                rear = node;
            } else {
                rear.next = node;
                rear = rear.next;
            }
            size++;
        }

        int pop() {
            if (size == 0) {
                return -1;
            }

            int res = front.data;
            if (size == 1) {
                front = null;
                rear = null;
            } else {
                front = front.next;
            }
            size--;
            return res;
        }
    }

    static class StackNode {
        int data;
        StackNode next;

        StackNode(int data) {
            this.data = data;
            next = null;
        }
    }

    static class MyStack {
        int size;
        StackNode top;

        void push(int a) {
            StackNode node = new StackNode(a);
            if (size != 0) {
                node.next = top;
            }
            top = node;
            size++;
        }

        int pop() {
            if (size == 0) {
                return -1;
            }

            int res = top.data;
            if (size == 1) {
                top = null;
            } else {
                top = top.next;
            }
            size--;
            return res;
        }
    }

    public static Node segregate(Node head) {
        int[] numbers = new int[3];

        while (head != null) {
            numbers[head.data]++;
            head = head.next;
        }

        int i = -1;
        for (int j = 0; j < 3; j++) {
            if (numbers[j] > 0) {
                i = j;
                break;
            }
        }

        Node start = null;
        Node curr = null;
        boolean noFirstEl = true;
        
        for (int j = 0; j < numbers[0]; j++) {
            if (noFirstEl) {
                start = new Node(0);
                noFirstEl = false;
                curr = start;
                continue;
            }
            curr.next = new Node(0);
            curr = curr.next;
        }

        for (int j = 0; j < numbers[1]; j++) {
            if (noFirstEl) {
                start = new Node(1);
                noFirstEl = false;
                curr = start;
                continue;
            }
            curr.next = new Node(1);
            curr = curr.next;
        }

        for (int j = 0; j < numbers[2]; j++) {
            if (noFirstEl) {
                start = new Node(2);
                noFirstEl = false;
                curr = start;
                continue;
            }
            curr.next = new Node(2);
            curr = curr.next;
        }

        return start;
    }

    public static void deleteNode(Node del) {
        del.data = del.next.data;
        del.next = del.next.next;
    }

    public static boolean isPalindrome(Node head) {
        int cnt = 0;
        Node tmp = head;

        while (tmp != null) {
            tmp = tmp.next;
            cnt++;
        }

        while (head != null) {
            Node last = head;
            for (int i = 0; i < cnt - 1; i++) {
                last = last.next;
            }

            if (head.data != last.data) {
                return false;
            }

            head = head.next;
            cnt--;
        }

        return true;
    }

    public static boolean findSum(int[] arr,int n) {
        int sum = 0;
        Set<Integer> sums = new HashSet<>();

        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (sum == 0 || arr[i] == 0 || sums.contains(sum)) {
                return true;
            }

            sums.add(sum);
        }

        return false;
    }

    private static int numberOfDigits(long n) {
        int count = 0;

        while (n != 0) {
            count++;
            n /= 10;
        }

        return count;
    }

    public static Node addTwoListsForShortNumbers(Node first, Node second){
        long firstNumber = 0;
        long secondNumber = 0;

        while (first != null) {
            firstNumber = firstNumber * 10 + first.data;
            first = first.next;
        }

        while (second != null) {
            secondNumber = secondNumber * 10 + second.data;
            second = second.next;
        }

        firstNumber += secondNumber;
        int n = numberOfDigits(firstNumber);
        int[] digits = new int[n];

        for (int i = n - 1; i >= 0 ; i--) {
            digits[i] = (int) (firstNumber % 10);
            firstNumber /= 10;
        }

        Node result = new Node(digits[0]);
        Node start = result;

        for (int i = 1; i < n; i++) {
            Node tmp = new Node(digits[i]);
            start.next = tmp;
            start = start.next;
        }

        return result;
    }

    public static Node addTwoLists(Node first, Node second) {
        Node reversedFirst = reverseList(first);
        Node reversedSecond = reverseList(second);

        int n = reversedFirst.data + reversedSecond.data;
        Node result;
        int plusOne = 0;

        if (n > 9) {
            result = new Node(n % 10);
            plusOne = 1;
        } else {
            result = new Node(n);
        }

        Node start = result;
        reversedFirst = reversedFirst.next;
        reversedSecond = reversedSecond.next;

        while (reversedFirst != null && reversedSecond != null) {
            n = reversedFirst.data + reversedSecond.data + plusOne;
            if (n > 9) {
                start.next = new Node(n % 10);
                start = start.next;
                plusOne = 1;
            } else {
                start.next = new Node(n);
                start = start.next;
                plusOne = 0;
            }
            reversedFirst = reversedFirst.next;
            reversedSecond = reversedSecond.next;
        }

        Node tail = reversedFirst == null ? reversedSecond : reversedFirst;

        if (tail == null && plusOne > 0) {
            start.next = new Node(plusOne);
            start = start.next;
            plusOne = 0;
        } else {
            while (tail != null) {
                n = tail.data + plusOne;
                if (n > 9) {
                    start.next = new Node(n % 10);
                    start = start.next;
                    plusOne = 1;
                } else {
                    start.next = new Node(n);
                    start = start.next;
                    plusOne = 0;
                }

                tail = tail.next;
            }
        }

        if (plusOne > 0) {
            start.next = new Node(plusOne);
        }

        return reverseList(result);
    }

    public static Node pairwiseSwap(Node head) {
        Node tmp, result = null, prev = null;
        boolean firstEntering = true;

        while (head != null && head.next != null) {
            tmp = head.next;
            head.next = tmp.next;
            tmp.next = head;

            if (firstEntering) {
                result = tmp;
                firstEntering = false;
            } else {
                prev.next = tmp;
            }
            prev = head;
            head = head.next;
        }

        return result == null ? head : result;
    }

    public static Node findIntersection(Node head1, Node head2) {
        Set<Integer> hs = new HashSet<>();

        while (head2 != null) {
            hs.add(head2.data);
            head2 = head2.next;
        }

        Node result = null, head = null;
        boolean firstEntering = true;

        while (head1 != null) {
            if (hs.contains(head1.data)) {
                if (firstEntering) {
                    head = new Node(head1.data);
                    result = head;
                    firstEntering = false;
                } else {
                    head.next = new Node(head1.data);
                    head = head.next;
                }
            }

            head1 = head1.next;
        }

        return result;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);

        /*node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;*/

        /*printLinkedList(node1);
        Node result = reverseList(node1);
        printLinkedList(result);*/

        /*Node result = rotate(node1, 9);
        printLinkedList(result);*/

        /*Node result = appendLinkedLists(node1, node6);
        printLinkedList(result);*/

        /*Node result = reverse(node1, 3);
        printLinkedList(result);*/

        /*node1.bottom = node3;
        node2.bottom = node4;
        node3.bottom = node5;
        node4.bottom = node6;
        node5.bottom = node7;
        node6.bottom = node8;
        node7.bottom = node9;

        printLinkedListWithBottomElements(node1);
        printLinkedListWithBottomElements(node2);
        Node result = mergeSortedLinkedListsWithBottomElements(node2, node1);
        printLinkedListWithBottomElements(result);*/

        // 1 2 1 3 2 1 4 2
        /*MyQueue q = new MyQueue();
        q.push(2);
        q.push(3);
        System.out.println(q.pop());
        q.push(4);
        System.out.println(q.pop());*/


    }

}
