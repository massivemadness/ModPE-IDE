/*
 * Licensed to the Light Team Software (Light Team) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The Light Team licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lightteam.modpeide.ui.common.dialogs

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.lightteam.modpeide.BaseApplication
import com.lightteam.modpeide.R

class DialogStore(context: Context, themeResId: Int) : AlertDialog(context, themeResId) {

    companion object Uris {
        private const val PACKAGE_NAME = BaseApplication.ULTIMATE
        private const val URI_ANDROID_MARKET = "market://details?id=$PACKAGE_NAME"
        private const val URI_GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=$PACKAGE_NAME"
    }

    class Builder(context: Context) : AlertDialog.Builder(context, R.style.Theme_MaterialComponents_Light_Dialog_Alert) {

        init {
            setView(R.layout.dialog_store)
        }

        override fun show(): AlertDialog {
            val dialog = super.show()
            val buttonGetIt = dialog.findViewById<View>(R.id.button_get_it)
            val buttonMaybeLater = dialog.findViewById<View>(R.id.button_maybe_later)

            buttonGetIt?.setOnClickListener {
                val intent = try {
                    Intent(Intent.ACTION_VIEW, Uri.parse(URI_ANDROID_MARKET))
                } catch (e: ActivityNotFoundException) {
                    Intent(Intent.ACTION_VIEW, Uri.parse(URI_GOOGLE_PLAY))
                }
                context.startActivity(intent)
                dialog.dismiss()
            }
            buttonMaybeLater?.setOnClickListener {
                dialog.dismiss()
            }
            return dialog
        }
    }
}