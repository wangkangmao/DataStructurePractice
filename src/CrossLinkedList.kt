/**
 * 判断链表交叉就像找两个人走的路有没有汇合点——要么用「脚印记录法」（哈希表），要么用「对齐起点法」（双指针）！
 */
fun main() {
    // 构造交叉链表
    val commonNode = ListNode(7).apply {
        next = ListNode(9)
    }
    val headA = ListNode(1).apply {
        next = ListNode(3).apply {
            next = ListNode(5).apply {
                next = commonNode
            }
        }
    }
    val headB = ListNode(2).apply {
        next = ListNode(4).apply {
            next = commonNode
        }
    }

    // 测试哈希表法
    println(getIntersectionNodeHash(headA, headB)?.value) // 输出7

    // 测试双指针法
    println(getIntersectionNode(headA, headB)?.value)     // 输出7

    // 测试无交叉情况
    val headC = ListNode(10)
    println(getIntersectionNode(headA, headC))            // 输出null

    // 测试其中一个链表为空
    println(getIntersectionNode(null, headB))             // 输出null
}


data class ListNode(val value: Int, var next: ListNode? = null)

// 哈希表法检测交叉点
fun getIntersectionNodeHash(headA: ListNode?, headB: ListNode?): ListNode? {
    val visited = hashSetOf<ListNode>() // 存储链表A的所有节点
    var currentA = headA

    // 记录链表A的节点
    while (currentA != null) {
        visited.add(currentA)
        currentA = currentA.next
    }

    // 检查链表B是否有重复节点
    var currentB = headB
    while (currentB != null) {
        if (visited.contains(currentB)) return currentB
        currentB = currentB.next
    }
    return null
}


fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
    // 获取链表长度
    fun getLength(head: ListNode?): Int {
        var len = 0
        var curr = head
        while (curr != null) {
            len++
            curr = curr.next
        }
        return len
    }

    val lenA = getLength(headA)
    val lenB = getLength(headB)

    var currA = headA
    var currB = headB

    // 长链表先走差值步
    if (lenA > lenB) {
        repeat(lenA - lenB) { currA = currA?.next }
    } else {
        repeat(lenB - lenA) { currB = currB?.next }
    }

    // 同步前进找交叉点
    while (currA != null && currB != null) {
        if (currA == currB) return currA
        currA = currA!!.next
        currB = currB!!.next
    }
    return null
}

