/**
 * 找最大差值就像找股票最佳买入卖出时机——记录历史最低价，然后看后面哪天卖出赚最多！
 * 时间复杂度：  O(n) （只需遍历一次数组）
 * 空间复杂度：  O(1) （仅用两个变量存储状态）
 */
fun main() {
    println(maxProfit(intArrayOf(7,1,5,3,6,4))) // 输出5（6-1）
    println(maxProfit(intArrayOf(5,4,3,2,1)))  // 输出0（一直跌）
    println(maxProfit(intArrayOf(2,4,1,7)))    // 输出6（7-1）
    println(maxProfit(intArrayOf(2,2,2)))      // 输出0（无上涨）
    println(maxProfit(intArrayOf(5)))          // 输出0（单元素）
}

// 定义一个函数，接收一个整数数组（表示股票每日价格），返回最大利润
fun maxProfit(prices: IntArray): Int {
    // 如果价格数组长度小于2，无法进行交易，直接返回0
    if (prices.size  < 2) return 0

    // 初始化最大利润差值为0
    var maxDiff = 0

    // 初始化历史最低价格为数组的第一个元素
    var minPrice = prices[0]

    // 遍历价格数组，从第二个元素开始
    for (i in 1 until prices.size)  {
        // 如果当前价格大于历史最低价，说明可以卖出获取利润
        if (prices[i] > minPrice) {
            // 更新最大利润差值为当前利润与历史最大利润中的较大者
            maxDiff = maxOf(maxDiff, prices[i] - minPrice)
        } else {
            // 如果当前价格小于或等于历史最低价，更新历史最低价为当前价格
            minPrice = prices[i]
        }
    }

    // 返回最大利润差值
    return maxDiff
}

