/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.dicadeveloper.runnerapp;

import org.dicadeveloper.runnerapp.content.MyTracksProviderUtils;
import org.dicadeveloper.runnerapp.content.Track;
import org.dicadeveloper.runnerapp.stats.TripStatistics;
import org.dicadeveloper.runnerapp.util.CalorieUtils.ActivityType;
import org.dicadeveloper.runnerapp.util.StatsUtils;
import org.dicadeveloper.runnerapp.R;

import android.os.Bundle;

import java.util.List;

/**
 * An activity to view aggregated stats from all recorded tracks.
 *
 * @author Fergus Nelson
 */
public class AggregatedStatsActivity extends AbstractMyTracksActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    StatsUtils.setTripStatisticsValues(
        this, this, null, getTripStatistics(), ActivityType.WALKING, null);
    StatsUtils.setLocationValues(this, this, null, null, false);
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.stats;
  }

  /**
   * Gets the aggregated trip statistics for all the recorded tracks or null if
   * there is no track.
   */
  private TripStatistics getTripStatistics() {
    List<Track> tracks = MyTracksProviderUtils.Factory.get(this).getAllTracks();
    TripStatistics tripStatistics = null;
    if (!tracks.isEmpty()) {
      tripStatistics = new TripStatistics(tracks.iterator().next().getTripStatistics());
      for (int i = 1; i < tracks.size(); i++) {
        tripStatistics.merge(tracks.get(i).getTripStatistics());
      }
    }
    return tripStatistics;
  }
}