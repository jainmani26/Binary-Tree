import java.io.*;
import java.util.*;

public class Main {
  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }

//   public static void iterativePrePostInTraversal(Node root)
//   {
//         Stack<Pair> stk = new Stack<>();
//         stk.push(new Pair(root, -1));
//         int idx = 0;
        
//         while(!stk.isEmpty())
//         {
            
//             Pair par = stk.peek();
            
//             if(par.state == -1){
//                 // preorder
//                 idx++;
                
//                 if(arr[idx] != null){
//                     Node child = new Node(arr[idx]);
//                     par.node.left = child;
//                     stk.push(new Pair(child, -1));
//                 }
//                 par.state++;
                
//             } else if(par.state == 0){
//                 // inorder
//                 idx++;
                
//                 if(arr[idx] != null){
//                     Node child = new Node(arr[idx]);
//                     par.node.right = child;
//                     stk.push(new Pair(child, -1));
//                 }
//                 par.state++;
                
//             } else if(par.state == 1){
//                 // postorder
//                 stk.pop();
//             }
//         }
//   }
  
  public static void iterativePrePostInTraversal(Node root)
  {
      Stack<Pair> stk = new Stack<>();
      stk.push(new Pair (root , -1));
      
      ArrayList <Integer> preorder = new ArrayList<>();
      ArrayList <Integer> inorder = new ArrayList<>();
      ArrayList <Integer> postorder = new ArrayList<>();
      
      while(!stk.isEmpty())
      {
          Pair par = stk.peek();
          if(par.state == -1)
          {
              //preorder
              preorder.add(par.node.data);
              if(par.node.left != null)
              {
                  stk.push(new Pair (par.node.left , -1));
              }
              par.state++;
          }
          else if(par.state == 0)
          {
              //inorder
              inorder.add(par.node.data);
              if(par.node.right != null)
              {
                  stk.push(new Pair (par.node.right , -1));
              }
              par.state++;
          }
          else if(par.state == 1)
          {
              postorder.add(par.node.data);
              stk.pop();
          }
          
      }
      
      for(Integer i : preorder) System.out.print(i+" ");
      System.out.println();
      for(Integer i : inorder) System.out.print(i+" ");
      System.out.println();
      for(Integer i : postorder) System.out.print(i+" ");
      System.out.println();
  }
  

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    Node root = construct(arr);
    iterativePrePostInTraversal(root);
  }

}
