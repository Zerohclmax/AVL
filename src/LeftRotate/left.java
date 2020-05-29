package LeftRotate;

public class left {
    public static void main(String[] args) {
//        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        int[] arr={4,3,6,5,7,8};
        BinarTree tree = new BinarTree();
        for (int i : arr) {
            Node node = new Node(i);
            tree.add(node);
        }
        tree.in_orde();
        System.out.println(tree.root.height());
        System.out.println(tree.root.left());
        System.out.println(tree.root.Right());
//        tree.delnode(10);
//        tree.delnode(1);
//        System.out.println();
//        tree.in_orde();
    }
}


class BinarTree {
    Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
        if ((root.left()-root.Right())>1){//左旋
            LeftRotate();
        }
        if ((root.Right()-root.left())>1){//右旋
            RightRotate();
        }
    }
    public void LeftRotate(){//右旋
        Node node = new Node(this.root.value);
        node.Right=root.Right;
        node.Left=root.Left.Right;
        root.value=root.Left.value;
        root.Right=node;
        root.Left=root.Left.Left;
    }
    public void RightRotate(){//左旋
        Node node = new Node(this.root.value);
        node.Left=this.root.Left;
        node.Right=root.Right.Left;
        root.value=root.Right.value;
        root.Left=node;
        root.Right=root.Right.Right;

    }

    public void in_orde() {
        if (root == null) {
            System.out.println("树为空");
        } else {
            root.in_order();
        }
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return this.root.search(value);
        }
    }

    public Node getparent(int value) {
        if (root == null) {
            return null;
        } else {
            return this.root.getparent(value);
        }
    }
    public int findminRight(Node node){//node就是删除节点的right
        if (node.Left != null){
            return findminRight(node.Left);
        }
        delnode(node.value);
        return node.value;
    }

    public void delnode(int value) {
        Node temp = root.search(value);
        if(temp==null){return ;}
        if (root.Left==null&&root.Right==null){root=null;return ;}
        if (temp.Right == null && temp.Left == null){
            Node parent=root.getparent(value);
            if (parent.Right.value==value){
                parent.Right=null;
            }else{
                parent.Left=null;
            }
        }else if(temp.Right != null && temp.Left != null){
            int minLeft = findminRight(temp.Right);
            temp.value=minLeft;
        }else{
            Node parent=root.getparent(value);
            if(parent==null){
                if (parent.Left != null){
                    root=parent.Left;
                }else{
                    root=parent.Right;
                }
            }
            if (parent.Left.value==value && parent.Left.Left !=null){
                parent.Left=parent.Left.Left;
            }else if (parent.Left.value==value && parent.Left.Right !=null){
                parent.Left=parent.Left.Right;
            }else if(parent.Right.value==value && parent.Left.Left !=null){
                parent.Right=parent.Left.Left;
            }else {
                parent.Right=parent.Left.Right;
            }
        }
    }
    public int height(){
        if (root==null){
            return 0;
        }else{
            return root.height();
        }
    }
    public int left(){
        if (root==null){
            return 0;
        }else{
            return root.left();
        }
    }
    public int right(){
        if (root==null){
            return 0;
        }else{
           return root.Right();
        }
    }
}

class Node {
    int value;
    Node Left;
    Node Right;

    public Node(int value) {
        this.value = value;
    }
    public int height(){
        return Math.max(Left==null?0:Left.height(),Right==null?0:Right.height())+1;
    }
    public int left(){
        if (Left==null){
            return 0;
        }else{
            return Left.height()+1;
        }
    }
    public int Right(){
        if(Right==null){
            return 0;
        }else{
            return Right.height()+1;
        }
    }


    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else {
            if (value > this.value) {
                return this.Right.search(value);
            } else if (value < this.value) {
                return this.Left.search(value);
            }
        }
        return null;
    }

    public Node getparent(int value) {
        if ((this.Left != null && this.Left.value == value) || (this.Right != null && this.Right.value == value)) {
            return this;
        } else {
            if (this.value > value && this.Left != null) {
                return this.Left.getparent(value);
            } else if (this.value < value && this.Right != null) {
                return this.Right.getparent(value);
            } else {
                return null;
            }
        }
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (this.value > node.value) {
            if (this.Left == null) {
                this.Left = node;
            } else {
                this.Left.add(node);
            }
        }
        if (this.value < node.value) {
            if (this.Right == null) {
                this.Right = node;
            } else {
                this.Right.add(node);
            }
        }

    }

    @Override
    public String toString() {
        return "Node{" +
                value +
                '}';
    }

    public void in_order() {
        if (this.Left != null) {
            this.Left.in_order();
        }
        System.out.println(this);
        if (this.Right != null) {
            this.Right.in_order();
        }
    }
}
