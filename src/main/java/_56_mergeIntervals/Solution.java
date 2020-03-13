package _56_mergeIntervals;

import java.util.*;

/**
 * 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Solution {

    public int[][] merge(int[][] intervals) {
        List<Interval> intervalList = new ArrayList<>();
        for (int[] interval:intervals) {
            intervalList.add(new Interval(interval));
        }
        //对区间集合进行排序
        intervalList.sort(new IntervalComparator());

        LinkedList<Interval> merged = new LinkedList<>();
        for (Interval interval:intervalList) {
            //merged链为空 或 链尾的end值小于当前interval的start值，意味着合并区间在这里断了，于是add
            if (merged.isEmpty() || merged.getLast().end < interval.start) {
                merged.add(interval);
            } else {
                //否则合并区间
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }
        //将结果从Interval转换为数组
        int[][] result = new int[merged.size()][2];
        int i = 0;
        for (Interval interval:merged) {
            result[i][0] = interval.start;
            result[i][1] = interval.end;
            i++;
        }

        return result;

    }

}

class IntervalComparator implements Comparator<Interval> {

    @Override
    public int compare(Interval a, Interval b) {
        return Integer.compare(a.start, b.start);
    }

}

class Interval {
    int start;
    int end;

    Interval(int[] interval) {
        this.start = interval[0];
        this.end = interval[1];
    }

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
