/**
 * 合并两个有序数组就像把两队按身高排队的小朋友合并成一队——每次从两队最前面挑最矮的放进新队伍，直到所有小朋友排好队！
 */
fun main() {
    // 测试用例1：普通情况
    val arr1 = intArrayOf(1, 3, 5)
    val arr2 = intArrayOf(2, 4, 6)
    println(mergeSortedArrays(arr1, arr2).contentToString())
    // 输出 [1, 2, 3, 4, 5, 6]

    // 测试用例2：一个数组为空
    val emptyArr = intArrayOf()
    val arr3 = intArrayOf(2, 4)
    println(mergeSortedArrays(emptyArr, arr3).contentToString())
    // 输出 [2, 4]

    // 测试用例3：有重复元素
    val arr4 = intArrayOf(1, 2, 2)
    val arr5 = intArrayOf(2, 3)
    println(mergeSortedArrays(arr4, arr5).contentToString())
    // 输出 [1, 2, 2, 2, 3]
}


fun mergeSortedArrays(arr1: IntArray, arr2: IntArray): IntArray {
    val merged = IntArray(arr1.size + arr2.size) // 创建新数组
    var i = 0 // arr1的指针
    var j = 0 // arr2的指针
    var k = 0 // merged的指针

    // 两队都还有人时，挑更小的放进去
    while (i < arr1.size && j < arr2.size) {
        merged[k++] = if (arr1[i] <= arr2[j]) arr1[i++] else arr2[j++]
    }

    // 处理剩下的arr1元素
    while (i < arr1.size) merged[k++] = arr1[i++]

    // 处理剩下的arr2元素
    while (j < arr2.size) merged[k++] = arr2[j++]

    return merged
}
