/**
 * 反转单链表就像把一列火车倒着开——原本车头变车尾，每节车厢都要调头指向前面的车厢！
 */
fun main() {
    // 创建链表 1 → 2 → 3
    val node3 = Node2(3)
    val node2 = Node2(2, node3)
    val node1 = Node2(1, node2)

    // 反转并打印
    var newHead = reverseListIterative(node1)
    printList(newHead) // 输出：3 → 2 → 1 → null

    // 递归法测试（需重新构建链表）
    val node3Rec = Node2(3)
    val node2Rec = Node2(2, node3Rec)
    val node1Rec = Node2(1, node2Rec)
    newHead = reverseListRecursive(node1Rec)
    printList(newHead) // 输出同上
}

// 辅助打印函数
fun printList(head: Node2?) {
    var curr = head
    while (curr != null) {
        print("${curr.data} → ")
        curr = curr.next
    }
    println("null")
}

class Node2(var data: Int, var next: Node2? = null)

fun reverseListIterative(head: Node2?): Node2? {
    var prev: Node2? = null   // 前一个节点（初始为空）
    var curr = head          // 当前节点（从头开始）

    while (curr != null) {
        val nextTemp = curr.next // 暂存下一个节点（防止断链）
        curr.next = prev         // 调头指向前一个
        prev = curr             // 前节点前进
        curr = nextTemp         // 当前节点前进
    }

    // 错误写法！
//    while (curr != null) {
//        curr.next = prev // 此时curr.next已经被覆盖，无法继续遍历
//        prev = curr
//        curr = curr.next // 这里curr.next已经是prev了，导致死循环
//    }

    return prev // 最后prev就是新头
}


fun reverseListRecursive(head: Node2?): Node2? {
    if (head?.next == null) return head // 终止条件：只剩一个节点

    val newHead = reverseListRecursive(head.next) // 递归到最深处
    head.next?.next = head // 反转指向（让后一个节点指回自己）
    head.next = null       // 断开原方向

    return newHead
}