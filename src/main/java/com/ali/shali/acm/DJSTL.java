package com.ali.shali.acm;

import java.util.*;

/**
 * @Author shali
 * @Date 2023/8/18 14:40
 * @PackageName:com.ali.shali.acm
 * @ClassName: DJSTL
 * @Description: 迪杰斯特拉算法
 * @Version 1.0
 */
public class DJSTL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //point nums
        int pn = sc.nextInt();
        int bn = sc.nextInt();
        int sn = sc.nextInt();

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= pn; i++) graph.add(new LinkedList<>());
        for (int i = 0; i < bn; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.get(u).add(new int[] {v, w});
            graph.get(v).add(new int[] {u, w});
        }
        int[] campus = new int[sn];
        //学校队列
        for (int i = 0; i < sn; i++) campus[i] = sc.nextInt();
        int[] dis = new int[pn + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        boolean[] vis = new boolean[pn + 1];
        dis[1] = 0;
        //小顶堆
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[] {1, 0});
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            int u = arr[0], d = arr[1];
            if (vis[u]) continue;
            vis[u] = true;
            for (int[] neighbor : graph.get(u)) {
                int v = neighbor[0], w = neighbor[1];
                if (dis[v] > dis[u] + w) {
                    dis[v] = dis[u] + w;
                    pq.offer(new int[] {v, dis[v]});
                }
            }
        }
        long ans = 0;
        for (int i : campus) ans += dis[i] * 2L;
        System.out.println(ans);
    }
}
