package dataStructure.Graph;

/**
 * @description:Õÿ∆À≈≈–Ú
 * @author: slfang
 * @time: 2020/8/3 14:54
 */
public class Topological {

    private Iterable<Integer> order;
    public Topological(Digraph digraph){
        if(!digraph.isCycle(digraph)){
            DepthFirstOrder  depthFirstOrder = new DepthFirstOrder(digraph);
            order = depthFirstOrder.getResversePost();//
        }
    }

    public Iterable<Integer> getOrder() {
        return order;
    }

    public boolean isDaG(){
        return order !=null;
    }
}
