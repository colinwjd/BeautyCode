package colin.algorithm.tree;

import java.util.*;

/**
 * 二叉树的遍历算法
 * Created by Colin Wang on 2015-04-12.
 */
public class Order {

	// 前序遍历递归算法：先访问根结点，然后访问左子结点，最后访问右子结点
	public void preOrderR(Node<String> root) {
		if (root == null)
			return;
		System.out.print(root.e);
		preOrderR(root.left);
		preOrderR(root.right);
	}

	// 中序遍历递归算法：先访问左子树，然后访问根结点，最后访问右子树
	public void inOrderR(Node<String> root) {
		if (root == null)
			return;
		inOrderR(root.left);
		System.out.print(root.e);
		inOrderR(root.right);

	}

	// 后序遍历递归算法：先访问左子树，然后访问右子树，最后访问根结点
	public void postOrderR(Node<String> root) {
		if (root == null)
			return;
		postOrderR(root.left);
		postOrderR(root.right);
		System.out.print(root.e);
	}

	// 层序遍历算法
	public void leverOrder(Node<String> root) {
		if (root == null)
			return;
		Queue<Node<String>> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node<String> value = queue.poll();
			System.out.print(value.e);
			if (value.left != null)
				queue.add(value.left);
			if (value.right != null)
				queue.add(value.right);
		}
	}

	// 二叉树前序遍历循环算法
	public void preOrder(Node<String> root) {
		Stack<Node<String>> stack = new Stack<>();
		// 当root==null并且stack为空时退出循环，使用了||短路的特性
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				System.out.print(root.e);
				stack.add(root);
				root = root.left;
			}
			if (!stack.isEmpty()) {
				root = stack.pop();
				root = root.right;
			}
		}
	}

	// 二叉树中序遍历非递归算法
	public void inOrder(Node<String> root) {
		Stack<Node<String>> stack = new Stack<>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.add(root);
				root = root.left;
			}
			if (!stack.isEmpty()) {
				root = stack.pop();
				System.out.print(root.e);
				root = root.right;
			}
		}
	}

	public static void main(String[] args) {
		Node<String> one = new Node();
		one.e = "1";
		Node<String> two = new Node();
		two.e = "2";
		Node<String> three = new Node();
		three.e = "3";
		Node<String> six = new Node();
		six.e = "6";
		Node<String> seven = new Node();
		seven.e = "7";

		one.left = two;
		one.right = three;
		three.left = six;
		three.right = seven;

		Order order = new Order();
		order.inOrder(one);

	}
}
