/**
 * 二叉树的层序遍历就像剥洋葱——一层一层剥开，每层从左到右处理节点，用队列来维护“待处理队伍”！
 */
fun main() {
    // 测试用例1：普通二叉树
    //       3
    //      / \
    //     9  20
    //       /  \
    //      15   7
    val root1 = TreeNode(3).apply {
        left = TreeNode(9)
        right = TreeNode(20).apply {
            left = TreeNode(15)
            right = TreeNode(7)
        }
    }
    println(levelOrder(root1)) // 输出 [[3], [9,20], [15,7]]

    // 测试用例2：空树
    println(levelOrder(null)) // 输出 []

    // 测试用例3：单节点树
    val root3 = TreeNode(1)
    println(levelOrder(root3)) // 输出 [[1]]

    // 测试用例4：只有左子树
    //       1
    //      /
    //     2
    //    /
    //   3
    val root4 = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(3)
        }
    }
    println(levelOrder(root4)) // 输出 [[1], [2], [3]]
}

// 定义二叉树节点
class TreeNode(var value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

// 定义一个函数，接收二叉树的根节点，返回按层级遍历的结果（每层作为一个列表）
fun levelOrder(root: TreeNode?): List<List<Int>> {
    // 初始化结果列表，用于存储每层的节点值
    val result = mutableListOf<List<Int>>()

    // 如果根节点为空，直接返回空列表
    if (root == null) return result

    // 初始化队列，用于存储待处理的节点（广度优先遍历）
    val queue = ArrayDeque<TreeNode>()

    // 将根节点加入队列，开始遍历
    queue.add(root)

    // 当队列不为空时，继续遍历
    while (queue.isNotEmpty())  {
        // 获取当前层的节点数量
        val levelSize = queue.size

        // 初始化当前层的节点值列表
        val currentLevel = mutableListOf<Int>()

        // 处理当前层的所有节点
        repeat(levelSize) {
            // 从队列中移除第一个节点
            val node = queue.removeFirst()

            // 将当前节点的值加入当前层的列表
            currentLevel.add(node.value)

            // 如果当前节点有左子节点，将其加入队列（下一层）
            node.left?.let  { queue.add(it)  }

            // 如果当前节点有右子节点，将其加入队列（下一层）
            node.right?.let  { queue.add(it)  }
        }

        // 将当前层的节点值列表加入结果列表
        result.add(currentLevel)
    }

    // 返回最终的结果列表
    return result
}

