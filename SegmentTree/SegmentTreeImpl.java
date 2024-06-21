package SegmentTree;
class SegmentTreeImpl{
    int[] segTree;
    int[] lazy;
    int N;
    int [] arr;

    public SegmentTree(int[] arr) {
        N = arr.length;
        segTree = new int[4 * N]; // Adjust the size as necessary
        lazy = new int[4 * N];
        this.arr = arr;
        build(0, 0, N - 1, arr);
    }

    // Initialize the segment tree
    private void build(int node, int start, int end, int[] arr) {
        if (start == end) {
            // Leaf node will have a single element
            segTree[node] = arr[start]; // Initialize with appropriate value
        } else {
            int mid = (start + end) >> 1;
            build(2 * node + 1, start, mid, arr); // Recursively build the segment tree
            build(2 * node + 2, mid + 1, end, arr);
            segTree[node] = merge(segTree[2 * node + 1], segTree[2 * node + 2]); // Merge function
        }
    }

    // Function to update the segment tree with lazy propagation
    public void updateRange(int startRange, int endRange, int value) {
        updateRange(0, 0, N - 1, startRange, endRange, value);
    }

    private void updateRange(int node, int start, int end, int l, int r, int val) {
        //no overlap
        if (start > end || start > r || end < l) return; // Out of range
        
        //update the pending task
        if (lazy[node] != 0) {
            
            // This node needs to be updated
            segTree[node] += lazy[node] * (end - start + 1); // Apply lazy update
            if (start != end) {
                lazy[2 * node + 1] += lazy[node]; // Propagate to children
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node] = 0; // Clear the lazy value
        }
        
        
        //complete overlap
        if (start >= l && end <= r) {
            // Current segment is completely within range
            segTree[node] += val * (end - start + 1);
            if (start != end) {
                lazy[2 * node + 1] += val;
                lazy[2 * node + 2] += val;
            }
            return;
        }
        
        int mid = (start + end) >> 2;
        updateRange(2 * node + 1, start, mid, l, r, val); // Update left child
        updateRange(2 * node + 2, mid + 1, end, l, r, val); // Update right child
        segTree[node] = merge(segTree[2 * node + 1], segTree[2 * node + 2]); // Update current node
    }

    // Function to query the segment tree
    public int query(int startRange, int endRange) {
        return query(0, 0, N - 1, startRange, endRange);
    }

    private int query(int node, int start, int end, int l, int r) {
        //no overlap
        if (start > end || start > r || end < l) return 0; // Out of range
        
        //update the pending task
        if (lazy[node] != 0) {
            // This node needs to be updated
            segTree[node] += lazy[node] * (end - start + 1); // Apply lazy update
            if (start != end) {
                lazy[2 * node + 1] += lazy[node]; // Propagate to children
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node] = 0; // Clear the lazy value
        }
        
        //complete overlap
        if (start >= l && end <= r) // Current segment is completely within range
            return segTree[node];
        
        //no overlap
        int mid = (start + end) / 2;
        int leftQuery = query(2 * node + 1, start, mid, l, r); // Query left child
        int rightQuery = query(2 * node + 2, mid + 1, end, l, r); // Query right child
        return merge(leftQuery, rightQuery); // Return result from merging left and right child
    }

    // Define your merge operation here
    private int merge(int a, int b) {
        return a + b; // Example: sum of two nodes
    }
}

