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

package org.ballerinalang.nats.basic.producer;

import org.ballerinalang.jvm.TypeChecker;
import org.ballerinalang.jvm.api.values.BObject;
import org.ballerinalang.jvm.scheduling.Scheduler;
import org.ballerinalang.jvm.types.TypeTags;
import org.ballerinalang.nats.Constants;
import org.ballerinalang.nats.observability.NatsMetricsReporter;
import org.ballerinalang.nats.observability.NatsTracingUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Extern function to close logical connection in producer.
 *
 * @since 0.995
 */
public class CloseConnection {

    public static void closeConnection(BObject producerObject) {
        NatsTracingUtil.traceResourceInvocation(Scheduler.getStrand(), producerObject);
        Object connection = producerObject.get(Constants.CONNECTION_OBJ);
        if (TypeChecker.getType(connection).getTag() == TypeTags.OBJECT_TYPE_TAG) {
            BObject connectionObject = (BObject) connection;
            ((AtomicInteger) connectionObject.getNativeData(Constants.CONNECTED_CLIENTS)).decrementAndGet();
            ((NatsMetricsReporter) connectionObject.getNativeData(Constants.NATS_METRIC_UTIL)).reportProducerClose();
        }
    }
}
