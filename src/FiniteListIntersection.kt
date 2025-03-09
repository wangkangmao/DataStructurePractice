import kotlin.math.min

/**
 *  实现有限List求交集
 *  使用集合（Set）快速查找，时间复杂度 O(n + m)
 */
fun main() {
    // 测试去重版本
    val listA = listOf(1, 2, 2, 3)
    val listB = listOf(2, 2, 4)
    println(findCommonElements(listA, listB)) // 输出 [2]

    // 测试保留重复版本
    println(findCommonElementsWithDuplicates(listA, listB)) // 输出 [2, 2]
}

// 有限 List 求交集（去重）
fun findCommonElements(list1: List<Int>, list2: List<Int>): Set<Int> {
    val set1 = list1.toSet()
    val set2 = list2.toSet()
    return set1 intersect set2
}

// 保留重复元素的最小次数（例如 list1=[2,2,3], list2=[2,2,2] → 返回 [2,2]）
fun findCommonElementsWithDuplicates(list1: List<Int>, list2: List<Int>): List<Int> {
    // 将list1中的元素分组并统计每个元素的出现次数，生成一个Map<Int, Int>，键为元素，值为出现次数
    val countMap1 = list1.groupingBy  { it }.eachCount()

    // 将list2中的元素分组并统计每个元素的出现次数，生成一个Map<Int, Int>，键为元素，值为出现次数
    val countMap2 = list2.groupingBy  { it }.eachCount()

    // 遍历countMap1中的每个键值对（num为元素，count1为该元素在list1中的出现次数）
    return countMap1.flatMap  { (num, count1) ->
        // 获取当前元素在list2中的出现次数，如果不存在则返回0
        val count2 = countMap2[num] ?: 0

        // 生成一个列表，包含当前元素，重复次数为min(count1, count2)，即两个列表中出现次数的最小值
        List(min(count1, count2)) { num }
    }
}
