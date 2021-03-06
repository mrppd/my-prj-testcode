/*
 * Copyright 2013 OBIGO Inc. All rights reserved.
 *             http://www.obigo.com
 */
package com.example.test_ssdp.service;

import org.teleal.cling.android.AndroidUpnpServiceConfiguration;
import org.teleal.cling.android.AndroidUpnpServiceImpl;

import android.net.wifi.WifiManager;

public class UpnpService extends AndroidUpnpServiceImpl {
    @Override
    protected AndroidUpnpServiceConfiguration createConfiguration(WifiManager wifiManager) {
        return new AndroidUpnpServiceConfiguration(wifiManager) {

            /* The only purpose of this class is to show you how you'd
               configure the AndroidUpnpServiceImpl in your application:

            @Override
            public int getRegistryMaintenanceIntervalMillis() {
                return 7000;
            }

            @Override
            public ServiceType[] getExclusiveServiceTypes() {
                return new ServiceType[] {
                        new UDAServiceType("SwitchPower")
                };
            }

             */

        };
    }

}
