/**
 * 快速排序的优化就像给赛车调校——选好基准（轮胎），小规模换策略（弯道用漂移），处理重复零件（三向切分），让排序速度更快更稳！
 * 1、选基准（Pivot） ：随便抓个元素当分界线（比如最后一个元素）
 * 2、分区（Partition） ：把小的扔左边，大的扔右边
 * 3、递归：左右两边重复上述操作
 */
fun main() {

    // 测试用例0：普通乱序数组,基本排序算法
    val arr0 = intArrayOf(3, 1, 14, 1, 5, 11, 2, 6)
    quickSortBasic(arr0)
    println(arr0.contentToString()) // [1, 1, 2, 3, 5, 6, 11, 14]

    // 测试用例1：普通乱序数组,优化后排序算法
    val arr1 = intArrayOf(3, 1, 4, 1, 5, 9, 2, 6)
    quickSortOptimized(arr1)
    println(arr1.contentToString()) // [1, 1, 2, 3, 4, 5, 6, 9]

    // 测试用例2：已排序数组（测试最坏情况优化）。优化后排序算法
    val arr2 = intArrayOf(1, 2, 3, 4, 5)
    quickSortOptimized(arr2)
    println(arr2.contentToString()) // [1, 2, 3, 4, 5]

    // 测试用例3：大量重复元素
    val arr3 = intArrayOf(2, 2, 1, 1, 3, 3, 3)
    quickSortThreeWay(arr3)
    println(arr3.contentToString()) // [1, 1, 2, 2, 3, 3, 3]

    // 测试用例4：空数组和单元素数组
    val arr4 = intArrayOf()
    quickSortOptimized(arr4) // 无异常
    val arr5 = intArrayOf(5)
    quickSortOptimized(arr5)
    println(arr5.contentToString()) // [5]
}


fun quickSortBasic(arr: IntArray, low: Int = 0, high: Int = arr.size - 1) {
    if (low < high) {
        val pivotIndex = partition(arr, low, high)
        quickSortBasic(arr, low, pivotIndex - 1)
        quickSortBasic(arr, pivotIndex + 1, high)
    }
}

fun quickSortOptimized(arr: IntArray, low: Int = 0, high: Int = arr.size - 1) {
    if (high - low < 10) { // 小数组阈值设为10
        insertionSort(arr, low, high)
        return
    }
    val pivotIndex = partitionOptimized(arr, low, high)
    quickSortOptimized(arr, low, pivotIndex - 1)
    quickSortOptimized(arr, pivotIndex + 1, high)
}

private fun insertionSort(arr: IntArray, low: Int, high: Int) {
    for (i in low + 1..high) {
        val key = arr[i]
        var j = i - 1
        while (j >= low && arr[j] > key) {
            arr[j + 1] = arr[j]
            j--
        }
        arr[j + 1] = key
    }
}


fun quickSortThreeWay(arr: IntArray, low: Int = 0, high: Int = arr.size - 1) {
    if (high <= low) return

    // 初始化三个指针
    var lt = low
    var gt = high
    val pivot = arr[low]
    var i = low + 1

    while (i <= gt) {
        when {
            arr[i] < pivot -> arr.swap(lt++, i++)
            arr[i] > pivot -> arr.swap(i, gt--)
            else -> i++
        }
    }
    // 递归处理左右
    quickSortThreeWay(arr, low, lt - 1)
    quickSortThreeWay(arr, gt + 1, high)
}


private fun partition(arr: IntArray, low: Int, high: Int): Int {
    val pivot = arr[high] // 选最后一个元素为基准（有优化空间！）
    var i = low - 1
    for (j in low until high) {
        if (arr[j] <= pivot) {
            i++
            arr.swap(i, j)
        }
    }
    arr.swap(i + 1, high)
    return i + 1
}

private fun partitionOptimized(arr: IntArray, low: Int, high: Int): Int {
    // 随机选基准并与末尾交换
    val randomIndex = (low..high).random()
    arr.swap(randomIndex, high)
    return partition(arr, low, high) // 继续用基础版分区
}


// 交换扩展函数
fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}



private fun printArray(arr: IntArray) {
    for (i in 0 until arr.size - 1) {
        println(arr[i])
    }
}




