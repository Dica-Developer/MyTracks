/*
 * Copyright 2013 Google Inc.
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

package org.dicadeveloper.runnerapp.io.drive;

import android.content.Intent;

import org.dicadeveloper.runnerapp.R;
import org.dicadeveloper.runnerapp.io.maps.SendMapsActivity;
import org.dicadeveloper.runnerapp.io.sendtogoogle.AbstractSendActivity;
import org.dicadeveloper.runnerapp.io.sendtogoogle.AbstractSendAsyncTask;
import org.dicadeveloper.runnerapp.io.sendtogoogle.SendRequest;
import org.dicadeveloper.runnerapp.io.sendtogoogle.UploadResultActivity;
import org.dicadeveloper.runnerapp.util.IntentUtils;

/**
 * An activity to send a track to Google Drive.
 * 
 * @author Jimmy Shih
 */
public class SendDriveActivity extends AbstractSendActivity {

  @Override
  protected AbstractSendAsyncTask createAsyncTask() {
    return new SendDriveAsyncTask(this, sendRequest.getTrackId(), sendRequest.getAccount(),
        sendRequest.getDriveShareEmails(), sendRequest.isDriveSharePublic());
  }

  @Override
  protected String getServiceName() {
    return getString(R.string.export_google_drive);
  }

  @Override
  protected void startNextActivity(boolean success, boolean isCancel) {
    sendRequest.setDriveSuccess(success);
    Class<?> next = getNextClass(isCancel);
    Intent intent = IntentUtils.newIntent(this, next)
        .putExtra(SendRequest.SEND_REQUEST_KEY, sendRequest);
    startActivity(intent);
    finish();
  }

  private Class<?> getNextClass(boolean isCancel) {
    if (isCancel) {
      return UploadResultActivity.class;
    } else {
      if (sendRequest.isSendMaps()) {
        return SendMapsActivity.class;
      }   else {
        return UploadResultActivity.class;
      }
    }
  }
}
