package Week5;

/**
 *  This program is used to implement prim's algorithm.
 *  
 * @author chetana.
 *
 */

import java.util.*;
import java.lang.*;

public class prims {
    static class Node{
        int position = -1;
        int source = -1;
        int dist = Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        
        PriorityQueue<Node> pq = new PriorityQueue(new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return n1.dist - n2.dist;
            }
        });
        
        ArrayList<int[]>[] graph = new ArrayList[N+1];
        HashMap<Integer, Node> map = new HashMap<Integer, Node>();
        
        for(int i=0; i<M; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();
            int[] to = {y,r};
            int[] from = {x,r};
            if(graph[x] == null)
                graph[x] = new ArrayList<int[]>();            
            if(graph[y] == null)
                graph[y] = new ArrayList<int[]>();
            graph[x].add(to);
            graph[y].add(from);
        }
        
        int start = in.nextInt();
        Node sp = new Node();
        sp.position = start;
        sp.source = 0;
        sp.dist = 0;
        pq.add(sp);
        map.put(start, sp);
        
        for(int i=1; i<N+1; i++){
            if(i != start){
                Node n = new Node();
                n.position = i;
                pq.add(n);
                map.put(i, n);
            }
        }
        
        int sum = 0;
        
        HashSet<Integer> visited = new HashSet<Integer>();
        
        while(!pq.isEmpty()){
            Node node = pq.poll();
            visited.add(node.position);
            
            if(node.dist == Integer.MAX_VALUE)
                break;
            
            //System.out.println("polling: " + node.position);
            sum+= node.dist;
           // System.out.println("Sum: " + sum);
            for(int[] arr : graph[node.position]){
                //0 has node
                //1 has dist
                if(!map.containsKey(arr[0]))
                    continue;
                Node n = map.get(arr[0]);
                int newDist = arr[1];
                if(newDist < n.dist && !visited.contains(n.position)){
                    //System.out.println("Updating " + n.position + " to " + newDist);
                    n.dist = newDist;
                    n.source = node.position;
                    pq.remove(n);
                    pq.add(n);
                }
            }
        }
        in.close();
        System.out.println(sum);
    }
}
