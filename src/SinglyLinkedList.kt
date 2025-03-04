/**
 * 单链表就像一列火车——每节车厢（节点）载着货物（数据），并且只记得下一节车厢的位置，想找特定车厢得从头开始一节节找！
 */

fun main() {
    val list = LinkedList<String>()

    // 测试添加头节点
    list.addToHead("香蕉")
    list.addToHead("苹果")
    list.printList() // 苹果 → 香蕉 → null

    // 测试添加尾节点
    list.addToTail("西瓜")
    list.printList() // 苹果 → 香蕉 → 西瓜 → null

    // 测试删除中间节点
    list.delete("香蕉")
    list.printList() // 苹果 → 西瓜 → null

    // 测试删除头节点
    list.delete("苹果")
    list.printList() // 西瓜 → null

    // 测试查找功能
    println("是否存在西瓜：${list.contains("西瓜")}") // true
    println("是否存在苹果：${list.contains("苹果")}") // false

    // 测试空链表操作
    list.delete("西瓜")
    list.printList() // null
    println("是否存在西瓜：${list.contains("西瓜")}") // false
}

// 节点类（车厢）
data class Node<T>(var data: T, var next: Node<T>? = null)


// 单链表类（火车）
class LinkedList<T> {
    private var head: Node<T>? = null // 火车头

    // 1. 添加节点到头部（新火车头）
    fun addToHead(data: T) {
        val newNode = Node(data)
        newNode.next = head // 新节点指向旧头
        head = newNode      // 更新头为新节点
    }

    // 2. 添加节点到尾部（加在最后）
    fun addToTail(data: T) {
        val newNode = Node(data)
        if (head == null) { // 空链表直接设头
            head = newNode
            return
        }
        var current = head
        while (current?.next != null) { // 找到最后一个节点
            current = current.next
        }
        current?.next = newNode // 挂到尾部
    }

    // 3. 删除节点（卸下车厢）
    fun delete(data: T) {
        if (head?.data == data) { // 删除头节点
            head = head?.next
            return
        }
        var current = head
        while (current?.next != null) {
            if (current.next?.data == data) {
                current.next = current.next?.next // 跳过目标节点
                return
            }
            current = current.next
        }
    }

    // 4. 检查是否存在（查货物）
    fun contains(data: T): Boolean {
        var current = head
        while (current != null) {
            if (current.data == data) return true
            current = current.next
        }
        return false
    }

    // 5. 打印链表（查看整列火车）
    fun printList() {
        var current = head
        while (current != null) {
            print("${current.data} → ")
            current = current.next
        }
        println("null")
    }
}