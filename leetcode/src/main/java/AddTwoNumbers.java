import java.math.BigInteger;

public class AddTwoNumbers {
    public static void main(String[] args) {
        // Example usage
        ListNode l1 = getListNode(BigInteger.valueOf(342).toString()); // represents the number 342
        ListNode l2 = getListNode(BigInteger.valueOf(465).toString()); // represents the number 465
        ListNode result = addTwoNumbers2(l1, l2);

        // Print result
        while (result != null) {
            System.out.print(result.val) ;
            result = result.next;
        }

    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger number1 = getNumber(l1);
        BigInteger number2 = getNumber(l2);
        BigInteger sum = number1.add(number2);
        return getListNode(sum.toString());
    }

    private static BigInteger getNumber(ListNode node) {
        StringBuilder num = new StringBuilder();
        num.append(node.val);
        while (node.next != null) {
            node = node.next;
            num.append(node.val);
        }
        String line = num.reverse().toString();
        return new BigInteger(line);
    }

    private static ListNode getListNode(String num) {
        if (num == null || num.isEmpty()) {
            return null;
        }
        if (num.length() == 1) {
            return new ListNode(Integer.parseInt(num));
        }
        int lastIndex = num.length() - 1;
        return new ListNode(
                Integer.parseInt(String.valueOf(num.charAt(lastIndex))),
                getListNode(num.substring(0, lastIndex))
        );
    }

    private static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode fakeHead = new ListNode();
        ListNode currentNode = fakeHead;
        int carry = 0;
        int sum = 0;
        while (l1 != null || l2 != null || carry != 0) {
            sum += (l1 != null) ? l1.val : 0;
            sum += (l2 != null) ? l2.val : 0;
            sum += carry;
            currentNode.next = new ListNode(sum % 10);
            currentNode = currentNode.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            carry = sum / 10;
            sum = 0;
        }
        return fakeHead.next;
    }

    public static class ListNode {

      int val;
      ListNode next;


      ListNode() {}

      ListNode(int val) {
          this.val = val;
      }

      ListNode(int val, ListNode next) {
          this.val = val;
          this.next = next;
      }

  }

}
