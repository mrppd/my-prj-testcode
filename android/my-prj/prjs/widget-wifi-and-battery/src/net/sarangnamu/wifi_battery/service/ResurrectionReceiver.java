/*
 * ResurrectionReceiver.java
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
package net.sarangnamu.wifi_battery.service;

import net.sarangnamu.common.service.immortal.ImmortalReceiver;

public class ResurrectionReceiver extends ImmortalReceiver {
    public static final String ACTION = "net.sarangnamu.wifi_battery.ACTION.RESURRECTION_RECEIVER";
    public static final String SERVICE = "net.sarangnamu.wifi_battery.ACTION.WIFI_BATTERY_SERVICE";

    @Override
    public String getActionString() {
        return ACTION;
    }

    @Override
    public String getServiceString() {
        return SERVICE;
    }
}
