package ch04.Algorithm_11;
// Practice2
// 2차원 정수형 배열 lists 가 주어졌다.
// lists[i] 에는 각 링크드 리스트의 원소 정보가 들어 있고,
// 원소들은 오름차순 정렬된 상태이다.
// 모든 링크드 리스트를 하나의 정렬된 링크드 리스트로 합병하세요.

// 입출력 예시
// lists: {{2, 3, 9}, {1, 5, 7}, {3, 6, 7, 11}}
// 출력: 1 -> 2 -> 3 -> 3 -> 5 -> 6 -> 7 -> 7 -> 9 -> 11


class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
        this.next = null;
    }
}

public class Practice2 {
    public static Node solution(Node[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        return divideList(lists, 0, lists.length - 1);
    }

    public static Node divideList(Node[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }

        int mid = left + (right - left) / 2;

        Node a = divideList(arr, left, mid);
        Node b = divideList(arr, mid + 1, right);


        return mergeNode(a, b);
    }

    public static Node mergeNode(Node a, Node b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        Node cur = new Node(0);
        Node r = cur;
        while (a != null && b != null) {
            if (a.val < b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }

        if (a == null) {
            cur.next = b;
        } else {
            cur.next = a;
        }

        return r.next;
    }


    // 문제에 주어진 2차원 배열을 링크드 리스트로 구성
    public static void setUpLinkedList(Node[] node, int[][] lists) {
        for (int i = 0; i < lists.length; i++) {
            node[i] = new Node(lists[i][0]);
        }

        for (int i = 0; i < lists.length; i++) {
            Node cur = node[i];
            for (int j = 1; j < lists[i].length; j++) {
                cur.next = new Node(lists[i][j]);
                cur = cur.next;
            }
        }
    }

    // 결과 출력 부분
    public static void printList(Node node) {
        Node cur = node;
        while (cur.next != null) {
            System.out.print(cur.val + " -> ");
            cur = cur.next;
        }
        System.out.println(cur.val);
    }

    public static void main(String[] args) {
        // Test code
        int[][] lists = {{2, 3, 9}, {1, 5, 7}, {3, 6, 7, 11}};
        Node[] node = new Node[lists.length];
        setUpLinkedList(node, lists);
        Node combinedNode = solution(node);
        printList(combinedNode);
    }
}