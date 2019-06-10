/***
  Copyright (c) 2013-2017 CommonsWare, LLC
  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
  by applicable law or agreed to in writing, software distributed under the
  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
  OF ANY KIND, either express or implied. See the License for the specific
  language governing permissions and limitations under the License.
  
  Covered in detail in the book _Android's Architecture Components_
    https://commonsware.com/AndroidArch
 */

package com.commonsware.android.livedata;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import com.commonsware.android.livedata.databinding.MainBinding;

public class MainActivity extends FragmentActivity {
  @BindingAdapter("android:text")
  public static void setLightReading(TextView tv, SensorLiveData.Event event) {
    if (event==null) {
      tv.setText(null);
    }
    else {
      tv.setText(String.format("%f", event.values[0]));
    }
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    MainBinding binding=MainBinding.inflate(getLayoutInflater());
    SensorViewModel vm=ViewModelProviders.of(this).get(SensorViewModel.class);

    binding.setViewModel(vm);
    binding.setLifecycleOwner(this);
    setContentView(binding.getRoot());
  }
}