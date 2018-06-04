package com.gang.store.storesystemmanager.base;

import java.io.Serializable;
import java.util.Map;

import rx.Observable;

/**
 * Created by Administrator on 2018/6/3.
 */

public class BaseEntity {
    class BaseBean implements Serializable {
        public long id;
        public String objectId;
        public Map<String, String> param;
    }

    interface IListBean {
        Observable getPageAt(int page);

        void setParam(Map<String, String> param);
    }

    public abstract class ListBean extends BaseBean implements IListBean {
        @Override
        public void setParam(Map<String, String> param) {
            this.param = param;
        }
    }
}
