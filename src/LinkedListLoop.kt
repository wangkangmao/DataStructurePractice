/**
 * 判断链表是否成环就像追人游戏——要么用“记脚印法”（哈希表），要么用“龟兔赛跑法”（快慢指针）！
 */
fun main() {
    // 测试1：无环链表 1 → 2 → 3
    val node3 = ListNode(3)
    val node2 = ListNode(2, node3)
    val node1 = ListNode(1, node2)
    println(hasCycleFloyd(node1)) // false

    // 测试2：有环链表 1 → 2 → 3 → 2
    node3.next = node2 // 形成环
    println(hasCycleFloyd(node1)) // true

    // 测试3：单节点自环
    val soloNode = ListNode(1)
    soloNode.next = soloNode
    println(hasCycleFloyd(soloNode)) // true
}



// 哈希表法检测环
fun hasCycleHash(head: ListNode?): Boolean {
    val visited = mutableSetOf<ListNode>() // 小本本记下所有走过的节点
    var current = head

    while (current != null) {
        if (current in visited) { // 发现重复脚印！
            return true
        }
        visited.add(current)      // 记录新位置
        current = current.next    // 继续前进
    }
    return false // 走到头没发现环
}


fun hasCycleFloyd(head: ListNode?): Boolean {
    var slow = head  // 乌龟
    var fast = head  // 兔子

    while (fast?.next != null) { // 兔子还能跳两步
        slow = slow?.next        // 乌龟走一步
        fast = fast.next?.next   // 兔子走两步

        if (slow == fast) {      // 龟兔相遇！
            return true
        }
    }
    return false // 兔子跑到了终点，没环
}

