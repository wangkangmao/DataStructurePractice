/**
 * 实现无限list求交集
 * 双指针法逐步生成元素并比较，时间复杂度 O(k)（k 为找到的交集元素数量）
 */

fun main() {
    // 生成两个无限序列的交集（自然数 vs 平方数，交集为 0,1,4,9,16,...）
    val common = findCommonInfiniteElements(infiniteSequence1(), infiniteSequence2())

    // 取前5个交集元素测试
    println(common.take(5).toList()) // 输出 [0, 1, 4, 9, 16]
}


// 生成无限递增序列（示例1：自然数；示例2：平方数）
fun infiniteSequence1(): Sequence<Int> = generateSequence(0) { it + 1 }
fun infiniteSequence2(): Sequence<Int> = generateSequence(0) { (it + 1) * (it + 1) }

// 双指针法求交集（要求两个序列递增）
// 定义一个函数，接收两个递增的整数序列，返回它们的交集序列
fun findCommonInfiniteElements(seq1: Sequence<Int>, seq2: Sequence<Int>): Sequence<Int> = sequence {
    // 获取seq1的迭代器，用于逐个访问seq1中的元素
    val iterator1 = seq1.iterator()

    // 获取seq2的迭代器，用于逐个访问seq2中的元素
    val iterator2 = seq2.iterator()

    // 检查seq1或seq2是否为空，如果任一序列为空，则直接返回空序列
    if (!iterator1.hasNext()  || !iterator2.hasNext())  return@sequence

    // 初始化变量a和b，分别存储seq1和seq2的当前元素
    var a = iterator1.next()
    var b = iterator2.next()

    // 进入无限循环，直到两个序列的元素全部遍历完
    while (true) {
        // 使用when语句比较a和b的值
        when {
            // 如果a等于b，说明找到了一个公共元素
            a == b -> {
                // 使用yield将公共元素添加到结果序列中
                yield(a)

                // 移动seq1和seq2的迭代器，获取下一个元素
                a = iterator1.next()
                b = iterator2.next()
            }

            // 如果a小于b，说明seq1的当前元素较小，移动seq1的迭代器
            a < b -> a = iterator1.next()

            // 否则（a大于b），说明seq2的当前元素较小，移动seq2的迭代器
            else -> b = iterator2.next()
        }
    }
}

