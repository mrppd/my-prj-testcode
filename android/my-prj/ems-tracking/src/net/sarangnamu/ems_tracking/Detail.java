/*
 * Detail.java
 * Copyright 2013 Burke Choi All rights reserved.
 *             http://www.sarangnamu.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sarangnamu.ems_tracking;

import net.sarangnamu.common.admob.AdMobDecorator;
import net.sarangnamu.ems_tracking.api.xml.Ems;
import net.sarangnamu.ems_tracking.api.xml.Ems.EmsData;
import net.sarangnamu.ems_tracking.cfg.Cfg;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Detail extends Activity {
    private Ems ems;
    private EmsHistory adapter;

    private TextView emsNum, detail;
    private ListView list;
    private LinearLayout adLayout;
    private AdMobDecorator admob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String emsNumber = getIntent().getStringExtra(EmsDataManager.EMS_NUM);
        ems = EmsDataManager.getInstance().getEmsData(emsNumber);
        if (ems == null) {
            finish();
        }

        setContentView(R.layout.detail);

        emsNum   = (TextView) findViewById(R.id.emsNum);
        detail   = (TextView) findViewById(R.id.detail);
        list     = (ListView) findViewById(R.id.list);
        adLayout = (LinearLayout) findViewById(R.id.adLayout);

        initLabel();
        initAdView();
        initListView();
    }

    @Override
    protected void onDestroy() {
        admob.destroy();

        super.onDestroy();
    }

    private void initLabel() {
        if (ems == null) {
            return ;
        }

        String anotherName = Cfg.getAnotherName(getApplicationContext(), ems.emsNum);
        if (anotherName == null) {
            emsNum.setText(ems.emsNum);
        } else {
            emsNum.setText(anotherName);
            detail.setText(ems.emsNum);
        }
    }

    private void initAdView() {
        admob = new AdMobDecorator(this, Cfg.ADMOB_ID);
        admob.load(adLayout);
    }

    private void initListView() {
        adapter = new EmsHistory();
        list.setAdapter(adapter);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // EmsHistory
    //
    ////////////////////////////////////////////////////////////////////////////////////

    class ViewHolder {
        TextView office;
        TextView status;
        TextView date;
    }

    class EmsHistory extends BaseAdapter {
        @Override
        public int getCount() {
            if (ems == null) {
                return 0;
            }

            return ems.emsData.size();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int pos, View view, ViewGroup vg) {
            ViewHolder vh;

            if (view == null) {
                vh = new ViewHolder();
                view = LayoutInflater.from(Detail.this).inflate(R.layout.detail_item, null);

                vh.office = (TextView) view.findViewById(R.id.office);
                vh.status = (TextView) view.findViewById(R.id.status);
                vh.date   = (TextView) view.findViewById(R.id.date);

                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }

            EmsData data = ems.getEmsData(pos);

            vh.office.setText(data.office);
            vh.status.setText(data.status);
            vh.date.setText(data.date);

            return view;
        }
    }
}
