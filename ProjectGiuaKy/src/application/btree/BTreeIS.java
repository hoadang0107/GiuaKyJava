package application.btree;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


public class BTreeIS {
	private final BTree<Integer, String> mBTree;
    private final Map<Integer, String> mMap;
    private BTTestIteratorImpl<Integer, String> mIter;


    public BTreeIS() {
        mBTree = new BTree<Integer, String>();
        mMap = new TreeMap<Integer, String>();
        mIter = new BTTestIteratorImpl<Integer, String>();
    }

    public BTree<Integer, String> getBTree() {
        return mBTree;
    }

    public BTNode<Integer, String> getRootNode() {
        return mBTree.getRootNode();
    }

    protected void add(Integer key, String value) {
        mMap.put(key, value);
        mBTree.insert(key, value);
    }

    protected void delete(Integer key) throws BTException {
        System.out.println("Delete key = " + key);
        String strVal1 = mMap.remove(key);
        String strVal2 = mBTree.delete(key);
        if (!isEqual(strVal1, strVal2)) {
            throw new BTException("Deleted key = " + key + " has different values: " + strVal1 + " | " + strVal2);
        }
    }

    private void clearData() {
        mBTree.clear();
        mMap.clear();
    }

    public void listItems(BTIteratorIF<Integer, String> iterImpl) {
        mBTree.list(iterImpl);
    }



    private boolean isEqual(String strVal1, String strVal2) {
        if ((strVal1 == null) && (strVal2 == null)) {
            return true;
        }

        if ((strVal1 == null) && (strVal2 != null)) {
            return false;
        }

        if ((strVal1 != null) && (strVal2 == null)) {
            return false;
        }

        if (!strVal1.equals(strVal2)) {
            return false;
        }

        return true;
    }


    public void validateSize() throws BTException {
        System.out.println("Validate size ...");
        if (mMap.size() != mBTree.size()) {
            throw new BTException("Error in validateSize(): Failed to compare the size:  " + mMap.size() + " <> " + mBTree.size());
        }
    }


    public void validateSearch(Integer key) throws BTException {
        System.out.println("Validate search for key = " + key + " ...");
        String strVal1 = mMap.get(key);
        String strVal2 = mBTree.search(key);

        if (!isEqual(strVal1, strVal2)) {
            throw new BTException("Error in validateSearch(): Failed to compare value for key = " + key);
        }
    }


    public void validateData() throws BTException {
        System.out.println("Validate data ...");

        for (Map.Entry<Integer, String> entry : mMap.entrySet()) {
            try {
                // System.out.println("Search key = " + entry.getKey());
                String strVal = mBTree.search(entry.getKey());
                if (!isEqual(entry.getValue(), strVal)) {
                    throw new BTException("Error in validateData(): Failed to compare value for key = " + entry.getKey());
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                throw new BTException("Runtime Error in validateData(): Failed to compare value for key = " + entry.getKey() + " msg = " + ex.getMessage());
            }
        }
    }


    public void validateOrder() throws BTException {
        System.out.println("Validate the order of the keys ...");
        mIter.reset();
        mBTree.list(mIter);
        if (!mIter.getStatus()) {
            throw new BTException("Error in validateData(): Failed to compare value for key = " + mIter.getCurrentKey());
        }
    }


    public void validateAll() throws BTException {
        validateData();
        validateSize();
        validateOrder();
    }


    public void addKey(int i) {
        add(i, "value-" + i);
    }


 
    //
    // Randomly generate integer within the specified range
    //
    public static int randInt(int min, int max) {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }


    /**
     * Inner class to implement BTree iterator
     */
    class BTTestIteratorImpl<K extends Comparable, V> implements BTIteratorIF<K, V> {
        private K mCurrentKey;
        private K mPreviousKey;
        private boolean mStatus;


        public BTTestIteratorImpl() {
            reset();
        }

        @Override
        public boolean item(K key, V value) {
            mCurrentKey = key;
            if ((mPreviousKey != null) && (mPreviousKey.compareTo(key) > 0)) {
                mStatus = false;
                return false;
            }
            mPreviousKey = key;
            return true;
        }

        public boolean getStatus() {
            return mStatus;
        }

        public K getCurrentKey() {
            return mCurrentKey;
        }

        public final void reset() {
            mPreviousKey = null;
            mStatus = true;
        }
    }

}
