/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ballerinalang.nats.streaming.consumer;

import io.nats.streaming.Subscription;
import org.ballerinalang.jvm.api.values.BObject;
import org.ballerinalang.nats.Constants;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Initializes the listener.
 *
 * @since 1.0.0
 */
public class Init {

    public static void streamingListenerInit(BObject streamingListener) {
        ConcurrentHashMap<BObject, StreamingListener> serviceListenerMap = new ConcurrentHashMap<>();
        streamingListener.addNativeData(Constants.STREAMING_DISPATCHER_LIST, serviceListenerMap);
        ConcurrentHashMap<BObject, Subscription> subscriptionsMap = new ConcurrentHashMap<>();
        streamingListener.addNativeData(Constants.STREAMING_SUBSCRIPTION_LIST, subscriptionsMap);
    }
}
