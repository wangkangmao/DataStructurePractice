/**
 * 循环队列就像环形停车场——车开进去绕一圈，出来时腾出位置，永远不会浪费停车位！
 */
fun main() {
    val parkingLot = CircularQueue(3) // 建一个3车位的环形停车场

    println(parkingLot.enqueue("Audi"))  // true
    println(parkingLot.enqueue("BMW"))   // true
    println(parkingLot.peek())           // Audi

    println(parkingLot.dequeue())        // Audi
    println(parkingLot.enqueue("Tesla")) // true
    println(parkingLot.enqueue("BYD"))   // true（此时已满）

    println(parkingLot.enqueue("NIO"))   // false（车位已满，进不去）
    println(parkingLot.dequeue())        // BMW
    println(parkingLot.enqueue("NIO"))   // true（腾出空位，可以进）
}


class CircularQueue(private val capacity: Int) {
    private val elements = arrayOfNulls<Any>(capacity)
    private var front = 0  // 队头指针
    private var rear = 0   // 队尾指针（下一个要插入的位置）
    private var size = 0   // 当前元素数量

    // 入队（停车）
    fun enqueue(item: Any): Boolean {
        if (isFull()) return false // 停车场满了，进不去
        elements[rear] = item
        rear = (rear + 1) % capacity // 环形移动
        size++
        return true
    }

    // 出队（开走）
    fun dequeue(): Any? {
        if (isEmpty()) return null // 停车场空的，没车可出
        val item = elements[front]
        front = (front + 1) % capacity
        size--
        return item
    }

    // 是否满员（车位已占满？）
    fun isFull(): Boolean = size == capacity

    // 是否为空（停车场没车？）
    fun isEmpty(): Boolean = size == 0

    // 看一眼队头（最老的车是谁？）
    fun peek(): Any? = if (isEmpty()) null else elements[front]
}

