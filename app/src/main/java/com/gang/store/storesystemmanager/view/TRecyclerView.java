package com.gang.store.storesystemmanager.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gang.store.storesystemmanager.R;
import com.gang.store.storesystemmanager.base.BaseEntity;
import com.gang.store.storesystemmanager.base.BaseViewHolder;
import com.gang.store.storesystemmanager.constant.SystemConstant;
import com.gang.store.storesystemmanager.view.holder.CommonFooterViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/6/3.
 */

public class TRecyclerView<T extends BaseEntity.ListBean> extends LinearLayout {
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.emptyView)
    LinearLayout emptyView;
    private LinearLayoutManager mLayoutManager;
    private Context context;
    private CoreAdapter<T> mCommAdapter = new CoreAdapter<>();
    private int begin = 0;
    private boolean isRefreshable = true, isHasHeadView = false, isEmpty = false;
    private T model;
//    public RxManager mRxManager = new RxManager();
    private Map<String, String> param = new HashMap<>();

    public TRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public TRecyclerView(Context context, AttributeSet att) {
        super(context, att);
        init(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        mRxManager.clear();
    }

    public void init(Context context) {
        this.context = context;
        View layout = LayoutInflater.from(context).inflate(
                R.layout.layout_list_recyclerview, null);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT));
        addView(layout);
        ButterKnife.bind(this, layout);
        initView(context);
    }

    private void initView(Context context) {
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright);
        swipeRefresh.setEnabled(isRefreshable);
        swipeRefresh.setOnRefreshListener(() -> reFetch());
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mCommAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            protected int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (recyclerView.getAdapter() != null
                        && newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == recyclerView.getAdapter()
                        .getItemCount() && mCommAdapter.isHasMore)
                    fetch();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int arg0, int arg1) {
                super.onScrolled(recyclerView, arg0, arg1);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
//        mRxManager.on(SystemConstant.EVENT_DEL_ITEM, (arg0) -> mCommAdapter.removeItem((Integer) arg0));
//        mRxManager.on(SystemConstant.EVENT_UPDATE_ITEM, (arg0) -> mCommAdapter.upDateItem(((UpDateData) arg0).i, ((UpDateData) arg0).oj));
        emptyView.setOnClickListener((view -> reFetch()));
    }

    public CoreAdapter getAdapter() {
        return mCommAdapter;
    }

    public void setRefreshing(boolean i) {
        swipeRefresh.setRefreshing(i);
    }

    public TRecyclerView setIsRefreshable(boolean i) {
        isRefreshable = i;
        swipeRefresh.setEnabled(i);
        return this;
    }

    public TRecyclerView setHeadView(Class<? extends BaseViewHolder> clazz) {
        if (clazz == null) {
            isHasHeadView = false;
            this.mCommAdapter.setHeadViewType(0, clazz, null);
        } else
            try {
                Object obj = ((Activity) context).getIntent().getSerializableExtra(SystemConstant.HEAD_DATA);
                int mHeadViewType = ((BaseViewHolder) (clazz.getConstructor(View.class)
                        .newInstance(new View(context)))).getType();
                this.mCommAdapter.setHeadViewType(mHeadViewType, clazz, obj);
                isHasHeadView = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return this;
    }

    public TRecyclerView setFooterView(Class<? extends BaseViewHolder> clazz) {
        this.begin = 0;
        try {
            int mFooterViewType = ((BaseViewHolder) (clazz.getConstructor(View.class)
                    .newInstance(new View(context)))).getType();
            this.mCommAdapter.setFooterViewType(mFooterViewType, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void setEmpty() {
        if (!isHasHeadView && !isEmpty) {
            isEmpty = true;
            emptyView.setVisibility(View.VISIBLE);
            swipeRefresh.setVisibility(View.GONE);
        }
    }

    public TRecyclerView setView(Class<? extends BaseViewHolder<T>> clazz) {
        try {
            BaseViewHolder mIVH = ((BaseViewHolder) (clazz.getConstructor(View.class)
                    .newInstance(new View(context))));
            int mType = mIVH.getType();
            /*this.model = ((Class<T>) ((ParameterizedType) (clazz
                    .getGenericSuperclass())).getActualTypeArguments()[0])
                    .newInstance();// 根据类的泛型类型获得model的实例*/
            this.mCommAdapter.setViewType(mType, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public TRecyclerView setParam(String key, String value) {
        this.param.put(key, value);
        return this;
    }

    public TRecyclerView setData(List<T> datas) {
        if (isEmpty) {
            emptyView.setVisibility(View.GONE);
            swipeRefresh.setVisibility(View.VISIBLE);
        }
        mCommAdapter.setBeans(datas, 1);
        return this;
    }

    public void reFetch() {
        this.begin = 0;
        swipeRefresh.setRefreshing(true);
        fetch();
    }

//    private Observable getResult() {
//        UserContext userContext = DiskLruCacheHelper.load(UserContext.class.getName(), UserContext.class);
//
//        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//        System.out.println(userContext);
//
//        return Api.getInstance().apiService.findStocks(userContext.getToken(), null, null, null).compose(RxSchedulers.io_main());
//    }

    public void fetch() {
        begin++;
        if (isEmpty) {
            emptyView.setVisibility(View.GONE);
            swipeRefresh.setVisibility(View.VISIBLE);
        }
        /*if (model == null) {
            Log.e("model", "null");
            return;
        }*/
//        model.setParam(param);
        /*mRxManager.add(getResult()
                .subscribe(
                        new Action1<List<T>>() {
                            @Override
                            public void call(List<T> results) {
                                swipeRefresh.setRefreshing(false);
                                mCommAdapter.setBeans(results, begin);
                                if (begin == 1 && (results == null || results.size() == 0))
                                    setEmpty();
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable e) {
                                e.printStackTrace();
                                setEmpty();
                            }
                        }
                ));*/
    }


    public class UpDateData {
        public int i;
        public T oj;

        public UpDateData(int i, T oj) {
            this.i = i;
            this.oj = oj;
        }
    }

    public class CoreAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        protected List<T> mItemList = new ArrayList<>();
        public boolean isHasMore = true;
        public int viewtype, isHasFooter = 1, isHasHader = 0, mHeadViewType;
        public Object mHeadData;
        public Class<? extends BaseViewHolder> mItemViewClass, mHeadViewClass, mFooterViewClass = CommonFooterViewHolder.class;
        public int mFooterViewType = CommonFooterViewHolder.LAYOUT_TYPE;

        public void setViewType(int i, Class<? extends BaseViewHolder> clazz) {
            this.isHasMore = true;
            this.viewtype = i;
            this.mItemList = new ArrayList<>();
            this.mItemViewClass = clazz;
            notifyDataSetChanged();
        }

        public void setHeadViewType(int i, Class<? extends BaseViewHolder> cla, Object data) {
            if (cla == null) {
                this.isHasHader = 0;
            } else {
                this.isHasHader = 1;
                this.mHeadViewType = i;
                this.mHeadViewClass = cla;
                this.mHeadData = data;
            }
        }

        public void setHeadViewData(Object data) {
            this.mHeadData = data;
        }

        public void setFooterViewType(int i, Class<? extends BaseViewHolder> clazz) {
            this.mFooterViewType = i;
            this.mFooterViewClass = clazz;
            this.mItemList = new ArrayList<>();
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            return isHasHader == 1 ? (position == 0 ? mHeadViewType
                    : (position + 1 == getItemCount() ? mFooterViewType : viewtype))
                    : (position + 1 == getItemCount() ? mFooterViewType : viewtype);
        }

        @Override
        public int getItemCount() {
            return mItemList.size() + isHasFooter + isHasHader;
        }

        public void setBeans(List<T> datas, int begin) {
            if (datas == null) datas = new ArrayList<>();
            this.isHasMore = datas.size() >= SystemConstant.PAGE_COUNT;
            if (begin > 1) {
                this.mItemList.addAll(datas);
            } else {
                this.mItemList = datas;
            }
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            try {
                boolean isFoot = viewType == mFooterViewType;
                return (RecyclerView.ViewHolder) (viewType == mHeadViewType ? mHeadViewClass
                        .getConstructor(View.class).newInstance(
                                LayoutInflater.from(parent.getContext()).inflate(
                                        mHeadViewType, parent, false))
                        : (RecyclerView.ViewHolder) (isFoot ? mFooterViewClass : mItemViewClass)
                        .getConstructor(View.class).newInstance(
                                LayoutInflater.from(parent.getContext())
                                        .inflate(
                                                isFoot ? mFooterViewType
                                                        : viewtype, parent,
                                                false)));
            } catch (Exception e) {
                Log.d("ViewHolderException", "onCreateViewHolder is xml file error.");
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((BaseViewHolder) holder).onBindViewHolder(holder.itemView,
                    position + 1 == getItemCount() ? (isHasMore ? new Object()
                            : null) : isHasHader == 1 && position == 0 ? mHeadData
                            : mItemList.get(position - isHasHader));
        }

        public void removeItem(int position) {
            mItemList.remove(position);
            notifyItemRemoved(position);
        }

        public void upDateItem(int position, T item) {
            mItemList.remove(position);
            mItemList.add(position, item);
            notifyItemChanged(position);
        }
    }
}
