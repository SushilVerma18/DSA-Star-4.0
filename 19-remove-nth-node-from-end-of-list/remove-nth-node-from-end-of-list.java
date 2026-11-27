/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = 0;
        ListNode temp = head;
        while(temp != null){
            temp = temp.next;
            size++;
        }

        if(size == n){
           return head.next;
        }

        temp = head;
        int indx = 0;
       
        while(temp != null && temp.next != null && indx < size - n - 1){
            temp = temp.next;
            indx++;
       }

       if(temp.next != null){
        temp.next = temp.next.next;
       }

       return head;
    }
    
}