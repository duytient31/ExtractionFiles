
// Copyright 2017 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by
//     java_cpp_enum.py
// From
//     ../../components/content_settings/core/common/content_settings_types.h

package org.chromium.chrome.browser;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    ContentSettingsType.CONTENT_SETTINGS_TYPE_DEFAULT,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_COOKIES,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_IMAGES,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_JAVASCRIPT,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_PLUGINS,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_POPUPS,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_GEOLOCATION,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_NOTIFICATIONS,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_AUTO_SELECT_CERTIFICATE,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_MIXEDSCRIPT,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_MEDIASTREAM_MIC,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_MEDIASTREAM_CAMERA,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_PROTOCOL_HANDLERS,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_PPAPI_BROKER,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_AUTOMATIC_DOWNLOADS,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_MIDI_SYSEX,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_SSL_CERT_DECISIONS,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_PROTECTED_MEDIA_IDENTIFIER,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_APP_BANNER,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_SITE_ENGAGEMENT,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_DURABLE_STORAGE,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_USB_CHOOSER_DATA,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_BLUETOOTH_GUARD,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_BACKGROUND_SYNC,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_AUTOPLAY,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_PROMPT_NO_DECISION_COUNT,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_IMPORTANT_SITE_INFO,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_PERMISSION_AUTOBLOCKER_DATA,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_SUBRESOURCE_FILTER,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_SUBRESOURCE_FILTER_DATA,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_MIDI,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_PUSH_MESSAGING,
    ContentSettingsType.CONTENT_SETTINGS_TYPE_PASSWORD_PROTECTION,
    ContentSettingsType.CONTENT_SETTINGS_NUM_TYPES_DO_NOT_USE
})
@Retention(RetentionPolicy.SOURCE)
public @interface ContentSettingsType {
  /**
   * "DEFAULT" is only used as an argument to the Content Settings Window opener; there it means
   * "whatever was last shown".
   */
  int CONTENT_SETTINGS_TYPE_DEFAULT = -1;
  int CONTENT_SETTINGS_TYPE_COOKIES = 0;
  int CONTENT_SETTINGS_TYPE_IMAGES = 1;
  int CONTENT_SETTINGS_TYPE_JAVASCRIPT = 2;
  int CONTENT_SETTINGS_TYPE_PLUGINS = 3;
  int CONTENT_SETTINGS_TYPE_POPUPS = 4;
  int CONTENT_SETTINGS_TYPE_GEOLOCATION = 5;
  int CONTENT_SETTINGS_TYPE_NOTIFICATIONS = 6;
  int CONTENT_SETTINGS_TYPE_AUTO_SELECT_CERTIFICATE = 7;
  int CONTENT_SETTINGS_TYPE_MIXEDSCRIPT = 8;
  int CONTENT_SETTINGS_TYPE_MEDIASTREAM_MIC = 9;
  int CONTENT_SETTINGS_TYPE_MEDIASTREAM_CAMERA = 10;
  int CONTENT_SETTINGS_TYPE_PROTOCOL_HANDLERS = 11;
  int CONTENT_SETTINGS_TYPE_PPAPI_BROKER = 12;
  int CONTENT_SETTINGS_TYPE_AUTOMATIC_DOWNLOADS = 13;
  int CONTENT_SETTINGS_TYPE_MIDI_SYSEX = 14;
  int CONTENT_SETTINGS_TYPE_SSL_CERT_DECISIONS = 15;
  int CONTENT_SETTINGS_TYPE_PROTECTED_MEDIA_IDENTIFIER = 16;
  int CONTENT_SETTINGS_TYPE_APP_BANNER = 17;
  int CONTENT_SETTINGS_TYPE_SITE_ENGAGEMENT = 18;
  int CONTENT_SETTINGS_TYPE_DURABLE_STORAGE = 19;
  int CONTENT_SETTINGS_TYPE_USB_CHOOSER_DATA = 20;
  int CONTENT_SETTINGS_TYPE_BLUETOOTH_GUARD = 21;
  int CONTENT_SETTINGS_TYPE_BACKGROUND_SYNC = 22;
  int CONTENT_SETTINGS_TYPE_AUTOPLAY = 23;
  /**
   * TODO(raymes): Deprecated. See crbug.com/681709. Remove after M60.
   */
  int CONTENT_SETTINGS_TYPE_PROMPT_NO_DECISION_COUNT = 24;
  int CONTENT_SETTINGS_TYPE_IMPORTANT_SITE_INFO = 25;
  int CONTENT_SETTINGS_TYPE_PERMISSION_AUTOBLOCKER_DATA = 26;
  int CONTENT_SETTINGS_TYPE_SUBRESOURCE_FILTER = 27;
  /**
   * Website setting which stores metadata for the subresource filter to aid in decisions for
   * whether or not to show the UI.
   */
  int CONTENT_SETTINGS_TYPE_SUBRESOURCE_FILTER_DATA = 28;
  /**
   * This is special-cased in the permissions layer to always allow, and as such doesn't have
   * associated prefs data.
   */
  int CONTENT_SETTINGS_TYPE_MIDI = 29;
  /**
   * This is only here temporarily and will be removed when we further unify it with notifications,
   * see crbug.com/563297. No prefs data is stored for this content type, we instead share values
   * with NOTIFICATIONS.
   */
  int CONTENT_SETTINGS_TYPE_PUSH_MESSAGING = 30;
  /**
   * This content setting type is for caching password protection service's verdicts of each origin.
   */
  int CONTENT_SETTINGS_TYPE_PASSWORD_PROTECTION = 31;
  /**
   * WARNING: This enum is going to be removed soon. Do not depend on NUM_TYPES.
   */
  int CONTENT_SETTINGS_NUM_TYPES_DO_NOT_USE = 32;
}