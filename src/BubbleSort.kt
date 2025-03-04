/**
 * 冒泡排序就像排队——个子高的同学不断往后冒泡，直到全体从矮到高排好队。优化就是让老师别傻盯着，发现队伍已经整齐就提前放学！
 *
 * 原理：
 * 1、从头开始比较相邻两个同学
 * 2、如果左边比右边高，就交换位置
 * 3、每轮结束，最高的同学冒到最后
 * 4、重复直到全体有序
 */

fun main() {
    val arr = intArrayOf(1, 5, 7, 2, 4, 9)
    bubbleSortBasic(arr)
    println(arr.contentToString()) // [1, 2, 4, 5, 7, 9]
    bubbleSortOptimized(arr)
    println(arr.contentToString()) // [1, 2, 4, 5, 7, 9]
    bubbleSortSuperOptimized(arr)
    println(arr.contentToString()) // [1, 2, 4, 5, 7, 9]
}

/**
 * 时间复杂度  O(n²)
 */
fun bubbleSortBasic(arr: IntArray) {
    for (i in 0 until arr.size - 1) {         // 外层：要排n-1轮
        for (j in 0 until arr.size - 1 - i) { // 内层：每轮少比一个（最后的已排好）
            if (arr[j] > arr[j + 1]) {        // 前一个比后一个大
                val temp = arr[j]             // 交换位置（矮个前移）
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
}

/**
 * 优化 1：提前放学（发现无交换就停止）
 * 无序列表时间复杂度  O(n²)
 * 有序列表时间复杂度  O(n)
 * 部分有序：接近O(n)
 */
fun bubbleSortOptimized(arr: IntArray) {
    var swapped: Boolean
    for (i in 0 until arr.size - 1) {
        swapped = false // 每轮开始重置标志
        for (j in 0 until arr.size - 1 - i) {
            if (arr[j] > arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
                swapped = true // 发生交换就标记
            }
        }
        if (!swapped) break // 一轮无交换 → 全体有序 → 收工！
    }
}

/**
 * 优化 2：记录最后交换位置（减少无效比较）
 * 无序列表时间复杂度  O(n²)
 * 有序列表时间复杂度  O(n)
 * 部分有序：接近O(n)
 */
fun bubbleSortSuperOptimized(arr: IntArray) {
    var lastSwapIndex = arr.size - 1
    var currentSwapIndex: Int

    repeat(arr.size) { // 最多还是n轮
        currentSwapIndex = -1 // 初始化
        for (j in 0 until lastSwapIndex) {
            if (arr[j] > arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
                currentSwapIndex = j // 记录最后一次交换的位置
            }
        }
        if (currentSwapIndex == -1) return@repeat // 无交换直接结束
        lastSwapIndex = currentSwapIndex // 下一轮只比到这里
    }

}