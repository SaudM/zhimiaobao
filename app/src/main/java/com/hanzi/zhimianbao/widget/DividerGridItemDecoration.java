package com.hanzi.zhimianbao.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**   
 * @Description:  [recyclerview的分割线]
 * @Author:       [Saud]   
 * @CreateDate:   [15/11/30 12:18]
 * @UpDate:       [15/11/30 12:18]   
 * @Version:      [v1.0] 
 * 
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration
{

	private static final int[] ATTRS = new int[] { android.R.attr.listDivider };
	private Drawable mDivider;
	private  int marginLife;
	private  int MARGIN_LIFE_RIGHT=16;

	public DividerGridItemDecoration(Context context)
	{
		final TypedArray a = context.obtainStyledAttributes(ATTRS);
		mDivider = a.getDrawable(0);
		a.recycle();

		marginLife = dip2px(context, MARGIN_LIFE_RIGHT);
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, State state)
	{

		drawHorizontal(c, parent);
		drawVertical(c, parent);

	}

	private int getSpanCount(RecyclerView parent)
	{
		// 列数
		int spanCount = -1;
		LayoutManager layoutManager = parent.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager)
		{

			spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
		} else if (layoutManager instanceof StaggeredGridLayoutManager)
		{
			spanCount = ((StaggeredGridLayoutManager) layoutManager)
					.getSpanCount();
		}
		return spanCount;
	}

	public void drawHorizontal(Canvas c, RecyclerView parent)
	{
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++)
		{
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
			final int left = child.getLeft() - params.leftMargin+marginLife;
			final int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth()-marginLife;
			final int top = child.getBottom() + params.bottomMargin;
			final int bottom = top + mDivider.getIntrinsicHeight();
			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public void drawVertical(Canvas c, RecyclerView parent)
	{
		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++)
		{
			final View child = parent.getChildAt(i);

			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
					.getLayoutParams();
			final int top = child.getTop() - params.topMargin;
			final int bottom = child.getBottom() + params.bottomMargin;
			final int left = child.getRight() + params.rightMargin;
			final int right = left + mDivider.getIntrinsicWidth();

			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}
	}

	private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
			int childCount)
	{
		LayoutManager layoutManager = parent.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager)
		{
			if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
			{
				return true;
			}
		} else if (layoutManager instanceof StaggeredGridLayoutManager)
		{
			int orientation = ((StaggeredGridLayoutManager) layoutManager)
					.getOrientation();
			if (orientation == StaggeredGridLayoutManager.VERTICAL)
			{
				if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
				{
					return true;
				}
			} else
			{
				childCount = childCount - childCount % spanCount;
				if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
					return true;
			}
		}
		return false;
	}

	private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
			int childCount)
	{
		LayoutManager layoutManager = parent.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager)
		{
			childCount = childCount - childCount % spanCount;
			if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
				return true;
		} else if (layoutManager instanceof StaggeredGridLayoutManager)
		{
			int orientation = ((StaggeredGridLayoutManager) layoutManager)
					.getOrientation();
			// StaggeredGridLayoutManager 且纵向滚动
			if (orientation == StaggeredGridLayoutManager.VERTICAL)
			{
				childCount = childCount - childCount % spanCount;
				// 如果是最后一行，则不需要绘制底部
				if (pos >= childCount)
					return true;
			} else
			// StaggeredGridLayoutManager 且横向滚动
			{
				// 如果是最后一行，则不需要绘制底部
				if ((pos + 1) % spanCount == 0)
				{
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void getItemOffsets(Rect outRect, int itemPosition,
			RecyclerView parent)
	{
		int spanCount = getSpanCount(parent);
		int childCount = parent.getAdapter().getItemCount();
		if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
		{
			outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
		} else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
		{
			outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
		} else
		{
			outRect.set(0, 0, mDivider.getIntrinsicWidth(),
					mDivider.getIntrinsicHeight());
		}
	}
}
