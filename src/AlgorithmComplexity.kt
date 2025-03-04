/**
 * 时间复杂度就是「要花多少时间」，空间复杂度就是「要占多少内存」，就像做饭——时间是你炒菜要几分钟，空间是你的灶台能放几个锅。
 */
fun main() {
    val arr = intArrayOf(1, 2, 3)
    println(getFirst(arr))
    println(findMax(arr))
    println(hasDuplicate(arr))
    println(hasDuplicateFast(arr))
    println(average(arr))
    println(squareArray(arr))
    println(factorial(1))
}


/**
 * 时间复杂度： 算法执行所需要的时间
 */
// 取数组第一个元素
/**
 * O(1) 常数时间（闪电侠）
 * 场景： 无论数据多少，一步搞定
 */
fun getFirst(arr: IntArray): Int? = arr.firstOrNull()


// 遍历数组找最大值
/**
 * O(n) 线性时间（老实人）
 * 场景： 数据量翻倍，时间也翻倍
 */
fun findMax(arr: IntArray): Int? {
    if (arr.isEmpty()) return null
    var max = arr[0]
    for (num in arr) {  // 要循环 n 次
        if (num > max) max = num
    }
    return max
}

/**
 * O(n²) 平方时间（拖延症）
 * 场景： 数据量翻倍，时间变四倍
 */
// 检查数组是否有重复元素（暴力版）
fun hasDuplicate(arr: IntArray): Boolean {
    for (i in arr.indices) {
        for (j in i+1 until arr.size) {  // 两层循环 → n*(n-1)/2 次
            if (arr[i] == arr[j]) return true
        }
    }
    return false
}

// 检查重复（哈希表优化版）
/**
 * 优化： 用 Set 空间换时间 → 时间从O(n²) 降到 O(n)
 */
fun hasDuplicateFast(arr: IntArray): Boolean {
    val seen = HashSet<Int>()  // 额外空间 O(n)
    for (num in arr) {
        if (num in seen) return true
        seen.add(num)
    }
    return false
}

/**
 * 空间复杂度： 算法执行所需要的空间
 */


// 计算数组元素平均值
/**
 * O(1) 常数空间（省空间达人）
 * 场景： 只用固定大小的额外空间
 */
fun average(arr: IntArray): Double {
    var sum = 0.0
    for (num in arr) {
        sum += num  // 只用了 sum 一个变量
    }
    return sum / arr.size
}


// 复制一个数组的平方值到新数组
/**
 * O(n) 线性空间（仓库管理员）
 * 场景： 额外空间随数据量线性增长
 */
fun squareArray(arr: IntArray): IntArray {
    val result = IntArray(arr.size)  // 创建了一个新数组
    for (i in arr.indices) {
        result[i] = arr[i] * arr[i]
    }
    return result
}


// 递归计算阶乘
/**
 * O(n) 递归空间（俄罗斯套娃）
 * 场景： 递归调用栈深度与数据量成正比
 */
fun factorial(n: Int): Int {
    if (n <= 1) return 1
    return n * factorial(n - 1)  // 递归调用n次
}