/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hbase.regionserver;

import org.apache.hadoop.hbase.CompatibilityFactory;
import org.apache.hadoop.hbase.SmallTests;
import org.apache.hadoop.hbase.regionserver.MetricsRegionServer;
import org.apache.hadoop.hbase.regionserver.MetricsRegionServerWrapperStub;
import org.apache.hadoop.hbase.regionserver.MetricsRegionServerSource;
import org.apache.hadoop.hbase.test.MetricsAssertHelper;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertNotNull;

/**
 * Unit test version of rs metrics tests.
 */
@Category(SmallTests.class)
public class TestMetricsRegionServer {

  public MetricsAssertHelper HELPER = CompatibilityFactory.getInstance(MetricsAssertHelper.class);

  @Test
  public void testWrapperSource() {

    MetricsRegionServer rsm = new MetricsRegionServer(new MetricsRegionServerWrapperStub());
    MetricsRegionServerSource serverSource = rsm.getMetricsSource();
    HELPER.assertTag("serverName", "test", serverSource);
    HELPER.assertTag("clusterId", "tClusterId", serverSource);
    HELPER.assertTag("zookeeperQuorum", "zk", serverSource);
    HELPER.assertGauge("regionServerStartTime", 100, serverSource);
    HELPER.assertGauge("regionCount", 101, serverSource);
    HELPER.assertGauge("storeCount", 2, serverSource);
    HELPER.assertGauge("storeFileCount", 300, serverSource);
    HELPER.assertGauge("memstoreSize", 1025, serverSource);
    HELPER.assertGauge("storeFileSize", 1900, serverSource);
    HELPER.assertCounter("totalRequestCount", 899, serverSource);
    HELPER.assertCounter("readRequestCount", 997, serverSource);
    HELPER.assertCounter("writeRequestCount", 707, serverSource);
    HELPER.assertCounter("checkMutateFailedCount", 401, serverSource);
    HELPER.assertCounter("checkMutatePassedCount", 405, serverSource);
    HELPER.assertGauge("storeFileIndexSize", 406, serverSource);
    HELPER.assertGauge("staticIndexSize", 407, serverSource);
    HELPER.assertGauge("staticBloomSize", 408, serverSource);
    HELPER.assertGauge("putsWithoutWALCount", 409, serverSource);
    HELPER.assertGauge("putsWithoutWALSize", 410, serverSource);
    HELPER.assertGauge("percentFilesLocal", 99, serverSource);
    HELPER.assertGauge("compactionQueueLength", 411, serverSource);
    HELPER.assertGauge("flushQueueLength", 412, serverSource);
    HELPER.assertGauge("blockCacheFreeSize", 413, serverSource);
    HELPER.assertGauge("blockCacheCount", 414, serverSource);
    HELPER.assertGauge("blockCacheSize", 415, serverSource);
    HELPER.assertCounter("blockCacheHitCount", 416, serverSource);
    HELPER.assertCounter("blockCacheMissCount", 417, serverSource);
    HELPER.assertCounter("blockCacheEvictionCount", 418, serverSource);
    HELPER.assertGauge("blockCountHitPercent", 98, serverSource);
    HELPER.assertGauge("blockCacheExpressHitPercent", 97, serverSource);
    HELPER.assertCounter("updatesBlockedTime", 419, serverSource);
  }

  @Test
  public void testConstuctor() {
    MetricsRegionServer rsm = new MetricsRegionServer(new MetricsRegionServerWrapperStub());
    assertNotNull("There should be a hadoop1/hadoop2 metrics source", rsm.getMetricsSource() );
    assertNotNull("The RegionServerMetricsWrapper should be accessable", rsm.getRegionServerWrapper());
  }
}
