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

package org.dicadeveloper.runnerapp.io.spreadsheets;

import org.dicadeveloper.runnerapp.io.sendtogoogle.AbstractSendActivity;
import org.dicadeveloper.runnerapp.io.sendtogoogle.AbstractSendAsyncTask;
import org.dicadeveloper.runnerapp.io.sendtogoogle.SendRequest;
import org.dicadeveloper.runnerapp.io.sendtogoogle.UploadResultActivity;
import org.dicadeveloper.runnerapp.util.IntentUtils;
import org.dicadeveloper.runnerapp.R;

import android.content.Intent;

/**
 * An activity to send a track to Google Spreadsheet.
 *
 * @author Jimmy Shih
 */
public class SendSpreadsheetsActivity extends AbstractSendActivity {

  @Override
  protected AbstractSendAsyncTask createAsyncTask() {
    return new SendSpreadsheetsAsyncTask(this, sendRequest.getTrackId(), sendRequest.getAccount());
  }

  @Override
  protected String getServiceName() {
    return getString(R.string.export_google_spreadsheets);
  }

  @Override
  protected void startNextActivity(boolean success, boolean isCancel) {
    sendRequest.setSpreadsheetsSuccess(success);
    Intent intent = IntentUtils.newIntent(this, UploadResultActivity.class)
        .putExtra(SendRequest.SEND_REQUEST_KEY, sendRequest);
    startActivity(intent);
    finish();
  }
}
