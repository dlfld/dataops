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
