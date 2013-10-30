/*
 * PageBaseFrgmt.java
 * Copyright 2013 Burke.Choi All rights reserved.
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
package net.sarangnamu.home.page;

import net.sarangnamu.common.DLog;
import net.sarangnamu.common.frgmt.FrgmtBase;
import net.sarangnamu.home.R;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class PageBaseFrgmt extends FrgmtBase {
    private static final String TAG = "PageBaseFrgmt";

    protected TextView title;
    protected LinearLayout content;
    protected View view;


    @Override
    protected int getLayoutId() {
        return R.layout.page_base;
    }

    @Override
    protected void initLayout() {
        title   = (TextView) base.findViewById(R.id.pageTitle);
        content = (LinearLayout) base.findViewById(R.id.pageContent);

        int id = getLayoutIdentifier(getClassSimpleName());
        if (id != 0) {
            view = inflate(id);
            content.addView(view);
        } else {
            DLog.e(TAG, "initLayout, not found layout id for content");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String name = getClassSimpleName();
        title.setText(getStringIdentifier(name));
    }

    protected String getClassSimpleName() {
        return getClass().getSimpleName().replace("Frgmt", "");
    }

    protected int getStringIdentifier(String name) {
        return getResources().getIdentifier("mnu_" + name.toLowerCase(), "string", getActivity().getPackageName());
    }

    protected int getLayoutIdentifier(String name) {
        return getResources().getIdentifier("page_" + name.toLowerCase(), "layout", getActivity().getPackageName());
    }

    public boolean onBackPressed() {
        return false;
    }
}
