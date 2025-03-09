/**
 * 找乘积最大子数组就像炒股——既要抓住暴涨，也要警惕暴跌（负数可能负负得正），还要记得及时止损（遇到零重置）！
 */
fun main() {
    println(maxProduct(intArrayOf(2, 3, -2, 4)))     // 输出6（子数组[2,3]）
    println(maxProduct(intArrayOf(-2, 0, -1)))       // 输出0（子数组[0]）
    println(maxProduct(intArrayOf(-3, -1, -2)))      // 输出6（子数组[-3,-1,-2]）
    println(maxProduct(intArrayOf(0, 2)))            // 输出2（子数组[2]）
    println(maxProduct(intArrayOf(-2, 3, -4)))       // 输出24（子数组[-2,3,-4]）
}


// 定义一个函数，接收一个整数数组，返回数组中连续子数组的最大乘积
fun maxProduct(nums: IntArray): Int {
    // 如果数组为空，直接返回0
    if (nums.isEmpty())  return 0

    // 初始化全局最大值，表示整个数组中已找到的最大乘积
    var maxSoFar = nums[0]

    // 初始化当前最大值，表示以当前元素结尾的子数组的最大乘积
    var currentMax = nums[0]

    // 初始化当前最小值，表示以当前元素结尾的子数组的最小乘积（用于处理负数的情况）
    var currentMin = nums[0]

    // 遍历数组，从第二个元素开始
    for (i in 1 until nums.size)  {
        // 获取当前元素的值
        val num = nums[i]

        // 计算新的候选值，考虑三种情况：
        // 1. 当前数本身（即从当前数重新开始）
        // 2. 当前数 × 当前最大值（延续之前的子数组）
        // 3. 当前数 × 当前最小值（处理负数的情况）
        val candidates = listOf(num, currentMax * num, currentMin * num)

        // 更新当前最大值为候选值中的最大值
        currentMax = candidates.maxOrNull()!!

        // 更新当前最小值为候选值中的最小值
        currentMin = candidates.minOrNull()!!

        // 更新全局最大值为当前最大值和全局最大值中的较大者
        maxSoFar = maxOf(maxSoFar, currentMax)
    }

    // 返回全局最大值
    return maxSoFar
}
