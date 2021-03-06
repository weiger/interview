package com.interview.algorithms.general;

/**
 * Created_By: zouzhile
 * Date: 4/8/14
 * Time: 9:20 AM
 *
 * See: http://algs4.cs.princeton.edu/15uf/
 *
 * The implementation is actually Weighted quick-union version
 *
 */

public class C1_3_UnionFind {

    private int N = 0;
    private int[] parents;
    private int[] unionSizes;
    private int unionsCount;

    public C1_3_UnionFind(int N) {
        this.N = N;

        parents = new int[N];
        for(int i = 0; i < N; i ++)
            parents[i] = i;

        unionSizes = new int[N];
        for(int i = 0; i < N; i ++)
            unionSizes[i] = 1;

        this.unionsCount = N;
    }

    /**
     * This is actually returning the root of the union
     *
     * @param p
     * @return
     */
    public int find(int p) {
        while(! (p == this.parents[p]))
            p = this.parents[p];
        return p;
    }

    /**
     * Weighted quick union
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int pRoot = this.find(p);
        int qRoot = this.find(q);
        if(pRoot == qRoot)
            return;
        else {
            //merge the two union, to keep the lowest union tree, always attach small union to large union
            if(unionSizes[pRoot] < unionSizes[qRoot]) {
                this.parents[pRoot] = qRoot; // attaching small union to large union
                this.unionSizes[qRoot] += this.unionSizes[pRoot];
            }
            else {
                this.parents[qRoot] = pRoot;
                this.unionSizes[pRoot] += this.unionSizes[qRoot];
            }
            this.unionsCount -- ;
        }
    }

    public int count() {
        return this.unionsCount;
    }

    public boolean connected(int p, int q) {
        return this.find(p) == this.find(q);
    }
}
