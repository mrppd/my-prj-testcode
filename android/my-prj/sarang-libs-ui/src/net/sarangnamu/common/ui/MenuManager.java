/*
 * MenuManager.java
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
package net.sarangnamu.common.ui;

import android.content.Context;
import android.view.View;
import android.widget.PopupMenu;

/**
 * {@code
 * <pre>
    MenuManager.getInstance().showMenu(this, btn, R.menu.id);
    MenuManager.getInstance().setListener(new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            // TODO
            return true;
        }
    });
    MenuManager.getInstance().show();
 * </pre>}
 */
public class MenuManager {
    private static MenuManager inst;
    private PopupMenu.OnMenuItemClickListener listener;
    private PopupMenu popup;

    public static MenuManager getInstance() {
        if (inst == null) {
            inst = new MenuManager();
        }

        return inst;
    }

    private MenuManager() {

    }

    public void showMenu(Context context, View v, int resid) {
        popup = new PopupMenu(context, v);
        popup.getMenuInflater().inflate(resid, popup.getMenu());
        popup.setOnMenuItemClickListener(listener);

        popup.show();
    }

    public void setListener(PopupMenu.OnMenuItemClickListener l) {
        listener = l;
    }

    public void show() {
        if (popup != null) {
            popup.show();
        }
    }

    //    @Override
    //    public boolean onKeyUp(int keyCode, KeyEvent event) {
    //        if (keyCode == KeyEvent.KEYCODE_MENU) {
    //            menu.performClick();
    //            return true;
    //        }
    //
    //        return super.onKeyUp(keyCode, event);
    //    }
}
