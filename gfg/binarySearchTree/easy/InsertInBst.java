package gfg.binarySearchTree.easy;

public class InsertInBst {

      public static Node insertNodeRecursive(Node root,int x){
          if(root==null){
              return new Node(x);
          } else if (root.data<x) {
              root.right=insertNodeRecursive(root.right,x);
          }else if(root.data>x){
              root.left=insertNodeRecursive(root.left,x);
          }
          return root;
      }

    public static Node insertNodeIterative(Node root,int x) {

          return root;
    }


      public static void main(String[] args) {
          Node root=new Node(10);
          root.left=new Node(5);
          root.right=new Node(15);
          root.right.left=new Node(12);
          root.right.right=new Node(18);
          root=insertNodeRecursive(root,20);
          inorder(root);

      }

    public static void inorder(Node root){
        if(root!=null){
            inorder(root.left);
            System.out.print(root.data+" ");
            inorder(root.right);
        }
    }
}
