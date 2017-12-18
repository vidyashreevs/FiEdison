/*
 * Copyright (C) 2015 yydcdut (yuyidong2015@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package automation.edisonbro.com.edison.sdlv.src.main.java.com.yydcdut.sdlv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import java.util.List;

/**
 * Created by yuyidong on 15/9/30.
 */
class DragListView<T> extends ListView {
    /* 数据 */
    protected List<T> mDataList;
    /* 监听器 */
    private SlideAndDragListView.OnDragListener mOnDragListener;
    /* 监听器 */
    private Callback.OnDragDropListener[] mOnDragDropListeners;

    public DragListView(Context context) {
        this(context, null);
    }

    public DragListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mOnDragDropListeners = new Callback.OnDragDropListener[2];
    }

    protected void setDragPosition(int position) {
        View view = getChildAt(position - getFirstVisiblePosition());
        if (mOnDragListener != null && view instanceof ItemMainLayout) {
            ItemMainLayout itemMainLayout = (ItemMainLayout) getChildAt(position - getFirstVisiblePosition());
            itemMainLayout.getItemLeftBackGroundLayout().setVisibility(GONE);
            itemMainLayout.getItemRightBackGroundLayout().setVisibility(GONE);
            SlideAndDragListView slideAndDragListView = (SlideAndDragListView) getParent();
            slideAndDragListView.setInterceptTouchEvent(true);
        }
    }

    /**
     * 设置drag的监听器，加入数据
     *
     * @param onDragListener
     * @param dataList
     */
    public void setOnDragListener(SlideAndDragListView.OnDragListener onDragListener, List<T> dataList) {
        mOnDragListener = onDragListener;
        mDataList = dataList;
    }

    public List<T> getDataList() {
        return mDataList;
    }

    private View getViewByPoint(int x, int y) {
        int count = getChildCount();
        View child;
        for (int childIdx = 0; childIdx < count; childIdx++) {
            child = getChildAt(childIdx);
            if (y >= child.getTop() && y <= child.getBottom() && x >= child.getLeft() && x <= child.getRight()) {
                return child;
            }
        }
        return null;
    }

    protected void handleDragStarted(int x, int y) {
        View view = getViewByPoint(x, y);
        if (view == null) {
            return;
        }
        if (mOnDragDropListeners[0] != null) {
            mOnDragDropListeners[0].onDragStarted(x, y, view);
        }
        if (mOnDragDropListeners[1] != null) {
            mOnDragDropListeners[1].onDragStarted(x, y, view);
        }
        if (mOnDragListener != null) {
            mOnDragListener.onDragViewStart(getPositionForView(view));
        }
    }

    protected void handleDragMoving(int x, int y) {
        View view = getViewByPoint(x, y);
        if (view == null) {
            return;
        }
        if (mOnDragDropListeners[0] != null) {
            mOnDragDropListeners[0].onDragMoving(x, y, view);
        }
        if (mOnDragDropListeners[1] != null) {
            mOnDragDropListeners[1].onDragMoving(x, y, view);
        }
        if (mOnDragListener != null) {
            mOnDragListener.onDragViewMoving(getPositionForView(view));
        }
    }

    protected void handleDragFinished(int x, int y) {
        if (mOnDragDropListeners[0] != null) {
            mOnDragDropListeners[0].onDragFinished(x, y);
        }
        if (mOnDragDropListeners[1] != null) {
            mOnDragDropListeners[1].onDragFinished(x, y);
        }
        if (mOnDragListener != null) {
            View view = getViewByPoint(x, y);
            if (view == null) {
                if (y < 0) {
                    mOnDragListener.onDragViewDown(getFirstVisiblePosition());
                } else {
                    mOnDragListener.onDragViewDown(getLastVisiblePosition());
                }
            } else {
                mOnDragListener.onDragViewDown(getPositionForView(view));
            }
        }
    }

    protected void add0OnDragDropListener(Callback.OnDragDropListener listener) {
        mOnDragDropListeners[0] = listener;
    }

    protected void add1OnDragDropListener(Callback.OnDragDropListener listener) {
        mOnDragDropListeners[1] = listener;
    }

}
