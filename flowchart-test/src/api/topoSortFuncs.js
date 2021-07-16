//计算每个节点的入度
export function getNodeIn(nodes, connections) {
    return nodes.map(node => {
        node.in = 0;
        for (let index = 0; index < connections.length; index++) {
            if (connections[index].destination.id === node.id) {
                node.in++;
            }
        }
        return node
    })
}

/**
 * 解释层
 * 作用是遍历页面上生成的图并生成拓扑排序列表
 * @param nodes  节点表
 * @param connections 连接表
 */
export function interpretationLayer(nodes, connections) {
    let resQueue = []//结果栈
    //这一步是查找每个节点的入度，并存到每一个节点里面
    while (nodes.length) {
        //查找每个节点的入度
        nodes = getNodeIn(nodes, connections)
        /**
         * 这儿有一个问题没解决 但是不影响运行
         *   用深拷贝的话就会陷入死循环  为什么？？？
         */
            // let tempNodes = Object.assign({}, nodes)
        let tempNodes = nodes
        for (let i = 0; i < tempNodes.length; i++) {
            if (tempNodes[i].in === 0) {
                //把入度为0的点放到结果队列里面
                resQueue.push(tempNodes[i])
                // 删除对应的nodes里面的项
                nodes = nodes.filter(item => {
                    return item !== tempNodes[i]
                })
                // 删除对应的connections里面的项
                connections = connections.filter(connection => {
                    return connection.source.id !== tempNodes[i].id
                })
            }
        }
    }
    return {
        connections: connections,
        nodes: resQueue
    }
}
